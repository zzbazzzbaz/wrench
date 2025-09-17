package top.chatgqt.wrench.test.design.framework.rule02.factory;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.chatgqt.wrench.design.framework.link.model2.LinkArmory;
import top.chatgqt.wrench.design.framework.link.model2.chain.BusinessLinkedList;
import top.chatgqt.wrench.test.design.framework.rule02.logic.RuleLogic201;
import top.chatgqt.wrench.test.design.framework.rule02.logic.RuleLogic202;
import top.chatgqt.wrench.test.design.framework.rule02.logic.XxxResponse;

@Service
public class Rule02TradeRuleFactory {

    @Bean("demo01")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo01(RuleLogic201 ruleLogic201, RuleLogic202 ruleLogic202) {
        LinkArmory<String, DynamicContext, XxxResponse> linkArmory = new LinkArmory<>("demo01", ruleLogic201, ruleLogic202);
        return linkArmory.getLogicLink();
    }

    @Bean("demo02")
    public BusinessLinkedList<String, DynamicContext, XxxResponse> demo02(RuleLogic202 ruleLogic202) {
        LinkArmory<String, DynamicContext, XxxResponse> linkArmory = new LinkArmory<>("demo02", ruleLogic202);
        return linkArmory.getLogicLink();
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext extends top.chatgqt.wrench.design.framework.link.model2.DynamicContext {
        private String age;
    }
}
