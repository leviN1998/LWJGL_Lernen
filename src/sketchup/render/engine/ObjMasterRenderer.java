package sketchup.render.engine;


import sketchup.files.ObjEntity;
import vbo.entities.Camera;
import vbo.entities.Light;
import vbo.shaders.StaticShader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 05.06.2017.
 */
public class ObjMasterRenderer {

    private ObjRenderer renderer;
    private List<ObjEntity> toRender;
    private StaticShader shader;

    public ObjMasterRenderer(){
        shader = new StaticShader();
        renderer = new ObjRenderer(shader);
        toRender = new ArrayList<>();
    }


    public void processEntity(ObjEntity e){
        //TODO: Optimieren
        toRender.add(e);
    }


    public void render(Light sun, Camera camera){
        renderer.prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(toRender);
        shader.stop();

        toRender = new ArrayList<>();
    }


    public void cleanUp(){
        shader.cleanUp();
    }
}
