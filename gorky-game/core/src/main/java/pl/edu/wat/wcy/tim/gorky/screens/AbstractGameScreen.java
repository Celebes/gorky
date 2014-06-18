package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class AbstractGameScreen implements Screen {
	protected GorkyGame game;
	
	public AbstractGameScreen(GorkyGame game) {
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
		//Assets.load();
	}

	@Override
	public void dispose() {
		//Assets2.instance.dispose();
	}
}