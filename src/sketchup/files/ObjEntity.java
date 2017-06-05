package sketchup.files;


import org.lwjgl.util.vector.Vector3f;
import vbo.models.TexturedModel;

import java.util.List;

/**
 * Created by Levin on 05.06.2017.
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

    public void increasePosition(float deltaX, float deltaY, float deltaZ){
        position.x += deltaX;
        position.y += deltaY;
        position.z += deltaZ;
    }

    public void increaseRotation(float deltaX, float deltaY, float deltaZ){
        rotX += deltaX;
        rotY += deltaY;
        rotZ += deltaZ;
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

    public List<TexturedModel> getModels(){
        return models;
    }
}
