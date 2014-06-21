package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.equipment.Equipable;
import pl.edu.wat.wcy.tim.gorky.equipment.Equipment;
import pl.edu.wat.wcy.tim.gorky.equipment.Sword;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends BattleGameObject {
	
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
	
	public Player() {
		init();
		initCharacterAttributes();
		initEquipment();
	}

	private void initCharacterAttributes() {
		characterAttributes = new CharacterAttributes();
		
		// podstawowe statystyki
		characterAttributes.setAtk(SaveStatePreferences.instance.atk);
		characterAttributes.setDef(SaveStatePreferences.instance.def);
		characterAttributes.setMagAtk(SaveStatePreferences.instance.magAtk);
		characterAttributes.setMagDef(SaveStatePreferences.instance.magDef);
		characterAttributes.setMaxHP(SaveStatePreferences.instance.maxHP);
		characterAttributes.setMaxMP(SaveStatePreferences.instance.maxMP);
		characterAttributes.setHP(SaveStatePreferences.instance.HP);
		characterAttributes.setMP(SaveStatePreferences.instance.MP);
		
		characterAttributes.setExp(SaveStatePreferences.instance.exp);
		characterAttributes.setLevel(SaveStatePreferences.instance.level);
		characterAttributes.setGold(SaveStatePreferences.instance.gold);
	}
	
	private void initEquipment() {
		// ekwipunek startowy
		this.equipment = new Equipment(characterAttributes);
		
		// zaloz miecz
		Sword sword = new Sword("Drewniany miecz wstydu", 5);
		this.equipment.equipItem(sword);
	}

	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(dimension.x / 2, dimension.y / 2);
		
		regPlayerUp = Assets.instance.player.player_up;
		regPlayerRight = Assets.instance.player.player_right;
		regPlayerDown = Assets.instance.player.player_down;
		
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
		
		// zapisz polozenie w swiecie
		SaveStatePreferences.instance.playerPositionX = position.x;
		SaveStatePreferences.instance.playerPositionY = position.y;
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

}