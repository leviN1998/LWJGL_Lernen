package sketchup.loaders;

import org.lwjgl.util.vector.Vector3f;
import sketchup.files.RawMaterial;
import sketchup.files.RawMtlList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by levin on 17.05.2017.
 */
public class MtlLoader {

    public static List<RawMaterial> loadFile(String path) throws FileNotFoundException {
        String newPath = "res/"+path;
        return loadFile(new File("res/"+path+".mtl"), newPath);
    }

    public static List<RawMaterial> loadFile(File f, String path) throws FileNotFoundException{
        return loadFile(new Scanner(f), path);
    }

    public static List<RawMaterial> loadFile(Scanner sc, String path){

        RawMtlList list = new RawMtlList();
        int counter = -1;

        while (sc.hasNextLine()){
            String ln = sc.nextLine();


            if(ln == null || ln.equals("") || ln.startsWith("{") || ln.startsWith("}")){

            }else {

                String[] split = ln.split(" ");

                if (split[0].startsWith("newmtl")) {

                    list.getHeaders().add(split[1]);
                    if(counter != -1){
                        try{
                            list.getNames().get(counter);
                           // System.out.println(list.getNames().get(counter));
                        }catch (Exception x){
                            list.getNames().add("noName");
                            //System.out.println(list.getHeaders().get(counter));
                        }
                    }
                    counter++;

                }else if(split[0].startsWith("Ka")){

                    Vector3f vector = new Vector3f(Float.parseFloat(split[1]),
                            Float.parseFloat(split[2]), Float.parseFloat(split[3]));
                    list.getKas().add(vector);

                }else if(split[0].startsWith("Kd")){

                    Vector3f vector = new Vector3f(Float.parseFloat(split[1]),
                            Float.parseFloat(split[2]), Float.parseFloat(split[3]));
                    list.getKds().add(vector);

                }else if(split[0].startsWith("Ks")){

                    Vector3f vector = new Vector3f(Float.parseFloat(split[1]),
                            Float.parseFloat(split[2]), Float.parseFloat(split[3]));
                    list.getKss().add(vector);

                }else if (split[0].startsWith("map_Kd")){

                    list.getNames().add(split[1]);

                }
            }
        }

        list.calcMats();
        return list.getMats();

    }

}
