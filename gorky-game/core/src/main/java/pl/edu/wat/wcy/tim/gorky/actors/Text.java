package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {
	private final float DURATION = 1.0f;
	
	private BitmapFont font;
	private String text;
	
	private float currentTime = 0;
	private float currentAlpha = 1.0f;
	private float currentYOffset = 0;
	
	public Text(BitmapFont font, String text) {
		this.font = font;
		this.text = text;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		currentTime += delta;
		currentYOffset += 0.1f;
		
		if(currentTime >= DURATION) {
			currentAlpha -= 0.05f;
			
			if(currentAlpha <= 0) {
				this.remove();
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setColor(1.0f, 1.0f, 1.0f, currentAlpha);
		//font.setScale(3.0f);
		font.draw(batch, text, getX(), getY() + currentYOffset);
	}
	
	@Override
	public float getHeight() {
		return font.getBounds(text).height;
	}
	
	@Override
	public float getWidth() {
		return font.getBounds(text).width;
	}
	
}
