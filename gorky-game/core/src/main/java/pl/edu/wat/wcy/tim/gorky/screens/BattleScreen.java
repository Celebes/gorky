package pl.edu.wat.wcy.tim.gorky.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;
import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.BattleActor;
import pl.edu.wat.wcy.tim.gorky.actors.KnightActor;
import pl.edu.wat.wcy.tim.gorky.actors.OrcActor;
import pl.edu.wat.wcy.tim.gorky.actors.Text;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;
import pl.edu.wat.wcy.tim.gorky.objects.Enemy;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BattleScreen extends AbstractGameScreen {
	
	private static final String TAG = BattleScreen.class.getName();
	
	private Stage stage;
	private Skin skinGorkyBattle;
	private Skin skinLibgdx;
	
	private Image imgBackground;
	private Button btnAttack;
	private Button btnMagic;
	private Button btnHeal;
	
	// okienka na koniec walki
	private Window popupWindow;
	private TextButton popupOKButton;
	
	// aktorzy bioracy udzial w walce..
	private BattleActor playerActor;
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
		enemyAttributes.setMaxHP(25);
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
			
		} else {
			
			if(playerTurn == true) {
				
				if(playerActor.isAttackFinished() == true) {
					// pobierz obrazenia gracza
					float playerDMG = player.getCalculatedDamage();
					
					// odejmij HP wroga
					int dmgReceivedByEnemy = enemy.receiveDamage(playerDMG);
					
					// wlacz animacje dostawania obrazen u wroga
					startDamageAnimation(enemyActor);
					
					// zrob cos z dmgReceivedByEnemy..
					System.out.println("Gracz uderzyl przeciwnika zabierajac mu " + dmgReceivedByEnemy + " punktow zycia!");
					showTextAboveActor(enemyActor, String.valueOf(dmgReceivedByEnemy));
					
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
					startDamageAnimation(playerActor);
					
					// zrob cos z dmgReceivedByPlayer..
					System.out.println("Przeciwnik uderzyl gracza zabierajac mu " + dmgReceivedByPlayer + " punktow zycia!");
					showTextAboveActor(playerActor, String.valueOf(dmgReceivedByPlayer));
					
					// jesli gracz zginal, zakoncz walke
					if(player.getCharacterAttributes().getHP() <= 0) {
						battleEnd = true;
					}
				}
				
				if(playerActor.isDamageFinished()) {
					playerTurn = true;
					showMenuButtons(true);
				}
				
			}
		}
	}
	
	private void showTextAboveActor(BattleActor actor, String text) {
		Text dmgText = new Text(Assets.instance.battleFonts.defaultBig, text);
		dmgText.setPosition(actor.getX() + actor.getWidth()/2 - dmgText.getWidth()/2, actor.getY() + actor.getHeight() + (int)(1.5*dmgText.getHeight()));
		stage.addActor(dmgText);
	}
	
	private void rebuildStage () {
		skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));
		skinGorkyBattle = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_BATTLE), new TextureAtlas(Constants.TEXTURE_ATLAS_BATTLE));
		
		Table layerBackground = buildBackgroundLayer();
		Table layerControls = buildControlsLayer();
		Table layerPlayer = buildPlayerLayer();
		Table layerEnemy = buildEnemyLayer();
		Table popupWindow = buildpopupWindow();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerPlayer);
		stack.add(layerEnemy);
		stack.add(layerControls);
		stage.addActor(popupWindow);
	}

	private Table buildpopupWindow() {
		popupWindow = new Window("ZWYCIÊSTWO!", skinLibgdx);
		
		// dodaj informacje jakies
		popupWindow.add(buildPlayerPopupInfoSection()).row();
		
		// dodaj przycisk OK
		popupWindow.add(buildPlayerPopupButtonSection()).pad(10, 0, 10, 0);
		
		// ustaw kolor lekko przezroczysty
		popupWindow.setColor(1, 1, 1, 0.8f);
		
		// domyslnie ukryj
		//showPopupWindow(false, false);
		
		// przelicz rozmiar
		popupWindow.pack();
		
		// ustaw na srodku ekranu
		popupWindow.setPosition((Constants.VIEWPORT_GUI_WIDTH/2) - (popupWindow.getWidth()/2), (Constants.VIEWPORT_GUI_HEIGHT/2) - (popupWindow.getHeight()/2));
		
		return popupWindow;
	}
	
	private void showPopupWindow (boolean visible, boolean animated) {
		float alphaTo = visible ? 0.8f : 0.0f;
		float duration = animated ? 1.0f : 0.0f;
		Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
		popupWindow.addAction(sequence(touchable(touchEnabled), alpha(alphaTo, duration)));
	}

	private Table buildPlayerPopupInfoSection() {
		Table tbl = new Table();
		
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("TESTOWA WIADOMOSC", skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);
		
		return tbl;
	}
	
	private Table buildPlayerPopupButtonSection() {
		Table tbl = new Table();
		
		// + Separator
		Label lbl = null;
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.75f, 0.75f, 0.75f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
		tbl.row();
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.5f, 0.5f, 0.5f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
		tbl.row();
		
		// + Save Button with event handler
		popupOKButton = new TextButton("OK", skinLibgdx);
		popupOKButton.getCells().get(0).size(35);
		tbl.add(popupOKButton).bottom().center().padLeft(popupOKButton.getWidth());
		
		popupOKButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onPopupOKClicked();
			}
		});
		
		return tbl;
	}
	
	private void onPopupOKClicked() {
		System.out.println("OK!");
		game.setScreen(new GameScreen(game));
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
		
		playerActor = new KnightActor();
		layer.addActor(playerActor);
		playerActor.setPosition(115, 115);
		
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
		startAttackAnimation(playerActor);
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
