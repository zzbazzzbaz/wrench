package top.chatgqt.wrench.test.design.framework;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import top.chatgqt.wrench.design.framework.link.model1.ILogicLink;
import top.chatgqt.wrench.test.design.framework.rule01.factory.Rule01TradeRuleFactory;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
@Import(ThreadPoolTestConfiguration.class) // 导入这个类下面的Bean对象，线程池
public class RuleLink01Test {

    @Resource
    private Rule01TradeRuleFactory factory;

    @Test
    public void test() {
        ILogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> ruleLink = factory.getRuleLink();
        try {
            String result = ruleLink.apply("123", new Rule01TradeRuleFactory.DynamicContext());
            log.info("测试结果:{}", JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
