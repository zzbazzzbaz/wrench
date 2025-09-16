package top.chatgqt.wrench.test.design.framework.tree;

import top.chatgqt.wrench.design.framework.tree.AbstractMultiThreadStrategyRouter;
import top.chatgqt.wrench.test.design.framework.tree.factory.DefaultStrategyFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractXxxSupport extends AbstractMultiThreadStrategyRouter<String, DefaultStrategyFactory.DynamicContext,String> {
    @Override
    protected void multiThread(String requestParameter, DefaultStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // xxx
    }
}
