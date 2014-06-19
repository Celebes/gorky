package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;

public class KnightActor extends AnimatedActor {
	
	private Animation animNormal;
	private Animation animDash;
	private Animation animAttack;
	private Animation animDamage;
	
	public KnightActor() {
		init();
	}

	private void init() {
		animNormal = Assets.instance.knight.animNormal;
		animDash = Assets.instance.knight.animDash;
		animAttack = Assets.instance.knight.animAttack;
		animDamage = Assets.instance.knight.animDamage;
		setAnimation(animNormal);
	}
	
}
