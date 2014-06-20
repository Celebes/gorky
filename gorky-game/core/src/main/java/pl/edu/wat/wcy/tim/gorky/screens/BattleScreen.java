package pl.edu.wat.wcy.tim.gorky.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.BattleActor;
import pl.edu.wat.wcy.tim.gorky.actors.KnightActor;
import pl.edu.wat.wcy.tim.gorky.actors.OrcActor;
import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;
import pl.edu.wat.wcy.tim.gorky.objects.Enemy;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	private BattleActor knightActor;
	private BattleActor enemyActor;
	
	private Player player;
	private Enemy enemy;
	
	// koniec walki
	private boolean battleEnd = false;
	
	// tura
	private boolean playerTurn = true;

	public BattleScreen(GorkyGame game, Player player) {
		super(game);
		this.player = player;
		initEnemy();
		System.out.println();
	}

	private void initEnemy() {
		// tworzy losowego przeciwnika
		enemy = new Enemy();
		
		// statystyki
		CharacterAttributes enemyAttributes = new CharacterAttributes();
		
		// podstawowe statystyki
		enemyAttributes.setAtk(9);
		enemyAttributes.setDef(2);
		enemyAttributes.setMagAtk(0);
		enemyAttributes.setMagDef(5);
		enemyAttributes.setMaxHP(15);
		enemyAttributes.setMaxMP(5);
		enemyAttributes.setHP(enemyAttributes.getMaxHP());
		enemyAttributes.setMP(enemyAttributes.getMaxMP());

		enemy.setCharacterAttributes(enemyAttributes);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
		
		if(battleEnd == true) {
			System.out.println("KONIEC WALKI!");
			
			// zapisz stan gry
			
			// zmien ekran
			game.setScreen(new GameScreen(game));
		} else {
			
			if(playerTurn == true) {
				
				if(knightActor.isAttackFinished() == true) {
					// pobierz obrazenia gracza
					float playerDMG = player.getCalculatedDamage();
					
					// odejmij HP wroga
					int dmgReceivedByEnemy = enemy.receiveDamage(playerDMG);
					
					// wlacz animacje dostawania obrazen u wroga
					startDamageAnimation(enemyActor);
					
					// zrob cos z dmgReceivedByEnemy..
					System.out.println("WOW! Gracz uderzyl przeciwnika zabierajac mu " + dmgReceivedByEnemy + " punktow zycia!");
					
					// jesli wrog zginal, zakoncz walke
					if(enemy.getCharacterAttributes().getHP() <= 0) {
						battleEnd = true;
					}
				}
				
				if(enemyActor.isDamageFinished() == true) {
					playerTurn = false;
					startAttackAnimation(enemyActor);
				}
				
			} else {
				
				if(enemyActor.isAttackFinished() == true) {
					// pobierz obrazenia wroga
					float enemyDMG = enemy.getCalculatedDamage();
					
					// odejmij HP gracza
					int dmgReceivedByPlayer = player.receiveDamage(enemyDMG);
					
					// wlacz animacje dostawania obrazen u wroga
					startDamageAnimation(knightActor);
					
					// zrob cos z dmgReceivedByPlayer..
					System.out.println("WOW! Przeciwnik uderzyl gracza zabierajac mu " + dmgReceivedByPlayer + " punktow zycia!");
					
					// jesli gracz zginal, zakoncz walke
					if(player.getCharacterAttributes().getHP() <= 0) {
						battleEnd = true;
					}
				}
				
				if(knightActor.isDamageFinished()) {
					playerTurn = true;
					showMenuButtons(true);
				}
				
			}
		}
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
		showMenuButtons(false);
		startAttackAnimation(knightActor);
	}
	
	private void startAttackAnimation(final BattleActor attacker) {
		float attackDelay = 1.0f;
		SequenceAction seq = sequence();
		seq.addAction(delay(attackDelay));
		
		seq.addAction(run(new Runnable() {
			
			@Override
			public void run() {
				attacker.startAttackAnimation();
			}
		}));

		stage.addAction(seq);
	}
	
	private void startDamageAnimation(final BattleActor defender) {
		SequenceAction seq = sequence();
		
		seq.addAction(run(new Runnable() {
			
			@Override
			public void run() {
				defender.startDamageAnimation();
			}
		}));

		stage.addAction(seq);
	}
	
	private void onMagicClicked() {

	}
	
	private void onHealClicked() {
		game.setScreen(new GameScreen(game));
	}
	
	private void showMenuButtons (boolean visible) {
		float moveDuration = 1.0f;
		Interpolation moveEasing = Interpolation.swing;
		float delayOptionsButton = 0.25f;

		float moveY = 150 * (visible ? 1 : -1);
		float moveX = 0 * (visible ? -1 : 1);
		final Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
		btnAttack.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		btnMagic.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		btnHeal.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));

		SequenceAction seq = sequence();
		if (visible) seq.addAction(delay(delayOptionsButton + moveDuration));
		seq.addAction(run(new Runnable() {
			public void run () {
				btnMagic.setTouchable(touchEnabled);
				btnHeal.setTouchable(touchEnabled);
				btnAttack.setTouchable(touchEnabled);
			}
		}));
		
		stage.addAction(seq);
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
