package top.chatgqt.wrench.design.framework.link.model2.handler;

import top.chatgqt.wrench.design.framework.link.model2.BaseDynamicContext;
/**
 * 逻辑处理器
 */
public interface ILogicHandler<T, D extends BaseDynamicContext, R> {

    default R next(T requestParameter, D dynamicContext) {
        dynamicContext.setProceed(true);
        return null;
    }

    default R stop(T requestParameter, D dynamicContext, R result){
        dynamicContext.setProceed(false);
        return result;
    }

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
