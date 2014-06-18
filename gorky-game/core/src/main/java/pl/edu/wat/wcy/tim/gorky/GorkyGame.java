package pl.edu.wat.wcy.tim.gorky;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.screens.BattleScreen;
import pl.edu.wat.wcy.tim.gorky.screens.GameScreen;
import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GorkyGame extends Game {
	
	public BattleScreen battleScreen;
	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.load();
		
		battleScreen = new BattleScreen(this);
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		
		setScreen(menuScreen);
	}
	
}
