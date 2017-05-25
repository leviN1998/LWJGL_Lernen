package ersteTests;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by levin on 29.10.2016.
 */
public class GLApplication {

    public float vertices[] = {
            -5f,-1f,5f,
            5f,-1f,5f,
            5f,-1f,-5f,
            -5f,-1f,-5f
    };

    public GLApplication(String titel, int width, int height){
        try{
            Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(width,height));
            //Display.setLocation(0,0);
            //Display.setResizable(false);
            Display.setTitle(titel);

            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }
    }



    public void start(){
        init();
        long lastFrame = System.currentTimeMillis();

        while(!Display.isCloseRequested()){
            long thisFrame = System.currentTimeMillis();
            float delta = (float)(thisFrame - lastFrame) / 1000f;
            lastFrame = thisFrame;

            update(delta);

            display();

            Display.update();
            Display.sync(60);    //sorgt für geregelte FPS mit Thread.sleep
        }

        Display.destroy();
    }

    public void init(){
        glEnable(GL_DEPTH_TEST);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(45, (float) Display.getWidth() / (float)Display.getHeight(), 0.3f, 1000f);
        glMatrixMode(GL_MODELVIEW);

        /*glFrontFace(GL_CCW); //Nur Quads im CounterClockWise werden angezeigt
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);*/


    }

    public void update(float delta){

    }

    float i = 0;
    public void display(){

        glClear(GL_COLOR_BUFFER_BIT);


        //glRotatef(0.1f, 0, 1, 0);
        glTranslatef(0f,0f,0f);




        //glDrawArrays(GL_QUADS,0,vertices.length);

        glBegin(GL_QUADS);

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



        glEnd();


    }



}
