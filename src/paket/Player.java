package paket;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by levin on 18.10.2016.
 */
public class Player {

    private Vector3f pos;
    private Vector2f rotation;
    private final int SPEED = 5;

    public Player(float x, float y, float z){

        pos = new Vector3f(x,y,z);
        rotation = new Vector2f();

    }

    public void update(float delta){
        float dx = Mouse.getX() - Display.getWidth()/2;
        float dy = Mouse.getY() - Display.getHeight()/2;

        rotation.y += dx / 5f;
        rotation.x -= dy / 5f;

        Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            //System.out.println(delta);
            pos.x += Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            pos.z += Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            //System.out.println(delta);
            pos.x -= Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            pos.z -= Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }
    }

    public Vector3f getPos() {
        return pos;
    }

    public Vector2f getRotation() {
        return rotation;
    }
}

