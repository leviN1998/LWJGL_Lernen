package sketchup.files;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Levin on 23.05.2017.
 */
public class RawMaterial {

    private String header;
    private Vector3f ka;
    private Vector3f kd;
    private Vector3f ks;
    private String name;
    private boolean hasFile;

    public RawMaterial(String header, Vector3f ka, Vector3f kd, Vector3f ks, String name) {
        this.header = header;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.name = name;
        hasFile = true;

        if(name.equals("noName")){
            hasFile = false;
        }
        calcName();
    }

    private void calcName(){
        String[] split = name.split("/");
        name = split[split.length-1];
    }

    public String getHeader() {
        return header;
    }

    public Vector3f getKa() {
        return ka;
    }

    public Vector3f getKd() {
        return kd;
    }

    public Vector3f getKs() {
        return ks;
    }

    public String getName() {
        return name;
    }

    public boolean isHasFile() {
        return hasFile;
    }
}
