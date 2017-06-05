package sketchup.loaders;


import sketchup.files.ObjFace;
import sketchup.files.RawMaterial;
import sketchup.files.RawObjModel;
import vbo.Loader;
import vbo.models.TexturedModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 05.06.2017.
 */
public class SketchUpLoader {

    private MasterLoader loader;


    public SketchUpLoader(Loader loader){
        this.loader = new MasterLoader(loader);
    }

    public List<TexturedModel> loadSketchUpFile(String path){
        List<TexturedModel> output = new ArrayList<>();
        List<ObjFace>[] faces = null;
        List<RawMaterial> mats = null;
        try {
            faces = ObjLoader.loadObj(path);
        } catch (FileNotFoundException e) {
            System.err.println("Die .obj Datei konnte nict gefunden werden");
            System.exit(-1);
        }
        try {
            mats = MtlLoader.loadFile(path);
        } catch (FileNotFoundException e) {
            System.err.println("Die .mtl Datei konnte nicht gefunden werden");
            System.exit(-1);
        }
        RawObjModel[] models = loader.loadModel(faces,mats, path);
        for (int i = 0;i<models.length;i++){
            output.add(loader.loadObjToVao(models[i]));
        }
        return output;
    }

    public void cleanUp(){
        loader.cleanUp();
    }
}
