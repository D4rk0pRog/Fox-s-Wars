#version 120

attribute vec3 in_position;
attribute vec3 in_colors;

varying vec3 v_colors;
void main()
{
	v_colors = in_colors;
	gl_Position = gl_ModelViewProjectionMatrix * vec4(in_position, 1.0);
}