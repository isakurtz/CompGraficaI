/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import main.DisplayManager;
import models.TextureModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author PC
 */
public class Player extends Entity {

    private static final float RUN_SPEED = 20;
    private static final float TURN_SPEED = 160;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;

    public Player(TextureModel model, Vector3f position, float rotx, float roty, float rotz, float scale, float furthestPoint) {
        super(model, position, rotx, roty, rotz, scale, furthestPoint);
    }

    public void move() {
        checkInputs();
        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds() , 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRoty())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRoty())));
        super.increasePosition(dx, 0, dz);
    }

    private void checkInputs() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            this.currentTurnSpeed = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            this.currentTurnSpeed = TURN_SPEED;
        } else {
            this.currentTurnSpeed = 0;
        }
    }
    
    public boolean isColliding(Entity ent){
        // d = sqrt((X1-X2)² + (Y1-Y2)² + (Z1-Z2)²). So, if D<(R1+R2), 
        float powX = (float) Math.pow(super.getPosition().x - ent.getPosition().x, 2);
        float powY = (float) Math.pow(super.getPosition().y - ent.getPosition().y, 2);
        float powZ = (float) Math.pow(super.getPosition().z - ent.getPosition().z, 2);
        float dist = (float) Math.sqrt(powX+powY+powZ);
        return dist < super.getFurthestPoint() + ent.getFurthestPoint();
    }
}
