package fr.ProgFox.Game;

import fr.ProgFox.World.Chunk;
import fr.ProgFox.World.Blocks.Block;
import fr.ProgFox.renderer.Camera;

public class Raycast {
	private Block[][][] blocks;
	private Camera camera;

	public Raycast(Block[][][] blocks, Camera camera) {
		this.blocks = blocks;
		this.camera = camera;

	}

	public void update() {

	}

	public Block getBlock(float x, float y, float z) {
		if (x < 0 || y < 0 || z < 0 || x >= Chunk.SIZE || y >= Chunk.HEIGHT || z >= Chunk.SIZE)
			return null;

		return blocks[(int) x][(int) y][(int) z];
	}

}
