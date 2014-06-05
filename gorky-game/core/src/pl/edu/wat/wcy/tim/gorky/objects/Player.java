package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends AbstractGameObject {
	
	public enum VIEW_DIRECTION { UP, RIGHT, DOWN, LEFT }
	
	private TextureRegion regPlayer;
	public VIEW_DIRECTION viewDirection;
	
	// przemieszczanie za kliknieciem
	
	private Vector2 start;
	private Vector2 stop;
	private Vector2 direction;
	boolean moving;
	float distance;
	
	public Player() {
		init();
	}
	
	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(dimension.x / 2, dimension.y / 2);
		regPlayer = Assets.instance.player.player;
		
		viewDirection = VIEW_DIRECTION.DOWN;
		
		// obramowanie do detekcji kolizji
		bounds.set(0, 0, dimension.x, dimension.y);
		
		terminalVelocity.set(5.0f, 5.0f);
		friction.set(12.0f, 12.0f);
		acceleration.set(0.0f, 0.0f);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(regPlayer.getTexture(), position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
				regPlayer.getRegionX(), regPlayer.getRegionY(), regPlayer.getRegionWidth(),
				regPlayer.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT, viewDirection == VIEW_DIRECTION.UP);
		
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if (velocity.x != 0) {
			viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT : VIEW_DIRECTION.RIGHT;
		}
		
		if (velocity.y != 0) {
			viewDirection = velocity.y > 0 ? VIEW_DIRECTION.UP : VIEW_DIRECTION.DOWN;
		}
		
		if(moving == true) {
			velocity.add(direction);
			direction = stop.cpy().sub(position.cpy()).nor();
			if(position.dst(start) >= distance) {
				velocity = new Vector2();
				//position = stop;
				moving = false;
			}
		}
	}

	public void setMoveToVector(Vector2 moveToVector) {
		start = position.cpy();
		stop = moveToVector;
		distance = start.dst(stop);
		direction = stop.cpy().sub(start.cpy()).nor();
		moving = true;
	}

}