package pl.edu.wat.tim.gorky.model;

import com.badlogic.gdx.math.Vector2;

public class Hero {
	
	static final float SIZE = 0.5f;
	
	Vector2 position = new Vector2();
	State state = State.IDLE;
	Direction direction = Direction.DOWN;
	
	public Hero(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public enum State {
		IDLE, WALKING, WAITING, DYING
	}
	
	public enum Direction {
		UP, RIGHT, DOWN, LEFT
	}
	
}
