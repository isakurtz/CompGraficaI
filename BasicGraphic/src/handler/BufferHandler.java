/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import com.jogamp.common.nio.Buffers;
//import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;


import java.nio.FloatBuffer;

/**
 *
 * @author Isadora_Goncalves
 */
public class BufferHandler {
    public static void setupBuffers(int[] objectVaoHandle, float[] positionData, float[] colorData, final int vertexPositionIndex, final int colorPsitionIndex, GL4 gl ){
        //Create the buffer objets        
        int vboHandles[] = new int[2];
        gl.glGenBuffers(2, vboHandles, 0);
        
        
        //Assign the handles to descriptive vars        
        int positionBufferHandle = vboHandles[0];
        int colorBufferHandle = vboHandles[1];
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, positionBufferHandle);
        FloatBuffer positionBufferData = Buffers.newDirectFloatBuffer(positionData);
        int numBytes = positionData.length * 4;
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, numBytes, positionBufferData, GL4.GL_STATIC_DRAW);
        
        //Populate the color buffer
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, colorBufferHandle);
        FloatBuffer colorBufferData = Buffers.newDirectFloatBuffer(colorData);
        numBytes = colorData.length *4;
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, numBytes, colorBufferData, GL4.GL_STATIC_DRAW);
        
        //Create and set up the new vertex array objetc
        gl.glGenBuffers(1, objectVaoHandle, 0);
        gl.glBindVertexArray(objectVaoHandle[0]);
        
        //Enable the vertex attribute arrays
        gl.glEnableVertexAttribArray(vertexPositionIndex);
        gl.glEnableVertexAttribArray(colorPsitionIndex);
        
        //Map Index 0 to the position buffer
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, positionBufferHandle);
        gl.glVertexAttribPointer(vertexPositionIndex, 3, GL4.GL_FLOAT, Boolean.FALSE, 0 , 0 );
        
        //Map index 1 to the color buffer
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, colorBufferHandle);
         gl.glVertexAttribPointer(colorPsitionIndex, 3, GL4.GL_FLOAT, Boolean.FALSE, 0 , 0 );
        
        
    }
}
