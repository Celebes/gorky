package pl.edu.wat.wcy.tim.gorky.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends AbstractGameScreen {
	
	SpriteBatch batch;
	Texture img;
	
	public MenuScreen(Game game) {
		super(game);
	}

	private static final String TAG = MenuScreen.class.getName();
	
	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
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
		batch = new SpriteBatch();
		img = new Texture("images/LOGO_TIM.png");
	}

	@Override
	public void hide() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void pause() {}
	
}
