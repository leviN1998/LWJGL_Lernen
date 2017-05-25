package blöcke.project.minecraft.environment;

import blöcke.Block5;
import blöcke.IDManager3;
import vbo.Loader;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;
import vbo.toolbox.objConverter.ModelData;
import vbo.toolbox.objConverter.OBJFileLoader;

/**
 * Created by levin on 30.03.2017.
 */
public class BlockManager {

    public static TexturedModel[] models;
    public static Loader loader;


    public static void init(Loader theLoader){
        models = new TexturedModel[IDManager3.highestId+1];
        loader = theLoader;
    }

    public static TexturedModel getBlockAt(int id){
        if(models[id] == null){
            initModel(id);
        }
        return models[id];
    }

    private static void initModel(int id){
        ModelData data = OBJFileLoader.loadOBJ(IDManager3.getOBJLocation(id));
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        models[id] = new TexturedModel(model, new ModelTexture(loader.loadTexture(IDManager3.getTextureLocation(id))));
        models[id].getTexture().setReflectivity(0);
    }

    public static void cleanUp(){
        loader.cleanUp();
    }
}
