package blöcke.project.minecraft.environment;


import org.lwjgl.util.vector.Vector3f;

/**
 * Created by levin on 30.03.2017.
 */
public class BlockChunk {

    private Vector3f position;
    private Vector3f rotation;
    private float scale;
    private int blockID;

    public BlockChunk(Vector3f position, Vector3f rotation, float scale, int blockID) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.blockID = blockID;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
