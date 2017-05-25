package kapitel2;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by levin on 25.10.2016.
 */
public class Example1 {
    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        glTranslatef(0f,-1f,-5f);
        glScalef(1f,1f,1f);
        glRotatef(0f,0f,0f,1f);


        glPushMatrix();

        glBegin(GL_QUADS);

        glVertex3f(0, 0, 0);
        glVertex3f(0, 0, 1);
        glVertex3f(1, 0, 1);
        glVertex3f(1, 0, 0);

        glVertex3f(0, 2, 0);
        glVertex3f(1, 2, 0);
        glVertex3f(1, 2, 1);
        glVertex3f(0, 2, 1);

        glEnd();

        glPopMatrix();



    }


}
