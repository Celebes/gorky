package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.actors.Text;
import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.GameplayFormulas;
import pl.edu.wat.wcy.tim.gorky.util.LoginPreferences;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;
import pl.edu.wat.wcy.tim.gorky.util.UltraIntegrator;

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

public class InventoryScreen extends AbstractGameScreen {

	private Image imgBackground;
	private Skin skinGorkyUiEq;
	private Stage stage;
	private Button btnSave;
	private Button btnContinue;

	public InventoryScreen(GorkyGame game) {
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
		skinGorkyUiEq = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_EQ), new TextureAtlas(Constants.TEXTURE_ATLAS_INTEGRATION_UI));
		
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
		layer.pad(145);
		
		if(LoginPreferences.instance.loggedIn == true) {
			btnSave = new Button(skinGorkyUiEq, "save");
			layer.add(btnSave).padBottom(10);
			
			btnSave.addListener(new ChangeListener() {
				
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					onSaveClicked();
				}
			});
			
			layer.row();
		} else {
			layer.padTop(170);
		}
		
		btnContinue = new Button(skinGorkyUiEq, "continue");
		layer.add(btnContinue);
		
		btnContinue.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onContinueClicked();
			}
		});
		
		return layer;
	}
	
	private void onSaveClicked() {
		System.out.println("SAVE!");
		
		UltraIntegrator.instance.saveDataOnServer();
	}
	
	private void onContinueClicked() {
		System.out.println("CONTINUE!");
		game.setScreen(new GameScreen(game, false));
	}
	
	private Table buildTextLayer() {
		Table layer = new Table();
		
		float yPosition;
		float xPosition = 60;
		float textHeight;
		
		Text statsText = new Text(Assets.instance.fontsUpsideDown.defaultNormal, "Statystyki");
		yPosition = 480 - 35 - statsText.getHeight()/2;
		textHeight = statsText.getHeight();
		statsText.setPosition(222 - statsText.getWidth()/2, yPosition);
		layer.addActor(statsText);
		
		yPosition -= (30 + textHeight);
		layer.addActor(createText("Imiê Postaci: " + SaveStatePreferences.instance.avatarName, xPosition, yPosition));
		
		yPosition -= (10 + textHeight);
		layer.addActor(createText("Poziom doœwiadczenia: " + SaveStatePreferences.instance.level, xPosition, yPosition));
		
		yPosition -= (10 + textHeight);
		layer.addActor(createText("Punkty doœwiadczenia: " + SaveStatePreferences.instance.exp + "/" + GameplayFormulas.getExpForNextLevel(SaveStatePreferences.instance.level), xPosition, yPosition));
		
		yPosition -= (20 + textHeight);
		layer.addActor(createText("Si³a ataku: " + SaveStatePreferences.instance.atk, xPosition, yPosition));
		
		yPosition -= (10 + textHeight);
		layer.addActor(createText("Pancerz: " + SaveStatePreferences.instance.def, xPosition, yPosition));
		
		yPosition -= (20 + textHeight);
		layer.addActor(createText("Punkty ¿ycia: " + SaveStatePreferences.instance.HP + "/" + SaveStatePreferences.instance.maxHP, xPosition, yPosition));
		
		yPosition -= (10 + textHeight);
		layer.addActor(createText("Punkty many: " + SaveStatePreferences.instance.MP + "/" + SaveStatePreferences.instance.maxMP, xPosition, yPosition));
		
		yPosition -= (20 + textHeight);
		layer.addActor(createText("Z³oto: " + SaveStatePreferences.instance.gold, xPosition, yPosition));
		
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
