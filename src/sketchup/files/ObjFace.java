package sketchup.files;

/**
 * Created by Levin on 04.06.2017.
 */
public class ObjFace {

    private ObjVertex v1;
    private ObjVertex v2;
    private ObjVertex v3;

    private int matID;

    public ObjFace(ObjVertex v1, ObjVertex v2, ObjVertex v3, int matID) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.matID = matID;
    }

    public ObjVertex getV1() {
        return v1;
    }

    public ObjVertex getV2() {
        return v2;
    }

    public ObjVertex getV3() {
        return v3;
    }

    public int getMatID() {
        return matID;
    }
}
