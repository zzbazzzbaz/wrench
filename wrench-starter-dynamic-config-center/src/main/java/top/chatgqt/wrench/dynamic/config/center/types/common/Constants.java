package top.chatgqt.wrench.dynamic.config.center.types.common;

public class Constants {

    public static final String dynamic_config_center_redis_topic = "wrench_dynamic_config_center_redis_topic";
    public static final String symbol_colon = ":";
    public static final String line = "_";

    public static String getTopic(String application) {
        return dynamic_config_center_redis_topic + symbol_colon + application;
    }

}
