package net.outmoded.outmodedEngine.templates;

public class ModelTemplate {

    private ModelTemplate(Builder builder){
        // do something
    }




    public void setDefaultPersistence(){

    }

    public static class Builder {
        private boolean isPersistent = true;
        private boolean defaultAnimationController = true;
        private String json = null;

        public Builder persistence(boolean b){
            isPersistent = b;
            return this;
        }

        public Builder animationController(boolean b){
            isPersistent = b;
            return this;
        }

        public Builder fromJson(String json){
            this.json = json;
            return this;
        }

        public ModelTemplate build() {
            return new ModelTemplate(this);
        }
    }

}
