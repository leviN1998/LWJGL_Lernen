package paket;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by levin on 18.10.2016.
 */
public class Frame {

    private Game game;
    public Frame(){
        initGL();
        game = new Game();

    }

    public void update(float delta){
        game.update(delta);
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glPushMatrix();

        game.render();

        glPopMatrix();
    }

    public void initGL(){
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(45, (float) Display.getWidth() / (float)Display.getHeight(), 0.3f, 1000f);
        glMatrixMode(GL_MODELVIEW);

        glFrontFace(GL_CCW); //Nur Quads im CounterClockWise werden angezeigt
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);

        Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
        Mouse.setGrabbed(true);
    }
}
