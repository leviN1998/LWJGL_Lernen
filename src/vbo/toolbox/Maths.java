package vbo.toolbox;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import vbo.entities.Camera;

/**
 * Created by levin on 14.11.2016.
 */
public class Maths{

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx,
                                                      float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1,0,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0,1,0), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;

    }

    public static Vector2f getNormalizedDeviceCoords(Vector2f displayCoords){
        float vecX = (2f*displayCoords.x) / Display.getWidth() - 1f;
        float vecY = (2f*displayCoords.y) / Display.getHeight() - 1f;
        return new Vector2f(vecX,vecY);
    }

    public static Vector2f getDisplayCoords(Vector2f ndc){
        float x = ((ndc.x+1) * Display.getWidth()) / 2;
        float y = ((ndc.y+1) * Display.getHeight()) / 2;
        return new Vector2f(x,y);
    }

    public static float getBetragOf(float zahl){
        if(zahl < 0){
            zahl = zahl * (-1);
        }
        return zahl;
    }

    public static float getHigher(Vector2f v){
        if(v.x < v.y){
            return v.y;
        }else {
            return v.x;
        }
    }

    public static float getLower(Vector2f v){
        if(v.x < v.y){
            return v.x;
        } else {
            return v.y;
        }
    }

    public static int getNext15(int zahl){
        for (int i = 0;i<8;i++) {
            if ((zahl + i) % 15 == 0) {
                return zahl + i;
            }
            if ((zahl - i) % 15 == 0) {
                return zahl - i;
            }
        }

        System.err.println("Couldn't find next 15-row");
        System.exit(-1);
        return 0;
    }

}
