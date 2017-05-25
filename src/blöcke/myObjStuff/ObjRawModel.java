package blöcke.myObjStuff;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 13.05.2017.
 */
public class ObjRawModel {
    List<ObjFace> faces;
    Material material;

    private float[] vertices;
    private float[] textureCoords;
    private float[] normals;
    private int[] indices;

    public ObjRawModel(List<ObjFace> faces, Material mat){
        this.faces = faces;
        this.material = mat;
        processArrays();
    }

    public void addFace(ObjFace face){
        faces.add(face);
    }

    private void processArrays(){
        List<Float> vertList = new ArrayList<>();
        List<Float> vtList = new ArrayList<>();
        List<Float> normalList = new ArrayList<>();
        List<Integer> indList = new ArrayList<>();

        for (ObjFace f : faces){
            for(int i = 0;i<f.getVertices().length;i++) {
                vertList.add(f.getVertices()[i]);
            }
            for(int i = 0; i<f.getTextureCoords().length;i++){
                vtList.add(f.getTextureCoords()[i]);
            }
            for(int i = 0;i<f.getNormals().length;i++){
                normalList.add(f.getNormals()[i]);
            }
            for (int i = 0; i<f.getIndices().length;i++){
                indList.add(f.getIndices()[i]);
            }
        }

        vertices = new float[vertList.size()];
        for(int i = 0; i<vertList.size();i++){
            vertices[i] = vertList.get(i);
        }

        textureCoords = new float[vtList.size()];
        for(int i = 0;i<vtList.size();i++){
            textureCoords[i] = vtList.get(i);
        }

        normals = new float[normalList.size()];
        for(int i = 0; i<normalList.size();i++){
            normals[i] = normalList.get(i);
        }

        indices = new int[indList.size()];
        for (int i = 0; i<indList.size();i++){
            indices[i] = indList.get(i);
        }
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextureCoords() {
        return textureCoords;
    }

    public float[] getNormals() {
        return normals;
    }

    public int[] getIndices() {
        return indices;
    }
}
