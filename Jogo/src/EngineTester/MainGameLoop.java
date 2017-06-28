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
import java.util.Random;
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
import projetocg.VideoDTO;
import terrains.Terrain;
import textures.ModelTexture;

/**
 *
 * @author PC
 */
public class MainGameLoop {

    public static void main(String[] args) {
        int points = 10;
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        List<Entity> flowers = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();

        ModelData flower = OBJFileLoader.loadOBJ("floe");
        RawModel flowerModel = loader.loadToVAO(flower.getVertices(), flower.getTextureCoords(), flower.getNormals(), flower.getIndices());
        TextureModel flowerTextModel = new TextureModel(flowerModel, new ModelTexture(loader.loadTexture("flor")));
        //ModelTexture flowerTex = flowerTextModel.getTexture();

        ModelData boneco = OBJFileLoader.loadOBJ("male");
        RawModel bonecoModel = loader.loadToVAO(boneco.getVertices(), boneco.getTextureCoords(), boneco.getNormals(), boneco.getIndices());
        TextureModel bonecoTextureModel = new TextureModel(bonecoModel, new ModelTexture(loader.loadTexture("1024")));

        ModelData roboto = OBJFileLoader.loadOBJ(("roboto"));
        RawModel robotoModel = loader.loadToVAO(roboto.getVertices(), roboto.getTextureCoords(), roboto.getNormals(), roboto.getIndices());
        TextureModel robotoTextureModel = new TextureModel(robotoModel, new ModelTexture(loader.loadTexture("roboto")));

        float minX = -90.0f;
        float maxX =  90.0f;
        float minZ = -50.0f;
        float maxZ = -200.0f;

        Random rand = new Random();

        // float finalX = rand.nextFloat() * (maxX - minX) + minX;
        for (int i = 0; i < 10; i++) {
            flowers.add(new Entity(flowerTextModel, new Vector3f(rand.nextFloat() * (maxX - minX) + minX, 5, rand.nextFloat() * (maxZ - minZ) + minZ), 0, 0, 0, 0.3f, flower.getFurthestPoint()));
        }

        //flowers.add(new Entity(flowerTextModel, new Vector3f(0, 50, -25), 0, 0, 0, 0.1f, flower.getFurthestPoint()));
        VideoDTO video = ManipulacaoArquivos.readFile("paths\\Br8_D.txt");

        for (int i = 0; i < video.getPessoas(); i++) {
            enemies.add(new Enemy(robotoTextureModel, new Vector3f(rand.nextFloat() * (maxX - minX) + minX, 5, rand.nextFloat() * (maxZ - minZ) + minZ), 0, 0, 0, 0.3f, flower.getFurthestPoint(), video.getPaths().get(i)));
        }

        Player player = new Player(bonecoTextureModel, new Vector3f(0, 5, -100), 0, 0, 0, 0.3f, boneco.getFurthestPoint());
        // Enemy enem = new Enemy(flowerTextModel, new Vector3f(0, 50, -50), 0, 0, 0, 1, flower.getFurthestPoint(), ManipulacaoArquivos.readFile("C:\\Users\\isadorag\\Documents\\NetBeansProjects\\CompGraficaI\\Jogo\\paths\\Japan_D.txt").getPaths().get(3));

        Light light = new Light(new Vector3f(100, 100, 100), new Vector3f(1, 1, 1));

        Terrain terrain = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("floorTex")));
        Terrain terrain2 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("floorTex")));

        Camera camera = new Camera();

        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture guiWon = new GuiTexture(loader.loadTexture("won"), new Vector2f(0.0f, 0.0f), new Vector2f(0.8f, 0.8f));
        GuiTexture guiOver = new GuiTexture(loader.loadTexture("over"), new Vector2f(0.0f, 0.0f), new Vector2f(0.8f, 0.8f));
        guis.add(guiWon);
        //guis.add(guiOver);

        GuiRenderer guiRenderer = new GuiRenderer(loader);
        MasterRenderer renderer = new MasterRenderer();

        boolean loose = false;
        boolean finish = false;
        while (!Display.isCloseRequested()) {

            //entity.increaseRotation(0, 1, 0);
            //entity.increasePosition(0, 0, -0.002f);
            camera.move();
            player.move();
            //enem.move();
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            if (!finish) {
                for (int i = 0; i < flowers.size(); i++) {
                    if (points <= 0) {
                        finish = true;
                        break;
                    }                   
                    if (player.isColliding(flowers.get(i))) {
                        flowers.remove(i);
                        points--;
                    } else {
                        flowers.get(i).increaseRotation(0, 1, 0);
                        renderer.processEntity(flowers.get(i));
                    }
                }
            }

            if (!loose) {
                for (Enemy ent : enemies) {
                    if (player.isColliding(ent)) {
                        loose = true;
                        break;
                    } else {
                        ent.move();
                        renderer.processEntity(ent);
                    }
                }
            }
            renderer.processEntity(player);
            camera.move();
            renderer.render(light, camera);
            if (loose) {
                guiRenderer.render(guiOver);
            } else if (finish) {
                guiRenderer.render(guiWon);
            }
            DisplayManager.updateDisplay();
        }
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
