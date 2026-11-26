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
                         "texture1.png": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAb0lEQVR4AeyQsQmAQAxFf9K4gr2ruIETuII6hrqCE7iBq9g7gl3kN1cIB3emkws8COQn/xM1wMh9Dka6fjKCxNJEXVSm7bWAVM0Ksm+zkOjGa+BPcNQjiABCkFn+BHQlmcZB7k8QTn1sSgLgBz94AAAA///8eNPnAAAABklEQVQDAL9HGiF64kRiAAAAAElFTkSuQmCC"
                     },
                     "structure": {
                         "079720da-8430-13b3-f506-f5f355b69346": {
                             "uuid": "079720da-8430-13b3-f506-f5f355b69346",
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
                         },
                         "2a295154-eacc-05de-1e77-84501cfcc06e": {
                             "uuid": "2a295154-eacc-05de-1e77-84501cfcc06e",
                             "type": "group",
                             "name": "bone2",
                             "transform": {
                                 "position": [
                                     0,
                                     0,
                                     7
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
                                     7
                                 ]
                             },
                             "properties": {},
                             "user_properties": {}
                         },
                         "d153e582-eb2b-be5f-d9eb-bf16db067464": {
                             "uuid": "d153e582-eb2b-be5f-d9eb-bf16db067464",
                             "type": "group",
                             "name": "bone3",
                             "transform": {
                                 "position": [
                                     0,
                                     0,
                                     -7
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
                                     -7
                                 ]
                             },
                             "properties": {},
                             "user_properties": {}
                         }
                     },
                     "animations": "data",
                     "texture_variants": {
                         "1": {
                             "079720da-8430-13b3-f506-f5f355b69346": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
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
                                             "faces": {}
                                         }
                                     ]
                                 }
                             },
                             "2a295154-eacc-05de-1e77-84501cfcc06e": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
                                 "data": {
                                     "elements": [
                                         {
                                             "from": [
                                                 -1,
                                                 0,
                                                 6
                                             ],
                                             "to": [
                                                 1,
                                                 2,
                                                 8
                                             ],
                                             "rotation": {
                                                 "x": 0,
                                                 "y": 0,
                                                 "z": 0,
                                                 "origin": [
                                                     0,
                                                     0,
                                                     7
                                                 ]
                                             },
                                             "faces": {}
                                         }
                                     ]
                                 }
                             },
                             "d153e582-eb2b-be5f-d9eb-bf16db067464": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
                                 "data": {
                                     "elements": [
                                         {
                                             "from": [
                                                 -1,
                                                 0,
                                                 -8
                                             ],
                                             "to": [
                                                 1,
                                                 2,
                                                 -6
                                             ],
                                             "rotation": {
                                                 "x": 0,
                                                 "y": 0,
                                                 "z": 0,
                                                 "origin": [
                                                     0,
                                                     0,
                                                     -7
                                                 ]
                                             },
                                             "faces": {}
                                         }
                                     ]
                                 }
                             }
                         },
                         "default": {
                             "079720da-8430-13b3-f506-f5f355b69346": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
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
                                             "faces": {}
                                         }
                                     ]
                                 }
                             },
                             "2a295154-eacc-05de-1e77-84501cfcc06e": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
                                 "data": {
                                     "elements": [
                                         {
                                             "from": [
                                                 -1,
                                                 0,
                                                 6
                                             ],
                                             "to": [
                                                 1,
                                                 2,
                                                 8
                                             ],
                                             "rotation": {
                                                 "x": 0,
                                                 "y": 0,
                                                 "z": 0,
                                                 "origin": [
                                                     0,
                                                     0,
                                                     7
                                                 ]
                                             },
                                             "faces": {}
                                         }
                                     ]
                                 }
                             },
                             "d153e582-eb2b-be5f-d9eb-bf16db067464": {
                                 "format_version": "1.21.11",
                                 "texture_size": [
                                     -1,
                                     -1
                                 ],
                                 "textures": {},
                                 "data": {
                                     "elements": [
                                         {
                                             "from": [
                                                 -1,
                                                 0,
                                                 -8
                                             ],
                                             "to": [
                                                 1,
                                                 2,
                                                 -6
                                             ],
                                             "rotation": {
                                                 "x": 0,
                                                 "y": 0,
                                                 "z": 0,
                                                 "origin": [
                                                     0,
                                                     0,
                                                     -7
                                                 ]
                                             },
                                             "faces": {}
                                         }
                                     ]
                                 }
                             }
                         }
                     }
                 }
                """;

        BBModelTemplate template = new BBModelTemplate(json);
        //NamespacedKey key = new NamespacedKey("test", "test");
        ModelTemplateManager.getInstance().registerModelTemplate(new NamespacedKey("outmoded_engine", "test"), template);

        ResourcePackManager.getInstance().generate();
    }


    @Override
    public void onDisable() { PacketEvents.getAPI().terminate(); }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
