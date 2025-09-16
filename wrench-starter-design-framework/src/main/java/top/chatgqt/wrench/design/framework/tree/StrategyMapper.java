package top.chatgqt.wrench.design.framework.tree;

/**
 * 策略映射器
 * @param <T> 入参类型
 * @param <D> 上下文参数
 * @param <R> 返参类型
 */
public interface StrategyMapper<T, D, R> {

    /**
     * 获取待执行策略
     *
     * @param requestParameter 入参
     * @param dynamicContext   上下文
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;
}
