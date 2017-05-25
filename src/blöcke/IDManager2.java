package blöcke;

import org.lwjgl.opengl.EXTVertexWeighting;
import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;

/**
 * Created by levin on 30.12.2016.
 */
public class IDManager2 {

    /**
     * Konstanten um Blocktypen zu erstellen
     */
    public static final int AIR_ID = 0;
    public static final int GRASS_ID = 1;
    public static final int STONE_ID = 2;
    public static final int DIRT_ID = 3;

    /**
     * Array für die blockKoords
     */
    public static final float[] BLOCK_POSITIONS = {
            0,1,1,
            0,0,1,
            1,0,1,
            1,1,1,

            1,1,1,
            1,0,1,
            1,0,0,
            1,1,0,

            1,1,0,
            1,0,0,
            0,0,0,
            0,1,0,

            0,1,0,
            0,0,0,
            0,0,1,
            0,1,1,

            0,1,1,
            1,1,1,
            1,1,0,
            0,1,0,

            0,0,0,
            1,0,0,
            1,0,1,
            0,0,1
    };


    /**
     * Array für die Indices (bleiben normal)
     */
    public static final int[] BLOCK_INDICES = {
            0,1,3,
            3,1,2,
            0,1,3,
            3,1,2,
            0,1,3,
            3,1,2,
            0,1,3,
            3,1,2,
            0,1,3,
            3,1,2,
            0,1,3,
            3,1,2
    };



    /**
     * Array für die normalen des blockes
     */

    public static final float[] BLOCK_NORMALS = {

            0,0,1,
            1,0,0,
            0,0,-1,
            -1,0,0,
            0,1,0,
            0,-1,0

    };

    /**
     *Methode um das tatsächliche Entity zu bekommen
     */

    public static Entity getEntityFromPosition(Vector3f position, int id){
        Entity entity = new Entity(getTexturedModelFromID(id), position, 0,0,0,1);
        return entity;
    }


    /**
     * Methode um ein Entity Objekt anhand der ID zu erstellen
     */
    public static TexturedModel getTexturedModelFromID(int id){
        Loader loader = new Loader();
        RawModel model = loader.loadToVAO(BLOCK_POSITIONS, getTExtureCoorinatesFromID(id), BLOCK_NORMALS, BLOCK_INDICES);
        ModelTexture texture = new ModelTexture(loader.loadTexture("Minecraft_2"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        loader.cleanUp();
        return texturedModel;
    }

    /**
     * Methode um die TexturCoordinaten anhand der BlockID zu bekommen
     */

    public static float[] getTExtureCoorinatesFromID(int id){
        if(id == AIR_ID){

        }

        if(id== GRASS_ID){
            return GRASS_VT;
        }

        return null;
    }


    /**
     * TexturCoordinaten für GRass
     */

    public static final float[] GRASS_VT = {
            3f/16f, 0,
            3f/16f, 1f/16f,
            4f/16f, 1f/16f,
            4f/16f, 0,

            3f/16f, 0,
            3f/16f, 1f/16f,
            4f/16f, 1f/16f,
            4f/16f, 0,

            3f/16f, 0,
            3f/16f, 1f/16f,
            4f/16f, 1f/16f,
            4f/16f, 0,

            3f/16f, 0,
            3f/16f, 1f/16f,
            4f/16f, 1f/16f,
            4f/16f, 0,

            15f/16f, 2f/16f,
            15f/16f, 3f/16f,
            1, 3f/16f,
            1, 2f/16f,

            2f/16f, 0,
            2f/16f, 1f/16f,
            3f/16f, 1f/16f,
            3f/16f, 0
    };


}
