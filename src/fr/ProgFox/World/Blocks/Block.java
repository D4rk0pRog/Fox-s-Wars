package fr.ProgFox.World.Blocks;


import fr.ProgFox.renderer.Vector3f;

public abstract class Block {
	public static final Block GRASS = new GrassBlock();
	public static final Block WOOD = new Wood();
	public static final Block LEAF = new LeafBlock();
	
	public Vector3f color;
	public static float sizeX = 1, sizeY = 1, sizeZ = 1;
	
	public Block(Vector3f color){
		
		this.color = color;
	}
	
	
	public float[] BlockData(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();
		
		return new float[] {
				
				x, y, z,		r, g, b,//////
				x+1, y, z,		r, g, b,////
				x+1, y+1, z,	r, g, b,// 1
				x, y+1, z,		r, g, b,////

				x, y, z+1,		r, g, b,//////
				x+1, y, z+1,	r, g, b,////
				x+1, y+1, z+1,	r, g, b,// 2
				x, y+1, z+1,	r, g, b,/////

				x, y, z,		r, g, b,//////
				x, y+1, z,		r, g, b,////
				x, y+1, z+1,	r, g, b,// 3
				x, y, z+1,		r, g, b,////
				
				x+1, y, z,		r, g, b,//////
				x+1, y+1, z,	r, g, b,////
				x+1, y+1, z+1,	r, g, b,// 4
				x+1, y, z+1,	r, g, b,////

				x, y, z,		r, g, b,//////
				x +1, y, z,		r, g, b,///
				x+1, y, z+1,	r, g, b,// 5
				x, y, z+1,		r, g, b,////
			
				x, y+1, z,		r, g, b,//////
				x+1, y+1, z,	r, g, b,////
				x+1, y+1, z+1,	r, g, b,// 6
				x, y+1, z+1,	r, g, b,////
		
		};
		
	}
	public float[] BlockDataFront(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();

		
		return new float[] {
				
				x, y, z,		r * 0.8f, g * 0.8f, b * 0.8f,//////
				x+1, y, z,		r * 0.8f, g * 0.8f, b * 0.8f,////
				x+1, y+1, z,	r * 0.8f, g * 0.8f, b * 0.8f,// 1
				x, y+1, z,		r * 0.8f, g * 0.8f, b * 0.8f,////

			
		
		};
		
	}
	public float[] BlockDataBack(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();

		
		return new float[] {
				
				x, y, z+1,		r * 0.8f, g * 0.8f, b * 0.8f,//////
				x+1, y, z+1,	r * 0.8f, g * 0.8f, b * 0.8f,////
				x+1, y+1, z+1,	r * 0.8f, g * 0.8f, b * 0.8f,// 2
				x, y+1, z+1,	r * 0.8f, g * 0.8f, b * 0.8f,/////

			
		
		};
		
	}
	public float[] BlockDataRight(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();

		
		return new float[] {
				
				x+1, y, z,		r * 0.75f, g * 0.75f, b * 0.75f,//////
				x+1, y+1, z,	r * 0.75f, g * 0.75f, b * 0.75f,////
				x+1, y+1, z+1,	r * 0.75f, g * 0.75f, b * 0.75f,// 4
				x+1, y, z+1,	r * 0.75f, g * 0.75f, b * 0.75f,////

			
		
		};
		
	}
	public float[] BlockDataLeft(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();

		
		return new float[] {
				
				x, y, z,		r * 0.75f, g * 0.75f, b * 0.75f,//////
				x, y+1, z,		r * 0.75f, g * 0.75f, b * 0.75f,////
				x, y+1, z+1,	r * 0.75f, g * 0.75f, b * 0.75f,// 3
				x, y, z+1,		r * 0.75f, g * 0.75f, b * 0.75f,////

			
		
		};
		
	}
	public float[] BlockDataUp(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();

		
		return new float[] {
				
				x, y+1, z,		r * 0.7f, g * 0.7f, b * 0.7f,//////
				x+1, y+1, z,	r * 0.7f, g * 0.7f, b * 0.7f,////
				x+1, y+1, z+1,	r * 0.7f, g * 0.7f, b * 0.7f,// 6
				x, y+1, z+1,	r * 0.7f, g * 0.7f, b * 0.7f,////

			
		
		};
		
	}
	public float[] BlockDataDown(float x, float y, float z){
		
		float r, g, b;
		r=color.getX(); g=color.getY(); b=color.getZ();
		
		
		return new float[] {
				
				x, y, z,		r * 0.7f, g * 0.7f, b * 0.7f,//////
				x +1, y, z,		r * 0.7f, g * 0.7f, b * 0.7f,///
				x+1, y, z+1,	r * 0.7f, g * 0.7f, b * 0.7f,// 5
				x, y, z+1,		r * 0.7f, g * 0.7f, b * 0.7f////

			
		
		};
		
	}
	
}
