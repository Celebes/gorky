package pl.edu.wat.tim.gorky.view;

import pl.edu.wat.tim.gorky.helpers.IsometricHelper;
import pl.edu.wat.tim.gorky.model.Tile;
import pl.edu.wat.tim.gorky.model.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	private World world;
	private OrthographicCamera cam;
	
	private Texture tileTexture;
	
	private SpriteBatch spriteBatch;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	private void loadTextures() {
		tileTexture = new Texture(Gdx.files.internal("images/basic-green-tile.png"));
	}
	
	public void render() {
		spriteBatch.begin();
		drawTiles();		
		spriteBatch.end();
	}
	
	private void drawTiles() {
		float spaceBetweenTiles = (Tile.SIZE / 2) - 1; // gdyby nie (-1) to krawedzie by przylegaly idealnie, a maja sie na siebie nakladac, aby byla pojedyncza ramka.

		for(Tile tile : world.getTiles()) {
			Vector2 isometricCoords = IsometricHelper.cartesianToIsometric(tile.getPosition());
			spriteBatch.draw(tileTexture, 375 + isometricCoords.x * spaceBetweenTiles, 115 + isometricCoords.y * spaceBetweenTiles);			
		}
	}

}
