/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import com.jogamp.opengl.GL2;


/**
 *
 * @author Isadora_Goncalves
 */
public class ShaderHandler {
    public static int createShader(String shaderPath, int shaderType, GL2 gl){
        //create sahder objecgt
        
        int shader = gl.glCreateShader(shaderType);
        
        if(shader == 0 ){
            System.err.println("Error creating shader");
            System.exit(1);
        }
        
        //copy the shader source code into shader object
        
        String shaderCode = ShaderLoader.loadShaderFile(shaderPath);
        gl.glShaderSource(shader, 1, new String[]{shaderCode}, null);
        
        // Compare the shader 
        
        gl.glCompileShader(shader);
        
        //verify shader compiled succesfuly
        
        int result[] = new int[1];
        gl.glGetShaderiv(shader, GL2.GL_COMPILE_STATUS, result, 0);
        
        if(result[0] == GL2.GL_FALSE){
            System.err.println("Shader compilatin failde" + shaderPath);
            
            int logLenght[] = new int[1];
            gl.glGetShaderiv(shader, GL2.GL_INFO_LOG_LENGTH, logLenght, 0);
            if(logLenght[0] > 0 ){
                byte[] log = new byte[logLenght[0]];
                gl.glGetShaderInfoLog(shader, logLenght[0], (int[]) null , 0, log, 0);
                System.out.println("Shader log: " + new String(log));
            }            
            System.exit(1);
        }        
        return  shader;
    }
    
    public static int createProgram(int shaderList[], GL2 gl){
        int programHandle = gl.glCreateProgram();
        
        if(programHandle == 0){
            System.err.println("Error creating program object");
            System.exit(1);
        }
        
        for (int i : shaderList) {
            gl.glAttachShader(programHandle, i);
        }
        
        return programHandle;
    }
    
    public static void linkProgram( int programHandle, GL2 gl){
        gl.glLinkProgram(programHandle);
        
        int status[] = new int[1];
        gl.glGetProgramiv(programHandle, GL2.GL_LINK_STATUS, status, 0);
        if(status[0] == GL2.GL_FALSE){
            System.err.println("Failde to link shaner progrma");
            int logLength[] = new int[1];
            gl.glGetProgramiv(programHandle, GL2.GL_INFO_LOG_LENGTH, logLength, 0);
            
            if (logLength[0]>1) {
                byte[] log = new byte[logLength[0]];
                gl.glGetProgramInfoLog(programHandle, logLength[0], (int[]) null, 0, log, 0);
                System.out.println("Program LOg: " + new String(log));
            }
            
            System.exit(1);
        }        
        
    }
}

