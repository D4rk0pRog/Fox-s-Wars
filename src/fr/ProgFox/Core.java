package fr.ProgFox;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.ProgFox.Entity.Entity;
import fr.ProgFox.Entity.Player;
import fr.ProgFox.Math.Vec3;
import fr.ProgFox.World.World;
import fr.ProgFox.renderer.Camera;
import fr.ProgFox.renderer.DisplayManager;
import fr.ProgFox.renderer.Vector3f;

public class Core {
	public static final int FRAME_CAP = 5000;
	boolean running = false;
	public static int frames = 0;
	public static int FPS;
	public static int newGravity = 0;
	Camera cam;
	World world;
	Player player;
	
	public Core() {
		DisplayManager.create(1200, 600, "Fox's Wars");
		world = new World();
		cam = new Camera(new Vec3(-0, -30, -0), world);
		cam.setPerspectiveProjection(70.0f, 0.1f, 1000.0f);
	}

	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Mouse.setGrabbed(false);
		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed())
			Mouse.setGrabbed(true);
		if (!Mouse.isGrabbed())
			return;

		cam.input();
		world.update();

	}

	public void render() {
		if (Display.wasResized()) {

			glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}
		DisplayManager.clearBuffers();
		cam.getPerspectiveProjection();
		cam.update();
		
		world.render();
	}

	// ----
	public void start() {
		running = true;
		loop();
	}

	public void stop() {
		running = false;
	}

	public void exit() {
		DisplayManager.dispose();
		System.exit(0);

	}

	public void loop() {
		long lastTickTime = System.nanoTime();
		long lastRenderTime = System.nanoTime();

		double tickTime = 1000000000.0 / 60.0;
		double RenderTime = 1000000000.0 / FRAME_CAP;

		int ticks = 0;

		long timer = System.currentTimeMillis();

		while (running) {
			if (DisplayManager.isClosed())
				stop();
			if (System.nanoTime() - lastTickTime > tickTime) {

				update();
				ticks++;
				lastTickTime += tickTime;
			} else if (System.nanoTime() - lastRenderTime > RenderTime) {
				render();

				DisplayManager.update();
				frames++;
				
				lastRenderTime += RenderTime;
				
			} 
			
			else {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (System.currentTimeMillis() - timer > 1000) {
				newGravity = 0;
				timer += 1000;
				FPS = frames;
				Display.setTitle("Fox's Wars : FPS = " + frames + "/ TPS = " + ticks + " | coords : " + cam.getPosition().getX() + "/" + cam.getPosition().getY() + "/" + cam.getPosition().getZ());
				ticks = 0;
				frames = 0;
			}
			//0.005099999

		}
		exit();
	}

	public static void main(String[] args) {
		Core main = new Core();
		main.start();
	}
}
