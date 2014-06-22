package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.game.WorldController;
import pl.edu.wat.wcy.tim.gorky.game.WorldRenderer;
import pl.edu.wat.wcy.tim.gorky.util.AudioManager;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen extends AbstractGameScreen {
	private static final String TAG = GameScreen.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
	// przycisk na dole
	private Skin skinGorkyMap;
	private Stage stage;
	private Button btnEq;
	
	private boolean paused;
	private boolean eqButtonClicked = false;
	private boolean restartMusic;

	public GameScreen(GorkyGame game, boolean restartMusic) {
		super(game);
		SaveStatePreferences.instance.load();
		this.restartMusic = restartMusic;
	}
	
	@Override
	public void render(float deltaTime) {
		
		if(eqButtonClicked == true) {
			SaveStatePreferences.instance.save();
			//game.setScreen(new InventoryScreen(game));
			//game.setScreen(new NewGameScreen(game));
			game.setScreen(new LoginScreen(game));
		}
		
		else if(worldController.isOnCollisionWithTeleport()) {
			game.setScreen(new GameScreen(game, worldController.isRestartMusic()));
		}
		
		else if(worldController.isCollisionWithEnemy()) {
			
			SaveStatePreferences.instance.save();
			game.setScreen(new BattleScreen(game, worldController.level.player));
			
		} else {
			
			if(!paused) {
				worldController.update(deltaTime);
			}
			
			Gdx.gl.glClearColor(57.0f / 255.0f, 181.0f / 225.0f, 115.0f / 255.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			worldRenderer.render();
			
			// przycisk przejscia do EQ
			stage.act(deltaTime);
			stage.draw();
		}

	}

	@Override
	public void resize(int width, int height) {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		worldRenderer.resize(width, height);
		rebuildStageEqGui();
	}

	@Override
	public void show() {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
		
		if(restartMusic == true) {
			if(SaveStatePreferences.instance.currentLevel.equals(Constants.LEVEL_01)) {
				AudioManager.instance.play(Assets.instance.music.sacredMusic);
			} else {
				AudioManager.instance.play(Assets.instance.music.gameMusic);
			}
		}
		
		// build EQ gui
		rebuildStageEqGui();
	}
	
	private void rebuildStageEqGui() {
		skinGorkyMap = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_MAP), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		
		Table layerVersus = buildEqButtonLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerVersus);
	}

	private Table buildEqButtonLayer() {
		Table layer = new Table();
		
		//layer.center().bottom();
		layer.right().top();
		layer.pad(10);
		
		// ATTACK
		btnEq = new Button(skinGorkyMap, "eq");
		layer.add(btnEq);
		
		btnEq.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onEqClicked();
			}
		});
		
		return layer;
	}
	
	private void onEqClicked() {
		System.out.println("EQ!");
		eqButtonClicked = true;
	}

	@Override
	public void hide() {
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
		stage.dispose();
	}

	@Override
	public void pause() {
		paused = true;
	}
	
	@Override
	public void resume() {
		super.resume();
		// Only called on Android!
		paused = false;
	}
	
	@Override
	public InputProcessor getInputProcessor() {
		return worldController;
	}

}
