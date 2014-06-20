package pl.edu.wat.wcy.tim.gorky.runnables;

import pl.edu.wat.wcy.tim.gorky.actors.BattleActor;

public class ReceiveDamageRunnable implements Runnable {
	
	private float damageReceived;
	private BattleActor defender;
	private float dmg;
	
	public ReceiveDamageRunnable(BattleActor defender, float dmg) {
		this.defender = defender;
		this.dmg = dmg;
	}

	@Override
	public void run() {
		damageReceived = defender.receiveDamage(dmg);
	}
	
	public float getDamageReceived() {
		return damageReceived;
	}

}
