package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	// wszystkie elementy
	public AssetGrass grass;
	public AssetWall wall;
	public AssetPlayer player;
	
	// prywatny konstruktor oznacza, ze klasa jest Singletonem - nie mozna jej inicjalizowac z innych klas
	private Assets() {}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		
		// ustaw error handlera
		assetManager.setErrorListener(this);
		
		// wczytaj atlas textur
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		
		// zacznij wczytywac rzeczy i poczekaj do konca
		assetManager.finishLoading();
		
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		
		for(String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		
		// wlacz filtrowanie pixeli zeby byly bardziej gladkie
		for(Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		// utworz obiekty
		grass = new AssetGrass(atlas);
		wall = new AssetWall(atlas);
		player = new AssetPlayer(atlas);
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
	}
	
	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	public class AssetGrass {
		public final AtlasRegion grass;
		
		public AssetGrass(TextureAtlas atlas) {
			grass = atlas.findRegion("tile_grass");
		}
	}
	
	public class AssetWall {
		public final AtlasRegion wall;
		
		public AssetWall(TextureAtlas atlas) {
			wall = atlas.findRegion("tile_wall");
		}
	}
	
	public class AssetPlayer {
		public final AtlasRegion player;
		public final AtlasRegion player_right;
		
		public AssetPlayer(TextureAtlas atlas) {
			player = atlas.findRegion("player");
			player_right = atlas.findRegion("player_right");
		}
	}
	
	public class AssetEnemy {
		public final AtlasRegion enemy;
		
		public AssetEnemy(TextureAtlas atlas) {
			enemy = atlas.findRegion("enemy");
		}
	}
	
}