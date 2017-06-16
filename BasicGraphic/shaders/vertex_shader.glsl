#version 200

layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec3 VertexColor;

out vec3 Color ;

void main(){
    Color = VexterColor;
    gl_Position = vec4(VertexPosition, 1.0);    
}