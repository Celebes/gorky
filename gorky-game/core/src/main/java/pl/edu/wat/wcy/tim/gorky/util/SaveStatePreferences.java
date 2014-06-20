package pl.edu.wat.wcy.tim.gorky.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveStatePreferences {
	public static final String TAG = SaveStatePreferences.class.getName();

	public static final SaveStatePreferences instance = new SaveStatePreferences();
	
	private Preferences prefs;
	
	public float playerPositionX;
	public float playerPositionY;
	public String currentLevel;

	// singleton - prywatny konstruktor
	private SaveStatePreferences() {
		prefs = Gdx.app.getPreferences(Constants.GAME_PREFERENCES);
	}
	
	public void load () {
		playerPositionX = prefs.getFloat("playerPositionX", 11.0f);
		playerPositionY = prefs.getFloat("playerPositionY", 13.0f);
		currentLevel = prefs.getString("currentLevel", Constants.LEVEL_02);
	}
	
	public void save () {
		prefs.putFloat("playerPositionX", playerPositionX);
		prefs.putFloat("playerPositionY", playerPositionY);
		prefs.putString("currentLevel", currentLevel);
		prefs.flush();
	}
}
