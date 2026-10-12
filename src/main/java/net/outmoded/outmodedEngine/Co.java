package net.outmoded.outmodedEngine;

/**
 * fast and easy logging
 * "co" stands for console
 */
public class Co {
    // would be nice to save all logs to a custom file
    public static void info(String msg){
        OutmodedEngine.getInstance().getLogger().info(msg);
    }

    public static void warn(String msg){
        OutmodedEngine.getInstance().getLogger().warning(msg);
    }

    public static void sevr(String msg){
        OutmodedEngine.getInstance().getLogger().severe(msg);
    }
}
