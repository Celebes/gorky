package pl.edu.wat.wcy.tim.gorky.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class LoginPreferences {
	public static final String TAG = LoginPreferences.class.getName();

	public static final LoginPreferences instance = new LoginPreferences();
	
	private Preferences prefs;
	
	public boolean loggedIn;
	public String login;
	public String password;
	public int idUser;
	
	// singleton - prywatny konstruktor
	private LoginPreferences() {
		prefs = Gdx.app.getPreferences(Constants.LOGIN_PREFERENCES);
	}
	
	public void load () {
		loggedIn = prefs.getBoolean("loggedIn", false);
		login = prefs.getString("login");
		password = prefs.getString("password");
		idUser = prefs.getInteger("idUser");
	}
	
	public void save() {
		prefs.putBoolean("loggedIn", loggedIn);
		prefs.putString("login", login);
		prefs.putString("password", password);
		prefs.putInteger("idUser", idUser);
		
		prefs.flush();
	}
}
