package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ProgressBarActor extends Actor {
	
	ShapeRenderer shapeRenderer;
	
	private float currentPercent;		// aktualna wartosc procent, moze byc akurat zmniejszana/zwiekszana
	private float percent;				// docelowa ilosc procent paska
	private float step = 0.01f;			// krok animacji
	private Color c;
	
	public ProgressBarActor(float percent, Color c) {
		shapeRenderer = new ShapeRenderer();
		
		this.percent = percent;
		this.currentPercent = percent;
		this.c = c;
	}
	
	@Override
    public void draw(Batch batch, float parentAlpha) {		
		batch.setColor(Color.GRAY);
		batch.draw(Assets.instance.progressBarImg, getX(), getY(), getWidth(), getHeight());
		batch.setColor(c);
		batch.draw(Assets.instance.progressBarImg, getX(), getY(), getWidth() * currentPercent, getHeight());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(currentPercent > percent) {
			if(currentPercent > 0) {
				currentPercent -= step;
			}
		} else if(currentPercent < percent) {
			if(currentPercent < 100) {
				currentPercent += step;
			}
		}
		
		if(Math.abs(percent - currentPercent) <= 0.01f) {
			currentPercent = percent;
		}
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	
}
