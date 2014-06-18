package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
	public static final String TAG = Assets.class.getName();
	
	public static TextureAtlas gorkyAtlas;
	
	// player
	public static AtlasRegion player_down;
	public static AtlasRegion player_right;
	public static AtlasRegion player_up;
	
	// enemy
	public static AtlasRegion enemy;
	
	// wall
	public static AtlasRegion wall;
	
	// grass
	public static AtlasRegion grass;
	
	// splash screeny
	public static Texture menuTexture;
	public static Texture battleTexture;
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () {
		gorkyAtlas = new TextureAtlas(Gdx.files.internal(Constants.TEXTURE_ATLAS_OBJECTS));
		
		// player
		player_down = gorkyAtlas.findRegion("player_knight_down");
		player_right = gorkyAtlas.findRegion("player_knight_right");
		player_up = gorkyAtlas.findRegion("player_knight_up");
		
		// enemy
		enemy = gorkyAtlas.findRegion("enemy");
		
		// grass
		grass = gorkyAtlas.findRegion("tile_grass");
		
		// wall
		wall = gorkyAtlas.findRegion("tile_wall");
		
		// splash screeny
		menuTexture = loadTexture("images/LOGO_TIM.png");
		menuTexture = loadTexture("images/BATTLE_UNDER_CONSTRUCTION.gif");
	}
}
