package blöcke;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import vbo.DisplayManager;
import vbo.Loader;
import vbo.renderEngine.Renderer;
import vbo.entities.Camera;
import vbo.shaders.StaticShader;

/**
 * Created by levin on 14.11.2016.
 */
@Deprecated
public class Test2 {


    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera();

        String[] imageNames = {
                "res/Minecraft.png",
                "res/Minecraft.png",
                "res/Minecraft.png",
                "res/Minecraft.png",
                "res/Minecraft.png",
                "res/Minecraft.png"
        };

        int[] imagePositions = {
                3,
                3,
                3,
                3,
                54,
                2
        };



        Block2 block2 = new Block2(new Vector3f(-0.5f,0.5f,-2f), loader, renderer, shader, camera,imageNames, imagePositions);



        while(!Display.isCloseRequested()){

            block2.update();

            //camera.move();

            block2.render();


            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }


}
