package pl.edu.wat.wcy.tim.gorky.objects;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wall extends AbstractGameObject {
	private TextureRegion regWall;
	
	public Wall() {
		init();
	}
	
	private void init() {
		dimension.set(1.0f, 1.0f);
		origin.set(0.5f, 0.5f);
		bounds.set(0, 0, dimension.x, dimension.y);
		regWall = Assets.wall;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(regWall.getTexture(), position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
				regWall.getRegionX(), regWall.getRegionY(), regWall.getRegionWidth(),
				regWall.getRegionHeight(), false, false);
	}

}
