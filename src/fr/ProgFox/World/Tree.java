package fr.ProgFox.World;

import fr.ProgFox.World.Blocks.Block;

public class Tree {
	public static void addTRee(Block[][][] blocks, int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0 || x >= Chunk.SIZE || y >= Chunk.HEIGHT || z >= Chunk.SIZE)
			return;

		if (x > Chunk.SIZE - 6 || z > Chunk.SIZE - 6)
			return;
		if (x < 6 || z < 6)
			return;

		for (int i = 0; i < 6; i++) {
			blocks[x][y + i][z] = Block.WOOD;
		}

		blocks[x + 1][y + 4][z + 1] = Block.LEAF;
		blocks[x + 2][y + 4][z + 1] = Block.LEAF;
		blocks[x - 1][y + 4][z + 1] = Block.LEAF;
		blocks[x - 2][y + 4][z + 1] = Block.LEAF;
		blocks[x][y + 4][z + 1] = Block.LEAF;

		blocks[x][y + 4][z + 2] = Block.LEAF;
		blocks[x + 1][y + 4][z + 2] = Block.LEAF;
		blocks[x - 1][y + 4][z + 2] = Block.LEAF;

		blocks[x][y + 4][z - 1] = Block.LEAF;
		blocks[x - 1][y + 4][z - 1] = Block.LEAF;
		blocks[x - 2][y + 4][z - 1] = Block.LEAF;
		blocks[x + 1][y + 4][z - 1] = Block.LEAF;
		blocks[x + 2][y + 4][z - 1] = Block.LEAF;

		blocks[x][y + 4][z - 2] = Block.LEAF;
		blocks[x - 1][y + 4][z - 2] = Block.LEAF;
		blocks[x + 1][y + 4][z - 2] = Block.LEAF;

		blocks[x + 1][y + 4][z] = Block.LEAF;
		blocks[x - 1][y + 4][z] = Block.LEAF;
		blocks[x + 2][y + 4][z] = Block.LEAF;
		blocks[x - 2][y + 4][z] = Block.LEAF;

		for (int x2 = 0; x2 < 3; x2++) {
			for (int z2 = 0; z2 < 3; z2++) {
				blocks[x + x2][y + 5][z + z2] = Block.LEAF;
				blocks[x - x2][y + 5][z - z2] = Block.LEAF;
				blocks[x + x2][y + 5][z - z2] = Block.LEAF;
				blocks[x - x2][y + 5][z + z2] = Block.LEAF;

			}
		}

		blocks[x + 1][y + 6][z + 1] = Block.LEAF;
		blocks[x + 2][y + 6][z + 1] = Block.LEAF;
		blocks[x - 1][y + 6][z + 1] = Block.LEAF;
		blocks[x - 2][y + 6][z + 1] = Block.LEAF;
		blocks[x][y + 6][z + 1] = Block.LEAF;

		blocks[x][y + 6][z + 2] = Block.LEAF;
		blocks[x + 1][y + 6][z + 2] = Block.LEAF;
		blocks[x - 1][y + 6][z + 2] = Block.LEAF;

		blocks[x][y + 6][z - 1] = Block.LEAF;
		blocks[x - 1][y + 6][z - 1] = Block.LEAF;
		blocks[x - 2][y + 6][z - 1] = Block.LEAF;
		blocks[x + 1][y + 6][z - 1] = Block.LEAF;
		blocks[x + 2][y + 6][z - 1] = Block.LEAF;

		blocks[x][y + 6][z - 2] = Block.LEAF;
		blocks[x - 1][y + 6][z - 2] = Block.LEAF;
		blocks[x + 1][y + 6][z - 2] = Block.LEAF;

		blocks[x + 1][y + 6][z] = Block.LEAF;
		blocks[x - 1][y + 6][z] = Block.LEAF;
		blocks[x + 2][y + 6][z] = Block.LEAF;
		blocks[x - 2][y + 6][z] = Block.LEAF;

		blocks[x][y + 7][z] = Block.LEAF;
		blocks[x][y + 7][z + 1] = Block.LEAF;
		blocks[x][y + 7][z - 1] = Block.LEAF;
		blocks[x + 1][y + 7][z] = Block.LEAF;
		blocks[x - 1][y + 7][z] = Block.LEAF;

		blocks[x + 1][y + 7][z + 1] = Block.LEAF;
		blocks[x - 1][y + 7][z - 1] = Block.LEAF;
		blocks[x + 1][y + 7][z - 1] = Block.LEAF;
		blocks[x - 1][y + 7][z + 1] = Block.LEAF;

		blocks[x][y + 7][z + 2] = Block.LEAF;
		blocks[x][y + 7][z - 2] = Block.LEAF;
		blocks[x + 2][y + 7][z] = Block.LEAF;
		blocks[x - 2][y + 7][z] = Block.LEAF;

	}
}
