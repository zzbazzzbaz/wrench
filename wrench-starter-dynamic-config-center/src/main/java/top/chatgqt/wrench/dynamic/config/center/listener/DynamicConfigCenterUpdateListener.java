package top.chatgqt.wrench.dynamic.config.center.listener;

import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.chatgqt.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import top.chatgqt.wrench.dynamic.config.center.domain.service.IDynamicConfigCenterService;

public class DynamicConfigCenterUpdateListener implements MessageListener<AttributeVO> {

    private final Logger log = LoggerFactory.getLogger(DynamicConfigCenterUpdateListener.class);
    private final IDynamicConfigCenterService dynamicConfigCenterService;

    public DynamicConfigCenterUpdateListener(IDynamicConfigCenterService dynamicConfigCenterService) {
        this.dynamicConfigCenterService = dynamicConfigCenterService;
    }

    @Override
    public void onMessage(CharSequence charSequence, AttributeVO attributeVO) {
        try {
            log.info("wrench dcc config attribute:{} value:{}", attributeVO.getAttribute(), attributeVO.getValue());
            dynamicConfigCenterService.updateConfigValue(attributeVO);
        } catch (Exception e) {
            log.error("wrench dcc config{}", e.getMessage(), e);
        }
    }
}
