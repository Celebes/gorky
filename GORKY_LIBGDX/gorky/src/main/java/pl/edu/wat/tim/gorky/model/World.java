package pl.edu.wat.tim.gorky.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	Array<Tile> tiles = new Array<Tile>();
	
	Hero hero;
	
	public World() {
		createDemoWorld();
	}

	private void createDemoWorld() {
		
		hero = new Hero(new Vector2(7, 2));
		
		// tworzy plansze 5x5
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				tiles.add(new Tile(new Vector2(j, i)));
			}
		}

	}

	public Array<Tile> getTiles() {
		return tiles;
	}

	public Hero getHero() {
		return hero;
	}
	
}
