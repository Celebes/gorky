package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {
	
	protected BitmapFont font;
	protected String text;

	public Text(BitmapFont font, String text) {
		this.font = font;
		this.text = text;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch, text, getX(), getY());
	}
	
	@Override
	public float getHeight() {
		return font.getBounds(text).height;
	}
	
	@Override
	public float getWidth() {
		return font.getBounds(text).width;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
