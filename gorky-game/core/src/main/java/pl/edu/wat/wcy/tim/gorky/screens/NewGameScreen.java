package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.Text;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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

/*
 * Ekran z ekwipunkiem + statystykami
 */

public class NewGameScreen extends AbstractGameScreen {

	private Image imgBackground;
	private Skin skinGorkyUiEq;
	private Stage stage;
	private Button btnLogIn;
	private Button btnLogOut;
	private Button btnNewGame;
	private Button btnContinue;

	public NewGameScreen(GorkyGame game) {
		super(game);
	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}

	@Override
	public void render(float deltaTime) {
		stage.act(deltaTime);
		stage.draw();
	}
	
	private void rebuildStage() {
		skinGorkyUiEq = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_NEW_GAME), new TextureAtlas(Constants.TEXTURE_ATLAS_INTEGRATION_UI));
		
		Table layerBg = buildBgLayer();
		Table layerText = buildTextLayer();
		Table layerButtons = buildButtonsLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBg);
		stack.add(layerText);
		stack.add(layerButtons);
	}
	
	private Table buildBgLayer() {
		Table layer = new Table();
		
		imgBackground = new Image(skinGorkyUiEq, "background");
		layer.add(imgBackground);
		
		return layer;
	}
	
	private Table buildButtonsLayer() {
		Table layer = new Table();
		
		layer.right().top();
		layer.padRight(145);
		layer.padTop(107);
		
		btnContinue = new Button(skinGorkyUiEq, "continue");
		layer.add(btnContinue).padBottom(10);
		
		btnContinue.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onContinueClicked();
			}
		});
		
		layer.row();
		
		btnNewGame = new Button(skinGorkyUiEq, "newgame");
		layer.add(btnNewGame).padBottom(10);
		
		btnNewGame.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onNewGameClicked();
			}
		});
		
		layer.row();
		
		btnLogIn = new Button(skinGorkyUiEq, "login");
		layer.add(btnLogIn).padBottom(10);
		
		btnLogIn.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onLogInClicked();
			}
		});
		
		layer.row();
		
		btnLogOut = new Button(skinGorkyUiEq, "logout");
		layer.add(btnLogOut);
		
		btnLogOut.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onLogOutClicked();
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
	}
	
	private void onContinueClicked() {
		//game.setScreen(new GameScreen(game, true));
		System.out.println("CONTINUE");
	}
	
	private Table buildTextLayer() {
		Table layer = new Table();
		
		float yPosition;
		float xPosition = 60;
		float textHeight;
		
		Text statsText = new Text(Assets.instance.fontsUpsideDown.defaultNormal, "Aktualnie zalogowany");
		yPosition = 480 - 35 - statsText.getHeight()/2;
		textHeight = statsText.getHeight();
		statsText.setPosition(222 - statsText.getWidth()/2, yPosition);
		layer.addActor(statsText);
		
		yPosition -= (30 + textHeight);
		layer.addActor(createText("Login: " + "Test1234", xPosition, yPosition));
		
		yPosition -= (10 + textHeight);
		layer.addActor(createText("Poziom doœwiadczenia: " + SaveStatePreferences.instance.level, xPosition, yPosition));
		
		return layer;
	}
	
	private Text createText(String text, float xPosition, float yPosition) {
		Text textActor = new Text(Assets.instance.fontsUpsideDown.defaultNormal, text);
		textActor.setPosition(xPosition, yPosition);
		return textActor;
	}
	
	@Override
	public void resize(int width, int height) {
		show();
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
	public void pause() {

	}

}
