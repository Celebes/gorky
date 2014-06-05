package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;
import pl.edu.wat.wcy.tim.gorky.util.CameraHelper;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	private WorldRenderer worldRenderer;
	public Game game;
	public Sprite[] testSprites;
	public int selectedSprite;
	public CameraHelper cameraHelper;
	public Level level;
	
	public WorldController(Game game) {
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
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				level.player.velocity.x = level.player.terminalVelocity.x;
			} 

			if (Gdx.input.isKeyPressed(Keys.UP)) {
				level.player.velocity.y = level.player.terminalVelocity.y;
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				level.player.velocity.y = -level.player.terminalVelocity.y;
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

}
