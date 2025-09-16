package top.chatgqt.wrench.design.framework.link.model1;

/**
 * 逻辑链装配器接口
 * 提供链式操作的基础能力，用于构建责任链模式
 * 
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型
 * @param <R> 返回结果类型
 */
public interface ILogicChainArmory<T, D, R> {

    /**
     * 获取链条中的下一个节点
     * @return 下一个逻辑节点，如果是最后一个节点则返回null
     */
    ILogicLink<T, D, R> getNext();

    /**
     * 向链条末尾追加下一个节点
     * @param next 要追加的逻辑节点
     * @return 返回传入的next节点，支持链式调用
     */
    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next);

}
