package top.chatgqt.wrench.design.framework.link.model2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 链路动态上下文
 */
public class BaseDynamicContext {

    private boolean proceed;
    private final Map<String, Object> dataObjects = new ConcurrentHashMap<>();

    public BaseDynamicContext() {
        this.proceed = true;
    }

    public boolean isProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }

    public <T> void setValue(String key, T value) {
        dataObjects.put(key, value);
    }

    public <T> T getValue(String key) {
        return (T) dataObjects.get(key);
    }

}
