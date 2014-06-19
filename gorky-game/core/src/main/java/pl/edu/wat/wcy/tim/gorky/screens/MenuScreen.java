package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen extends AbstractGameScreen {
	
	private static final String TAG = MenuScreen.class.getName();
	
	//private SpriteBatch batch;
	//private Texture img;
	
	// GUI
	private Stage stage;
	private Skin skinGorky;
	
	// menu
	private Image imgBackground;
	private Image imgLogo;
	private Image imgInfo;
	private Image imgKnight;
	private Button btnMenuPlay;
	private Button btnMenuOptions;
	
	// options
	private Window winOptions;
	private TextButton btnWinOptSave;
	private TextButton btnWinOptCancel;
	private CheckBox chkSound;
	private Slider sldSound;
	private CheckBox chkMusic;
	private Slider sldMusic;
	private CheckBox chkShowFpsCounter;
	
	// debug
	private final float DEBUG_REBUILD_INTERVAL = 5.0f;
	private boolean debugEnabled = false;
	private float debugRebuildStage;
	
	public MenuScreen(GorkyGame game) {
		super(game);
	}
	
	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (debugEnabled) {
			debugRebuildStage -= deltaTime;
			if (debugRebuildStage <= 0) {
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
		
		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
	}
	
	private void rebuildStage () {
		skinGorky = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();
		Table layerLogo = buildLogoLayer();
		Table layerInfo = buildInfoLayer();
		Table layerControls = buildControlsLayer();
		Table layerOptionsWindow = buildOptionsWindowLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		stack.add(layerLogo);
		stack.add(layerInfo);
		stack.add(layerControls);
		stage.addActor(layerOptionsWindow);
	}

	private Table buildControlsLayer() {
		Table layer = new Table();
		layer.right().bottom();
		layer.setPosition(5, 5);

		// PLAY
		btnMenuPlay = new Button(skinGorky, "play");
		layer.add(btnMenuPlay).pad(20);
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onPlayClicked();
			}
		});
		
		layer.row();

		// OPTIONS
		btnMenuOptions = new Button(skinGorky, "options");
		layer.add(btnMenuOptions).padBottom(20);
		btnMenuOptions.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onOptionsClicked();
			}
		});
		
		if (debugEnabled)
			layer.debug();
		
		

		return layer;
	}
	
	private void onPlayClicked () {
		game.setScreen(new GameScreen(game));
	}
	
	private void onOptionsClicked () { }

	private Table buildOptionsWindowLayer() {
		Table layer = new Table();
		return layer;
	}

	private Table buildLogoLayer() {
		Table layer = new Table();

		layer.left().top();
		
		imgLogo = new Image(skinGorky, "logo");
		layer.add(imgLogo).pad(10);
		
		if (debugEnabled) layer.debug();
		
		return layer;
	}
	
	private Table buildInfoLayer() {
		Table layer = new Table();
		
		layer.left().bottom();
		
		imgInfo = new Image(skinGorky, "info");
		layer.add(imgInfo);
		
		if (debugEnabled) layer.debug();
		
		return layer;
	}

	private Table buildObjectsLayer() {
		Table layer = new Table();
		
		imgKnight = new Image(skinGorky, "knight");
		layer.addActor(imgKnight);
		imgKnight.setPosition(115, 85);
		
		return layer;
	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		
		imgBackground = new Image(skinGorky, "background");
		layer.add(imgBackground);
		
		return layer;
	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}

	@Override
	public void resize(int width, int height) {
		//stage.setViewport(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	@Override
	public void hide() {
		stage.dispose();
	}

	@Override
	public void pause() {}
	
}
