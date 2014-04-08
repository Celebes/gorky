package pl.edu.wat.tim.gorky;

import pl.edu.wat.tim.gorky.screens.FightScreen;

import com.badlogic.gdx.Game;

public class GorkyGame extends Game {

	@Override
	public void create() {
		setScreen(new FightScreen());
	}
	
}
