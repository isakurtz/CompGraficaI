#version 400

layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec3 VertexColor;

out vec3 Color ;

void main(){
    Color = VertexColor;
    gl_Position = vec4(vertexPosition, 1.0);    
}