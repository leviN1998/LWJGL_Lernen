package sketchup.files;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import sketchup.loaders.MasterLoader;
import vbo.textures.ModelTexture;

import java.util.List;

/**
 * Created by Levin on 04.06.2017.
 */
public class RawObjModel {

    private float[] positions;
    private float[] texturecoords;
    private float[] normals;

    private ModelTexture texture;

    public RawObjModel(List<Vector3f> pos, List<Vector3f> normals, List<Vector2f> vts, RawMaterial mat, MasterLoader loader, String path){

        positions = new float[pos.size()*3];
        texturecoords = new float[vts.size()*2];
        this.normals = new float[normals.size()*3];

        calcPositions(pos);
        calcNormals(normals);
        calcVts(vts);

        texture = loader.loadMaterial(mat, path);
    }

    //for testing
    @Deprecated
    public RawObjModel(List<Vector3f> pos){
        positions = new float[pos.size()*3];
        calcPositions(pos);
        System.out.println(positions[0]);
        System.out.println(positions[positions.length-1]);
    }



    private void calcPositions(List<Vector3f> pos){
        int pointer = 0;
        for(Vector3f v : pos){
            addVector3fToArray(v,pointer,positions);
            pointer += 3;
        }
    }

    private void calcVts(List<Vector2f> vts){
        int pointer = 0;
        for (Vector2f v : vts){
            addVector2fToArray(v, pointer, texturecoords);
            pointer += 2;
        }
    }

    private void calcNormals(List<Vector3f> normals){
        int pointer = 0;
        for (Vector3f v : normals){
            addVector3fToArray(v,pointer,this.normals);
            pointer+=3;
        }
    }

    private void addVector3fToArray(Vector3f vector, int pointer, float[] array){
        array[pointer] = vector.getX();
        pointer++;
        array[pointer] = vector.getY();
        pointer++;
        array[pointer] = vector.getZ();
        pointer++;
    }

    private void addVector2fToArray(Vector2f vector, int pointer, float[] array){
        array[pointer] = vector.x;
        pointer++;
        array[pointer] = vector.y;
        pointer++;
    }

    public float[] getPositions() {
        return positions;
    }

    public float[] getTexturecoords() {
        return texturecoords;
    }

    public float[] getNormals() {
        return normals;
    }

    public ModelTexture getTexture() {
        return texture;
    }
}
