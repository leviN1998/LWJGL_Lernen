package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Entity;

/**
 * Created by levin on 30.12.2016.
 */
@Deprecated
public class Block4 {

    /**
     *   Dieser Block muss mit einer extra Klasse (BlockRenderer4) gerendert werden und löst den letzten
     *   Block (Block3) ab
     *   Block4 ist insofern effizienter, da nur noch ein entity objekt benötigt wird , da die einzelnen Seiten
     *   nun durch TextureCoords texturiert werden
     */

    /**
     * Entity um den block zu rendern
     */
    private Entity entity;

    private Vector3f position;


    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    private int id;

    public Block4(Vector3f position, int BlockID, float rotX, float rotY, float rotZ, float scale){
        id = BlockID;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.position = position;
        createEntity();
    }

    private void createEntity(){
        this.entity = IDManager2.getEntityFromPosition(position, id);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
