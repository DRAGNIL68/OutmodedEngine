package net.outmoded.outmodedEngine.templates;

import org.bukkit.NamespacedKey;

import java.util.concurrent.ConcurrentHashMap;

public final class ModelTemplateManager {
    private final ConcurrentHashMap<String, ModelTemplate> modelTemplateHashMap = new ConcurrentHashMap<>();

    private ModelTemplateManager() {}

    private static class SingletonHelper {
        private static final ModelTemplateManager SINGLETON_INSTANCE = new ModelTemplateManager();
    }

    public static ModelTemplateManager getInstance() {
        return SingletonHelper.SINGLETON_INSTANCE;
    }

    public void registerModelTemplate(NamespacedKey namespacedKey, ModelTemplate modelTemplate){
        if (namespacedKey == null || modelTemplate == null)
            throw new NullPointerException();

        modelTemplateHashMap.put(namespacedKey.getNamespace(), modelTemplate);
    }

    public void unregisterModelTemplate(NamespacedKey namespacedKey){
        modelTemplateHashMap.remove(namespacedKey.getNamespace());
    }

    public boolean hasModelTemplate(NamespacedKey namespacedKey){
        return modelTemplateHashMap.containsKey(namespacedKey.getNamespace());
    }

    public ModelTemplate getModelTemplate(NamespacedKey namespacedKey){
        if (namespacedKey == null)
            return null;

        return modelTemplateHashMap.get(namespacedKey.getNamespace());

    }


}
