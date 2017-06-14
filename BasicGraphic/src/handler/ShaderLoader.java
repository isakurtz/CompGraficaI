/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Isadora_Goncalves
 */
public class ShaderLoader {
    public static String loadShaderFile(String filePath){
        File f = new File(filePath);
        BufferedReader reader;
        String temp;
        String separator = System.getProperty("line.separator");
        StringBuilder shaderCode = new StringBuilder();
        
        if( !f.exists()){
            System.err.println("Could not find shader file: " + filePath);
            System.exit(1);
        }
        
        try{
            reader = new BufferedReader(new FileReader(f));
            
            while((temp = reader.readLine()) != null){
                shaderCode.append(temp);
                shaderCode.append(separator);
                
            }
            reader.close();
        }catch(Exception e){
            System.err.println("Error Reading: " + filePath + ", " + e.getMessage());
        }
        return shaderCode.toString();
    }
}
