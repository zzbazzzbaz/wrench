package top.chatgqt.wrench.test.design.framework.tree.node;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.chatgqt.wrench.design.framework.tree.StrategyHandler;
import top.chatgqt.wrench.test.design.framework.tree.AbstractXxxSupport;
import top.chatgqt.wrench.test.design.framework.tree.factory.DefaultStrategyFactory;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class AccountNode extends AbstractXxxSupport {

    @Autowired
    private MemberLevel1Node memberLevel1Node;

    @Autowired
    private MemberLevel2Node memberLevel2Node;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 1. 可执行多线程异步操作，尤其在需要大量加载数据的时候非常有用
     * 2. multiThread 在需要的节点就重写，不需要的节点不用处理
     */
    @Override
    protected void multiThread(String requestParameter, DefaultStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> accountType01 = CompletableFuture.supplyAsync(() -> {
            log.info("异步查询账户标签，账户标签；开户|冻结|止付|可用");
            return new Random().nextBoolean() ? "账户冻结" : "账户可用";
        }, threadPoolExecutor);

        CompletableFuture<String> accountType02 = CompletableFuture.supplyAsync(() -> {
            log.info("异步查询授信数据，拦截|已授信|已降档");
            return new Random().nextBoolean() ? "拦截" : "已授信";
        }, threadPoolExecutor);

        CompletableFuture.allOf(accountType01, accountType02)
                .thenRun(() -> {
                    dynamicContext.setValue("accountType01", accountType01.join());
                    dynamicContext.setValue("accountType02", accountType02.join());
                }).join();
    }

    @Override
    protected String doApply(String requestParameter, DefaultStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("【账户节点】规则决策树 userId:{}", requestParameter);

        // 模拟查询用户级别
        int level = new Random().nextInt(2);
        log.info("模拟查询用户级别 level:{}", level);

        dynamicContext.setLevel(level);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<String, DefaultStrategyFactory.DynamicContext, String> get(String requestParameter, DefaultStrategyFactory.DynamicContext dynamicContext) throws Exception {
        String accountType01 = dynamicContext.getValue("accountType01");
        String accountType02 = dynamicContext.getValue("accountType02");

        int level = dynamicContext.getLevel();

        if ("账户冻结".equals(accountType01)) {
            return memberLevel1Node;
        }

        if ("拦截".equals(accountType02)) {
            return memberLevel1Node;
        }

        if (level == 1) {
            return memberLevel1Node;
        }

        return memberLevel2Node;
    }

}
