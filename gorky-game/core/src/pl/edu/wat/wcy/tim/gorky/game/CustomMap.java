package pl.edu.wat.wcy.tim.gorky.game;

import pl.edu.wat.wcy.tim.gorky.objects.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public class CustomMap implements Disposable {
	public static final String TAG = CustomMap.class.getName();
	public TiledMap map;
	public OrthogonalTiledMapRenderer mapRenderer;
	
	public Player player;
	
	public CustomMap(String filename) {
		init(filename);
	}

	private void init(String filename) {
		MapObject object = null;
		
		// wczytaj mape
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load(filename);
		
		// ustaw renderera
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		
		// pobierz wspolrzedne SpawnPoint
		object = map.getLayers().get("spawn").getObjects().get("SpawnPoint");
		Rectangle spawnRectangle = ((RectangleMapObject)object).getRectangle();
		//float spawnX = spawnRectangle.getX() + spawnRectangle.getWidth()/2;
		//float spawnY = spawnRectangle.getY() - spawnRectangle.getHeight()/2;
		
		
		
		// utworz gracza na SpawnPoint
		player = new Player();
		//player.position.set(18*32, 18*32);
	}
	
	public void render(SpriteBatch batch) {
		mapRenderer.render();
		
		mapRenderer.getSpriteBatch().begin();
		player.render((SpriteBatch)mapRenderer.getSpriteBatch());
		mapRenderer.getSpriteBatch().end();
	}
	
	public void update (float deltaTime) {
		player.update(deltaTime);
	}

	@Override
	public void dispose() {
		map.dispose();
		mapRenderer.dispose();
	}
	
}
