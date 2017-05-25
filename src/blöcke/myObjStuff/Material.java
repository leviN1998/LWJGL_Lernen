package blöcke.myObjStuff;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by levin on 13.05.2017.
 */
public class Material {

    //TODO: fertig stellen

    private String name;
    private String path;

    private Vector3f ambient;
    private Vector3f diffuse;
    private Vector3f mirrorColour;

    public Material(String name, String path, Vector3f ambient, Vector3f diffuse, Vector3f mirrorColour) {
        this.name = name;
        this.path = path;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.mirrorColour = mirrorColour;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public Vector3f getMirrorColour() {
        return mirrorColour;
    }
}
