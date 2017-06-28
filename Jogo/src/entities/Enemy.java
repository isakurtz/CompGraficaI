/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.awt.Point;
import java.util.List;
import main.DisplayManager;
import models.TextureModel;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author PC
 */
public class Enemy extends Entity{

    private static final float RUN_SPEED = 20;
    private static final float TURN_SPEED = 160;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    
    private int next =-1;
    private int listPos = 0;
    private Point currentPos;
    List<Point> path;
    public Enemy(TextureModel model, Vector3f position, float rotx, float roty, float rotz, float scale, List<Point> path) {
        super(model, position, rotx, roty, rotz, scale);
        this.path = path;
        currentPos = path.get(0);
        
    }
    
    public void move(){
        if(listPos-1 < 0 || listPos+1 == path.size()){
            next*=-1;
        }
        int nextPos = listPos+next;
        checkInputs(path.get(nextPos));
        
        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds() , 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRoty())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRoty())));
        super.increasePosition(dx, 0, dz);
        currentPos = path.get(nextPos);
        listPos = nextPos;        
    }
    
    private void checkInputs(Point nextPosition) {
        if (currentPos.getY() < nextPosition.getY()) {
            this.currentSpeed = RUN_SPEED;
        } else if (currentPos.getY() > nextPosition.getY()) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (currentPos.getX()< nextPosition.getX()) {
            this.currentTurnSpeed = -TURN_SPEED;
        } else if (currentPos.getX() > nextPosition.getX()) {
            this.currentTurnSpeed = TURN_SPEED;
        } else {
            this.currentTurnSpeed = 0;
        }
    }
    
    
    
}
