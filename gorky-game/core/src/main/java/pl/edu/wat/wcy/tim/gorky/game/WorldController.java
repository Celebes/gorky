package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.objects.Enemy;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.objects.Wall;
import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;
import pl.edu.wat.wcy.tim.gorky.util.CameraHelper;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	private WorldRenderer worldRenderer;
	public GorkyGame game;
	public CameraHelper cameraHelper;
	public Level level;
	
	private boolean onCollisionWithEnemy = false;
	
	// Detekcja kolizji
	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();
	
	public WorldController(GorkyGame game) {
		this.game = game;
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initLevel();
	}
	
	private void initLevel() {
		level = new Level(Constants.LEVEL_02);
		cameraHelper.setTarget(level.player);
	}

	public void update(float deltaTime) {
		handleDebugInput(deltaTime);
		handleInputGame(deltaTime);
		level.update(deltaTime);
		testCollisions();
		cameraHelper.update(deltaTime);
	}

	private void testCollisions() {
		// TODO Auto-generated method stub
		r1.set(level.player.position.x, level.player.position.y, level.player.bounds.width, level.player.bounds.height);
		
		// sprawdz kolizje z WALL
		for(Wall w : level.wallTiles) {
			r2.set(w.position.x, w.position.y, w.bounds.width, w.bounds.height);
			
			if (!r1.overlaps(r2)) {
				continue;
			}
			
			onCollisionPlayerWithWall(w);
		}
		
		// sprawdz kolizje z ENEMY

		for(Enemy e : level.enemies) {
			r2.set(e.position.x, e.position.y, e.bounds.width, e.bounds.height);
			
			if(!r1.overlaps(r2)) {
				continue;
			}
			
			if(onCollisionWithEnemy == true) {
				continue;
			}
			
			onCollisionPlayerWithEnemy(e);
			
			//level.enemies.removeValue(e, false);
		}
		
	}

	private void onCollisionPlayerWithEnemy(Enemy e) {
		onCollisionWithEnemy = true;
	}

	private void onCollisionPlayerWithWall(Wall w) {
		Player p = level.player;
		
		float upperValue = Math.abs(p.position.y -(w.position.y - w.bounds.height / 4.0f));
		float bottomValue = Math.abs(p.position.y - (w.position.y + p.bounds.height / 4.0f));
		float rightValue = Math.abs(p.position.x - (w.position.x - p.bounds.width / 4.0f));
		float leftValue = Math.abs(p.position.x - (w.position.x + w.bounds.width / 4.0f));
		
		if(upperValue > bottomValue && upperValue > rightValue && upperValue > leftValue) {
			p.position.y = w.position.y + p.bounds.height;
		} else if(bottomValue > upperValue && bottomValue > rightValue && bottomValue > leftValue) {
			p.position.y = w.position.y - p.bounds.height;
		} else if(rightValue > upperValue && rightValue > bottomValue && rightValue > leftValue) {
			p.position.x = w.position.x + w.bounds.width;
		} else if(leftValue > upperValue && leftValue > bottomValue && leftValue > rightValue) {
			p.position.x = w.position.x - p.bounds.width;
		}
	}

	private void handleDebugInput(float deltaTime) {
		if(Gdx.app.getType() != ApplicationType.Desktop) {
			return;
		}
		
		if(!cameraHelper.hasTarget(level.player)) {
			// sterowanie kamera
			float camMoveSpeed = 5 * deltaTime;
			float camMoveSpeedAccelerationFactor = 5;
			
			if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				camMoveSpeed *= camMoveSpeedAccelerationFactor;
			}
			
			if(Gdx.input.isKeyPressed(Keys.A)) {
				moveCamera(-camMoveSpeed, 0);
			}
			
			if(Gdx.input.isKeyPressed(Keys.D)) {
				moveCamera(camMoveSpeed, 0);
			}
			
			if(Gdx.input.isKeyPressed(Keys.W)) {
				moveCamera(0, camMoveSpeed);
			}
			
			if(Gdx.input.isKeyPressed(Keys.S)) {
				moveCamera(0, -camMoveSpeed);
			}
			
			if(Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
				cameraHelper.setPosition(0, 0);
			}
		}
		
		// przyblizanie kamery
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		}
		
		if(Gdx.input.isKeyPressed(Keys.COMMA)) {
			cameraHelper.addZoom(camZoomSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.PERIOD)) {
			cameraHelper.addZoom(-camZoomSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.SLASH)) {
			cameraHelper.setZoom(1);
		}
	}
	
	private void handleInputGame (float deltaTime) {
		if(cameraHelper.hasTarget(level.player)) {
			// ruch postaci
			
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				level.player.velocity.x = -level.player.terminalVelocity.x;
				level.player.setFollowingTouch(false);
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				level.player.velocity.x = level.player.terminalVelocity.x;
				level.player.setFollowingTouch(false);
			} 

			if (Gdx.input.isKeyPressed(Keys.UP)) {
				level.player.velocity.y = level.player.terminalVelocity.y;
				level.player.setFollowingTouch(false);
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				level.player.velocity.y = -level.player.terminalVelocity.y;
				level.player.setFollowingTouch(false);
			}
		}
		
		if(Gdx.input.isTouched()) {			
			Vector3 touchScreen = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			Vector3 touchGame3 = worldRenderer.getCamera().unproject(touchScreen);
			
			Vector2 touchInGame = new Vector2(touchGame3.x, touchGame3.y);
			
			level.player.setMoveToVector(touchInGame);
		}
		
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	@Override
	public boolean keyUp(int keycode) {
		// zresetuj swiat gry
		if(keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game world resetted!");
		}
		
		// wlacz sledzenie postaci
		else if(keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : level.player);
			Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
		}
		
		// wroc do menu
		else if(keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			backToMenu();
		}
		
		return false;
	}
	
	private void backToMenu() {
		game.setScreen(new MenuScreen(game));
	}
	
	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}

	public void setWorldRenderer(WorldRenderer worldRenderer) {
		this.worldRenderer = worldRenderer;
	}

	public boolean isCollisionWithEnemy() {
		return onCollisionWithEnemy;
	}

}
