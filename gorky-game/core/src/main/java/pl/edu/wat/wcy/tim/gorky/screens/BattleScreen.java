package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.AnimatedActor;
import pl.edu.wat.wcy.tim.gorky.actors.KnightActor;
import pl.edu.wat.wcy.tim.gorky.actors.OrcActor;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BattleScreen extends AbstractGameScreen {
	
	private static final String TAG = BattleScreen.class.getName();
	
	private Stage stage;
	private Skin skinGorkyBattle;
	
	private Image imgBackground;
	private Button btnAttack;
	private Button btnMagic;
	private Button btnHeal;
	
	// aktorzy bioracy udzial w walce..
	private KnightActor knightActor;
	private AnimatedActor enemyActor;

	public BattleScreen(GorkyGame game) {
		super(game);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
	}
	
	private void rebuildStage () {
		skinGorkyBattle = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_BATTLE), new TextureAtlas(Constants.TEXTURE_ATLAS_BATTLE));
		
		Table layerBackground = buildBackgroundLayer();
		Table layerControls = buildControlsLayer();
		Table layerPlayer = buildPlayerLayer();
		Table layerEnemy = buildEnemyLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerPlayer);
		stack.add(layerEnemy);
		stack.add(layerControls);
	}

	private Table buildEnemyLayer() {
		Table layer = new Table();
		
		// tymczasowo ORC zawsze, pozniej bedzie w konsruktorze zmieniany enemy losowo..
		enemyActor = new OrcActor();
		layer.addActor(enemyActor);
		enemyActor.setPosition(550, 115);
		
		return layer;
	}

	private Table buildPlayerLayer() {
		Table layer = new Table();
		
		knightActor = new KnightActor();
		layer.addActor(knightActor);
		knightActor.setPosition(115, 115);
		
		return layer;
	}

	private Table buildControlsLayer() {
		Table layer = new Table();
		
		layer.center().bottom();
		
		// ATTACK
		btnAttack = new Button(skinGorkyBattle, "attack");
		layer.add(btnAttack).pad(10);
		
		btnAttack.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onAttackClicked();
			}
		});
		
		// MAGIC
		btnMagic = new Button(skinGorkyBattle, "magic");
		layer.add(btnMagic).pad(10);
		
		btnMagic.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onMagicClicked();
			}
		});
		
		// HEAL
		btnHeal = new Button(skinGorkyBattle, "heal");
		layer.add(btnHeal).pad(10);
		
		btnHeal.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onHealClicked();
			}
		});
		
		return layer;
	}
	
	private void onAttackClicked() {
		game.setScreen(new GameScreen(game));
	}
	
	private void onMagicClicked() {
			
	}
	
	private void onHealClicked() {
		
	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		
		imgBackground = new Image(skinGorkyBattle, "background");
		layer.add(imgBackground);
		
		return layer;
	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}

	@Override
	public void resize(int width, int height) {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	@Override
	public void show() {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
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
