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


public class Renderer implements GLEventListener{

    public Renderer() {
    }

    
    @Override
    public void init(GLAutoDrawable drawable) {
        //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();
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

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      //final GL4 gl = drawable.getGL().getGL4();
        final GL2 gl = drawable.getGL().getGL2();

    }
    
    
    
}
