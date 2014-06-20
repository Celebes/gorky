package pl.edu.wat.wcy.tim.gorky.actors;

import com.badlogic.gdx.graphics.g2d.Batch;

import pl.edu.wat.wcy.tim.gorky.game.Assets;
import pl.edu.wat.wcy.tim.gorky.objects.BattleGameObject;
import pl.edu.wat.wcy.tim.gorky.objects.Player;

public class KnightActor extends BattleActor {
	
	public KnightActor() {
		init();
	}
	
	public KnightActor(BattleGameObject player) {
		super(player);
		init();
	}

	private void init() {
		animNormal = Assets.instance.knight.animNormal;
		animAttack = Assets.instance.knight.animAttack;
		animDamage = Assets.instance.knight.animDamage;
		setAnimation(animNormal);
	}
	
}
