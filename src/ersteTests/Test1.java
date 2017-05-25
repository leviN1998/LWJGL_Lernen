package ersteTests;

import blöcke.Block;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by levin on 29.10.2016.
 */
public class Test1 extends GLApplication{
    public Vector2f rotation = new Vector2f();
    public Vector3f position = new Vector3f();
    private final int SPEED = 5;
    Block block1;

    public Test1(String titel, int width, int height) {
        super(titel, width, height);
        position.x = 0;
        position.y = 1;
        position.z = 5;
        block1 = new Block(new Vector3f(-0.5f,0.5f,0f), "res/Minecraft.png");
        block1.initFaces();
    }

    @Override
    public void update(float delta){
        if(Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(true);


            float dx = Mouse.getX() - Display.getWidth() / 2;
            float dy = Mouse.getY() - Display.getHeight() / 2;

            rotation.y += dx /5f;
            rotation.x -= dy /5f;
            Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.x += Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            position.z += Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.x -= Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            position.z -= Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }

    }

    @Override
    public void display(){
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glPushMatrix();

        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);


        glTranslatef(-position.x, -position.y, -position.z);



        block1.render();
        //glDrawArrays(GL_QUADS,0,vertices.length);

        /*glBegin(GL_QUADS);

        glColor3f(1f,1f,1f);

        glVertex3f(0, 0, 0);
        glVertex3f(0, 1, 0);
        glVertex3f(1, 1, 0);
        glVertex3f(1, 0, 0);

        glColor3f(1f,0f,1f);

        glVertex3f(0, 1, 0);
        glVertex3f(1, 1, 0);
        glVertex3f(1, 1, -1);
        glVertex3f(0, 1, -1);

        glColor3f(0,1,0);
        glDrawArrays(GL_QUADS,0,vertices.length);


        glColor3f(1, 1f, 1);

        glVertex3f(0, 0, 0);
        glVertex3f(0, 0, 1);
        glVertex3f(1, 0, 1);
        glVertex3f(1, 0, 0);

        glVertex3f(0, 2, 0);
        glVertex3f(1, 2, 0);
        glVertex3f(1, 2, 1);
        glVertex3f(0, 2, 1);*/



        glEnd();
        glPopMatrix();
    }

    public static void main(String[] args){
        Test1 test1 = new Test1("Test2", 800,500);
        test1.start();
    }
}
