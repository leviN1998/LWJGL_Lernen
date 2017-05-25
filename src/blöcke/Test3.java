package blöcke;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import vbo.DisplayManager;
import vbo.Loader;
import vbo.renderEngine.MasterRenderer;
import vbo.renderEngine.Renderer;
import vbo.entities.Camera;
import vbo.entities.Entity;
import vbo.entities.Light;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.shaders.StaticShader;
import vbo.textures.ModelTexture;
import vbo.toolbox.objConverter.ModelData;
import vbo.toolbox.objConverter.OBJFileLoader;

/**
 * Created by levin on 19.11.2016.
 */
public class Test3 {
    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera();
        Updater updater = new Updater(camera);


        Block3[] boden = new Block3[36];
        int ii = 0;
        for(int z = -3;z<3;z++) {
            for(int x = -3; x<3; x++) {
                boden[ii++] = new Block3(new Vector3f((float) x/2, -1, (float) z/2), 1, loader, 0, 0, 0, 1);
            }
        }

        /**
         * Das ist neu dank dem MasterRenderer
         */
        MasterRenderer masterRenderer = new MasterRenderer();



        Block3 block3 = new Block3(new Vector3f(0,1,0), 1, loader, 0, 0, 0, 1);

        Block5 block5 = new Block5(IDManager3.GRASS_ID, new Vector3f(0,0,0), loader);

        ModelData modelData = OBJFileLoader.loadOBJ("Baum2");
        RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(),
                modelData.getNormals(), modelData.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("Baum2Texture"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Entity entity = new Entity(texturedModel, new Vector3f(0, -2,-20),0,0,0,1);
        Light light = new Light(new Vector3f(0,0,-10), new Vector3f(1,1,1));

        Mouse.setGrabbed(true);


        while(!Display.isCloseRequested()){
            updater.update();

            block3.increasePosition(0,0,0);
            //camera.move();
            block3.increaseRotation(0,0,0);
            /*renderer.prepare();

            shader.start();
            shader.loadViewMatrix(camera);
            shader.disableLighting(true);
            shader.loadLight(light);
            for(int i = 0;i<block3.getEntities().length;i++){
                renderer.render(block3.getEntities()[i], shader);
            }
            for(int i = 0; i<boden.length;i++){
                for(int o = 0; o<boden[i].getEntities().length;o++){
                    renderer.render(boden[i].getEntities()[o], shader);
                }
            }
            shader.disableLighting(false);
            renderer.render(entity, shader);
            shader.stop();*/

            /**
             * Das sind jz die einzig nötigen Schritte zum Rendern
             * Der Master Renderer erledigt den Rest
             *
             * Funktioniert leider noch nicht
             */
            masterRenderer.processEntity(entity);
            masterRenderer.processEntity(block5.getEntity());

            masterRenderer.render(light, camera);

            //Dannach is fertig

            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        masterRenderer.cleanUp();
        DisplayManager.closeDisplay();
    }


}
