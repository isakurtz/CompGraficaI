/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author PC
 */
public class RawModel {
    private int vaoId;
    private int VertexCount;
    
    public RawModel (int vaoId, int vertexcount){
        this.vaoId = vaoId;
        this.VertexCount = vertexcount;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return VertexCount;
    }
}
