package kapitel2;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by levin on 25.10.2016.
 */
public class Frame {
    public static void main(String[] args){
        try{
            Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(1920,1080));
            //Display.setLocation(0,0);
            //Display.setResizable(false);
            Display.setTitle("Titel");
            //Display.setParent(c);
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }

        Example1 example1 = new Example1();
        Example2 example2 = new Example2(new Dimension(1920,1080));

        example2.init();
        initGl();


        long lastFrame = System.currentTimeMillis();

        while(!Display.isCloseRequested()){
            long thisFrame = System.currentTimeMillis();
            float delta = (float)(thisFrame - lastFrame) / 1000f;
            lastFrame = thisFrame;

            example2.display();


            Display.update();
            Display.sync(60);    //sorgt für geregelte FPS mit Thread.sleep
        }

        Display.destroy();
    }

    public static void initGl(){
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(45, (float) Display.getWidth() / (float)Display.getHeight(), 0.3f, 1000f);
        glMatrixMode(GL_MODELVIEW);

    }
}
