package blöcke.myObjStuff;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 13.05.2017.
 */
public class ObjFace {
    private float[] vertices;
    private float[] textureCoords;
    private float[] normals;
    private int[] indices;

    private int material;

    public ObjFace(float[] vertices, float[] textureCoords, float[] normals, int[] indices, int material){

    }

    public ObjFace(Vector3f[] vertices, Vector2f[] textureCoords, Vector3f[] normals, int[] indices, int material){

    }

    public ObjFace(ObjVertex v1, ObjVertex v2, ObjVertex v3, int material){
        List<Float> vert = new ArrayList<>();
        List<Float> vt = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        vert = v1.addVertsToList(vert);
        vert = v2.addVertsToList(vert);
        vert = v3.addVertsToList(vert);

        vt = v1.addVtsToList(vt);
        vt = v2.addVtsToList(vt);
        vt = v3.addVtsToList(vt);

        normals = v1.addNormalToList(normals);
        normals = v2.addNormalToList(normals);
        normals = v3.addNormalToList(normals);

        indices.add(v1.getIndex());
        indices.add(v2.getIndex());
        indices.add(v3.getIndex());
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextureCoords() {
        return textureCoords;
    }

    public float[] getNormals() {
        return normals;
    }

    public int[] getIndices(){return indices;}

    public int getMaterial() {
        return material;
    }

    private float[] listToArray(List<Float> l){
        float[] array = new float[l.size()];
        for (int i = 0;i<l.size();i++){
            array[i] = l.get(i);
        }
        return array;
    }
}
