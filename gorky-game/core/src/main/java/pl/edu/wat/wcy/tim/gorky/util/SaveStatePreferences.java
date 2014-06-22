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
	
	// character attributes
	public int atk;				// sila ataku
	public int def;				// obrona przeciwko atakom fizycznym
	public int magAtk;			// sila ataku magicznego
	public int magDef;			// obrona przeciwko atakom magicznym
	public int HP;				// aktualne punkty zycia
	public int maxHP;			// max punkty zycia
	public int MP;				// aktualne punkty many
	public int maxMP;			// max punkty many
	
	public int gold;			// ilosc posiadanego zlota
	public int exp;				// ilosc posiadanego doswiadczenia
	public int level;			// aktualny poziom postaci
	
	// weapon
	public boolean weaponEquipped;
	public String weaponName;
	public int weaponAtk;

	// singleton - prywatny konstruktor
	private SaveStatePreferences() {
		prefs = Gdx.app.getPreferences(Constants.GAME_PREFERENCES);
	}
	
	public void load () {
		playerPositionX = prefs.getFloat("playerPositionX", 6);
		playerPositionY = prefs.getFloat("playerPositionY", 4);
		currentLevel = prefs.getString("currentLevel", Constants.LEVEL_01);
		
		// character attributes
		atk = prefs.getInteger("atk", 8);
		def = prefs.getInteger("def", 5);
		magAtk = prefs.getInteger("magAtk", 15);
		magDef = prefs.getInteger("magDef", 5);
		HP = prefs.getInteger("HP", 50);
		maxHP = prefs.getInteger("maxHP", 50);
		MP = prefs.getInteger("MP", 25);
		maxMP = prefs.getInteger("maxMP", 25);
		gold = prefs.getInteger("gold", 100);
		exp = prefs.getInteger("exp", 0);
		level = prefs.getInteger("level", 1);
		
		// weapon
		weaponEquipped = prefs.getBoolean("weaponEquipped", false);
		weaponName = prefs.getString("weaponName");
		weaponAtk = prefs.getInteger("weaponAtk");
	}
	
	public void save() {
		prefs.putFloat("playerPositionX", playerPositionX);
		prefs.putFloat("playerPositionY", playerPositionY);
		prefs.putString("currentLevel", currentLevel);
		
		// character attributes
		prefs.putInteger("atk", atk);
		prefs.putInteger("def", def);
		prefs.putInteger("magAtk", magAtk);
		prefs.putInteger("magDef", magDef);
		prefs.putInteger("HP", HP);
		prefs.putInteger("maxHP", maxHP);
		prefs.putInteger("MP", MP);
		prefs.putInteger("maxMP", maxMP);
		prefs.putInteger("gold", gold);
		prefs.putInteger("exp", exp);
		prefs.putInteger("level", level);
		
		// weapon
		prefs.putBoolean("weaponEquipped", weaponEquipped);
		prefs.putString("weaponName", weaponName);
		prefs.putInteger("weaponAtk", weaponAtk);
		
		prefs.flush();
	}
	
	public void reset() {
		prefs.clear();
		prefs.flush();
	}
}
