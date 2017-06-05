package sketchup.files;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Levin on 04.06.2017.
 */
public class ObjVertex {

    private Vector3f position;
    private Vector3f normal;
    private Vector2f vt;


    public ObjVertex(Vector3f position, Vector2f vt, Vector3f normal) {
        this.position = position;
        this.normal = normal;
        this.vt = vt;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public Vector2f getVt() {
        return vt;
    }


}
