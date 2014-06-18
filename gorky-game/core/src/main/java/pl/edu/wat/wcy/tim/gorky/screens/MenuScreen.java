package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends AbstractGameScreen {
	
	private static final String TAG = MenuScreen.class.getName();
	
	private SpriteBatch batch;
	private Texture img;
	
	public MenuScreen(GorkyGame game) {
		super(game);
		Gdx.app.log(TAG, "KONSTRUKTOR() | img: " + img + " | batch: " + batch);
		img = Assets.loadTexture(Constants.MENU_SPLASH_SCREEN);
	}
	
	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(img == null) {
			Gdx.app.log(TAG, "RENDER() | img: " + img + " | batch: " + batch);
		}
		batch.draw(img, 0, 0);
		batch.end();
		
		if(Gdx.input.isTouched()) {
			Gdx.app.log(TAG, "RENDER() | img: " + img + " | batch: " + batch);
			game.setScreen(game.gameScreen);
		}
	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Gdx.app.log(TAG, "SHOW() | img: " + img + " | batch: " + batch);
		batch = new SpriteBatch();
	}

	@Override
	public void hide() {
		//batch.dispose();
	}

	@Override
	public void pause() {}
	
}
