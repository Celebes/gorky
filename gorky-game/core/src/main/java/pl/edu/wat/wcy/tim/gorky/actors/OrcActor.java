package pl.edu.wat.wcy.tim.gorky.actors;

import pl.edu.wat.wcy.tim.gorky.game.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;

public class OrcActor extends AnimatedActor {
	
	private Animation animNormal;
	
	public OrcActor() {
		init();
	}

	private void init() {
		animNormal = Assets.instance.orc.animNormal;
		setAnimation(animNormal);
	}

}
