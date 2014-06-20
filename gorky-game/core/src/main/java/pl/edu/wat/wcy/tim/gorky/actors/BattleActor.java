package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.objects.BattleGameObject;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BattleActor extends AnimatedActor {
	
	protected boolean attackFinished = false;
	protected boolean turnFinished = false;
	protected boolean damageFinished = false;
	
	protected Animation animNormal;
	protected Animation animAttack;
	protected Animation animDamage;
	
	private BattleGameObject battleGameObject;
	
	private float damageToBeReceived = 0.0f;
	
	public BattleActor() {}
	
	public BattleActor(BattleGameObject battleGameObject) {
		this.battleGameObject = battleGameObject;
	}
	
	public boolean isDamageFinished() {
		return damageFinished;
	}

	public boolean isTurnFinished() {
		return turnFinished;
	}
	
	public void setTurnFinished(boolean turnFinished) {
		this.turnFinished = turnFinished;
	}
	
	public boolean isAttackFinished() {
		return attackFinished;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(animation == animNormal) {
			
			/*if(turnFinished == true) {
				turnFinished = false;
			}*/
			
			if(attackFinished == true) {
				attackFinished = false;
			}
			
			if(damageFinished == true) {
				damageFinished = false;
			}
		}
		
		else if(animation == animAttack) {

			if(animation.isAnimationFinished(stateTime)) {
				setAnimation(animNormal);
				attackFinished = true;
			}
		}
		
		else if(animation == animDamage) {
			
			if(animation.isAnimationFinished(stateTime)) {
				setAnimation(animNormal);
				battleGameObject.receiveDamage(damageToBeReceived);
				damageToBeReceived = 0.0f;
				damageFinished = true;
				//turnFinished = true;
			}
			
		}
		
	}
	
	public float attack() {
		// oblicza obrazenia, ktore zostana zadane
		float attackDamage = battleGameObject.dealDamage();
		
		// uruchamia animacje
		setAnimation(animAttack);
		
		// zwraca obrazenia
		return attackDamage;
	}
	
	public float receiveDamage(float dmg) {
		// oblicza obrazenia, ktore zostana otrzymane i zapisuje je na pozniej
		this.damageToBeReceived = battleGameObject.calculateReceivedDamage(dmg);
		
		// uruchamia animacje
		setAnimation(animDamage);
		
		// zwraca obrazenia
		return this.damageToBeReceived;
	}
}
