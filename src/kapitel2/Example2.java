package kapitel2;

import jdk.internal.org.objectweb.asm.ByteVector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by levin on 28.10.2016.
 */
public class Example2 {
    private float aX = 1f;
    private float aY = 1f;
    private float y = 0f;
    private float sf = 1f / (float)Math.sqrt(2);
    private float angle = 15f;
    private float up = 1f;

    private Dimension viewPortSize;

    //Zahl der Bytes pro float
    private final static int SIZE_OF_FLOAT = 4;

    //Anzahl der Komponenten(Koordinaten) eines Vertex
    private static final int VERTEX_COMPONENT_COUNT = 3;

    //Definiert ein Rechteck
    private static float vertices[] = {
            0f,0f,0f, 0f,1f,0f, 1f,1f,0f, 1f,0f,0f
    };

    //Direkter Puffer für die Vertices
    private static FloatBuffer vertexArray;

    private Example2(){};

    public Example2(Dimension canvasSize){

        viewPortSize = new Dimension(canvasSize);

        if(canvasSize.height<canvasSize.width){
            aY = (float)canvasSize.height/(float)canvasSize.width;
        }else{
            aX = (float)canvasSize.width/(float)canvasSize.height;
        }

    }

    public void init(){
        initializeQuads();

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GL11.glViewport(0,0,1920,1080);
        GL11.glOrtho(-10f*aX,10f*aX,-10f*aY,10f*aY,1f,-1f);
    }

    public void display(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glPushMatrix();

        GL11.glTranslatef(2f,y,0f);
        GL11.glScalef(3f,3f,1f);

        GL11.glPushMatrix();

        GL11.glTranslatef(-0.5f,-0.5f,0f);
        GL11.glColor3f(0f,0.3f,1f);
        drawQuad();
        GL11.glPopMatrix();

        GL11.glScalef(sf,sf,1f);
        GL11.glRotatef(angle,0f,0f,1f);

        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5f,-0.5f,0f);
        GL11.glColor3f(1f,0.3f,0.3f);
        drawQuad();
        GL11.glPopMatrix();

        GL11.glPopMatrix();

        //Einstellung der BewegungsParameter

        angle += 7.5f;
        if(angle>360f) angle = 0f;

        y = y + up*0.1f;
        if(y>=1f){
            up = -1f;
        } else {
            if(y<=-1f){
                up = 1f;
            }
        }
    }

    private void drawQuad(){
        GL11.glDrawArrays(GL11.GL_QUADS,0,vertexArray.limit());
    }

    private void initializeQuads(){
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE_OF_FLOAT*vertices.length);
        bb.order(ByteOrder.nativeOrder());
        vertexArray = bb.asFloatBuffer();
        vertexArray.put(vertices,0,vertices.length);
        vertexArray.flip();

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glVertexPointer(VERTEX_COMPONENT_COUNT,GL11.GL_FLOAT,vertexArray);
    }

}
