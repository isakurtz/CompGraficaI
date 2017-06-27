/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import java.util.List;
import main.Loader;
import models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Isadora_Goncalves
 */
public class GuiRenderer {

    private final RawModel quad;

    public GuiRenderer(Loader loader) {
        float[] positions = {-1, 1, -1, -1, 1 ,1, 1, -1};
        quad = loader.LoadToVAO(positions);
    }

    
    public void render(List<GuiTexture> guis){
        GL30.glBindVertexArray(quad.getVaoId());
        GL20.glEnableVertexAttribArray(0);
        //remder
        for (GuiTexture gui : guis) {
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
