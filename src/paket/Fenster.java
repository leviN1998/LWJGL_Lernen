package paket;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.*;

/**
 * Created by levin on 17.10.2016.
 */
public class Fenster {   //Main methode und FRame() wir erstellt



    public static void main(String[] args){
        Canvas c = new Canvas();
        try{
            Display.setDisplayMode(new DisplayMode(800, 500));
            //Display.setLocation(0,0);
            //Display.setResizable(false);
            Display.setTitle("Titel");
            //Display.setParent(c);
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }

        Frame f = new Frame();

        long lastFrame = System.currentTimeMillis();

        while(!Display.isCloseRequested()){
            long thisFrame = System.currentTimeMillis();
            float delta = (float)(thisFrame - lastFrame) / 1000f;
            lastFrame = thisFrame;

            f.update(delta);

            f.render();

            Display.update();
            Display.sync(60);    //sorgt für geregelte FPS mit Thread.sleep
        }

        Display.destroy();
    }
}
