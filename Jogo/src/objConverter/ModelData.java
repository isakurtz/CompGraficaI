package objConverter;
 
public class ModelData {
 
    private float[] vertices;
    private float[] textureCoords;
    private float[] normals;
    private int[] indices;
    private float furthestPoint;
    private float xMax;
    private float xMin;
    private float yMax;
    private float yMin;
    private float zMax;
    private float zMin;

    public ModelData() {
    }
 
//    public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices,
//            float furthestPoint) {
//        this.vertices = vertices;
//        this.textureCoords = textureCoords;
//        this.normals = normals;
//        this.indices = indices;
//        this.furthestPoint = furthestPoint;
//    }

    public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, float furthestPoint) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;
        this.furthestPoint = furthestPoint;
//        this.xMax = xMax;
//        this.xMin = xMin;
//        this.yMax = yMax;
//        this.yMin = yMin;
//        this.zMax = zMax;
//        this.zMin = zMin;
    }
 
    public float[] getVertices() {
        return vertices;
    }
 
    public float[] getTextureCoords() {
        return textureCoords;
    }
 
    public float[] getNormals() {
        return normals;
    }
 
    public int[] getIndices() {
        return indices;
    }
 
    public float getFurthestPoint() {
        return furthestPoint;
    }
 
}