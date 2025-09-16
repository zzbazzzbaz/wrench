package top.chatgqt.wrench.dynamic.config.center.domain.service;

import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import top.chatgqt.wrench.dynamic.config.center.config.DynamicConfigCenterAutoProperties;
import top.chatgqt.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import top.chatgqt.wrench.dynamic.config.center.types.annotations.DCCValue;
import top.chatgqt.wrench.dynamic.config.center.types.common.Constants;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicConfigCenterService implements IDynamicConfigCenterService {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterService.class);
    private final Map<String, Object> dccBeanMap = new ConcurrentHashMap<>();

    private final DynamicConfigCenterAutoProperties properties;
    private final RedissonClient redissonClient;

    public DynamicConfigCenterService(DynamicConfigCenterAutoProperties properties, RedissonClient redissonClient) {
        this.properties = properties;
        this.redissonClient = redissonClient;
    }

    @Override
    public Object proxyBean(Object bean) {
        Class<?> targetBeanClass = bean.getClass();
        Object targetBean = bean;

        // 如果是代理对象，获取原始对象
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBean = AopProxyUtils.ultimateTargetClass(bean);
        }

        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DCCValue.class)) continue;
            // @DccValue("user:10")
            // public String user;

            // "user:10"
            String value = field.getAnnotation(DCCValue.class).value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException("err!");
            }

            // ["user","10"]
            String[] split = value.split(Constants.symbol_colon);
            String key = properties.getKey(split[0].trim());

            String defaultValue = split.length == 2 ? split[1] : null;
            String setValue = defaultValue;

            try {
                // Redis 操作，判断配置Key是否存在，不存在则创建，存在则获取最新值
                RBucket<String> bucket = redissonClient.getBucket(key);
                boolean exists = bucket.isExists();

                if (!exists) bucket.set(defaultValue);
                else setValue = bucket.get();

                field.setAccessible(true);
                field.set(targetBean, setValue);
                field.setAccessible(false);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            dccBeanMap.put(key, targetBean);
        }
        return bean;
    }

    @Override
    public void updateConfigValue(AttributeVO attributeVO) {
        // 属性信息
        String key = properties.getKey(attributeVO.getAttribute());
        String value = attributeVO.getValue();

        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (!bucket.isExists()) return;

        Object targetBean = dccBeanMap.get(key);
        if (targetBean == null) return;
        Class<?> targetBeanClass = targetBean.getClass();

        if (AopUtils.isAopProxy(targetBean)) {
            targetBeanClass = AopUtils.getTargetClass(targetBean);
            targetBean = AopProxyUtils.ultimateTargetClass(targetBean);
        }

        try {
            Field field = targetBeanClass.getDeclaredField(attributeVO.getAttribute());
            field.setAccessible(true);
            field.set(targetBean, value);
            field.setAccessible(false);

            // 更新 Redis 存储的值
            bucket.set(value);

            log.info("DCC 节点监听，动态设置值 {} {}", key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
