package pl.edu.wat.wcy.tim.gorky.util;

public class Constants {
	
	// widziany swiat ma 12.5 jednostek szerokosci i 7.5 wysokosci (64 pixele na metr)
	public static final float VIEWPORT_WIDTH = 12.5f;
	public static final float VIEWPORT_HEIGHT = 7.5f;
	
	// lokalizacja pliku zawieraj¹cego atlas textur
	public static final String TEXTURE_ATLAS_OBJECTS = "images/gorky.pack";
	
	// splash screeny
	public static final String MENU_SPLASH_SCREEN = "images/LOGO_TIM.png";
	public static final String BATTLE_SPLASH_SCREEN = "images/BATTLE_UNDER_CONSTRUCTION.gif";
	
	// lokalizacja pliku opisujacego poziom 01
	public static final String LEVEL_01 = "levels/level-01.png";
	public static final String LEVEL_02 = "levels/level-02.png";
	
	// ilosc przeciwnikow w poziomach
	public static final int LEVEL_02_ENEMY_COUNT = 5;
	
	public static float CURRENT_MAP_WIDTH = 0.0f;
	public static float CURRENT_MAP_HEIGHT = 0.0f;
	
	public static final String GAME_PREFERENCES = "game_preferences.prefs";
	public static final String SAVE_STATE_PREFERENCES = "save_state.prefs";
	
	// GUI
	public static final float VIEWPORT_GUI_WIDTH = 800.0f;
	public static final float VIEWPORT_GUI_HEIGHT = 480.0f;
	
	public static final String TEXTURE_ATLAS_UI = "images/gorky-ui.pack";
	public static final String SKIN_GORKY_UI = "images/gorky-ui.json";
	
	public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";
	public static final String SKIN_LIBGDX_UI = "images/uiskin.json";
	
	public static final String TEXTURE_ATLAS_BATTLE = "images/gorky-battle.pack";
	public static final String SKIN_GORKY_BATTLE = "images/gorky-battle.json";
	
	public static final String TEXTURE_ATLAS_KNIGHT_BATTLE = "images/gorky-knight.pack";
	public static final String TEXTURE_ATLAS_ORC_BATTLE = "images/gorky-orc.pack";
	
}
