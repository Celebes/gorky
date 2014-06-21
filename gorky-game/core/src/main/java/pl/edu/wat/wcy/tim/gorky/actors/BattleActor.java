package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.BattleGameObject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BattleActor extends AnimatedActor {
	
	protected boolean attackFinished = false;
	protected boolean damageFinished = false;
	protected boolean animateSwordSwing = false;
	
	protected Animation animNormal;
	protected Animation animAttack;
	protected Animation animDamage;
	protected Animation animSwordSwing;
	
	// animacja smierci
	protected boolean deathAnimation = false;
	protected float deathAnimationOffsetY = 0.0f;
	protected float deathAnimationAlphaChannel = 1.0f;
	
	public BattleActor() {
		this.animSwordSwing = Assets.instance.swordSwing.animSwordSwing;
	}
	
	public boolean isDamageFinished() {
		return damageFinished;
	}
	
	public boolean isAttackFinished() {
		return attackFinished;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(animSwordSwing != null && animSwordSwing.isAnimationFinished(stateTime)) {
			animateSwordSwing = false;
		}
		
		if(animation == animNormal) {
			
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
				
				
				if(deathAnimation == false) {
					setAnimation(animNormal);
					damageFinished = true;
				}
			}
			
		}
		
		if(deathAnimation == true) {
			deathAnimationAlphaChannel -= 0.07f;
			deathAnimationOffsetY += 0.3f;
			this.offsetY += deathAnimationOffsetY;
			
			if(deathAnimationAlphaChannel <= 0) {
				this.remove();
			}
		}
		
	}
	
	public void startAttackAnimation() {		
		// uruchamia animacje
		setAnimation(animAttack);
	}
	
	public void startDamageAnimation() {	
		// uruchamia animacje
		setAnimation(animDamage);
		
		// ustaw poczatek animacji otrzymywania ciosu
		animateSwordSwing = true;
	}
	
	public void startDeathAnimation() {
		deathAnimation = true;
		
		// uruchamia animacje
		setAnimation(animDamage);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		if(animation == animDamage || deathAnimation == true) {
			batch.setColor(1, 0, 0, deathAnimationAlphaChannel);
		}
		
		super.draw(batch, parentAlpha);
		
		batch.setColor(1, 1, 1, 1);
		
		// dorysuj efekt ruchu mieczem
		if(animation == animDamage && animateSwordSwing == true) {
			TextureRegion reg = animSwordSwing.getKeyFrame(stateTime, true);
			batch.draw(reg, getX(), getY(), getOriginX(), getOriginY(), reg.getRegionWidth(), reg.getRegionHeight(), 1.5f, 1.5f, 1.0f);
		}
	}
	
	@Override
	public float getWidth() {
		return animation.getKeyFrame(stateTime).getRegionWidth();
	}
	
	@Override
	public float getHeight() {
		return animation.getKeyFrame(stateTime).getRegionHeight();
	}
}
