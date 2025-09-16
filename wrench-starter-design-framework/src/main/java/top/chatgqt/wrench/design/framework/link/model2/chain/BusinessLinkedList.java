package top.chatgqt.wrench.design.framework.link.model2.chain;

import top.chatgqt.wrench.design.framework.link.model2.DynamicContext;
import top.chatgqt.wrench.design.framework.link.model2.handler.ILogicHandler;

/**
 * 业务链路
 */
public class BusinessLinkedList<T, D extends DynamicContext, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R> {

    public BusinessLinkedList(String name) {
        super(name);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        // 从链表头节点开始遍历
        Node<ILogicHandler<T, D, R>> current = this.first;
        do {
            // 获取当前节点的业务处理器
            ILogicHandler<T, D, R> item = current.item;
            // 执行处理器的业务逻辑
            R apply = item.apply(requestParameter, dynamicContext);
            // 如果 proceed == false，立即返回并终止责任链
            if (!dynamicContext.isProceed()) return apply;

            // 移动到下一个节点继续处理
            current = current.next;
        } while (current != null); // 遍历直到链表末尾
        // 所有处理器都返回null时的默认返回值
        return null;
    }
}
