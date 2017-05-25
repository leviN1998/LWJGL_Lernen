package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.renderEngine.Renderer;
import vbo.shaders.StaticShader;

/**
 * Created by levin on 14.11.2016.
 */
@Deprecated
public class Block {

    private Face[] faces;
    private String file;

    private Loader loader;
    private Renderer renderer;
    private StaticShader shader;

    private Vector3f position;

    public Block(Vector3f position, String file){
        this.file = file;
        this.position = position;

        loader = new Loader();

        shader = new StaticShader();
        renderer = new Renderer(shader);

        faces = new Face[6];
        for(int i = 0; i<faces.length;i++){
            faces[i] = new Face(loader, renderer, shader, file);
        }
    }

    public void initFaces(){
        faces[0].face0(position, 6);
        faces[1].face1(position, 6);
        faces[2].face2(position, 6);
        faces[3].face3(position, 6);
        faces[4].face4(position, 6);
        faces[5].face5(position, 6);

       for(int i = 0; i<faces.length;i++){
           faces[i].prepare();
       }
    }

    public void render(){
        renderer.prepare();

        for(int i = 0; i<faces.length;i++){
            faces[i].render();
        }
    }

    public void cleanUp(){
        shader.cleanUp();
        loader.cleanUp();
    }


}
