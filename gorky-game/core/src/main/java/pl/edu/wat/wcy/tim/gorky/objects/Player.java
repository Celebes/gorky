package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.equipment.Equipable;
import pl.edu.wat.wcy.tim.gorky.equipment.Equipment;
import pl.edu.wat.wcy.tim.gorky.equipment.Sword;
import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends AbstractGameObject {
	
	public enum VIEW_DIRECTION { UP, RIGHT, DOWN, LEFT }
	
	private TextureRegion regPlayerUp;
	private TextureRegion regPlayerRight;
	private TextureRegion regPlayerDown;
	
	public VIEW_DIRECTION viewDirection;
	
	// przemieszczanie za kliknieciem
	
	private Vector2 start;
	private Vector2 stop;
	private Vector2 direction;
	private volatile boolean followingTouch;
	private float distance;
	
	// statystyki
	CharacterAttributes characterAttributes;
	
	// ekwipunek
	Equipment ekwipunek;
	
	public Player() {
		init();
		initCharacterAttributes();
		initEquipment();
	}
	
	private void initEquipment() {
		// podstawowy ekwipunek
		Equipable startingSword = new Sword("Drewniany miecz wstydu", 3);
		//ekwipunek.
	}

	private void initCharacterAttributes() {
		characterAttributes = new CharacterAttributes();
		
		// podstawowe statystyki
		characterAttributes.setAtk(5);
		characterAttributes.setDef(5);
		characterAttributes.setMagAtk(15);
		characterAttributes.setMagDef(5);
		characterAttributes.setMaxHP(100);
		characterAttributes.setMaxMP(100);
		characterAttributes.setHP(characterAttributes.getMaxHP());
		characterAttributes.setHP(characterAttributes.getMaxMP());
	}

	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(dimension.x / 2, dimension.y / 2);
		
		regPlayerUp = Assets.player_up;
		regPlayerRight = Assets.player_right;
		regPlayerDown = Assets.player_down;
		
		viewDirection = VIEW_DIRECTION.DOWN;
		
		// obramowanie do detekcji kolizji
		bounds.set(0, 0, dimension.x, dimension.y);
		
		terminalVelocity.set(5.0f, 5.0f);
		friction.set(12.0f, 12.0f);
		acceleration.set(0.0f, 0.0f);
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		
		boolean flipX = false;
		
		if(viewDirection == VIEW_DIRECTION.DOWN) {
			reg = regPlayerDown;
		} else if(viewDirection == VIEW_DIRECTION.UP) {
			reg = regPlayerUp;		
		} else if(viewDirection == VIEW_DIRECTION.RIGHT) {
			reg = regPlayerRight;
		} else if(viewDirection == VIEW_DIRECTION.LEFT) {
			reg = regPlayerRight;
			flipX = true;
		}
		
		batch.draw(reg.getTexture(), position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
				reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
				reg.getRegionHeight(), flipX, false);
		
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		/*if (velocity.x != 0) {
			viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT : VIEW_DIRECTION.RIGHT;
		}
		
		if (velocity.y != 0) {
			viewDirection = velocity.y > 0 ? VIEW_DIRECTION.UP : VIEW_DIRECTION.DOWN;
		}*/
		
		if(velocity.x != 0 || velocity.y != 0) {
			if(Math.abs(velocity.x) > Math.abs(velocity.y)) {
				viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT : VIEW_DIRECTION.RIGHT;
			} else {
				viewDirection = velocity.y > 0 ? VIEW_DIRECTION.UP : VIEW_DIRECTION.DOWN;
			}
		}
		
		
		if(followingTouch == true) {
			velocity.add(direction);
			direction = stop.cpy().sub(position.cpy()).nor();
			if(position.dst(start) >= distance) {
				velocity = new Vector2();
				followingTouch = false;
			}
		}
	}

	public void setMoveToVector(Vector2 moveToVector) {
		start = position.cpy();
		stop = moveToVector;
		distance = start.dst(stop);
		direction = stop.cpy().sub(start.cpy()).nor();
		followingTouch = true;
	}

	public boolean isFollowingTouch() {
		return followingTouch;
	}

	public void setFollowingTouch(boolean moving) {
		this.followingTouch = moving;
	}

	public Equipment getEkwipunek() {
		return ekwipunek;
	}

	public void setEkwipunek(Equipment ekwipunek) {
		this.ekwipunek = ekwipunek;
	}

	public CharacterAttributes getCharacterAttributes() {
		return characterAttributes;
	}

	public void setCharacterAttributes(CharacterAttributes characterAttributes) {
		this.characterAttributes = characterAttributes;
	}

}