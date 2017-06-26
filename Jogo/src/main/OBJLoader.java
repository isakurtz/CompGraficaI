/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.RawModel;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author PC
 */
public class OBJLoader {

    public static RawModel loadObjModel(String fileName, Loader loader) {
        String line;
            List<Vector3f> vertices = new ArrayList<>();
            List<Vector2f> textures = new ArrayList<>();
            List<Vector3f> normals = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            float[] verticesArray = null;
            float[] normalsArray = null;
            float[] texturesArray = null;
            int[] indicesArray = null;
        try (FileReader fr = new FileReader(new File("res/" + fileName + ".obj")); BufferedReader buff = new BufferedReader(fr)) {
           
            while (true) {
                line = buff.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);
                } else if (line.startsWith("vt ")) {
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if (line.startsWith("f ")) {
                    texturesArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }
            while (line != null) {
                if (!line.startsWith("f ")) {
                    line = buff.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
                processVertex(vertex1, indices, textures, normals, texturesArray, normalsArray);
                processVertex(vertex2, indices, textures, normals, texturesArray, normalsArray);
                processVertex(vertex3, indices, textures, normals, texturesArray, normalsArray);
                line = buff.readLine();
            }

        } catch (IOException ex) {
            System.err.print("Couldn't load file!");
            ex.printStackTrace();
        }
        verticesArray = new float[vertices.size()*3];
        indicesArray = new int[indices.size()];
        int vertexPointer = 0;
        
        for(Vector3f vertex : vertices){
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;            
        }
        
        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);            
        }
        return loader.loadToVAO(verticesArray, texturesArray, normalsArray,indicesArray);
    }

    private static void processVertex(String[] vertexData, List<Integer> indices,
            List<Vector2f> textures, List<Vector3f> normals, float[] textureArray,
            float[] normalsArray) {
        
        int currentVexterPointer = Integer.parseInt(vertexData[0]) -1;
        indices.add(currentVexterPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
        textureArray[currentVexterPointer*2] = currentTex.x;
        textureArray[currentVexterPointer*2+1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
        normalsArray[currentVexterPointer*3] = currentNorm.x;
        normalsArray[currentVexterPointer*3+1] = currentNorm.y;
        normalsArray[currentVexterPointer*3+2] = currentNorm.z;
    }
}
