package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.BattleGameObject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class OrcActor extends BattleActor {

	public OrcActor() {
		init();
	}
	
	public OrcActor(BattleGameObject enemy) {
		super(enemy);
		init();
	}

	private void init() {
		animNormal = Assets.instance.orc.animNormal;
		animAttack = Assets.instance.orc.animAttack;
		animDamage = Assets.instance.orc.animDamage;
		setAnimation(animNormal);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		float offsetX = 0;
		float offsetY = 0;
		
		if(animation == animAttack) {
			int currentFrame = animation.getKeyFrameIndex(stateTime);
			
			switch(currentFrame) {
			case 0:
				offsetX = 36;
				break;
			case 1:
				offsetX = 36;
				break;
			case 2:
				offsetX = 36;
				break;
			case 3:
				offsetX = -130;
				break;
			case 4:
				offsetX = -125;
				break;
			case 5:
				offsetX = -124;
				break;
			case 6:
				offsetX = -125;
				break;
			case 7:
				offsetX = -110;
				break;
			case 8:
				offsetX = -95;
				break;
			case 9:
				offsetX = -107;
				break;
			case 10:
				offsetX = -95;
				break;
			case 11:
				offsetX = -5;
				break;
			case 12:
				offsetX = -5;
				break;
			}
		}
		
		TextureRegion reg = animation.getKeyFrame(stateTime, true);
		batch.draw(reg, getX() + offsetX, getY() + offsetY, reg.getRegionWidth(), reg.getRegionHeight());
	}

}
