package top.chatgqt.wrench.design.framework.link.model1;

/**
 * 逻辑链节点接口
 * 继承自链装配器，具备链式操作能力，并定义具体的业务逻辑执行方法
 * 实现责任链模式中的处理者接口
 * 
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型  
 * @param <R> 返回结果类型
 */
public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {

    /**
     * 执行当前节点的业务逻辑
     * @param requestParameter 请求参数
     * @param dynamicContext 动态上下文，包含运行时状态信息
     * @return 处理结果
     * @throws Exception 处理过程中可能抛出的异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;
}
