package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/*
 * Ekran z ekwipunkiem + statystykami
 */

public class NewGameScreen extends AbstractGameScreen {

	private Image imgBackground;
	private Skin skinGorkyUiEq;
	private Stage stage;
	private Button btnSave;
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
		
		return layer;
	}
	
	private Table buildButtonsLayer() {
		Table layer = new Table();
		
		return layer;
	}
	
	private Table buildTextLayer() {
		Table layer = new Table();
		
		return layer;
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
