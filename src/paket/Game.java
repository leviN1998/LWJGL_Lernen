package paket;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 * Created by levin on 18.10.2016.
 */
public class Game {

    private Player player;

    public Game(){
        player = new Player(0, 1, 5);
    }



    public void update(float delta){
        player.update(delta);
    }

    public void render(){
        glRotatef(player.getRotation().x, 1, 0, 0);
        glRotatef(player.getRotation().y, 0, 1, 0);
        glTranslatef(-player.getPos().x, -player.getPos().y, -player.getPos().z);

        glBegin(GL_QUADS);
        //Dazwischen Vertices(Punkte) ReihenFolge!!!

        glColor3f(1, 1f, 1);

        glVertex3f(0, 0, 0);
        glVertex3f(0, 0, 1);
        glVertex3f(1, 0, 1);
        glVertex3f(1, 0, 0);

        glVertex3f(0, 2, 0);
        glVertex3f(1, 2, 0);
        glVertex3f(1, 2, 1);
        glVertex3f(0, 2, 1);

        glEnd();
    }
}
