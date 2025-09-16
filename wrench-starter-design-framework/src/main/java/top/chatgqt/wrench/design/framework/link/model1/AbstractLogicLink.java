package top.chatgqt.wrench.design.framework.link.model1;

/**
 * 抽象逻辑链节点
 * 实现责任链模式的抽象处理者，提供链式操作的默认实现
 * 子类只需要实现具体的apply方法即可
 * 
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型
 * @param <R> 返回结果类型
 */
public abstract class AbstractLogicLink<T, D, R> implements ILogicLink<T, D, R> {

    /**
     * 指向链条中下一个节点的引用
     */
    private ILogicLink<T, D, R> next;

    /**
     * 获取链条中的下一个节点
     * @return 下一个逻辑节点
     */
    @Override
    public ILogicLink<T, D, R> getNext() {
        return next;
    }

    /**
     * 向链条末尾追加下一个节点
     * 注意：这里返回的是传入的next节点，而不是this，便于链式调用
     * @param next 要追加的逻辑节点
     * @return 返回传入的next节点
     */
    @Override
    public ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next) {
        this.next = next;
        return next;
    }

    /**
     * 调用链条中下一个节点的处理方法
     * 为子类提供便捷的方法来传递请求给下一个节点
     * @param requestParameter 请求参数
     * @param dynamicContext 动态上下文
     * @return 下一个节点的处理结果
     * @throws Exception 处理过程中可能抛出的异常
     */
    protected R next(T requestParameter, D dynamicContext) throws Exception {
        return next.apply(requestParameter, dynamicContext);
    }


}
