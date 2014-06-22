package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class NumberText extends Text {
	
	private int number;
	private int currentNumber;
	private boolean changing = false;

	public NumberText(BitmapFont font, int number) {
		super(font, String.valueOf(number));
		this.number = number;
		this.currentNumber = number;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(currentNumber > number) {
			currentNumber--;
			changing = true;
		} else if(currentNumber < number) {
			currentNumber++;
			changing = true;
		}
		
		if(changing) {
			this.text = String.valueOf(currentNumber < 0 ? 0 : currentNumber);
			changing = false;
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
