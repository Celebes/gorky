package pl.edu.wat.wcy.tim.gorky.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;
import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.KnightActor;
import pl.edu.wat.wcy.tim.gorky.actors.Text;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.AudioManager;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.GamePreferences;
import pl.edu.wat.wcy.tim.gorky.util.LoginPreferences;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sun.webkit.ContextMenu.ShowContext;

public class MenuScreen extends AbstractGameScreen {
	
	private static final String TAG = MenuScreen.class.getName();
	
	// wielkosc elementow w opcjach
	public final float CUSTOM_CHECKBOX_SIZE = 25.0f;

	private Stage stage;
	private Skin skinGorky;
	private Skin skinLibgdx;
	
	// menu
	private Image imgBackground;
	private Image imgLogo;
	private Image imgInfo;
	private KnightActor knightActor;
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
	
	// new game
	private Skin skinGorkyNewGame;
	private Button btnLogIn;
	private Button btnLogOut;
	private Button btnNewGame;
	private Button btnContinue;
	private Button btnNewGameCancel;
	
	// aktualnie zalogowany jako..
	private Text currentLoginTextHeader;
	private Text currentLoginText;
	
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
		skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));
		skinGorkyNewGame = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_NEW_GAME), new TextureAtlas(Constants.TEXTURE_ATLAS_INTEGRATION_UI));
		
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();
		Table layerLogo = buildLogoLayer();
		Table layerInfo = buildInfoLayer();
		Table layerControls = buildControlsLayer();
		Table layerOptionsWindow = buildOptionsWindowLayer();
		
		// new game
		Table layerButtons = buildNewGameButtonsLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		stack.add(layerLogo);
		stack.add(layerInfo);
		stack.add(layerControls);
		
		// new game
		stack.add(layerButtons);
		
		// aktualnie zalogowany uzytkownik
		if(LoginPreferences.instance.loggedIn == true) {
			showCurrentLogin();
		}
		
		stage.addActor(layerOptionsWindow);
	}
	
	private Table buildNewGameButtonsLayer() {
		Table layer = new Table();
		
		int offsetY = 0;
		
		if(LoginPreferences.instance.loggedIn == false) {
			offsetY = 50;
		} else {
			offsetY = 22;
		}
		
		layer.right().top();
		layer.padRight(40 - 300);
		layer.padTop(100 + offsetY);
		
		// kontynuacja mozliwa tylko po zalogowaniu
		
		if(LoginPreferences.instance.loggedIn == true) {
			btnContinue = new Button(skinGorkyNewGame, "continue");
			layer.add(btnContinue).padBottom(10);
			
			btnContinue.addListener(new ChangeListener() {
				
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					onContinueClicked();
				}
			});
			
			layer.row();
		}
		
		btnNewGame = new Button(skinGorkyNewGame, "newgame");
		layer.add(btnNewGame).padBottom(10);
		
		btnNewGame.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onNewGameClicked();
			}
		});
		
		layer.row();
		
		// logowanie mozliwe tylko jak sie jest wylogowanym
		
		if(LoginPreferences.instance.loggedIn == false) {
			btnLogIn = new Button(skinGorkyNewGame, "login");
			layer.add(btnLogIn).padBottom(10);
			
			btnLogIn.addListener(new ChangeListener() {
				
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					onLogInClicked();
				}
			});
			
			layer.row();
		}
		
		// wylogowanie mozliwe tylko po zalogowaniu
		
		if(LoginPreferences.instance.loggedIn == true) {
			btnLogOut = new Button(skinGorkyNewGame, "logout");
			layer.add(btnLogOut).padBottom(10);
			
			btnLogOut.addListener(new ChangeListener() {
				
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					onLogOutClicked();
				}
			});
			
			layer.row();
		}
		
		btnNewGameCancel = new Button(skinGorkyNewGame, "cancel");
		layer.add(btnNewGameCancel);
		
		btnNewGameCancel.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onNewGameCancelClicked();
			}
		});

		return layer;
	}
	
	private void onLogInClicked() {
		System.out.println("LOG IN");
	}
	
	private void onLogOutClicked() {
		System.out.println("LOG OUT");
	}

	private void onNewGameClicked() {
		System.out.println("NEW GAME");
		SaveStatePreferences.instance.reset();
		game.setScreen(new GameScreen(game, true));
	}
	
	private void onContinueClicked() {
		//game.setScreen(new GameScreen(game, true));
		System.out.println("CONTINUE");
		game.setScreen(new GameScreen(game, true));
	}
	
	private void onNewGameCancelClicked() {
		System.out.println("CANCEL");
		showMenuButtons(true);
		showNewGameButtons(false);
	}
	
	private void showCurrentLogin() {
		currentLoginTextHeader = new Text(Assets.instance.battleFontsSmall.defaultNormal, "Zalogowany jako:");
		currentLoginTextHeader.setPosition(800 - 10 - currentLoginTextHeader.getWidth() + 300, 480 - 10);
		currentLoginText = new Text(Assets.instance.battleFontsSmall.defaultNormal, "ASDSAD_" + LoginPreferences.instance.login);
		currentLoginText.setPosition(800 - 10 - currentLoginTextHeader.getWidth() + 300, 480 - 10 - 5 - currentLoginText.getHeight());
		currentLoginText.setColor(Color.GREEN);
		stage.addActor(currentLoginTextHeader);
		stage.addActor(currentLoginText);
	}

	private Table buildControlsLayer() {
		Table layer = new Table();
		layer.right().bottom();

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
		//game.setScreen(new GameScreen(game, true));
		
		// pokaz przyciski new game
		showNewGameButtons(true);
		
		// ukryj przyciski play/opcje
		showMenuButtons(false);
	}
	
	private void onOptionsClicked () {
		loadSettings();
		showMenuButtons(false);
		showOptionsWindow(true, true);
	}
	
	private void showMenuButtons (boolean visible) {
		float moveDuration = 1.0f;
		Interpolation moveEasing = Interpolation.swing;
		float delayOptionsButton = 0.25f;

		float moveX = 300 * (visible ? -1 : 1);
		float moveY = 0 * (visible ? -1 : 1);
		final Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
		btnMenuPlay.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		btnMenuOptions.addAction(sequence(delay(delayOptionsButton), moveBy(moveX, moveY, moveDuration, moveEasing)));

		SequenceAction seq = sequence();
		if (visible) seq.addAction(delay(delayOptionsButton + moveDuration));
		seq.addAction(run(new Runnable() {
			public void run () {
				btnMenuPlay.setTouchable(touchEnabled);
				btnMenuOptions.setTouchable(touchEnabled);
			}
		}));
		stage.addAction(seq);
	}
	
	private void showNewGameButtons(boolean visible) {
		float moveDuration = 1.0f;
		Interpolation moveEasing = Interpolation.swing;

		float moveX = 300 * (visible ? -1 : 1);
		float moveY = 0 * (visible ? -1 : 1);
		final Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
		
		if(LoginPreferences.instance.loggedIn == false) {
			btnLogIn.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		}
		
		btnNewGame.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		
		btnNewGameCancel.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		
		if(LoginPreferences.instance.loggedIn == true) {
			btnContinue.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
			btnLogOut.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
			currentLoginTextHeader.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
			currentLoginText.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
		}

		SequenceAction seq = sequence();
		if (visible) seq.addAction(delay(moveDuration));
		seq.addAction(run(new Runnable() {
			public void run () {
				btnMenuPlay.setTouchable(touchEnabled);
				btnMenuOptions.setTouchable(touchEnabled);
			}
		}));
		stage.addAction(seq);
	}

	private Table buildOptionsWindowLayer () {
		winOptions = new Window(Assets.instance.stringBundle.get("menu_options_header_window"), skinLibgdx);
		// + Audio Settings: Sound/Music CheckBox and Volume Slider
		winOptions.add(buildOptWinAudioSettings()).row();
		// + Debug: Show FPS Counter
		winOptions.add(buildOptWinDebug()).row();
		// + Separator and Buttons (Save, Cancel)
		winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);
		// Make options window slightly transparent
		winOptions.setColor(1, 1, 1, 0.8f);
		// Hide options window by default
		showOptionsWindow(false, false);
		if (debugEnabled) winOptions.debug();
		// Let TableLayout recalculate widget sizes and positions
		winOptions.pack();
		// Move options window to bottom right corner
		winOptions.setPosition(Constants.VIEWPORT_GUI_WIDTH - winOptions.getWidth() - 50, 50);
		
		return winOptions;
	}
	
	private void showOptionsWindow (boolean visible, boolean animated) {
		float alphaTo = visible ? 0.8f : 0.0f;
		float duration = animated ? 1.0f : 0.0f;
		Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
		winOptions.addAction(sequence(touchable(touchEnabled), alpha(alphaTo, duration)));
	}
	
	private Table buildOptWinAudioSettings () {
		Table tbl = new Table();
		// + Title: "Audio"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label(Assets.instance.stringBundle.get("menu_options_header_audio"), skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);
		// + Checkbox, "Sound" label, sound volume slider
		chkSound = new CheckBox("", skinLibgdx);
		chkSound.padBottom(10);
		chkSound.padTop(10);
		chkSound.getCells().get(0).size(CUSTOM_CHECKBOX_SIZE);
		tbl.add(chkSound);
		tbl.add(new Label(Assets.instance.stringBundle.get("menu_options_audio_sound"), skinLibgdx));
		sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldSound);
		tbl.row();
		// + Checkbox, "Music" label, music volume slider
		chkMusic = new CheckBox("", skinLibgdx);
		chkMusic.getCells().get(0).size(CUSTOM_CHECKBOX_SIZE);
		tbl.add(chkMusic);
		tbl.add(new Label(Assets.instance.stringBundle.get("menu_options_audio_music"), skinLibgdx));
		sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldMusic);
		tbl.row();
		return tbl;
	}
	
	private Table buildOptWinDebug () {
		Table tbl = new Table();
		// + Title: "Debug"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label(Assets.instance.stringBundle.get("menu_options_header_debug"), skinLibgdx, "default-font", Color.RED)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);
		// + Checkbox, "Show FPS Counter" label
		chkShowFpsCounter = new CheckBox("", skinLibgdx);
		chkShowFpsCounter.getCells().get(0).size(CUSTOM_CHECKBOX_SIZE);
		chkShowFpsCounter.padTop(10);
		tbl.add(new Label(Assets.instance.stringBundle.get("menu_options_debug_fps"), skinLibgdx));
		tbl.add(chkShowFpsCounter);
		tbl.row();
		return tbl;
	}
	
	private Table buildOptWinButtons () {
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
		btnWinOptSave = new TextButton(Assets.instance.stringBundle.get("global_save"), skinLibgdx);
		tbl.add(btnWinOptSave).padRight(30);
		btnWinOptSave.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onSaveClicked();
			}
		});
		// + Cancel Button with event handler
		btnWinOptCancel = new TextButton(Assets.instance.stringBundle.get("global_cancel"), skinLibgdx);
		tbl.add(btnWinOptCancel);
		btnWinOptCancel.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onCancelClicked();
			}
		});
		return tbl;
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
		
		knightActor = new KnightActor();
		layer.addActor(knightActor);
		knightActor.setPosition(115, 115);
		
		return layer;
	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		
		imgBackground = new Image(skinGorky, "background");
		layer.add(imgBackground);
		
		return layer;
	}
	
	private void loadSettings() {
		GamePreferences prefs = GamePreferences.instance;
		
		prefs.load();
		chkSound.setChecked(prefs.sound);
		sldSound.setValue(prefs.volSound);
		chkMusic.setChecked(prefs.music);
		sldMusic.setValue(prefs.volMusic);
		chkShowFpsCounter.setChecked(prefs.showFpsCounter);
	}
	
	private void saveSettings() {
		GamePreferences prefs = GamePreferences.instance;
		prefs.sound = chkSound.isChecked();
		prefs.volSound = sldSound.getValue();
		prefs.music = chkMusic.isChecked();
		prefs.volMusic = sldMusic.getValue();
		prefs.showFpsCounter = chkShowFpsCounter.isChecked();
		prefs.save();
	}
	
	private void onSaveClicked () {
		saveSettings();
		onCancelClicked();
		AudioManager.instance.onSettingsUpdated();
	}

	private void onCancelClicked () {
		showMenuButtons(true);
		showOptionsWindow(false, true);
		AudioManager.instance.onSettingsUpdated();
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
		AudioManager.instance.play(Assets.instance.music.menuMusic);
	}

	@Override
	public void hide() {
		stage.dispose();
		skinGorky.dispose();
		skinLibgdx.dispose();
	}

	@Override
	public void pause() {}
	
}
