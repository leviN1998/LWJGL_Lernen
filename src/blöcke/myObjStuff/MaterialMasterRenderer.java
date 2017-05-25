package blöcke.myObjStuff;

import vbo.entities.Camera;
import vbo.entities.Entity;
import vbo.entities.Light;
import vbo.models.TexturedModel;
import vbo.renderEngine.Renderer;
import vbo.shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by levin on 13.05.2017.
 */
public class MaterialMasterRenderer {

        private StaticShader shader = new StaticShader();
        private MaterialRenderer renderer = new MaterialRenderer(shader);

        private List<Map<TexturedModel, List<Entity>>> entities = new ArrayList<>();

        public void render(Light sun, Camera camera){
            renderer.prepare();
            shader.start();
            shader.loadLight(sun);
            shader.loadViewMatrix(camera);
            renderer.render(entities);
            shader.stop();
            entities.clear();
        }

        public void processEntity(ObjEntity entity){





        }



        public void cleanUp(){
            shader.cleanUp();
        }



}
