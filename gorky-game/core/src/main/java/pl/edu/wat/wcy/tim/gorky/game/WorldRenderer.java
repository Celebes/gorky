package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.GamePreferences;

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

		batch.end();
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
