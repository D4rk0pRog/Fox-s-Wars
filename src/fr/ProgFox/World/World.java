package fr.ProgFox.World;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import fr.ProgFox.Logs.Logs;
import fr.ProgFox.World.Blocks.Block;
import fr.ProgFox.renderer.Camera;
import fr.ProgFox.renderer.Vector3f;
 
public class World {
	private static ArrayList<Vector3f> blocks = new ArrayList<>();
	private static Chunk[][] chunks;
	public static final int SIZE = 10;
	private Noise noise;
	public static Camera cam;
	public World() {
		chunks = new Chunk[SIZE][SIZE];
		noise = new Noise(new Random().nextLong(), 15, 17);
		for (int x = 0; x < SIZE; x++) {
			for (int z = 0; z < SIZE; z++) {
				chunks[x][z] = new Chunk(x, 0, z, noise, this);
			}
		}
		new Logs().Info("Fin de la génération / début du changement");
		new Logs().Info("Fin du changement / début de la création");
		for (int x = 0; x < SIZE; x++) {
			for (int z = 0; z < SIZE; z++) {
				chunks[x][z].createChunk(this);
			}
			new Logs().Info("Chargement des chunk : " + (x * 100 / SIZE));
		}
		new Logs().Info("The game  is ready to play");

	}

	public void render() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			
			chunks[0][0].updateChunk(this);
		}
		
		
		for (int x = 0; x < SIZE; x++) {
			for (int z = 0; z < SIZE; z++) {
				
				chunks[x][z].render();
			}
		}
		
	}

	public void update() {
		for (int x = 0; x < SIZE; x++) {
			for (int z = 0; z < SIZE; z++) {
				
				chunks[x][z].update();
			}
		}
	}
	public Block getBlock(float x, float y, float z){
		float x2 = x / Chunk.SIZE;
		float z2 = z / Chunk.SIZE;
		
		if(x2 < 0 || z2 < 0 || x2 >= SIZE || z2 >= SIZE) return null;
		
		Chunk c = chunks[(int)x2][(int)z2];
		
		float x3 = x % Chunk.SIZE;
		float y3 = y;
		float z3 = z % Chunk.SIZE;
		
		return c.getBlock(x3, y3, z3);
		
	}
	public Chunk getChunk(int x, int z){
		if(x < 0 || z < 0 || x >= SIZE || z >= SIZE) return null;
		return chunks[x][z];
	}
	



}
