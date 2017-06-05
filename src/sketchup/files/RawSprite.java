package sketchup.files;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 17.05.2017.
 */
public class RawSprite {

    private String fileName;
    private Vector2f position;
    private Vector2f dimension;

    private boolean rotated;
    private boolean trimmed;

    private Vector2f sourceDimension;
    private Vector2f pivot;


    public RawSprite(String fileName, Vector4f frame, boolean rotated, boolean trimmed, Vector2f sourceDimension, Vector2f pivot){
        this.fileName = fileName;
        this.position = new Vector2f(frame.getX(), frame.getY());
        this.dimension = new Vector2f(frame.getZ(), frame.getW());
        this.rotated = rotated;
        this.trimmed = trimmed;
        this.sourceDimension = sourceDimension;
        this.pivot = pivot;
    }

    public String getFileName() {
        return fileName;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getDimension() {
        return dimension;
    }

    public boolean isRotated() {
        return rotated;
    }

    public boolean isTrimmed() {
        return trimmed;
    }

    public Vector2f getSourceDimension() {
        return sourceDimension;
    }

    public Vector2f getPivot() {
        return pivot;
    }
}
