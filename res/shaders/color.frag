#version 330

out vec4 frag_color;

varying vec3 v_colors;



void main()
{

	frag_color = vec4(v_colors.x, v_colors.y, v_colors.z, 1);
}