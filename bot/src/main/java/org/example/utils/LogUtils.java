package org.example.utils;

/**
 * 日志
 *
 * @author 13988
 * @date 2023/03/09
 */
public class LogUtils {
    public static void info(String info) {
        BotConfig.getBot().getLogger().info(info);
    }

    public static void warn(String warn) {
        BotConfig.getBot().getLogger().warning(warn);
    }

    public static void error(String error) {
        BotConfig.getBot().getLogger().error(error);
    }

    public static void debug(String debug) {
        BotConfig.getBot().getLogger().debug(debug);
    }
}
