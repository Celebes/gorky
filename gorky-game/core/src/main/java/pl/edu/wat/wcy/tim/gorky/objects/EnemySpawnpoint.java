package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemySpawnpoint extends AbstractGameObject {
	
	public EnemySpawnpoint() {
		init();
	}
	
	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(dimension.x / 2, dimension.y / 2);
		bounds.set(0, 0, dimension.x, dimension.y);
	}

	@Override
	public void render(SpriteBatch batch) {
		// puste pole, oznaczma po prostu miejsca, gdzie moze sie pojawic przeciwnik
	}

}
