package pl.edu.wat.wcy.tim.gorky.screens;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;
import pl.edu.wat.wcy.tim.gorky.util.Constants;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/*
 * Ekran z ekwipunkiem + statystykami
 */

public class LoginScreen extends AbstractGameScreen {

	private Image imgBackground;
	private Skin skinGorkyUiEq;
	private Skin skinLibgdx;
	private Stage stage;
	private TextField loginTF;
	private TextField passwordTF;
	private Button btnOk;
	private Button btnCancel;

	public LoginScreen(GorkyGame game) {
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
		skinGorkyUiEq = new Skin(Gdx.files.internal(Constants.SKIN_GORKY_LOGIN), new TextureAtlas(Constants.TEXTURE_ATLAS_INTEGRATION_UI));
		skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));
		
		Table layerBg = buildBgLayer();
		Table layerText = buildTextLayer();
		Table layerTF = buildTFLayer();
		Table layerButtons = buildButtonsLayer();
		
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBg);
		stack.add(layerText);
		stack.add(layerTF);
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
		layer.padTop(200);
		
		btnOk = new Button(skinGorkyUiEq, "ok");
		layer.add(btnOk).padBottom(10);
		
		btnOk.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onOkClicked();
			}
		});
		
		layer.row();
		
		btnCancel = new Button(skinGorkyUiEq, "cancel");
		layer.add(btnCancel).padBottom(10);
		
		btnCancel.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onCancelClicked();
			}
		});

		return layer;
	}
	
	private Table buildTFLayer() {
		Table layer = new Table();
		
		layer.top().right();
		layer.padRight(148);
		layer.padTop(120);
		
		loginTF = new TextField("", skinLibgdx, "default");
		loginTF.setMessageText("Login");
		layer.add(loginTF).padBottom(5);	
		
		layer.row();
		
		passwordTF = new TextField("", skinLibgdx, "default");
		passwordTF.setMessageText("Has³o");
		passwordTF.setPasswordCharacter('*');
		passwordTF.setPasswordMode(true);
		layer.add(passwordTF);
		
		
		return layer;
	}
	
	private void onOkClicked() {
		System.out.println("OK!");
	}
	
	private void onCancelClicked() {
		System.out.println("CANCEL!");
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
