package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.outmoded.fastListenerRegistration.ListenerRegister;
import net.outmoded.outmodedEngine.defaultFormats.blockbench.BBModelTemplate;
import net.outmoded.outmodedEngine.live.ModelManager;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class OutmodedEngine extends JavaPlugin {


    @Override
    public void onLoad() {

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        ListenerRegister.register(this);
        PacketEvents.getAPI().init();

        // this is here to start some code not very smart
        ModelTemplateManager.getInstance();
        ModelManager.getInstance();
        // ####################################
        String json = "{\n" +
                "    \"settings\": {\n" +
                "        \"namespacedId\": \"\",\n" +
                "        \"credit\": \"Made with BlockBench\",\n" +
                "        \"user_properties\": {}\n" +
                "    },\n" +
                "    \"textures\": {\n" +
                "        \"texture.png\": \"iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAeUlEQVR4AeyQoQ2AQAxFe6eReBa4UXDgUIwAQd0MLIBCI9iAVfAIJAJBcuTbS5prUkdo8kTb3/anttmuAO69D6BqhwBIGFaoY2X2cBOBZ8kJrPNoADsRNfQOurMmkHlvQHQgmeodlK4wIHmKEegdMIvF5d8B0Qd+8AIAAP//bQT7vwAAAAZJREFUAwBhuCIhFW4C3gAAAABJRU5ErkJggg==\"\n" +
                "    },\n" +
                "    \"structure\": {\n" +
                "        \"0820bbae-fd2c-4896-d7cf-2725a85e50cb\": {\n" +
                "            \"uuid\": \"0820bbae-fd2c-4896-d7cf-2725a85e50cb\",\n" +
                "            \"type\": \"group\",\n" +
                "            \"name\": \"bone\",\n" +
                "            \"transform\": {\n" +
                "                \"position\": [\n" +
                "                    0,\n" +
                "                    0,\n" +
                "                    0\n" +
                "                ],\n" +
                "                \"left_rotation\": [\n" +
                "                    0,\n" +
                "                    0,\n" +
                "                    0,\n" +
                "                    0\n" +
                "                ],\n" +
                "                \"translation\": [\n" +
                "                    0,\n" +
                "                    0,\n" +
                "                    0\n" +
                "                ],\n" +
                "                \"scale\": [\n" +
                "                    1,\n" +
                "                    1,\n" +
                "                    1\n" +
                "                ]\n" +
                "            },\n" +
                "            \"properties\": {},\n" +
                "            \"user_properties\": {}\n" +
                "        }\n" +
                "    },\n" +
                "    \"animations\": \"data\",\n" +
                "    \"texture_variants\": {\n" +
                "        \"name\": \"default\",\n" +
                "        \"textureMap\": {},\n" +
                "        \"rp_data\": {\n" +
                "            \"0820bbae-fd2c-4896-d7cf-2725a85e50cb\": {\n" +
                "                \"format_version\": \"1.21.11\",\n" +
                "                \"texture_size\": [\n" +
                "                    -1,\n" +
                "                    -1\n" +
                "                ],\n" +
                "                \"textures\": {\n" +
                "                    \"0\": \"outmoded_engine:texture.png\"\n" +
                "                },\n" +
                "                \"elements\": [\n" +
                "                    {\n" +
                "                        \"from\": [\n" +
                "                            -1,\n" +
                "                            0,\n" +
                "                            -1\n" +
                "                        ],\n" +
                "                        \"to\": [\n" +
                "                            1,\n" +
                "                            2,\n" +
                "                            1\n" +
                "                        ],\n" +
                "                        \"rotation\": {\n" +
                "                            \"x\": 0,\n" +
                "                            \"y\": 0,\n" +
                "                            \"z\": 0,\n" +
                "                            \"origin\": [\n" +
                "                                0,\n" +
                "                                0,\n" +
                "                                0\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        \"faces\": {\n" +
                "                            \"north\": {\n" +
                "                                \"uv\": [\n" +
                "                                    0,\n" +
                "                                    0,\n" +
                "                                    2,\n" +
                "                                    2\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            },\n" +
                "                            \"east\": {\n" +
                "                                \"uv\": [\n" +
                "                                    0,\n" +
                "                                    2,\n" +
                "                                    2,\n" +
                "                                    4\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            },\n" +
                "                            \"south\": {\n" +
                "                                \"uv\": [\n" +
                "                                    2,\n" +
                "                                    0,\n" +
                "                                    4,\n" +
                "                                    2\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            },\n" +
                "                            \"west\": {\n" +
                "                                \"uv\": [\n" +
                "                                    2,\n" +
                "                                    2,\n" +
                "                                    4,\n" +
                "                                    4\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            },\n" +
                "                            \"up\": {\n" +
                "                                \"uv\": [\n" +
                "                                    0,\n" +
                "                                    4,\n" +
                "                                    2,\n" +
                "                                    6\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            },\n" +
                "                            \"down\": {\n" +
                "                                \"uv\": [\n" +
                "                                    4,\n" +
                "                                    0,\n" +
                "                                    6,\n" +
                "                                    2\n" +
                "                                ],\n" +
                "                                \"texture\": \"#0\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        BBModelTemplate template = new BBModelTemplate(json);
        NamespacedKey key = new NamespacedKey("test", "test");
        ModelTemplateManager.getInstance().registerModelTemplate(new NamespacedKey("test", "test"), template);


//        PacketUtils.getNextId();
//        BBModelTemplate modelTemplate = new BBModelTemplate.Builder().build();
//
//        NamespacedKey namespacedKey = new NamespacedKey("frog", "test");
//
//        ModelTemplateManager.getInstance().registerModelTemplate(namespacedKey, modelTemplate);

    }


    @Override
    public void onDisable() { PacketEvents.getAPI().terminate(); }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
