package pl.edu.wat.wcy.tim.gorky;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GorkyGame extends Game {

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Assets2.instance.init(new AssetManager());
		Assets.load();
		setScreen(new MenuScreen(this));
	}
	
}
