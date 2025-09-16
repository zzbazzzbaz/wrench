package top.chatgqt.wrench.dynamic.config.center.domain.service;

import top.chatgqt.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;

/**
 * 动态配置中心服务接口
 */
public interface IDynamicConfigCenterService {

    /**
     * 代理Spring的bean对象
     *
     * @param bean 待处理的Spring Bean对象
     * @return 增强后的Bean对象
     */
    Object proxyBean(Object bean);

    /**
     * 更新配置值
     * 外部发起配置变更时的处理逻辑
     * @param attributeVO 属性值对象，包含属性名和新值
     */
    void updateConfigValue(AttributeVO attributeVO);
}
