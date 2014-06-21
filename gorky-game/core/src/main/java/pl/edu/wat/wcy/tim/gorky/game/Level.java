package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.objects.AbstractGameObject;
import pl.edu.wat.wcy.tim.gorky.objects.Enemy;
import pl.edu.wat.wcy.tim.gorky.objects.EnemySpawnpoint;
import pl.edu.wat.wcy.tim.gorky.objects.Grass;
import pl.edu.wat.wcy.tim.gorky.objects.NextLevelTeleport;
import pl.edu.wat.wcy.tim.gorky.objects.Player;
import pl.edu.wat.wcy.tim.gorky.objects.Wall;
import pl.edu.wat.wcy.tim.gorky.util.Constants;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class Level {
	public static final String TAG = Level.class.getName();
	
	public enum BLOCK_TYPE {
		EMPTY(255, 255, 255),					// bialy
		PLAYER_SPAWNPOINT(255, 0, 0),			// czerwony
		ENEMY_SPAWNPOINT(0, 255, 0),			// zielony
		NEXT_LEVEL(255, 0, 255),				// magneta
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
	public Array<Enemy> enemies;
	public Array<EnemySpawnpoint> enemySpawnpoints;
	public Array<NextLevelTeleport> nextLevelTeleports;
	public Player player;
	public String fileName;
	
	public Level (String filename) {
		init(filename);
	}
	
	private void init(String filename) {
		this.fileName = filename;
		player = null;
		
		wallTiles = new Array<>();
		grassTiles = new Array<>();
		enemies = new Array<>();
		enemySpawnpoints = new Array<>();
		nextLevelTeleports = new Array<>();
		
		// wczytaj obrazek z opisem poziomu
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		
		// ustaw wymiary w Constans
		Constants.CURRENT_MAP_WIDTH = pixmap.getWidth();
		Constants.CURRENT_MAP_HEIGHT = pixmap.getHeight();

		// skanuj pixele od gornego-lewego rogu do dolnego-prawego		
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				AbstractGameObject obj = null;
				
				// wysokosc rosnie z dolu do gory
				float baseHeight = pixmap.getHeight() - pixelY;
				
				// pobierz kolor aktualnego pixela
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				
				// sprawdz kolor i utworz odpowiedni obiekt
				
				// dodaj trawe wszedzie
				obj = new Grass();
				obj.position.set(pixelX, baseHeight * obj.dimension.y);
				grassTiles.add((Grass)obj);
				
				// puste pole
				if(BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
					// nic nie rob
				}
				
				// gracz
				else if(BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
					obj = new Player();
					obj.position.set(SaveStatePreferences.instance.playerPositionX, SaveStatePreferences.instance.playerPositionY);
					//System.out.println("PLAYER: " + pixelX + " | " + pixelY);
					player = (Player)obj;
				}
				
				// potworek
				else if(BLOCK_TYPE.ENEMY_SPAWNPOINT.sameColor(currentPixel)) {
					obj = new EnemySpawnpoint();
					obj.position.set(pixelX,baseHeight * obj.dimension.y);
					enemySpawnpoints.add((EnemySpawnpoint)obj);
				}
				
				// teleport do innego poziomu
				else if(BLOCK_TYPE.NEXT_LEVEL.sameColor(currentPixel)) {
					obj = new NextLevelTeleport();
					obj.position.set(pixelX,baseHeight * obj.dimension.y);
					nextLevelTeleports.add((NextLevelTeleport)obj);
				}
				
				// murek
				else if(BLOCK_TYPE.WALL.sameColor(currentPixel)) {
					obj = new Wall();
					obj.position.set(pixelX,baseHeight * obj.dimension.y);
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
		
		for(Enemy e : enemies) {
			e.render(batch);
		}
		
		player.render(batch);
	}
	
	public void update (float deltaTime) {
		player.update(deltaTime);
	}
}
