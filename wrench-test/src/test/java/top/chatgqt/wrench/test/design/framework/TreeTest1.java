package top.chatgqt.wrench.test.design.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import top.chatgqt.wrench.design.framework.tree.StrategyHandler;
import top.chatgqt.wrench.test.design.framework.tree.factory.DefaultStrategyFactory;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
@Import(ThreadPoolTestConfiguration.class) // 导入这个类下面的Bean对象，线程池
public class TreeTest1 {

    @Resource
    private DefaultStrategyFactory defaultStrategyFactory;

    /**
     * 测试规则树模板
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        StrategyHandler<String, DefaultStrategyFactory.DynamicContext, String> strategyHandler = defaultStrategyFactory.strategyHandler();
        String result = strategyHandler.apply("xiaofuge", new DefaultStrategyFactory.DynamicContext());

        log.info("测试结果:{}", result);
    }
}

