/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EngineTester;

import main.DisplayManager;
import main.Loader;
import models.RawModel;
import main.Render;
import models.TextureModel;
import org.lwjgl.opengl.Display;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 *
 * @author PC
 */
public class MainGameLoop {

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Render renderer = new Render();
        StaticShader shader = new StaticShader();

        float[] vertices = {
            -0.5f, 0.5f, 0,
            -0.5f, -0.5f, 0,
            0.5f, -0.5f, 0,
            0.5f, 0.5f, 0
        };

        int[] indices = {
            0, 1, 3,
            3, 1, 2
        };

        float[] textureCoords = {
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("datboi"));
        TextureModel textureModel = new TextureModel(model, texture);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            //game logic 
            renderer.render(textureModel);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
