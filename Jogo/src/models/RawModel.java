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
    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;
    

    public RawModel(int vaoId, int VertexCount, float xMax, float xMin, float yMax, float yMin, float zMax, float zMin) {
        this.vaoId = vaoId;
        this.VertexCount = VertexCount;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        this.zMax = zMax;
        this.zMin = zMin;
    }    

    public RawModel (int vaoId, int vertexcount){
        this.vaoId = vaoId;
        this.VertexCount = vertexcount;
    }

    public float getxMax() {
        return xMax;
    }

    public float getxMin() {
        return xMin;
    }

    public float getyMax() {
        return yMax;
    }

    public float getyMin() {
        return yMin;
    }

    public float getzMax() {
        return zMax;
    }

    public float getzMin() {
        return zMin;
    }

    
    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return VertexCount;
    }
}
