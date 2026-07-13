package net.outmoded.outmodedEngine.templates;

import java.util.concurrent.ConcurrentHashMap;

public class ModelTemplate {
    private final ConcurrentHashMap<String, VariantTemplate> variants = new ConcurrentHashMap<>();

    private ModelTemplate(Builder builder){
        // do something
    }


    public static class Builder {
        private boolean isPersistent = true;
        private String json = null;

        public Builder persistence(boolean b){
            isPersistent = b;
            return this;
        }

        //this.variants = ImmutableList.copyOf(variants);
        public Builder fromJson(String json){
            this.json = json;
            return this;
        }

        public ModelTemplate build() {
            return new ModelTemplate(this);
        }
    }

}
