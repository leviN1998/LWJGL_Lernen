package blöcke.myObjStuff;


import org.lwjgl.util.vector.Vector3f;
import vbo.models.TexturedModel;

import java.util.List;

/**
 * Created by levin on 13.05.2017.
 */
public class ObjEntity {

    private List<TexturedModel> models;
    private Vector3f position;
    private float rotX,rotY,rotZ;
    private float scale;

    public ObjEntity(List<TexturedModel> models, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.models = models;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }

    public List<TexturedModel> getModels() {
        return models;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public float getScale() {
        return scale;
    }
}
