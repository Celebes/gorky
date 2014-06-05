package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.helpers.IsometricHelper;
import pl.edu.wat.wcy.tim.gorky.objects.AbstractGameObject;
import pl.edu.wat.wcy.tim.gorky.objects.Grass;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.objects.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class Level {
	public static final String TAG = Level.class.getName();
	
	public enum BLOCK_TYPE {
		GRASS(255, 255, 255),					// bialy
		PLAYER_SPAWNPOINT(255, 0, 0),			// czerwony
		WALL(0, 0, 0);							// czarny
		
		private int color;
		
		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor(int color) {
			return (this.color == color);
		}
		
		public int getColor() {
			return color;
		}
	}
	
	public Array<Wall> wallTiles;
	public Array<Grass> grassTiles;
	public Player player;
	
	public Level (String filename) {
		init(filename);
	}
	
	private void init(String filename) {
		player = null;
		
		wallTiles = new Array<>();
		grassTiles = new Array<>();
		
		// wczytaj obrazek z opisem poziomu
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));


		// skanuj pixele od gornego-lewego rogu do dolnego-prawego		
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				
				// wysokosc rosnie z dolu do gory
				float baseHeight = pixmap.getHeight() - pixelY;
				
				// pobierz kolor aktualnego pixela
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				
				// sprawdz kolor i utworz odpowiedni obiekt
				
				// trawa
				if(BLOCK_TYPE.GRASS.sameColor(currentPixel)) {
					obj = new Grass();
					//offsetHeight = -3.0f;
					//obj.position = IsometricHelper.cartesianToIsometric(new Vector2(pixelX, pixelY * obj.dimension.y + offsetHeight));
					//obj.position.set(pixelX, pixelY * obj.dimension.y + offsetHeight);
					obj.position.set(pixelX, baseHeight * obj.dimension.y);
					grassTiles.add((Grass)obj);
				}
				
				// gracz
				else if(BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
					obj = new Player();
					//offsetHeight = -3.0f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					player = (Player)obj;
				}
				
				// murek
				else if(BLOCK_TYPE.WALL.sameColor(currentPixel)) {
					obj = new Wall();
					//offsetHeight = -3.0f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y + offsetHeight);
					wallTiles.add((Wall)obj);
				}

				// nieznany kolor
				else {
					int r = 0xff & (currentPixel >>> 24);		// red color channel
					int g = 0xff & (currentPixel >>> 16);		// green color channel
					int b = 0xff & (currentPixel >>> 8);		// blue color channel
					int a = 0xff & currentPixel;				// alpha channel
					
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<" + pixelY + ">: r<" + r + "> g<" + g + "> b<" + b + "> a<" + a + ">");
				}
			}

		}
		
		// oczysc pamiec
		pixmap.dispose();
		Gdx.app.debug(TAG, "level '" + filename + "' loaded");
	}
	
	public void render(SpriteBatch batch) {
		for(Grass g : grassTiles) {
			g.render(batch);
		}
		
		for(Wall w : wallTiles) {
			w.render(batch);
		}
		
		player.render(batch);
	}
	
	public void update (float deltaTime) {
		player.update(deltaTime);
	}
}
