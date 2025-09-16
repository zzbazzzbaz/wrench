package top.chatgqt.wrench.design.framework.tree;


/**
 * 受理策略处理器
 * @param <T> 入参类型
 * @param <D> 上下文参数
 * @param <R> 返参类型
 */
public interface StrategyHandler<T, D, R> {

    static StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
