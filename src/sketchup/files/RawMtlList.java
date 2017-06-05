package sketchup.files;

import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 17.05.2017.
 */
public class RawMtlList {

    private List<String> headers;
    private List<Vector3f> Kas;
    private List<Vector3f> Kds;
    private List<Vector3f> Kss;
    private List<String> names;

    private List<RawMaterial> mats;


    public RawMtlList(){

        headers = new ArrayList<>();
        Kas = new ArrayList<>();
        Kds = new ArrayList<>();
        Kss = new ArrayList<>();
        names = new ArrayList<>();
        mats = new ArrayList<>();

    }

    public void calcMats(){
        for(int i = 0;i<names.size();i++){
            RawMaterial mtl = new RawMaterial(headers.get(i), Kas.get(i), Kds.get(i), Kss.get(i), names.get(i));
            mats.add(mtl);
        }
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Vector3f> getKas() {
        return Kas;
    }

    public List<Vector3f> getKds() {
        return Kds;
    }

    public List<Vector3f> getKss() {
        return Kss;
    }

    public List<String> getNames() {
        return names;
    }

    public List<RawMaterial> getMats(){
        return mats;
    }
}
