package net.outmoded.outmodedEngine.template;

import org.bukkit.NamespacedKey;

import java.util.concurrent.ConcurrentHashMap;

public final class ModelTemplateManager {
    private final ConcurrentHashMap<NamespacedKey, ModelTemplate> modelTemplateHashMap = new ConcurrentHashMap<>();

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

        modelTemplateHashMap.put(namespacedKey, modelTemplate);
    }

    public void unregisterModelTemplate(NamespacedKey namespacedKey){
        modelTemplateHashMap.remove(namespacedKey);
    }

    public boolean hasModelTemplate(NamespacedKey namespacedKey){
        return modelTemplateHashMap.containsKey(namespacedKey);
    }

    public ModelTemplate getModelTemplate(NamespacedKey namespacedKey){
        if (namespacedKey == null)
            return null;

        return modelTemplateHashMap.get(namespacedKey);

    }


}
