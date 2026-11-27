package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.outmoded.fastListenerRegistration.ListenerRegister;
import net.outmoded.outmodedEngine.defaultformats.blockbench.BBModelTemplate;
import net.outmoded.outmodedEngine.defaultformats.blockbench.RPCallback;
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


        ModelManager.getInstance().init();
        RPCallback.register();
        // ####################################
        String json = """
{
    "settings": {
        "namespacedId": "",
        "credit": "Made with BlockBench",
        "user_properties": {}
    },
    "textures": {
        "texture.png": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAeUlEQVR4AeyQoQ2AQAxFe6eReBa4UXDgUIwAQd0MLIBCI9iAVfAIJAJBcuTbS5prUkdo8kTb3/anttmuAO69D6BqhwBIGFaoY2X2cBOBZ8kJrPNoADsRNfQOurMmkHlvQHQgmeodlK4wIHmKEegdMIvF5d8B0Qd+8AIAAP//bQT7vwAAAAZJREFUAwBhuCIhFW4C3gAAAABJRU5ErkJggg==",
        "texture1.png": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAATklEQVR4AeyQwQoAIAhDl///z8Yuuyxh0C0SBpHPTawG+iSEVSE3YmawgEUhLDMI54TJgKmUOuFDBiFvWDGVsk74cb9BGDRifwPggRtsAAAA//9LYt8MAAAABklEQVQDAGnADCGBPxr1AAAAAElFTkSuQmCC"
    },
    "structure": {
        "8dd7c0c9-b695-ecdd-f3b0-f63ff14b5f51": {
            "uuid": "8dd7c0c9-b695-ecdd-f3b0-f63ff14b5f51",
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
                    1
                ],
                "translation": [
                    0,
                    0,
                    0
                ]
            },
            "properties": {},
            "user_properties": {}
        }
    },
    "animations": "data",
    "texture_variants": {
        "1": {
            "8dd7c0c9-b695-ecdd-f3b0-f63ff14b5f51": {
                "format_version": "1.21.11",
                "texture_size": [
                    -1,
                    -1
                ],
                "textures": {
                    "5": "texture1.png"
                },
                "data": {
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
                                    "texture": "#5"
                                },
                                "east": {
                                    "uv": [
                                        0,
                                        2,
                                        2,
                                        4
                                    ],
                                    "texture": "#5"
                                },
                                "south": {
                                    "uv": [
                                        2,
                                        0,
                                        4,
                                        2
                                    ],
                                    "texture": "#5"
                                },
                                "west": {
                                    "uv": [
                                        2,
                                        2,
                                        4,
                                        4
                                    ],
                                    "texture": "#5"
                                },
                                "up": {
                                    "uv": [
                                        0,
                                        4,
                                        2,
                                        6
                                    ],
                                    "texture": "#5"
                                },
                                "down": {
                                    "uv": [
                                        4,
                                        0,
                                        6,
                                        2
                                    ],
                                    "texture": "#5"
                                }
                            }
                        }
                    ]
                }
            }
        },
        "default": {
            "8dd7c0c9-b695-ecdd-f3b0-f63ff14b5f51": {
                "format_version": "1.21.11",
                "texture_size": [
                    -1,
                    -1
                ],
                "textures": {
                    "5": "texture.png"
                },
                "data": {
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
                                    "texture": "#5"
                                },
                                "east": {
                                    "uv": [
                                        0,
                                        2,
                                        2,
                                        4
                                    ],
                                    "texture": "#5"
                                },
                                "south": {
                                    "uv": [
                                        2,
                                        0,
                                        4,
                                        2
                                    ],
                                    "texture": "#5"
                                },
                                "west": {
                                    "uv": [
                                        2,
                                        2,
                                        4,
                                        4
                                    ],
                                    "texture": "#5"
                                },
                                "up": {
                                    "uv": [
                                        0,
                                        4,
                                        2,
                                        6
                                    ],
                                    "texture": "#5"
                                },
                                "down": {
                                    "uv": [
                                        4,
                                        0,
                                        6,
                                        2
                                    ],
                                    "texture": "#5"
                                }
                            }
                        }
                    ]
                }
            }
        }
    }
}
                """;

        NamespacedKey key = new NamespacedKey("outmoded_engine", "test");
        BBModelTemplate template = new BBModelTemplate(json, key);
        ModelTemplateManager.getInstance().registerModelTemplate(key, template);

        ResourcePackManager.getInstance().generate();
    }


    @Override
    public void onDisable() { PacketEvents.getAPI().terminate(); }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
