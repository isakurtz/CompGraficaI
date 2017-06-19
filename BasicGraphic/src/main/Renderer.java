/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import handler.BufferHandler;
import handler.ShaderHandler;
import shapes.Triangle;


public class Renderer implements GLEventListener{

    private int[] triangleVaoHandle = new int[1];
    public Renderer() {
    }

    
    @Override
    public void init(GLAutoDrawable drawable) {
        //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();
        
        //create shader objects 
        int vertexShader = ShaderHandler.createShader("shaders/vertex_shader.glsl", GL2.GL_VERTEX_SHADER,gl);
        int fragmentShader = ShaderHandler.createShader("shaders/vertex_shader.glsl", GL2.GL_VERTEX_SHADER,gl);
        
        int[] shaderList = {vertexShader, fragmentShader};
        
        int programHandle = ShaderHandler.createProgram(shaderList, gl);
        
        final int VERTEX_POSITION_INDEX = 0;
        final int VERTEX_COLOR_INDEX = 1;
        
        Triangle triangle = new Triangle();
        BufferHandler.setupBuffers(triangleVaoHandle, triangle.getPositionData(), triangle.getColorData(), VERTEX_POSITION_INDEX, VERTEX_COLOR_INDEX, gl);
        
        ShaderHandler.linkProgram(programHandle, gl);
        
        gl.glUseProgram(programHandle);
        
        
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBindVertexArray(triangleVaoHandle[0]);
        gl.glDrawArrays(GL2.GL_TRIANGLES, 0, 3);
        gl.glFlush();

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();

    }
    
    
    
}
