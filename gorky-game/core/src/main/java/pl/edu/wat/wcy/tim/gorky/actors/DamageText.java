package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class DamageText extends Text {
	
	private float duration;
	private Color color;
	private float currentTime = 0;
	private float currentAlpha = 1.0f;
	private float currentYOffset = 0;
	
	public DamageText(BitmapFont font, String text, Color color, float duration) {
		super(font, text);
		this.duration = duration;
		this.color = color;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		currentTime += delta;
		currentYOffset += 0.1f;
		
		if(currentTime >= duration) {
			currentAlpha -= 0.05f;
			
			if(currentAlpha <= 0) {
				this.remove();
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setColor(color);
		font.getColor().a = currentAlpha;
		font.draw(batch, text, getX(), getY() + currentYOffset);
	}

}
