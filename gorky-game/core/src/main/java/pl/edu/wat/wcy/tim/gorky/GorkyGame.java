package pl.edu.wat.wcy.tim.gorky;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class GorkyGame extends Game {

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());
		setScreen(new MenuScreen(this));
	}
	
}
