package fr.ProgFox.renderer;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import fr.ProgFox.Game.Raycast;
import fr.ProgFox.Math.Vec3;
import fr.ProgFox.World.World;
import fr.ProgFox.World.Blocks.Block;

public class Camera {

	private float fov, zNear, zFar;

	public Vec3 position;
	public Vec3 rotation;
	public boolean gravity = true;
	public boolean grounded = false;
	public float gravityFactor = 0;
	public float jumpFactor = 0;
	public boolean isJumping = false;
	public float timeToJump = 0;
	World world;
	public Raycast raycast;
	public Camera(Vec3 position, World world) {
		raycast = new Raycast(world.getChunk(0, 0).blocks, this);
		
		this.position = position;
		rotation = new Vec3(0, 0, 0);
		this.world = world;
	}

	public Camera setPerspectiveProjection(float fov, float zNear, float zFar) {
		this.fov = fov;
		this.zNear = zNear;
		this.zFar = zFar;

		return this;
	}

	public Vector3f getForward() {
		Vector3f r = new Vector3f();
		float cosY = (float) Math.cos(Math.toRadians(rotation.getY() + 90));
		float sinY = (float) Math.sin(Math.toRadians(rotation.getY() + 90));
		float cosP = (float) Math.cos(Math.toRadians(rotation.getX()));
		float sinP = (float) Math.sin(Math.toRadians(rotation.getX()));

		r.setX(cosY * cosP);
		r.setY(sinP);
		r.setZ(sinY * cosP);
		return r;
	}

	public Vector3f getRight() {
		Vector3f r = new Vector3f();
		float cosY = (float) Math.cos(Math.toRadians(rotation.getY()));
		float sinY = (float) Math.sin(Math.toRadians(rotation.getY()));

		r.setX(cosY);
		r.setZ(sinY);

		return r;
	}

	public void getPerspectiveProjection() {
		glEnable(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float) Display.getWidth() / Display.getHeight(), zNear, zFar);
		glEnable(GL_MODELVIEW);
	}

	public void update() {  
		glPushAttrib(GL_TRANSFORM_BIT);
		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glRotatef(rotation.getZ(), 0, 0, 1);

		glTranslatef(position.getX(), position.getY(), position.getZ());

		glPopMatrix();
		
		raycast.update();
		
		world.cam = this;
	}

	float speed = 0.1f;
	float sensibilite = 5;

	

	public void input() {

		float xDir = 0, yDir = 0, zDir = 0;
		rotation.addX(-Mouse.getDY() / sensibilite);
		rotation.addY(Mouse.getDX() / sensibilite);
		if (rotation.getX() > 90)
			rotation.setX(90);
		if (rotation.getX() < -90)
			rotation.setX(-90);

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			speed = 0.8f;
		} else {
			speed = 0.1f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {

			xDir = getForward().mul(new Vector3f(speed, 0, speed)).getX();
			zDir = getForward().mul(new Vector3f(speed, 0, speed)).getZ();
			move(xDir, yDir, zDir);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {

			xDir = getForward().mul(new Vector3f(-speed, 0, -speed)).getX();
			zDir = getForward().mul(new Vector3f(-speed, 0, -speed)).getZ();
			move(xDir, yDir, zDir);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {

			xDir = getRight().mul(new Vector3f(speed, 0, speed)).getX();
			zDir = getRight().mul(new Vector3f(speed, 0, speed)).getZ();
			move(xDir, yDir, zDir);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

			xDir = getRight().mul(new Vector3f(-speed, 0, -speed)).getX();
			zDir = getRight().mul(new Vector3f(-speed, 0, -speed)).getZ();
			move(xDir, yDir, zDir);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && grounded && timeToJump > 1) {

			isJumping = true;
			timeToJump = 0;

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !grounded) {
			yDir = speed;
			move(0, yDir, 0);
		}

gravity();

	}

	public Vec3 getPosition() {
		return position;
	}
	public void gravity(){
		gravityFactor += 0.01f;
		if (isJumping) {
			jumpFactor += 0.03f;

			move(0, -jumpFactor, 0);

		}
		if (jumpFactor > 0.27f) {
			jumpFactor = 0;
			isJumping = false;
		}
		if (gravity) {
			if (grounded) {
				gravityFactor = 0;
			}
			if (!grounded) {
			}

			move(0, gravityFactor, 0);

			if (isColliding(0, 0.5f, 0)) {
				grounded = true;
			} else {
				grounded = false;
			}
		}
		if (grounded) {
			timeToJump += 0.1f;
		}
	}
	public void move(float xDir, float yDir, float zDir) {


		if (!isColliding(xDir, 0, 0)) {
			position.addX(xDir);
		}
		if (!isColliding(0, yDir, 0)) {
			position.addY(yDir);
		}
		if (!isColliding(0, 0, zDir)) {
			position.addZ(zDir);
		}

	}

	public boolean isColliding(float xDir, float yDir, float zDir) {

		float rayon = 0.3f;

		float x0 = Math.abs(position.getX() + xDir) - rayon;
		float x1 = Math.abs(position.getX() + xDir) + rayon;

		float y0 = Math.abs(position.getY() + yDir) - rayon + 0.5f;
		float y1 = Math.abs(position.getY() + yDir) + rayon - 2;

		float z0 = Math.abs(position.getZ() + zDir) - rayon;
		float z1 = Math.abs(position.getZ() + zDir) + rayon;

		if (world.getBlock(x0, y0, z0) != null)
			return true;
		if (world.getBlock(x1, y0, z0) != null)
			return true;
		if (world.getBlock(x1, y1, z0) != null)
			return true;

		if (world.getBlock(x0, y1, z0) != null)
			return true;

		if (world.getBlock(x0, y0, z1) != null)
			return true;
		if (world.getBlock(x1, y0, z1) != null)
			return true;
		if (world.getBlock(x1, y1, z1) != null)
			return true;
		if (world.getBlock(x0, y1, z1) != null)
			return true;

		return false;
	}

	public void setPosition(Vec3 position) {

		this.position = position;
	}

	public Vector3f getDirection() { 
		Vector3f r = new Vector3f();
		float cosY = (float) Math.cos(Math.toRadians(rotation.getY() + 90));
		float sinY = (float) Math.sin(Math.toRadians(rotation.getY() + 90));
		float cosP = (float) Math.cos(Math.toRadians(rotation.getX()));
		float sinP = (float) Math.sin(Math.toRadians(rotation.getX()));

		r.setX(cosY * cosP);
		r.setY(sinP);
		r.setZ(sinY * cosP);
		return r;
	}
}
