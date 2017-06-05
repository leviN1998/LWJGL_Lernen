package sketchup.loaders;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import sketchup.files.ObjFace;
import sketchup.files.RawMaterial;
import sketchup.files.RawObjModel;
import vbo.Loader;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 04.06.2017.
 */
public class MasterLoader {

    Loader loader;

    public MasterLoader(Loader loader){
        this.loader = loader;
    }

    public ModelTexture loadMaterial(RawMaterial mat, String path){
        if(mat.isHasFile()){
            ModelTexture texture = new ModelTexture(loader.loadTextureJpg(path+"/"+mat.getName()));
            return texture;
        }else{
            ModelTexture texture = new ModelTexture(false);
            return texture;
        }
    }

    public TexturedModel loadObjToVao(RawObjModel model){
        RawModel rawModel = loader.loadToVAO(model.getPositions(), model.getTexturecoords(), model.getNormals(),
                generateInices(model.getPositions().length/3));
        TexturedModel texturedModel = new TexturedModel(rawModel, model.getTexture());
        return texturedModel;
    }

    public int[] generateInices(int max){
        int[] output = new int[max];
        for (int i = 0; i<max;i++){
            output[i] = i;
        }
        return output;
    }



    public RawObjModel[] loadModel(List<ObjFace>[] faces, List<RawMaterial> materials, String path){
        RawObjModel[] output = new RawObjModel[faces.length];
        for(int i = 0; i<faces.length;i++){
            output[i] = loadModel(faces[i], materials.get(faces[i].get(0).getMatID()), path);
        }
        return output;
    }

    private RawObjModel loadModel(List<ObjFace> faces, RawMaterial material, String path){
        List<Vector3f> positions = new ArrayList<>();
        List<Vector2f> vts = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        for(ObjFace face : faces){
            positions.add(face.getV1().getPosition());
            positions.add(face.getV2().getPosition());
            positions.add(face.getV3().getPosition());

            vts.add(face.getV1().getVt());
            vts.add(face.getV2().getVt());
            vts.add(face.getV3().getVt());

            normals.add(face.getV1().getNormal());
            normals.add(face.getV2().getNormal());
            normals.add(face.getV3().getNormal());
        }
        return new RawObjModel(positions, normals, vts, material, this, path);
    }



    public void cleanUp(){
        loader.cleanUp();
    }
}
