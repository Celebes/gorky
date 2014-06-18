package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

public abstract class AbstractGameScreen implements Screen {
	protected Game game;
	
	public AbstractGameScreen(Game game) {
		this.game = game;
	}

	public abstract InputProcessor getInputProcessor ();
	
	@Override
	public abstract void render(float deltaTime);

	@Override
	public abstract void resize(int width, int height);

	@Override
	public abstract void show();

	@Override
	public abstract void hide();

	@Override
	public abstract void pause();

	@Override
	public void resume() {
		Assets.load();
	}

	@Override
	public void dispose() {
		//Assets2.instance.dispose();
	}
}