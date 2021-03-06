package fr.ProgFox.World;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import fr.ProgFox.Shader.ColorShader;
import fr.ProgFox.Shader.Shader;
import fr.ProgFox.World.Blocks.Block;
import fr.ProgFox.renderer.Camera;

public class Chunk {
	private int vbo;
	public static final int SIZE = 16;
	public static final int HEIGHT = 32;
	private Shader shader;
	private int bufferSize;
	private static FloatBuffer buffer;
	private Noise noise;
	private float x, y, z;
	public Block[][][] blocks;
	public static boolean noBlockUp;

	public Chunk(float x, float y, float z, Noise noise, World world) {
		this.noise = noise;
		this.x = x;
		this.y = y;
		this.z = z;
		blocks = new Block[SIZE][HEIGHT][SIZE];

		shader = new ColorShader();
		generate(world);
	}

	public void generate(World world) {
		Block block = Block.GRASS;
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					int x2 = (int) this.x * SIZE + x;
					int y2 = (int) this.y * HEIGHT + y;
					int z2 = (int) this.z * SIZE + z;
					if (noise.getNoise(x2, z2) > y2) {
						blocks[x][y][z] = block;
						noBlockUp = noise.getNoise(x2, z2) > y2 && noise.getNoise(x2, z2) < y2 + 1;

						if (Math.random() < 0.03f && y > 0 && x > 0 && z > 0 && y <= SIZE && x <= SIZE && z <= SIZE
								&& noBlockUp) {

							Tree.addTRee(blocks, x, y, z);

						}
					}
				}
			}
		}
	}

	public void createChunk(World world) {
		buffer = BufferUtils.createFloatBuffer(SIZE * SIZE * SIZE * 6 * 4 * (3 + 3));

		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					int x2 = (int) this.x * SIZE + x;
					int y2 = (int) this.y * HEIGHT + y;
					int z2 = (int) this.z * SIZE + z;

					boolean up = world.getBlock(x2, y2 + 1, z2) == null;
					boolean down = world.getBlock(x2, y2 - 1, z2) == null;

					boolean back = world.getBlock(x2, y2, z2 + 1) == null;
					boolean front = world.getBlock(x2, y2, z2 - 1) == null;

					boolean right = world.getBlock(x2 + 1, y2, z2) == null;
					boolean left = world.getBlock(x2 - 1, y2, z2) == null;

					if (!up && !down && !left && !right && !front && !back)
						continue;
					if (blocks[x][y][z] == null)
						continue;
					Block block = blocks[x][y][z];
					int size = 0;
					if (up) {
						buffer.put(block.BlockDataUp(x2, y2, z2));
						size++;
					}
					if (down) {
						buffer.put(block.BlockDataDown(x2, y2, z2));
						size++;
					}
					if (back) {
						buffer.put(block.BlockDataBack(x2, y2, z2));
						size++;
					}
					if (front) {
						buffer.put(block.BlockDataFront(x2, y2, z2));
						size++;
					}
					if (right) {
						buffer.put(block.BlockDataRight(x2, y2, z2));
						size++;
					}
					if (left) {
						buffer.put(block.BlockDataLeft(x2, y2, z2));
						size++;
					}

					bufferSize += size * 4;

				}
			}
		}
		buffer.flip();
		createBuffer();

	}

	public void updateChunk(World world) {
		buffer.clear();
		bufferSize = 0;
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					int x2 = (int) this.x * SIZE + x;
					int y2 = (int) this.y * HEIGHT + y;
					int z2 = (int) this.z * SIZE + z;

					boolean up = world.getBlock(x2, y2 + 1, z2) == null;
					boolean down = world.getBlock(x2, y2 - 1, z2) == null;

					boolean back = world.getBlock(x2, y2, z2 + 1) == null;
					boolean front = world.getBlock(x2, y2, z2 - 1) == null;

					boolean right = world.getBlock(x2 + 1, y2, z2) == null;
					boolean left = world.getBlock(x2 - 1, y2, z2) == null;

					if (!up && !down && !left && !right && !front && !back)
						continue;
					if (blocks[x][y][z] == null)
						continue;
					Block block = blocks[x][y][z];
					blocks[x][y][z] = null;
					int size = 0;
					if (up) {
						buffer.put(block.BlockDataUp(x2, y2, z2));
						size++;
					}
					if (down) {
						buffer.put(block.BlockDataDown(x2, y2, z2));
						size++;
					}
					if (back) {
						buffer.put(block.BlockDataBack(x2, y2, z2));
						size++;
					}
					if (front) {
						buffer.put(block.BlockDataFront(x2, y2, z2));
						size++;
					}
					if (right) {
						buffer.put(block.BlockDataRight(x2, y2, z2));
						size++;
					}
					if (left) {
						buffer.put(block.BlockDataLeft(x2, y2, z2));
						size++;
					}

					bufferSize += size * 4;
				}
			}
		}

		buffer.flip();
		createBuffer();
	}

	private void createBuffer() {
		vbo = glGenBuffers();

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

	}

	public void update() {
//		float x0 = (int) (Math.abs(cam.position.getX()) + cam.getDirection().getX()) + 0;
//		float y0 = (int) (Math.abs(cam.position.getY()) + cam.getDirection().getY()) - 2;
//		float z0 = (int) (Math.abs(cam.position.getZ()) + cam.getDirection().getZ()) + 0;

		// System.out.println(x0 + " / " + y0 + " / " + z0);
//		if (getBlock(x0, y0, z0) != null) {
//			System.out.println(getBlock(x0, y0, z0));
//		}
	}

	public void render() {

		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * 4, 0);
		glVertexAttribPointer(1, 3, GL_FLOAT, true, 6 * 4, 12);
		shader.bind();

		glDrawArrays(GL_QUADS, 0, bufferSize);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}

	public void renderGUI() {

	}

	public void removeBlock(int x, int y, int z) {

		blocks[x][y][z] = null;
	}

	public Block getBlock(float x, float y, float z) {
		if (x < 0 || y < 0 || z < 0 || x >= SIZE || y >= HEIGHT || z >= SIZE)
			return null;
		return blocks[(int) x][(int) y][(int) z];
	}

}
