package top.chatgqt.wrench.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RTopic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.chatgqt.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import top.chatgqt.wrench.dynamic.config.center.types.annotations.DCCValue;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApiTest {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @Resource
    private RTopic dynamicConfigCenterRedisTopic;

    @Test
    public void test_get() throws InterruptedException {
        log.info("测试结果:{}", downgradeSwitch);
    }

    @Test
    public void test_publish() throws InterruptedException {
        // 推送
        dynamicConfigCenterRedisTopic.publish(new AttributeVO("downgradeSwitch", "6006"));

        new CountDownLatch(1).await();
    }

}
