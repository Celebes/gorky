package pl.edu.wat.wcy.tim.gorky.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {
	public static final String TAG = GamePreferences.class.getName();

	public static final GamePreferences instance = new GamePreferences();

	public boolean showFpsCounter;
	
	// statystyki gracza
	

	private Preferences prefs;

	// singleton - prywatny konstruktor
	private GamePreferences() {
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
	}

}
