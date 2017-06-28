/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EngineTester;

import entities.Camera;
import entities.Enemy;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import java.util.ArrayList;
import java.util.List;
import main.DisplayManager;
import main.Loader;
import main.MasterRenderer;


import models.RawModel;
import models.TextureModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import projetocg.ManipulacaoArquivos;
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
        List<Entity> allEntities = new ArrayList<>();
        
        ModelData flower = OBJFileLoader.loadOBJ("emerald");
        RawModel flowerModel = loader.loadToVAO(flower.getVertices(), flower.getTextureCoords(), flower.getNormals(), flower.getIndices());
        TextureModel flowerTextModel = new TextureModel(flowerModel, new ModelTexture(loader.loadTexture("gem2")));
        //ModelTexture flowerTex = flowerTextModel.getTexture();
                    
        
        allEntities.add(new Entity(flowerTextModel, new Vector3f(0, 0, -25), 0, 0, 0,1 , flower.getFurthestPoint()));
        
        
        Player player = new Player(flowerTextModel, new Vector3f(0, 50, -50), 0,0,0,1, flower.getFurthestPoint());
        Enemy enem = new Enemy(flowerTextModel, new Vector3f(0, 50, -50), 0,0,0,1, flower.getFurthestPoint(),ManipulacaoArquivos.readFile("C:\\Users\\PC\\Desktop\\Paths_D.txt").getPaths().get(3));
        
        Light light = new Light(new Vector3f(100, 100, 100), new Vector3f(1, 1, 1));
        
        Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("floorTex")));
        Terrain terrain2 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("floorTex")));
        
        Camera camera = new Camera();

        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture guiWon = new GuiTexture(loader.loadTexture("won"),new Vector2f(0.0f, 0.0f), new Vector2f(0.8f, 0.8f));
        GuiTexture guiOver = new GuiTexture(loader.loadTexture("over"),new Vector2f(0.0f, 0.0f), new Vector2f(0.8f, 0.8f));
        guis.add(guiWon);
        guis.add(guiOver);
        
        
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);        
        MasterRenderer renderer = new MasterRenderer();

        
       
        while (!Display.isCloseRequested()) {
            //entity.increaseRotation(0, 1, 0);
            //entity.increasePosition(0, 0, -0.002f);
            camera.move();
            //player.move();
            enem.move();
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.processEntity(enem);
            for (Entity ent : allEntities) {
                //ent.increasePosition(0, 0, 0);
                //ent.increaseRotation(0, 1, 0);
                //renderer.processEntity(ent);
            }
            //game logic 
            renderer.render(light, camera);
            guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
