package blöcke;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import vbo.entities.Camera;

/**
 * Created by levin on 19.11.2016.
 */
public class Updater {

    Camera camera;
    public boolean escape;
    public float sensitivity = 0.2f;
    private int escapeTimer,escapeTimer2;

    public Updater(Camera c){
        this.camera = c;
    }

    public void update(){
        if(!escape) {

            float dx = Mouse.getX() - Display.getWidth() / 2;
            float dy = (Mouse.getY() - Display.getHeight() / 2) * -1;

            dx = dx * sensitivity;
            dy = dy * sensitivity;

            camera.setPitch(camera.getPitch()+dy);
            if(camera.getPitch()>90){
                camera.setPitch(camera.getPitch()-dy);
            }
            camera.setYaw(camera.getYaw()+dx);
            /*if(camera.getYaw() > 90){
                camera.setYaw(camera.getYaw()-dx);
            }*/

            Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
            escapeTimer2++;
        }else{
            escapeTimer++;
        }
        if(escape&&Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && escapeTimer>30){
            escape = false;
            Mouse.setGrabbed(true);
            Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
            escapeTimer = 0;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && escapeTimer2 > 30){
            Mouse.setGrabbed(false);
            escape = true;
            escapeTimer2 = 0;
        }



        /*
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            camera.setPitch((float)camera.getPitch()+0.4f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_J)){
            camera.setYaw(camera.getYaw()-0.4f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_K)){
            camera.setPitch((float)camera.getPitch()-0.4f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
            camera.setYaw(camera.getYaw()+0.4f);
        }
        */
    }
}
