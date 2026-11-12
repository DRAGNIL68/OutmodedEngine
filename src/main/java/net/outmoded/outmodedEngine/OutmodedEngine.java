package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.outmoded.fastListenerRegistration.ListenerRegister;
import net.outmoded.outmodedEngine.defaultformats.blockbench.BBModelTemplate;
import net.outmoded.outmodedEngine.live.ModelManager;
import net.outmoded.outmodedEngine.resourcepack.ResourcePackManager;
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
        String json = """
                {
                    "settings": {
                        "namespacedId": "",
                        "credit": "Made with BlockBench",
                        "user_properties": {}
                    },
                    "textures": {
                        "texture.png": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAeUlEQVR4AeyQoQ2AQAxFe6eReBa4UXDgUIwAQd0MLIBCI9iAVfAIJAJBcuTbS5prUkdo8kTb3/anttmuAO69D6BqhwBIGFaoY2X2cBOBZ8kJrPNoADsRNfQOurMmkHlvQHQgmeodlK4wIHmKEegdMIvF5d8B0Qd+8AIAAP//bQT7vwAAAAZJREFUAwBhuCIhFW4C3gAAAABJRU5ErkJggg=="
                    },
                    "structure": {
                        "0820bbae-fd2c-4896-d7cf-2725a85e50cb": {
                            "uuid": "0820bbae-fd2c-4896-d7cf-2725a85e50cb",
                            "type": "group",
                            "name": "bone",
                            "transform": {
                                "position": [
                                    0,
                                    0,
                                    0
                                ],
                                "left_rotation": [
                                    0,
                                    0,
                                    0,
                                    0
                                ],
                                "translation": [
                                    0,
                                    0,
                                    0
                                ],
                                "scale": [
                                    1,
                                    1,
                                    1
                                ]
                            },
                            "properties": {},
                            "user_properties": {}
                        }
                    },
                    "animations": "data",
                    "texture_variants": {
                        "name": "default",
                        "textureMap": {},
                        "rp_data": {
                            "0820bbae-fd2c-4896-d7cf-2725a85e50cb": {
                                "format_version": "1.21.11",
                                "texture_size": [
                                    -1,
                                    -1
                                ],
                                "textures": {
                                    "0": "outmoded_engine:texture.png"
                                },
                                "elements": [
                                    {
                                        "from": [
                                            -1,
                                            0,
                                            -1
                                        ],
                                        "to": [
                                            1,
                                            2,
                                            1
                                        ],
                                        "rotation": {
                                            "x": 0,
                                            "y": 0,
                                            "z": 0,
                                            "origin": [
                                                0,
                                                0,
                                                0
                                            ]
                                        },
                                        "faces": {
                                            "north": {
                                                "uv": [
                                                    0,
                                                    0,
                                                    2,
                                                    2
                                                ],
                                                "texture": "#0"
                                            },
                                            "east": {
                                                "uv": [
                                                    0,
                                                    2,
                                                    2,
                                                    4
                                                ],
                                                "texture": "#0"
                                            },
                                            "south": {
                                                "uv": [
                                                    2,
                                                    0,
                                                    4,
                                                    2
                                                ],
                                                "texture": "#0"
                                            },
                                            "west": {
                                                "uv": [
                                                    2,
                                                    2,
                                                    4,
                                                    4
                                                ],
                                                "texture": "#0"
                                            },
                                            "up": {
                                                "uv": [
                                                    0,
                                                    4,
                                                    2,
                                                    6
                                                ],
                                                "texture": "#0"
                                            },
                                            "down": {
                                                "uv": [
                                                    4,
                                                    0,
                                                    6,
                                                    2
                                                ],
                                                "texture": "#0"
                                            }
                                        }
                                    }
                                ]
                            }
                        }
                    }
                }""";

        BBModelTemplate template = new BBModelTemplate(json);
        NamespacedKey key = new NamespacedKey("test", "test");
        ModelTemplateManager.getInstance().registerModelTemplate(new NamespacedKey("test", "test"), template);

        ResourcePackManager.getInstance().generate();

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
