package top.chatgqt.wrench.test.design.framework.rule01.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import top.chatgqt.wrench.design.framework.link.model1.ILogicLink;
import top.chatgqt.wrench.test.design.framework.rule01.logic.RuleLogic101;
import top.chatgqt.wrench.test.design.framework.rule01.logic.RuleLogic102;

import javax.annotation.Resource;

@Component
public class Rule01TradeRuleFactory {

    @Resource
    private RuleLogic101 ruleLogic101;
    @Resource
    private RuleLogic102 ruleLogic102;

    public ILogicLink<String, DynamicContext, String> getRuleLink() {
        ruleLogic101.appendNext(ruleLogic102);
        return ruleLogic101;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
