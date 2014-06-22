package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.I18NBundle;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	// wszystkie elementy
	public AssetGrass grass;
	public AssetWall wall;
	public AssetPlayer player;
	public AssetEnemy enemy;
	
	// animowanie aktorzy z battle screen
	public AssetKnightBattle knight;
	public AssetOrcBattle orc;
	
	// dzwieki
	public AssetSounds sounds;
	public AssetMusic music;
	
	// animacje atakow
	public AssetSwordSwing swordSwing;
	
	// lokalizacja
	public I18NBundle stringBundle;
	
	// czcionka
	public AssetFonts fonts;
	public AssetFonts fontsUpsideDown;
	public AssetFonts battleFonts;
	public AssetFonts battleFontsSmall;
	
	// pasek postepu
	public AtlasRegion progressBarImg;
	
	// czar leczenia
	public AssetHealSpell healSpell;
	
	// prywatny konstruktor oznacza, ze klasa jest Singletonem - nie mozna jej inicjalizowac z innych klas
	private Assets() {}
	
	public Texture loadTexture(String name) {
		assetManager.unload(name);
		assetManager.load(name, Texture.class);
		assetManager.finishLoading();
		return assetManager.get(name, Texture.class);
	}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		
		// wczytaj locale
		FileHandle baseFileHandle = Gdx.files.internal("i18n/strings");
		stringBundle = I18NBundle.createBundle(baseFileHandle);
		
		// ustaw error handlera
		assetManager.setErrorListener(this);
		
		// wczytaj atlas textur dla GameScreen
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.load(Constants.TEXTURE_ATLAS_KNIGHT_BATTLE, TextureAtlas.class);
		assetManager.load(Constants.TEXTURE_ATLAS_ORC_BATTLE, TextureAtlas.class);
		assetManager.load(Constants.TEXTURE_ATLAS_BATTLE, TextureAtlas.class);
		assetManager.load(Constants.TEXTURE_ATLAS_HEAL_SPELL, TextureAtlas.class);
		
		// wczytaj dzwieki
		assetManager.load("sounds/player_attack.ogg", Sound.class);
		assetManager.load("sounds/player_hit.ogg", Sound.class);
		assetManager.load("sounds/orc_attack.ogg", Sound.class);
		assetManager.load("sounds/orc_hit.ogg", Sound.class);
		assetManager.load("sounds/heal_spell.ogg", Sound.class);
		
		// wczytaj muzyke
		assetManager.load("music/music_battle.ogg", Music.class);
		assetManager.load("music/music_game.ogg", Music.class);
		assetManager.load("music/music_menu.ogg", Music.class);
		assetManager.load("music/music_victory.ogg", Music.class);
		assetManager.load("music/music_sacred.ogg", Music.class);
		
		// zacznij wczytywac rzeczy i poczekaj do konca
		assetManager.finishLoading();
		
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		
		for(String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas atlasGameScreen = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		
		// wlacz filtrowanie pixeli zeby byly bardziej gladkie
		for(Texture t : atlasGameScreen.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		// utworz czcionki
		fonts = new AssetFonts("images/arial_pl.fnt", true);
		fontsUpsideDown = new AssetFonts("images/arial_pl.fnt", false);
		battleFonts = new AssetFonts("images/battle_font.fnt", false);
		battleFontsSmall = new AssetFonts("images/battle_font_small.fnt", false);
		
		// utworz obiekty
		grass = new AssetGrass(atlasGameScreen);
		wall = new AssetWall(atlasGameScreen);
		player = new AssetPlayer(atlasGameScreen);
		enemy = new AssetEnemy(atlasGameScreen);
		
		TextureAtlas atlasKnightBattleScreen = assetManager.get(Constants.TEXTURE_ATLAS_KNIGHT_BATTLE);
		knight = new AssetKnightBattle(atlasKnightBattleScreen);
		
		TextureAtlas atlasOrcBattleScreen = assetManager.get(Constants.TEXTURE_ATLAS_ORC_BATTLE);
		orc = new AssetOrcBattle(atlasOrcBattleScreen);
		
		TextureAtlas atlasBattleScreen = assetManager.get(Constants.TEXTURE_ATLAS_BATTLE);
		swordSwing = new AssetSwordSwing(atlasBattleScreen);
		
		TextureAtlas atlasHealSpell = assetManager.get(Constants.TEXTURE_ATLAS_HEAL_SPELL);
		healSpell = new AssetHealSpell(atlasHealSpell);
		
		// dzwieki
		sounds = new AssetSounds(assetManager);
		music = new AssetMusic(assetManager);
		
		// pasek postepu
		progressBarImg = atlasBattleScreen.findRegion("progress_bar_img");
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
		public final AtlasRegion player_down;
		public final AtlasRegion player_right;
		public final AtlasRegion player_up;
		
		public AssetPlayer(TextureAtlas atlas) {
			player_down = atlas.findRegion("player_knight_down");
			player_right = atlas.findRegion("player_knight_right");
			player_up = atlas.findRegion("player_knight_up");
		}
	}
	
	// postac gracza w czasie walki
	public class AssetKnightBattle {
		public final Animation animNormal;
		public final Animation animAttack;
		public final Animation animDamage;
		
		public AssetKnightBattle(TextureAtlas atlas) {
			Array<AtlasRegion> regions = null;
			
			// normal
			regions = atlas.findRegions("anim_knight_normal");
			animNormal = new Animation(1.0f / 5.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
			
			// attack
			regions = atlas.findRegions("anim_knight_attack");
			animAttack = new Animation(1.0f / 10.0f, regions);
			
			// damage
			regions = atlas.findRegions("anim_knight_damage");
			animDamage = new Animation(1.0f / 10.0f, regions);
		}
	}
	
	public class AssetOrcBattle {
		public final Animation animNormal;
		public final Animation animAttack;
		public final Animation animDamage;
		
		public AssetOrcBattle(TextureAtlas atlas) {
			Array<AtlasRegion> regions = null;
			
			// normal
			regions = atlas.findRegions("anim_orc_normal");
			animNormal = new Animation(1.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
			
			// attack
			regions = atlas.findRegions("anim_orc_attack");
			animAttack = new Animation(1.0f / 15.0f, regions);
			
			// damage
			regions = atlas.findRegions("anim_orc_damage");
			animDamage = new Animation(1.0f / 5.0f, regions);
		}
	}
	
	public class AssetHealSpell {
		public final Animation animHealSpell;
		
		public AssetHealSpell(TextureAtlas atlas) {
			Array<AtlasRegion> regions = null;
			
			regions = atlas.findRegions("anim_heal_spell");
			animHealSpell = new Animation (1.0f / 15.0f, regions);
		}
	}
	
	public class AssetSwordSwing {
		public final Animation animSwordSwing;
		
		public AssetSwordSwing(TextureAtlas atlas) {
			Array<AtlasRegion> regions = null;
			
			// normal
			regions = atlas.findRegions("anim_sword_swing");
			animSwordSwing = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.NORMAL);
		}
	}
	
	public class AssetEnemy {
		public final AtlasRegion enemy;
		
		public AssetEnemy(TextureAtlas atlas) {
			enemy = atlas.findRegion("enemy");
		}
	}
	
	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;
		
		public AssetFonts(String fontName, boolean flip) {
			defaultSmall = new BitmapFont(Gdx.files.internal(fontName), flip);
			defaultNormal = new BitmapFont(Gdx.files.internal(fontName), flip);
			defaultBig = new BitmapFont(Gdx.files.internal(fontName), flip);
			
			// rozmiary czcionek
			defaultSmall.setScale(0.75f);
			defaultNormal.setScale(0.95f);
			defaultBig.setScale(2.0f);
			
			// wlacz 'linear-filtering' by wygladzic czcionki
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
	
	public class AssetSounds {
		public final Sound playerAttack;
		public final Sound playerHit;
		public final Sound enemyAttack;
		public final Sound enemyHit;
		public final Sound healSpell;

		public AssetSounds(AssetManager am) {
			playerAttack = am.get("sounds/player_attack.ogg", Sound.class);
			playerHit = am.get("sounds/player_hit.ogg", Sound.class);
			enemyAttack = am.get("sounds/orc_attack.ogg", Sound.class);
			enemyHit = am.get("sounds/orc_hit.ogg", Sound.class);
			healSpell = am.get("sounds/heal_spell.ogg", Sound.class);
		}
	}
	
	public class AssetMusic {
		public final Music menuMusic;
		public final Music gameMusic;
		public final Music battleMusic;
		public final Music victoryMusic;
		public final Music sacredMusic;

		public AssetMusic(AssetManager am) {
			menuMusic = am.get("music/music_menu.ogg", Music.class);
			gameMusic = am.get("music/music_game.ogg", Music.class);
			battleMusic = am.get("music/music_battle.ogg", Music.class);
			victoryMusic = am.get("music/music_victory.ogg", Music.class);
			sacredMusic = am.get("music/music_sacred.ogg", Music.class);
		}
	}
	
}
