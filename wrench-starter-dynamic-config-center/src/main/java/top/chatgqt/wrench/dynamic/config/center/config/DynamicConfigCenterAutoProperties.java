package top.chatgqt.wrench.dynamic.config.center.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import top.chatgqt.wrench.dynamic.config.center.types.common.Constants;

@ConfigurationProperties(prefix = "wrench.config", ignoreUnknownFields = true)
public class DynamicConfigCenterAutoProperties {

    private String system;

    public String getKey(String attributeName) {
        return this.system + Constants.line + attributeName;
    }

    public String getSystem() {
        return this.system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
