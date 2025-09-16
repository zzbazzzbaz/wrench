package top.chatgqt.wrench.dynamic.config.center.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import top.chatgqt.wrench.dynamic.config.center.domain.service.IDynamicConfigCenterService;

@Configuration
public class DynamicConfigCenterAutoConfig implements BeanPostProcessor {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterAutoConfig.class);
    private final IDynamicConfigCenterService dynamicConfigCenterService;

    public DynamicConfigCenterAutoConfig(IDynamicConfigCenterService dynamicConfigCenterService) {
        this.dynamicConfigCenterService = dynamicConfigCenterService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return dynamicConfigCenterService.proxyBean(bean);
    }
}
