package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Entity;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;
import vbo.toolbox.objConverter.ModelData;
import vbo.toolbox.objConverter.OBJFileLoader;

/**
 * Created by levin on 29.01.2017.
 */
public class IDManager3 {

    /**
     * Konstanten um die Blöcke zu unterscheiden
     */

    public static int highestId = 3;

    public static final int AIR_ID = 0;
    public static final int GRASS_ID = 1;
    public static final int STONE_ID = 2;
    public static final int DIRT_ID = 3;

    public static String getTextureLocation(int id){
        String output = null;
        if(id == AIR_ID){

        }else if(id == GRASS_ID){
            output = "Minecraft_31";
        }

        return output;
    }

    public static String getOBJLocation(int id){
        String output = null;
        if(id == AIR_ID){

        }else if(id == GRASS_ID){
            output = "blocks/Grass";
        }

        return output;
    }

    


}
