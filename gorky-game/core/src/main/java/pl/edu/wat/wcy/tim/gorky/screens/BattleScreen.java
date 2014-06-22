package pl.edu.wat.wcy.tim.gorky.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;

import java.util.Random;

import javax.sound.midi.Sequence;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.BattleActor;
import pl.edu.wat.wcy.tim.gorky.actors.DamageText;
import pl.edu.wat.wcy.tim.gorky.actors.KnightActor;
import pl.edu.wat.wcy.tim.gorky.actors.NumberText;
import pl.edu.wat.wcy.tim.gorky.actors.OrcActor;
import pl.edu.wat.wcy.tim.gorky.actors.ProgressBarActor;
import pl.edu.wat.wcy.tim.gorky.actors.Text;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.Enemy;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.util.AudioManager;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.GameplayFormulas;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BattleScreen extends AbstractGameScreen {
	
	private static final String TAG = BattleScreen.class.getName();
	
	private Stage stage;
	private Stage stageVersusGui;
	private Skin skinGorkyBattle;
	private Skin skinLibgdx;
	
	private Image imgBackground;
	private Button btnAttack;
	private Button btnMagic;
	private Button btnHeal;
	private Image imgVersus;
	
	// nazwa + level
	private Text playerNameAndLevelText;
	private Text enemyNameAndLevelText;
	
	// paski HP i MP
	ProgressBarActor playerHPBar;
	ProgressBarActor playerMPBar;
	ProgressBarActor enemyHPBar;
	ProgressBarActor enemyMPBar;
	
	NumberText playerHPNumber;
	NumberText playerMPNumber;
	NumberText enemyHPNumber;
	NumberText enemyMPNumber;
	
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
	private boolean assignPopupStrings = true;
	private boolean showPopup = true;
	
	String popupHeader;
	String popupInfo;
	
	// tura
	private boolean playerTurn = true;
	
	Random r = new Random();

	public BattleScreen(GorkyGame game, Player player) {
		super(game);
		this.player = player;
		initEnemy();
		System.out.println();
	}

	private void initEnemy() {
		// tworzy losowego przeciwnika
		enemy = new Enemy();

		enemy.setCharacterAttributes(GameplayFormulas.generateEnemyAttributes());
		
		System.out.println("Rozpoczeto walke z przeciwnikiem o poziomie: " + enemy.getCharacterAttributes().getLevel());
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(deltaTime);
		stage.draw();
		
		// tutaj narysuj prostokaciki z HP, MP itp
		
		stageVersusGui.act(deltaTime);
		stageVersusGui.draw();
		
		Table.drawDebug(stage);
		
		if(battleEnd == true) {
			
			boolean playerWON = (player.getCharacterAttributes().getHP() > 0);

			// ustaw teksty do popupa
			if(assignPopupStrings == true) {
				StringBuilder sb = new StringBuilder();
				
				if(playerWON == false) {
					// gracz przegral
					popupHeader = Assets.instance.stringBundle.get("battle_popup_defeat_header");
					popupInfo = Assets.instance.stringBundle.get("battle_popup_defeat_info");
					
					player.deathExpPenalty();
					
					// wywal gracza na poziom numer 1
					SaveStatePreferences.instance.currentLevel = Constants.LEVEL_01;
					SaveStatePreferences.instance.playerPositionX = 6;
					SaveStatePreferences.instance.playerPositionY = 4;
					
					SaveStatePreferences.instance.save();
				} else {
					// gracz wygral
					
					// wlacz muzyczke
					AudioManager.instance.play(Assets.instance.music.victoryMusic);
					
					// losuj jakies przedmioty w nagrode albo zloto
					int expPointsAfterWin = GameplayFormulas.getExpFromEnemy(enemy.getCharacterAttributes().getLevel());
					int goldCoinsAfterWin = GameplayFormulas.getGoldFromEnemy(enemy.getCharacterAttributes().getLevel());
					
					// dodaj expa
					boolean levelUP = player.increaseExperience(expPointsAfterWin);
					
					// dodaj golda
					player.increaseGold(goldCoinsAfterWin);
					
					// utworz okienko					
					popupHeader = Assets.instance.stringBundle.get("battle_popup_victory_header");
					
					sb.append(Assets.instance.stringBundle.format("battle_popup_victory_exp_info", expPointsAfterWin));
					
					if(levelUP) {
						sb.append(Assets.instance.stringBundle.format("battle_popup_victory_level_info", player.getCharacterAttributes().getLevel()));
						
						// aktualizuj level
						playerNameAndLevelText.setText("Player Lv. " + player.getCharacterAttributes().getLevel());
						
						// dodaj zycia i mane
						playerHPBar.setPercent(player.calculateHpPercent());
						playerMPBar.setPercent(player.calculateMpPercent());
						
						// aktualizuj cyferki
						playerHPNumber.setNumber(player.getCharacterAttributes().getHP());
						playerMPNumber.setNumber(player.getCharacterAttributes().getMP());
					}
					
					sb.append(Assets.instance.stringBundle.format("battle_popup_victory_gold_info", goldCoinsAfterWin));
					
					//sb.append(Assets.instance.stringBundle.format("battle_popup_victory_item_info", "LELE"));
					
					popupInfo = sb.toString();
					sb.setLength(0);
				}
				
				assignPopupStrings = false;
				
				System.out.println("STATSY PO WALCE:\nLEVEL = " + SaveStatePreferences.instance.level + " | HP = " + SaveStatePreferences.instance.HP + " | EXP = " + SaveStatePreferences.instance.exp);
			}
			
			if(showPopup == true) {
				Table popupWindow = buildpopupWindow(popupHeader, popupInfo);
				stage.addActor(popupWindow);
				showPopupWindow(true, true);
				
				showPopup = false;
			}
			
		} else {
			
			if(playerTurn == true) {
				
				if(playerActor.isAttackFinished() == true) {
					// pobierz obrazenia gracza
					float playerDMG = player.getCalculatedDamage();
					
					// odejmij HP wroga
					int dmgReceivedByEnemy = enemy.receiveDamage(playerDMG);
					
					// wlacz animacje dostawania obrazen u wroga
					startDamageAnimation(enemyActor);
					
					// dzwiek obrazen wroga
					AudioManager.instance.play(Assets.instance.sounds.enemyHit);
					
					// aktualizuj pasek zdrowia
					enemyHPBar.setPercent(enemy.calculateHpPercent());
					
					// aktualizuj cyferki HP
					enemyHPNumber.setNumber(enemy.getCharacterAttributes().getHP());
					
					// zrob cos z dmgReceivedByEnemy..
					System.out.println("Gracz uderzyl przeciwnika zabierajac mu " + dmgReceivedByEnemy + " punktow zycia!");
					showTextAboveActor(enemyActor, String.valueOf(dmgReceivedByEnemy));
					
					// jesli wrog zginal, zakoncz walke
					if(enemy.getCharacterAttributes().getHP() <= 0) {
						battleEnd = true;
						
						// wyswietl animacje smierci wroga
						enemyActor.startDeathAnimation();
						
						// dzwiek obrazen wroga
						AudioManager.instance.play(Assets.instance.sounds.enemyHit);
					}
				}
				
				if(enemyActor.isDamageFinished() == true) {
					playerTurn = false;
					startAttackAnimation(enemyActor);
					// dzwiek ataku przeciwnika
					playSoundWithDelay(0.7f, Assets.instance.sounds.enemyAttack);
				}
				
			} else {
				
				if(enemyActor.isAttackFinished() == true) {
					// pobierz obrazenia wroga
					float enemyDMG = enemy.getCalculatedDamage();
					
					// odejmij HP gracza
					int dmgReceivedByPlayer = player.receiveDamage(enemyDMG);
					
					// wlacz animacje dostawania obrazen u gracza
					startDamageAnimation(playerActor);
					
					// dzwiek obrazen gracza
					AudioManager.instance.play(Assets.instance.sounds.playerHit);
					
					// aktualizuj pasek zdrowia
					playerHPBar.setPercent(player.calculateHpPercent());
					
					// aktualizuj cyferki HP
					playerHPNumber.setNumber(player.getCharacterAttributes().getHP());
					
					// zrob cos z dmgReceivedByPlayer..
					System.out.println("Przeciwnik uderzyl gracza zabierajac mu " + dmgReceivedByPlayer + " punktow zycia!");
					showTextAboveActor(playerActor, String.valueOf(dmgReceivedByPlayer));
					
					// jesli gracz zginal, zakoncz walke
					if(player.getCharacterAttributes().getHP() <= 0) {
						battleEnd = true;
						
						// wyswietl animacje smierci gracza
						playerActor.startDeathAnimation();
						
						// dzwiek obrazen gracza
						AudioManager.instance.play(Assets.instance.sounds.playerHit);
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
		DamageText dmgText = new DamageText(Assets.instance.battleFonts.defaultBig, text);
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
		
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerPlayer);
		stack.add(layerEnemy);
		stack.add(layerControls);
		
	}
	
	private void rebuildStageVersusGui() {
		skinGorkyBattle = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_BATTLE), new TextureAtlas(Constants.TEXTURE_ATLAS_BATTLE));
		
		Table layerVersus = buildVersusLayer();
		
		stageVersusGui.clear();
		Stack stack = new Stack();
		stageVersusGui.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerVersus);
		
		showPlayerNameAndLevel();
		showEnemyNameAndLevel();
		
		showPlayerHPNumber();
		showPlayerMPNumber();
		showEnemyHPNumber();
		showEnemyMPNumber();
	}
	
	private void showEnemyHPNumber() {
		enemyHPNumber = new NumberText(Assets.instance.battleFontsSmall.defaultNormal, enemy.getCharacterAttributes().getHP());
		enemyHPNumber.setPosition(783 - enemyHPNumber.getWidth(), 62 + enemyHPNumber.getHeight());
		stageVersusGui.addActor(enemyHPNumber);
	}

	private void showEnemyMPNumber() {
		enemyMPNumber = new NumberText(Assets.instance.battleFontsSmall.defaultNormal, enemy.getCharacterAttributes().getMP());
		enemyMPNumber.setPosition(783 - enemyMPNumber.getWidth(), 51);
		stageVersusGui.addActor(enemyMPNumber);
	}

	private void showPlayerHPNumber() {
		playerHPNumber = new NumberText(Assets.instance.battleFontsSmall.defaultNormal, player.getCharacterAttributes().getHP());
		playerHPNumber.setPosition(199 - playerHPNumber.getWidth(), 62 + playerHPNumber.getHeight());
		stageVersusGui.addActor(playerHPNumber);
	}
	
	private void showPlayerMPNumber() {
		playerMPNumber = new NumberText(Assets.instance.battleFontsSmall.defaultNormal, player.getCharacterAttributes().getMP());
		playerMPNumber.setPosition(199 - playerMPNumber.getWidth(), 51);
		stageVersusGui.addActor(playerMPNumber);
	}

	private void showPlayerNameAndLevel() {
		playerNameAndLevelText = new Text(Assets.instance.battleFontsSmall.defaultNormal, "Player Lv. " + player.getCharacterAttributes().getLevel());
		playerNameAndLevelText.setPosition(10 + (196/2) - (playerNameAndLevelText.getWidth()/2), 10 + 75 + 5 + playerNameAndLevelText.getHeight());
		stageVersusGui.addActor(playerNameAndLevelText);
	}
	
	private void showEnemyNameAndLevel() {
		enemyNameAndLevelText = new Text(Assets.instance.battleFontsSmall.defaultNormal, "Orc Lv. " + enemy.getCharacterAttributes().getLevel());
		enemyNameAndLevelText.setPosition(800 - (10 + (196/2) + (enemyNameAndLevelText.getWidth()/2)), 10 + 75 + 5 + enemyNameAndLevelText.getHeight());
		stageVersusGui.addActor(enemyNameAndLevelText);
	}

	private Table buildVersusLayer() {
		Table layer = new Table();
		
		layer.center().bottom().padBottom(10);
		
		// ustaw paski zdrowia i many
		playerHPBar = setupProgressBar(Color.RED, 85, 50, 115, 10, player.calculateHpPercent());
		layer.addActor(playerHPBar);
		
		playerMPBar = setupProgressBar(Color.BLUE, 85, 18, 115, 10, player.calculateMpPercent());
		layer.addActor(playerMPBar);
		
		enemyHPBar = setupProgressBar(Color.RED, 670, 50, 115, 10, enemy.calculateHpPercent());
		layer.addActor(enemyHPBar);
		
		enemyMPBar = setupProgressBar(Color.BLUE, 670, 18, 115, 10, enemy.calculateMpPercent());
		layer.addActor(enemyMPBar);
		
		// ustaw tabelki z HP i MP
		imgVersus = new Image(skinGorkyBattle, "versus_gui");
		layer.add(imgVersus);
		
		return layer;
	}
	
	private ProgressBarActor setupProgressBar(Color c, float posX, float posY, float width, float height, float percent) {
		ProgressBarActor pba = new ProgressBarActor(percent, c);
		pba.setPosition(posX, posY);
		pba.setHeight(height);
		pba.setWidth(width);
		
		return pba;
	}

	private Table buildpopupWindow(String popupHeader, String popupInfo) {
		popupWindow = new Window(popupHeader, skinLibgdx);
		
		// dodaj informacje jakies
		popupWindow.add(buildPlayerPopupInfoSection(popupInfo)).row();
		
		// dodaj przycisk OK
		popupWindow.add(buildPlayerPopupButtonSection()).pad(10, 0, 10, 0);
		
		// ustaw kolor lekko przezroczysty
		popupWindow.setColor(1, 1, 1, 0.8f);
		
		// domyslnie ukryj
		showPopupWindow(false, false);
		
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

	private Table buildPlayerPopupInfoSection(String popupInfo) {
		Table tbl = new Table();
		
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label(popupInfo, skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
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
		game.setScreen(new GameScreen(game));
	}

	private Table buildEnemyLayer() {
		Table layer = new Table();
		
		// tymczasowo ORC zawsze, pozniej bedzie w konsruktorze zmieniany enemy losowo..
		enemyActor = new OrcActor();
		layer.addActor(enemyActor);
		enemyActor.setPosition(550, 130);
		
		return layer;
	}

	private Table buildPlayerLayer() {
		Table layer = new Table();
		
		playerActor = new KnightActor();
		layer.addActor(playerActor);
		playerActor.setPosition(115, 130);
		
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
		
		// dzwiek ataku gracza
		playSoundWithDelay(0.7f, Assets.instance.sounds.playerAttack);
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
	
	private void playSoundWithDelay(float delay, final Sound s) {
		SequenceAction seq = sequence();
		seq.addAction(delay(delay));
		
		seq.addAction(run(new Runnable() {
			
			@Override
			public void run() {
				AudioManager.instance.play(s);
			}
		}));
		
		stage.addAction(seq);
	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}

	@Override
	public void resize(int width, int height) {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		stageVersusGui = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
		rebuildStageVersusGui();
	}

	@Override
	public void show() {
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		stageVersusGui = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
		rebuildStageVersusGui();
		AudioManager.instance.play(Assets.instance.music.battleMusic);
	}

	@Override
	public void hide() {
		stage.dispose();
		stageVersusGui.dispose();
	}

	@Override
	public void pause() {}

}
