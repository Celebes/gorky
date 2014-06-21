package pl.edu.wat.wcy.tim.gorky.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NextLevelTeleport extends AbstractGameObject {
	
	public NextLevelTeleport() {
		init();
	}
	
	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(dimension.x / 2, dimension.y / 2);
		bounds.set(0, 0, dimension.x, dimension.y);
	}

	@Override
	public void render(SpriteBatch batch) {
		// puste pole, oznaczam po prostu miejsca, gdzie nastapi teleportacja na inna mape
	}

}