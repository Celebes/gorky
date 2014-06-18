package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.game.WorldController;
import pl.edu.wat.wcy.tim.gorky.game.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractGameScreen {
	private static final String TAG = GameScreen.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
	private boolean paused;

	public GameScreen(GorkyGame game) {
		super(game);
	}
	
	@Override
	public void render(float deltaTime) {
		
		if(worldController.isCollisionWithEnemy()) {
			game.setScreen(game.battleScreen);
		} else {
			if(!paused) {
				worldController.update(deltaTime);
			}
			
			// kolor jasnozielony
			Gdx.gl.glClearColor(57.0f / 255.0f, 181.0f / 225.0f, 115.0f / 255.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			worldRenderer.render();
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void pause() {
		paused = true;
	}
	
	@Override
	public void resume() {
		super.resume();
		// Only called on Android!
		paused = false;
	}
	
	@Override
	public InputProcessor getInputProcessor() {
		return worldController;
	}

}
