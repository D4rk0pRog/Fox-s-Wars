package fr.ProgFox.Shader;

import static org.lwjgl.opengl.GL20.*;
public abstract class Shader {
	protected int id;
	
	public Shader(int id) {
		this.id = id;
	}
	
	public void bind()
	{
		glUseProgram(id);
	}
	
	public void unbind()
	{
		glUseProgram(0);
	}
}
