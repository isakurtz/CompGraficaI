/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EngineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import java.util.ArrayList;
import java.util.List;
import main.DisplayManager;
import main.Loader;
import main.MasterRenderer;
import main.OBJLoader;
import models.RawModel;
import models.TextureModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import terrains.Terrain;
import textures.ModelTexture;

/**
 *
 * @author PC
 */
public class MainGameLoop {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();

        RawModel model = OBJLoader.loadObjModel("dragon", loader);

        TextureModel textureModel = new TextureModel(model, new ModelTexture(loader.loadTexture("green")));

        ModelTexture texture = textureModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        List<Entity> allEntities = new ArrayList<>();
        Entity entity = new Entity(textureModel, new Vector3f(0, 0, -25), 0, 0, 0, 1);

        allEntities.add(entity);
        Light light = new Light(new Vector3f(200, 200, 100), new Vector3f(1, 1, 1));
        
        Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grassy2")));
        Terrain terrain2 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grassy2")));
        
        Camera camera = new Camera();

        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("socuwan"),new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
        guis.add(gui);
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        
        MasterRenderer renderer = new MasterRenderer();

        while (!Display.isCloseRequested()) {
            //entity.increaseRotation(0, 1, 0);
            //entity.increasePosition(0, 0, -0.002f);
            camera.move();
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for (Entity ent : allEntities) {
                ent.increasePosition(0.002f, 0, -0.002f);
                renderer.processEntity(ent);
            }
            //game logic 
            renderer.render(light, camera);
            guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
