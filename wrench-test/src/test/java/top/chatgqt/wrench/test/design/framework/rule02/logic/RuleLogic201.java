package top.chatgqt.wrench.test.design.framework.rule02.logic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chatgqt.wrench.design.framework.link.model2.handler.ILogicHandler;
import top.chatgqt.wrench.test.design.framework.rule02.factory.Rule02TradeRuleFactory;

@Slf4j
@Service
public class RuleLogic201 implements ILogicHandler<String, Rule02TradeRuleFactory.DynamicContext, XxxResponse> {

    public XxxResponse apply(String requestParameter, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {

        log.info("link model02 RuleLogic201");

        return next(requestParameter, dynamicContext);
    }

}

