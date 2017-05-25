package vbo.entities;

import blöcke.project.minecraft.Collisions;
import blöcke.project.minecraft.environment.Grid;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;

/**
 * Created by levin on 14.11.2016.
 */
public class Camera {

    private Vector3f position = new Vector3f(1,0,1);
    private float pitch;
    private float yaw;
    private float roll;


    private float speed = 0.02f;      //0.02

    public Camera(){
    }

    public void move(){

    }

    public void move(Grid g){
        Vector2f[] ranges = Collisions.getMaxDistance(position, g);
        Vector2f maxPlus = ranges[0];
        Vector2f maxMinus = ranges[1];

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            //position.z -= 0.02f;
            //position.z -= (Math.cos(Math.toRadians(yaw))*speed);
            //position.x += (Math.sin(Math.toRadians(yaw))*speed);

            move(2, (float)-(Math.cos(Math.toRadians(yaw))*speed), maxPlus.getY(), maxMinus.getY());
            move(0, (float)(Math.sin(Math.toRadians(yaw))*speed), maxPlus.getX(), maxMinus.getX());


            /*move((float)-(Math.cos(Math.toRadians(yaw))*speed), g, 2);
            move((float)(Math.sin(Math.toRadians(yaw))*speed), g, 0);*/
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            //position.x += 0.02f;
            position.x += (Math.cos(Math.toRadians(yaw))*speed);
            position.z += (Math.sin(Math.toRadians(yaw))*speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            //position.x -= 0.02f;
            position.x -= (Math.cos(Math.toRadians(yaw))*speed);
            position.z -= (Math.sin(Math.toRadians(yaw))*speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            //position.z += 0.02f;
            position.z += (Math.cos(Math.toRadians(yaw))*speed);
            position.x -= (Math.sin(Math.toRadians(yaw))*speed);
        }

    }

    public void update(Grid g){
        //erst nach dem grid Update benutzen
        if (Collisions.updateCameraSides(position, g)){
            g.update(position);
            System.out.println("Kollision");
        }
    }

    @Deprecated
    private void move(float distance, Grid g, int xyz){
        Vector3f testing = new Vector3f();
        testing.x = position.x;
        testing.y = position.y;
        testing.z = position.z;

        if(xyz == 0){
            testing.setX(testing.getX()+distance);
            g.update(testing);
        }else if(xyz == 1){
            testing.y += distance;
            g.update(testing);
        }else if(xyz == 2){
            testing.z += distance;
            g.update(testing);
        }
        if(!Collisions.updateCameraSides(testing, g)){
            position = testing;
        }else {
            System.out.println("Kollision");
        }

    }

    private void move(int xyz, float range, float maxP, float maxM){
        if(xyz == 0){
            if(range <= maxP && range >= maxM){
                position.x += range;
            }else if(range > maxP){
                //position.x -= maxP;
            }else if(range < maxM){
                //position.x -= maxM;
            }
        }
        if(xyz == 1){
            if(range <= maxP && range >= maxM){
                position.y += range;
            }else if(range > maxP){
                position.y -= maxP;
            }else if(range < maxM){
                position.y -= maxM;
            }
        }
        if(xyz == 2){
            if(range <= maxP && range >= maxM){
                position.z += range;
            }else if(range > maxP){
                //position.z -= maxP;
            }else if(range < maxM){
                //position.z -= maxM;
            }
        }
    }



    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
