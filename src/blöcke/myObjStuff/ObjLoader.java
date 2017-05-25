package blöcke.myObjStuff;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.models.RawModel;
import vbo.models.TexturedModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by levin on 24.11.2016.
 */
public class ObjLoader {

    public static List<TexturedModel> loadObjModel(String fileName, String matFileName, ObjVaoLoader loader){

        Material[] materials = processMats(matFileName);

        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/"+fileName+".obj"));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file!");
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;

        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        List<Integer> mats = new ArrayList<>();
        List<ObjFace> faces = new ArrayList<>();

        float[] matsArray = null;
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        ObjFace[] facesArray = null;
        int[] indicesArray = null;
        try{
            int currentMat = -1;

            while (true) {

                line = reader.readLine();
                if(line==null)break;
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {

                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);

                } else if (line.startsWith("vt ")) {

                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {

                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    normals.add(normal);

                } else if (line.startsWith("usemtl ")){
                    for(int i = 0; i<materials.length;i++){
                        if(currentLine[1].equals(materials[i].getName())){
                            currentMat = i;
                        }
                    }
                }else if (line.startsWith("f ")){

                    String[] vertex1 = currentLine[1].split("/");
                    String[] vertex2 = currentLine[2].split("/");
                    String[] vertex3 = currentLine[3].split("/");

                    ObjVertex v1 = new ObjVertex(vertices.get(Integer.parseInt(vertex1[0])-1), textures.get(Integer.parseInt(vertex1[1])-1),
                            normals.get(Integer.parseInt(vertex1[2])-1), Integer.parseInt(vertex1[0])-1);
                    ObjVertex v2 = new ObjVertex(vertices.get(Integer.parseInt(vertex2[0])-1), textures.get(Integer.parseInt(vertex2[1])-1),
                            normals.get(Integer.parseInt(vertex2[2])-1), Integer.parseInt(vertex2[0])-1);
                    ObjVertex v3 = new ObjVertex(vertices.get(Integer.parseInt(vertex3[0])-1), textures.get(Integer.parseInt(vertex3[1])-1),
                            normals.get(Integer.parseInt(vertex3[2])-1), Integer.parseInt(vertex3[0])-1);

                    ObjFace face = new ObjFace(v1,v2,v3,currentMat);
                    faces.add(face);
                }
            }

        }catch (Exception x){
            x.printStackTrace();
            //System.out.println();
        }
        List<ObjRawModel> models = new ArrayList<>();
        for(int i = 0;i<materials.length;i++){
            for(ObjFace f : faces){
                if(i==f.getMaterial()){
                    if(i>= models.size()){
                        List<ObjFace> l = new ArrayList<>();
                        l.add(f);
                        ObjRawModel model = new ObjRawModel(l,materials[i]);
                        models.add(i,model);
                    }else{
                        models.get(i).addFace(f);
                    }
                }
            }
        }
        List<TexturedModel> output = new ArrayList<>();
        for(ObjRawModel m : models){
            output.add(loader.getTexturedModel(m));
        }

        return output;

    }


    private static Material[] processMats(String file){

        List<Material> materials = new ArrayList<>();

        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/"+file+".mtl"));
        }catch (FileNotFoundException x){
            System.err.println("Couldn't find file");
            x.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fr);
        String line;

        //TODO: Muss erweitert werden

        String name = null;
        String path = null;
        Vector3f ambient = null;
        Vector3f diffuse = null;
        Vector3f mirrorColour = null;


        String[] namesArray = null;
        String[] pathesArray = null;
        float[] ambientsArray = null;
        float[] diffusesArray = null;
        float[] mirrorColoursArray = null;

        try{

            while (true) {

                line = reader.readLine();
                if(line == null)break;


                //System.out.println(line);
                String[] currentLine = line.split(" ");

                if (line.startsWith("newmtl ")) {

                    if(name != null){
                        Material mat = new Material(name,path,ambient,diffuse,mirrorColour);
                        materials.add(mat);
                        path = null;
                        ambient = null;
                        diffuse = null;
                        mirrorColour = null;
                    }

                    name = (currentLine[1]);

                } else if (line.startsWith("Ka ")) {

                    ambient = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    ;

                } else if (line.startsWith("Kd ")) {

                    diffuse = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));


                } else if (line.startsWith("Ks ")) {

                    mirrorColour = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));


                } else if (line.startsWith("map_Kd ")) {

                    path = currentLine[1];

                    Material mat = new Material(name,path,ambient,diffuse,mirrorColour);
                    materials.add(mat);
                    name = null;
                    ambient = null;
                    diffuse = null;
                    mirrorColour = null;


                }
                //TODO: To be continued
            }


        } catch (IOException e) {

        }

        List<Material> batch = new ArrayList<>();
        for(int i = 0; i<materials.size(); i++){
            if(i != 0 && materials.get(i-1).getName().equals(materials.get(i).getName())){
                continue;
            }else {
                batch.add(materials.get(i));
            }
        }
        Material[] output = new Material[batch.size()];
        for(int i = 0; i<batch.size();i++){
            output[i] = batch.get(i);
        }
        return output;

    }

}
