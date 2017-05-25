package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;

/**
 * Created by levin on 19.11.2016.
 */
@Deprecated
public class Block3{

    private TexturedModel[] faces;
    private ModelTexture[] textures;
    private RawModel[] models;
    private Entity[] entities;

    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    private int id;


    public Block3(Vector3f position, int BlockID, Loader loader, float rotX, float rotY, float rotZ, float scale){
        id = BlockID;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        models = IDManager.getRawModelsFromPosition(position, loader);
        textures = IDManager.getTextureFromID(id);
        faces = new TexturedModel[6];
        entities = new Entity[6];
        for(int i = 0; i<faces.length; i++){
            faces[i] = new TexturedModel(models[i], textures[i]);
            entities[i] = new Entity(faces[i], new Vector3f(position.x+0.5f, position.y-0.5f, position.z-0.5f),
                    rotX, rotY, rotZ, scale);
        }
    }

    public void increasePosition(float dx, float dy, float dz){
        for(int i = 0;i<entities.length;i++){
            Vector3f p = entities[i].getPosition();
            entities[i].setPosition(new Vector3f(p.x + dx, p.y+dy, p.z+dz));
        }
    }

    public void increaseRotation(float dx, float dy, float dz){
        for(int i = 0;i<entities.length;i++){
            entities[i].setRotX(entities[i].getRotX()+dx);
            entities[i].setRotY(entities[i].getRotY()+dy);
            entities[i].setRotZ(entities[i].getRotZ()+dz);
        }
    }

    public TexturedModel[] getFaces() {
        return faces;
    }

    public void setFaces(TexturedModel[] faces) {
        this.faces = faces;
    }

    public ModelTexture[] getTextures() {
        return textures;
    }

    public void setTextures(ModelTexture[] textures) {
        this.textures = textures;
    }

    public RawModel[] getModels() {
        return models;
    }

    public void setModels(RawModel[] models) {
        this.models = models;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
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
