package net.outmoded.outmodedEngine.defaultformats.blockbench;

import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.resourcepack.ResourcePackManager;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;
import org.bukkit.NamespacedKey;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class RPCallback {
    private RPCallback(){}

    private static final ResourcePackManager.Callback callback = resourcePack -> {
        ConcurrentHashMap<NamespacedKey, ModelTemplate> map = ModelTemplateManager.getInstance().getAllTemplates();

        map.forEach(((namespacedKey, modelTemplate) -> {
            if (!(modelTemplate instanceof BBModelTemplate model)){
                return;
            }

            model.getTexturesMap().forEach((key, texture) -> {

                byte[] decodedTexture = Base64.getDecoder().decode(texture);

                Co.warn(key+" : "+texture);

                try (InputStream is = new ByteArrayInputStream(decodedTexture)) {
                    resourcePack.writeInputStream("assets/"+namespacedKey.getNamespace()+"/textures/item/"+namespacedKey.getKey()+"/"+key, is); // key has .png at the end
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            });

            model.getModelDataMap().forEach((key, modelData) -> {
                try (InputStream is = new ByteArrayInputStream(modelData.getBytes(StandardCharsets.UTF_8))) {
                    resourcePack.writeInputStream("assets/"+namespacedKey.getNamespace()+"/models/item/"+namespacedKey.getKey()+"/"+key+".json", is);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            });
        }));
    };

    public static void register(){
        ResourcePackManager.getInstance().registerCallback(callback); // this could be a lambda, but I don't want messy code
        Co.info("started BBModel Callback");
    }
}
