package blöcke;

import blöcke.myObjStuff.ObjLoader;
import blöcke.myObjStuff.ObjVaoLoader;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import vbo.Loader;
import vbo.models.TexturedModel;
import vbo.toolbox.Maths;
import vbo.toolbox.OBJLoader;
import vbo.toolbox.Triangle;

import java.util.List;


/**
 * Created by levin on 17.02.2017.
 */
public class Testing {
    public static void main(String[] args){
        Loader loader = new Loader();
        ObjVaoLoader vaoLoader = new ObjVaoLoader(loader);
        List<TexturedModel> models = ObjLoader.loadObjModel("objTest/Test", "objTest/Test", vaoLoader);
        System.out.println(models.get(0).getRawModel().getVertexCount());
    }
}
