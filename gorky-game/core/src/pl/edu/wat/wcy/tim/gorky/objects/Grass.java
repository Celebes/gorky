package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Grass extends AbstractGameObject {

	private TextureRegion regGrass;

	public Grass() {
		init();
	}

	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(0.5f, 0.5f);
		regGrass = Assets.instance.grass.grass;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(regGrass.getTexture(), position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
				regGrass.getRegionX(), regGrass.getRegionY(), regGrass.getRegionWidth(),
				regGrass.getRegionHeight(), false, false);
	}

}
