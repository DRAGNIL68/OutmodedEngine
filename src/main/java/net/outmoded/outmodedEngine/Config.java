package net.outmoded.outmodedEngine;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class Config {

    private static boolean debug = true;
    private static boolean showAutoSaveTimer = false;
    private static boolean generatePack = true;
    private static int autoSaveTimer = 5;
    private static boolean selfHost = true;

    private static final HashMap<String, String> lang = new HashMap<>();
    private static YamlConfiguration configYml;

    private static File configFile;


    private Config(){} // constructor makes it private

    public static void load(){

        File lang = new File(OutmodedEngine.getInstance().getDataFolder(), "lang.yml");
        configFile = new File(OutmodedEngine.getInstance().getDataFolder(), "config.yml");
        File contentsFolder = new File(OutmodedEngine.getInstance().getDataFolder(), "contents");
        File outputFolder = new File(OutmodedEngine.getInstance().getDataFolder(), "output");


        if (!configFile.exists())
            OutmodedEngine.getInstance().saveResource("config.yml", false);

        if (!lang.exists())
            OutmodedEngine.getInstance().saveResource("lang.yml", false);

        if (!contentsFolder.exists()){
            boolean b = contentsFolder.mkdir();

            //TODO: update this to new logo
            final Component logo = MiniMessage.miniMessage().deserialize(
                    "<color:#1235ff>[</color><color:#3daeff>animated-skript</color><color:#1235ff>]</color> "
            );

            final Component text = MiniMessage.miniMessage().deserialize(
                    "<color:#0dff1d>Created Contents Folder</green>"
            );

            getServer().getConsoleSender().sendMessage(logo.append(text));

        }

        if (!outputFolder.exists()){
            boolean b = outputFolder.mkdir();

        }


        configYml = new YamlConfiguration();
        configYml.options().parseComments(true);



       try{
           configYml.load(configFile);
           autoSaveTimer = Integer.parseInt(Objects.requireNonNull(configYml.getString("auto_save_timer")));
           debug = Boolean.parseBoolean(configYml.getString("debug"));
           generatePack = Boolean.parseBoolean(configYml.getString("generate_resource_pack"));
           showAutoSaveTimer = Boolean.parseBoolean(configYml.getString("show_auto_save_timer"));
           selfHost = Boolean.parseBoolean(configYml.getString("self_host_pack"));

       } catch (Exception e) {
            e.printStackTrace();

       }
    }
 
    public static boolean debugMode(){
        return debug;
    }

    public static boolean selfHost(){
        return selfHost;
    }

    public static boolean showAutoSaveTimer(){
        return showAutoSaveTimer;
    }

    public static int getAutoSaveTimer(){
        return autoSaveTimer;
    }

    public static boolean shouldGeneratePack(){
        return generatePack;
    }

    /**
     * loads the lang file
     */
    public static void loadLang(){

        configYml = new YamlConfiguration();

        configYml.options().parseComments(true);

        File configFile = new File(OutmodedEngine.getInstance().getDataFolder(), "lang.yml");
        try {
            configYml.load(configFile);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        lang.clear();
        for (String key : configYml.getKeys(false)) {
            lang.put(key, String.valueOf(configYml.getString(key)));
        }
    }

    /**
     * get key from lang hashmap
     */
    public static String getLang(String key){
        if (lang.containsKey(key))
            return lang.get(key);

        return null;
    }
}

