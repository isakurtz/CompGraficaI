/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import models.TextureModel;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author PC
 */
public class Entity {
    private TextureModel model;
    private Vector3f position;
    private float rotx, roty, rotz;
    private float scale;
    private float furthestPoint;
    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;  
   
    
    public Entity(TextureModel model, Vector3f position, float rotx, float roty, float rotz, float scale, float furthestPoint) {
        this.model = model;
        this.position = position;
        this.rotx = rotx;
        this.roty = roty;
        this.rotz = rotz;
        this.scale = scale;
        this.furthestPoint = furthestPoint * scale;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z+= dz;
    }
    
    public void increaseRotation (float dx, float dy, float dz){
        this.rotx +=dx;
        this.roty += dy;
        this.rotz += dz;
    }
    
    public TextureModel getModel() {
        return model;
    }

    public float getFurthestPoint() {
        return furthestPoint;
    }

    
    public void setModel(TextureModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotx() {
        return rotx;
    }

    public void setRotx(float rotx) {
        this.rotx = rotx;
    }

    public float getRoty() {
        return roty;
    }

    public void setRoty(float roty) {
        this.roty = roty;
    }

    public float getRotz() {
        return rotz;
    }

    public void setRotz(float rotz) {
        this.rotz = rotz;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    
}
