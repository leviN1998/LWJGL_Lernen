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
public class Block5 {

    private int id;
    private Entity entity;
    private Vector3f position;
    private IDManager3 idManager3;


    public Block5(int ID, Vector3f position, Loader loader){
        idManager3 = new IDManager3();
        this.id = ID;
        this.position = position;
        this.entity = loader.loadEntity(idManager3.getTextureLocation(ID), idManager3.getOBJLocation(ID), position);
    }


    public void increasePosition(float dx, float dy, float dz){
        this.entity.increasePosition(dx,dy,dz);
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.entity.increaseRotation(dx,dy,dz);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
