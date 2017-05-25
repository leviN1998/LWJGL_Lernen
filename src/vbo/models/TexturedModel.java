package vbo.models;

import vbo.textures.ModelTexture;

/**
 * Created by levin on 13.11.2016.
 */
public class TexturedModel {

    private  RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;

    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

}
