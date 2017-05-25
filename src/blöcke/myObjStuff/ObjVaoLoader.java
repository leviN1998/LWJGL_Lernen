package blöcke.myObjStuff;

import vbo.Loader;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 13.05.2017.
 */
public class ObjVaoLoader {

    private Loader loader;

    public ObjVaoLoader(Loader loader){
        this.loader = loader;
    }

    public TexturedModel getTexturedModel(ObjRawModel rawModel){
        RawModel model = loader.loadToVAO(rawModel.getVertices(), rawModel.getTextureCoords(), rawModel.getNormals(), rawModel.getIndices());
        TexturedModel texturedModel = new TexturedModel(model, getTexture(rawModel.material));
        return texturedModel;
    }

    public ModelTexture getTexture(Material m){
        return new ModelTexture(loader.loadTexture(m.getPath()));
    }

    public void cleanUp(){
        loader.cleanUp();
    }

}
