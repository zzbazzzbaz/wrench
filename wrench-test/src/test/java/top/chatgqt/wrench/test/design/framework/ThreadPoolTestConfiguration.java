package top.chatgqt.wrench.test.design.framework;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@TestConfiguration
public class ThreadPoolTestConfiguration {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                2,  // core pool size
                5,  // maximum pool size
                60L, // keep alive time
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                r -> new Thread(r, "test-thread-pool-")
        );
    }

}