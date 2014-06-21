package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Batch;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.BattleGameObject;
import pl.edu.wat.wcy.tim.gorky.objects.Player;

public class KnightActor extends BattleActor {
	
	public KnightActor() {
		init();
	}

	private void init() {
		animNormal = Assets.instance.knight.animNormal;
		animAttack = Assets.instance.knight.animAttack;
		animDamage = Assets.instance.knight.animDamage;
		setAnimation(animNormal);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		int currentFrame = animation.getKeyFrameIndex(stateTime);
		
		if(animation == animAttack) {
			switch(currentFrame) {
			case 0:
				offsetX = -27;
				break;
			case 1:
				offsetX = -7;
				break;
			case 2:
				offsetX = -92;
				offsetY = -14;
				break;
			case 3:
			case 4:
				offsetX = 3;
				offsetY = -21;
				break;
			case 5:
				break;
			}
		} else if(animation == animDamage) {
			switch(currentFrame) {
			case 0:
				offsetX = -9;
				break;
			case 1:
				offsetX = 3;
				break;
			case 2:
				offsetX = -18;
				break;
			case 3:
				offsetX = -27;
				break;
			}
		}
		
		super.draw(batch, parentAlpha);
	}
	
}
