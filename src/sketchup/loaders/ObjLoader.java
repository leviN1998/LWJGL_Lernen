package sketchup.loaders;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import sketchup.files.ObjFace;
import sketchup.files.ObjVertex;
import sketchup.files.RawObjList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Levin on 23.05.2017.
 */
public class ObjLoader {

    public static List<ObjFace>[] loadObj(String path) throws FileNotFoundException {
        return loadObj(new File("res/"+ path + ".obj"));
    }

    public static List<ObjFace>[] loadObj(File f) throws FileNotFoundException {
        return loadObj(new Scanner(f));
    }

    public static List<ObjFace>[] loadObj(Scanner sc){

        RawObjList list = new RawObjList();
        int currentTexID = -1;

        while (sc.hasNextLine()){
            String ln = sc.nextLine();

            if(ln == null || ln.startsWith("#")){

            }else {
                String[] split = ln.split(" ");

                if (split[0].startsWith("usemtl")) {
                    currentTexID = getCurrentTexName(split[1], list);
                }else if (split[0].startsWith("v") && !(split[0].startsWith("vt")) && !(split[0].startsWith("vn"))){
                    float x = Float.parseFloat(split[1]);
                    float y = Float.parseFloat(split[2]);
                    float z = Float.parseFloat(split[3]);
                    Vector3f vector = new Vector3f(x,y,z);
                    list.getVertices().add(vector);

                }else if(split[0].startsWith("vt")){

                    float x = Float.parseFloat(split[1]);
                    float y = Float.parseFloat(split[2]);
                    Vector2f vector = new Vector2f(x,y);
                    list.getVts().add(vector);

                }else if(split[0].startsWith("vn")){

                    float x = Float.parseFloat(split[1]);
                    float y = Float.parseFloat(split[2]);
                    float z = Float.parseFloat(split[3]);
                    Vector3f vector = new Vector3f(x,y,z);
                    list.getNormals().add(vector);

                }else if(split[0].startsWith("f")){

                    String[] v1 = split[1].split("/");
                    String[] v2 = split[2].split("/");
                    String[] v3 = split[3].split("/");

                    ObjVertex vertex1 = createvertex(v1, list);
                    ObjVertex vertex2 = createvertex(v2, list);
                    ObjVertex vertex3 = createvertex(v3, list);

                    list.generateFace(vertex1,vertex2,vertex3,currentTexID);
                }

            }

        }

        return list.getModel();

    }

    private static int getCurrentTexName(String toCheck, RawObjList list){
        for (int i = 0; i<list.getTextureNames().size();i++){
            if(toCheck.equals(list.getTextureNames().get(i))){
                return i;
            }
        }
        list.getTextureNames().add(toCheck);
        return list.getTextureNames().size() -1;
    }

    private static ObjVertex createvertex(String[] data, RawObjList list){
        int v = Integer.parseInt(data[0]) -1;
        int vt = Integer.parseInt(data[1]) -1;
        int vn = Integer.parseInt(data[2]) -1;
        return list.generateVertex(v,vt,vn);
    }

}
