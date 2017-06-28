/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author PC
 */
public class Camera {

    private Vector3f position = new Vector3f(0, 100, 0);
    private float pitch = 45;
    private float yaw;
    private float roll;

    public Camera() {
    }

    public void move() {
        
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
