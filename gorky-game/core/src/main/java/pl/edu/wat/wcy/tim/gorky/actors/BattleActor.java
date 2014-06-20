package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BattleActor extends AnimatedActor {
	
	boolean turnFinished = false;
	
	protected Animation animNormal;
	protected Animation animAttack;
	protected Animation animDamage;
	
	public boolean isTurnFinished() {
		return turnFinished;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(animation == animNormal) {
			if(turnFinished == true) {
				turnFinished = false;
			}
		}
		
		else if(animation == animAttack) {

			if(animation.isAnimationFinished(stateTime)) {
				setAnimation(animNormal);
				turnFinished = true;
			}
		}
		
	}

	public void setAttackAnimation() {
		setAnimation(animAttack);
	}
}
