package pl.edu.wat.tim.gorky.model;

import com.badlogic.gdx.math.Vector2;

public class Tile {
	
	public static final float SIZE = 50f;

	Vector2 position = new Vector2();
	
	public Tile(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
