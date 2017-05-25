package blöcke.myObjStuff;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 13.05.2017.
 */
public class ObjVertex {
    private float[] vertex;
    private float[] vt;
    private float[] normal;
    private int index;

    public ObjVertex(Vector3f vertex, Vector2f textureCoord, Vector3f normal, int index){
        this.vertex = new float[3];
        this.vt = new float[2];
        this.normal = new float[3];
        this.index = index;

        this.vertex[0] = vertex.x;
        this.vertex[1] = vertex.y;
        this.vertex[2] = vertex.z;

        this.vt[0] = textureCoord.x;
        this.vt[1] = textureCoord.y;

        this.normal[0] = normal.x;
        this.normal[1] = normal.y;
        this.normal[2] = normal.z;
    }

    public float[] getVertex() {
        return vertex;
    }

    public float[] getVt() {
        return vt;
    }

    public float[] getNormal() {
        return normal;
    }

    public int getIndex() {
        return index;
    }

    public List<Float> addVertsToList(List<Float> l){
        for(int i = 0;i<vertex.length;i++){
            l.add(vertex[i]);
        }
        return l;
    }

    public List<Float> addVtsToList(List<Float> l){
        for(int i = 0;i<vt.length;i++){
            l.add(vt[i]);
        }
        return l;
    }

    public List<Float> addNormalToList(List<Float> l){
        for(int i = 0;i<normal.length;i++){
            l.add(normal[i]);
        }
        return l;
    }
}
