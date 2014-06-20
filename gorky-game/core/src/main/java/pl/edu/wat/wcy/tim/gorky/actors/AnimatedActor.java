package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AnimatedActor extends Actor {
	
	// animacja
	public float stateTime;
	public Animation animation;
	
	// przesuniecie w zaleznosci od klatki
	protected float offsetX = 0;
	protected float offsetY = 0;
	
	public AnimatedActor() {

	}
	
	public void setAnimation (Animation animation) {
		this.animation = animation;
		stateTime = 0;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		TextureRegion reg = animation.getKeyFrame(stateTime, true);
		
		batch.draw(reg, getX() + offsetX, getY() + offsetY, reg.getRegionWidth(), reg.getRegionHeight());
		
		offsetX = 0;
		offsetY = 0;
	}

	@Override
	public void act(float delta) {
		stateTime += delta;
		super.act(delta);
	}

}
