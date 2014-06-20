package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;

public class OrcActor extends BattleActor {

	public OrcActor() {
		init();
	}

	private void init() {
		animNormal = Assets.instance.orc.animNormal;
		animAttack = Assets.instance.orc.animAttack;
		animDamage = Assets.instance.orc.animDamage;
		setAnimation(animNormal);
	}

}
