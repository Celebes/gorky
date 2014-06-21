package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.GamePreferences;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
private static final String TAG = WorldRenderer.class.getName();
	
	private OrthographicCamera camera;
	private OrthographicCamera cameraGUI;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}
	
	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true);
		cameraGUI.update();
		
		this.worldController.setWorldRenderer(this);
	}
	
	public void render() {
		renderWorld(batch);
		renderGui(batch);
	}
	
	private void renderWorld(SpriteBatch batch) {
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		worldController.level.render(batch);
		
		batch.end();
	}
	
	private void renderGui(SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		
		if (GamePreferences.instance.showFpsCounter) {
			renderGuiFpsCounter(batch);
		}
		
		if(SaveStatePreferences.instance.currentLevel.equals(Constants.LEVEL_01)) {
			renderSacredLandInfo(batch);
		}

		batch.end();
	}
	
	private void renderSacredLandInfo(SpriteBatch batch) {
		BitmapFont fpsFont = Assets.instance.fonts.defaultBig;
		
		String sacredLandInfo1 = Assets.instance.stringBundle.get("sacred_land_info_1");
		String sacredLandInfo2 = Assets.instance.stringBundle.get("sacred_land_info_2");
		
		float x1 = cameraGUI.viewportWidth / 2 - fpsFont.getBounds(sacredLandInfo1).width/2;
		float y1 = cameraGUI.viewportHeight - 150;
		
		float x2 = cameraGUI.viewportWidth / 2 - fpsFont.getBounds(sacredLandInfo2).width/2;
		float y2 = cameraGUI.viewportHeight - 150 + fpsFont.getBounds(sacredLandInfo1).height + 10;
		
		fpsFont.setColor(0, 1, 0, 1);
		
		fpsFont.draw(batch, sacredLandInfo1, x1, y1);
		fpsFont.draw(batch, sacredLandInfo2, x2, y2);
		
		fpsFont.setColor(1, 1, 1, 1); // white
	}

	private void renderGuiFpsCounter(SpriteBatch batch) {
		float x = cameraGUI.viewportWidth - 85;
		float y = cameraGUI.viewportHeight - 25;
		int fps = Gdx.graphics.getFramesPerSecond();
		BitmapFont fpsFont = Assets.instance.fonts.defaultNormal;
		if (fps >= 45) {
			// 45 or more FPS show up in green
			fpsFont.setColor(0, 1, 0, 1);
		} else if (fps >= 30) {
			// 30 or more FPS show up in yellow
			fpsFont.setColor(1, 1, 0, 1);
		} else {
			// less than 30 FPS show up in red
			fpsFont.setColor(1, 0, 0, 1);
		}
		fpsFont.draw(batch, "FPS: " + fps, x, y);
		fpsFont.setColor(1, 1, 1, 1); // white
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
		
		cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

}
