package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.screens.MenuScreen;
import pl.edu.wat.wcy.tim.gorky.util.CameraHelper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	public Game game;
	public Sprite[] testSprites;
	public int selectedSprite;
	public CameraHelper cameraHelper;
	
	public WorldController(Game game) {
		this.game = game;
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initTestObjects();
	}
	
	private void initTestObjects() {
		testSprites = new Sprite[5];
		
		int width = 32;
		int height = 32;
		
		Pixmap pixmap = createProceduralBitmap(width, height);
		
		// utworz nowa texture z pixmapy
		Texture texture = new Texture(pixmap);
		
		// utworz nowe Sprite'y na podstawie textury
		for(int i=0; i<testSprites.length; i++) {
			Sprite spr = new Sprite(texture);
			
			// rozmiar kazdego sprite'a to 1x1 [m] w swiecie gry (cala gra ma 5x5 [m])
			spr.setSize(1, 1);
			
			// przestaw poczatek sprite'a z dolnego lewego rogu na srodek
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
			
			// wybierz losowa pozycje na ekranie
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			
			// dodaj do tablicy
			testSprites[i] = spr;
		}
		
		// ustaw pierwszego sprite'a jako wybranego
		selectedSprite = 0;
	}

	private Pixmap createProceduralBitmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		
		// czerwony kwadrat z 50% opacity
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		
		// zolty X na kwadracie
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		
		// turkusowa ramka
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		
		return pixmap;
	}

	public void update(float deltaTime) {
		handleDebugInput(deltaTime);
		updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void handleDebugInput(float deltaTime) {
		if(Gdx.app.getType() != ApplicationType.Desktop) {
			return;
		}
		
		// kontrolowanie zaznaczonego sprite'a, 5 metrow na sekunde
		float sprMoveSpeed = 5 * deltaTime;
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			moveSelectedSprite(-sprMoveSpeed, 0f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			moveSelectedSprite(sprMoveSpeed, 0f);
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)) {
			moveSelectedSprite(0f, sprMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)) {
			moveSelectedSprite(0f, -sprMoveSpeed);
		}
		
		// sterowanie kamera
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveCamera(-camMoveSpeed, 0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveCamera(camMoveSpeed, 0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			moveCamera(0, camMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			moveCamera(0, -camMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
			cameraHelper.setPosition(0, 0);
		}
		
		// przyblizanie kamery
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		}
		
		if(Gdx.input.isKeyPressed(Keys.COMMA)) {
			cameraHelper.addZoom(camZoomSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.PERIOD)) {
			cameraHelper.addZoom(-camZoomSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.SLASH)) {
			cameraHelper.setZoom(1);
		}
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	private void moveSelectedSprite(float x, float y) {
		testSprites[selectedSprite].translate(x, y);
	}

	private void updateTestObjects(float deltaTime) {
		// pobierz aktualna rotacje wybranego sprite'a
		float rotation = testSprites[selectedSprite].getRotation();
		
		// obroc go o 90 stopni na sekunde
		rotation += (90 * deltaTime);
		
		// pilnuj zeby nie przekroczyc 360 stopni
		rotation %= 360;
		
		// zastosuj nowy obrot
		testSprites[selectedSprite].setRotation(rotation);
	}

	@Override
	public boolean keyUp(int keycode) {
		// zresetuj swiat gry
		if(keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game world resetted!");
		}
		
		// wybierz nastepnego sprite'a
		else if(keycode == Keys.SPACE) {
			selectedSprite = (selectedSprite + 1) % testSprites.length;
			
			// zaktualizuj kamere by sledzila nowy obiekt
			if(cameraHelper.hasTarget()) {
				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
			
			Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
		}
		
		// wlacz sledzenie kamery
		else if(keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : testSprites[selectedSprite]);
			Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
		}
		
		// wroc do menu
		else if(keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			backToMenu();
		}
		
		return false;
	}
	
	private void backToMenu() {
		game.setScreen(new MenuScreen(game));
	}
}
