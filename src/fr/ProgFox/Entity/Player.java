package fr.ProgFox.Entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.ProgFox.Math.Mathf;
import fr.ProgFox.Math.Vec2;
import fr.ProgFox.Math.Vec3;
import fr.ProgFox.renderer.Vector3f;

public class Player extends Entity {

	public static Vec3 pos;
	public static Vec2 rot;
	private float xDir, yDir, zDir;
	private float xa, ya, za;

	float sensibilite = 0.5f;
	

	
	public void input() {
		rot.x -= Mouse.getDY() * sensibilite;
		rot.y += Mouse.getDX() * sensibilite;

		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			zDir = 1;
			pos.x += Mathf.sin(Mathf.toRadians(rot.y));
			pos.z -= Mathf.cos(Mathf.toRadians(rot.y));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			zDir = -1;
			pos.x -= Mathf.sin(Mathf.toRadians(rot.y));
			pos.z += Mathf.cos(Mathf.toRadians(rot.y));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			xDir = -1;
			pos.x += Mathf.sin(Mathf.toRadians(rot.y - 90));
			pos.z -= Mathf.cos(Mathf.toRadians(rot.y - 90));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xDir = -1;
			pos.x -= Mathf.sin(Mathf.toRadians(rot.y - 90));
			pos.z += Mathf.cos(Mathf.toRadians(rot.y - 90));

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			pos.y++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			pos.y--;
		}
		
		xa += xDir * Mathf.cos(Mathf.toRadians(rot.y)) + zDir * Mathf.sin(Mathf.toRadians(rot.y));
		za += zDir * Mathf.cos(Mathf.toRadians(rot.y)) + xDir * Mathf.sin(Mathf.toRadians(rot.y));
		
		move(xa, 0, za);
	
		xa *= 0.9f;
		za *= 0.9f;
	}

}
