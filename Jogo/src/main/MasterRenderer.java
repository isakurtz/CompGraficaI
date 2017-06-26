/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import entities.Camera;
import entities.Entity;
import entities.Light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.TextureModel;
import shaders.StaticShader;

/**
 *
 * @author PC
 */
public class MasterRenderer {
    private StaticShader shader = new StaticShader();
    private Render render = new Render(shader);
    
    private Map<TextureModel, List<Entity>> entities = new HashMap<TextureModel, List<Entity>>();
    
    public void render (Light sun, Camera camera){
        render.prepare();
        shader.start();
        shader.loadLigt(sun);
        shader.loadViewMatrix(camera);
        render.render(entities);
        shader.stop();
        entities.clear();    
    }
    
    public void processEntity(Entity entity){
        TextureModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!= null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
        
    }
    
    public void cleanUp(){
        shader.cleanUp();
    }
}
