package top.chatgqt.wrench.design.framework.link.model2;


import top.chatgqt.wrench.design.framework.link.model2.chain.BusinessLinkedList;
import top.chatgqt.wrench.design.framework.link.model2.handler.ILogicHandler;

public class LinkArmory<T, D extends BaseDynamicContext, R> {

    private final BusinessLinkedList<T, D, R> logicLink;

    @SafeVarargs
    public LinkArmory(String linkName, ILogicHandler<T, D, R>... logicHandlers) {
        logicLink = new BusinessLinkedList<>(linkName);
        for (ILogicHandler<T, D, R> logicHandler : logicHandlers) {
            logicLink.add(logicHandler);
        }
    }

    public BusinessLinkedList<T, D, R> getLogicLink() {
        return logicLink;
    }
}
