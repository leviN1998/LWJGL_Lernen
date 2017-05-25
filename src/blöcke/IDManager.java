package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.models.RawModel;
import vbo.textures.ModelTexture;

import java.util.*;

/**
 * Created by levin on 19.11.2016.
 */
@Deprecated
public class IDManager {

    public static ModelTexture[] getTextureFromID(int BlockID){
        Loader loader = new Loader();
        ModelTexture[] textures = new ModelTexture[6];
        if(BlockID == 1){

            //Top
            int texture2ID = loader.loadTexture("Grass_top");
            textures[0] = new ModelTexture(texture2ID);

            //Sides
            int texture1ID = loader.loadTextureSprite("res/Minecraft.png", 16, 16, 1, 0, 64, 64, 1 , 3);
            textures[1] = new ModelTexture(texture1ID);
            textures[2] = new ModelTexture(texture1ID);
            textures[3] = new ModelTexture(texture1ID);
            textures[4] = new ModelTexture(texture1ID);


            //Bottom
            int texture3ID = loader.loadTextureSprite("res/Minecraft.png", 16, 16, 1, 0, 64, 64, 1 , 2);
            textures[5] = new ModelTexture(texture3ID);
        }

        return textures;
    }


    public static RawModel[] getRawModelsFromPosition(Vector3f position, Loader loader){
        RawModel[] output = new RawModel[6];

        Vector3f[] positions = getPositionsFromPosition(position);
        float[] dataTop = getDataFromPositions(getAllTopPositionsFromAllVertices(positions));
        float[] dataSide0 = getDataFromPositions(getAllSide0PositionsFromAllVertices(positions));
        float[] dataSide1 = getDataFromPositions(getAllSide1PositionsFromAllVertices(positions));
        float[] dataSide2 = getDataFromPositions(getAllSide2PositionsFromAllVertices(positions));
        float[] dataSide3 = getDataFromPositions(getAllSide3PositionsFromAllVertices(positions));
        float[] dataBot = getDataFromPositions(getAllBotPositionsFromAllVertices(positions));

        output[0] = loader.loadToVAO(dataTop, TEXTURECOORDS,normals, INDICES);
        output[1] = loader.loadToVAO(dataSide0, TEXTURECOORDS,normals, INDICES);
        output[2] = loader.loadToVAO(dataSide1, TEXTURECOORDS,normals, INDICES);
        output[3] = loader.loadToVAO(dataSide2, TEXTURECOORDS,normals, INDICES);
        output[4] = loader.loadToVAO(dataSide3, TEXTURECOORDS,normals, INDICES);
        output[5] = loader.loadToVAO(dataBot, TEXTURECOORDS,normals, INDICES);

        return output;

    }

    public static float[] getDataFromPositions(Vector3f[] positions){
        List<Float> dataList = new ArrayList<Float>();
        int index = 0;
        for(int i = 0; i<positions.length;i++){
            dataList.add(positions[i].x);
            index++;
            dataList.add(positions[i].y);
            index++;
            dataList.add(positions[i].z);
            index++;
        }
        float[] output = new float[index];
        for(int i = 0; i<output.length;i++){
            output[i] = dataList.get(i);
        }
        return output;
    }

    public static Vector3f[] getAllSide0PositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];
        //Face 0
        output[0] = vertices[0];
        output[1] = vertices[4];
        output[2] = vertices[5];
        output[3] = vertices[1];

        return output;
    }
    public static Vector3f[] getAllSide1PositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];

        //Face 1
        output[0] = vertices[1];
        output[1] = vertices[5];
        output[2] = vertices[6];
        output[3] = vertices[2];

        return output;
    }

    public static Vector3f[] getAllSide2PositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];

        //Face 2
        output[0] = vertices[2];
        output[1] = vertices[6];
        output[2] = vertices[7];
        output[3] = vertices[3];

        return output;
    }

    public static Vector3f[] getAllSide3PositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];

        //Face 3
        output[0] = vertices[3];
        output[1] = vertices[7];
        output[2] = vertices[4];
        output[3] = vertices[0];

        return output;
    }


    public static Vector3f[] getAllTopPositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];

        output[0] = vertices[3];
        output[1] = vertices[0];
        output[2] = vertices[1];
        output[3] = vertices[2];

        return output;
    }

    public static Vector3f[] getAllBotPositionsFromAllVertices(Vector3f[] vertices){
        Vector3f[] output = new Vector3f[4];

        output[0] = vertices[4];
        output[1] = vertices[7];
        output[2] = vertices[6];
        output[3] = vertices[5];

        return output;
    }

    public static Vector3f[] getPositionsFromPosition(Vector3f position){
        Vector3f[] output = new Vector3f[8];
        output[0] = position;                                               //Front Top Left 0
        output[1] = new Vector3f(position.x+1, position.y, position.z);     //Front Top Right 1
        output[2] = new Vector3f(position.x+1, position.y, position.z-1);   //Back Top Right 2     (Von vorne durch gesehen)
        output[3] = new Vector3f(position.x, position.y, position.z-1);     //Back Top Left 3
        output[4] = new Vector3f(position.x, position.y-1, position.z);     //Front Bot Left 4
        output[5] = new Vector3f(position.x+1, position.y-1, position.z);   //Front Bot Right 5
        output[6] = new Vector3f(position.x+1, position.y-1, position.z-1); //Back Bot Right 6
        output[7] = new Vector3f(position.x, position.y-1, position.z-1);   //Back Bot Left 7

        return output;
    }

    public static final float[] TEXTURECOORDS = {
            0, 0,
            0, 1,
            1, 1,
            1, 0
    };

    public static final int[] INDICES = {
            0,1,3,
            3,1,2
    };

    private static float[] normals = {
            0,0,0,
            0,0,0,
            0,0,0
    };


}
