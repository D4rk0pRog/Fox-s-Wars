package fr.ProgFox.Entity;

import fr.ProgFox.Math.Vec2;
import fr.ProgFox.Math.Vec3;

public abstract class Entity {
	public static Vec3 pos;
	public static Vec2 rot;
		public Entity(){

			init();
		}
		
		
	public void move(float xa, float ya, float za){
		pos.x += xa;
		pos.y += ya;
		pos.z += za;
		
		
	}
	public static void init(){
		pos.setX(1);
		pos.setY(1);
		pos.setZ(1);
	
		rot.setX(1);
		rot.setY(1);
	}
	
}
