package top.chatgqt.wrench.test.design.framework.rule01.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.chatgqt.wrench.design.framework.link.model1.AbstractLogicLink;
import top.chatgqt.wrench.test.design.framework.rule01.factory.Rule01TradeRuleFactory;

@Service
public class RuleLogic101 extends AbstractLogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> {

    private static final Logger log = LoggerFactory.getLogger(RuleLogic101.class);

    @Override
    public String apply(String requestParameter, Rule01TradeRuleFactory.DynamicContext dynamicContext) throws Exception {

        log.info("link model01 RuleLogic101");

        return next(requestParameter, dynamicContext);
    }
}
