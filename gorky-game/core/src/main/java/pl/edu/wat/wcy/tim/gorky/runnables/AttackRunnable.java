package pl.edu.wat.wcy.tim.gorky.runnables;

import pl.edu.wat.wcy.tim.gorky.actors.BattleActor;

public class AttackRunnable implements Runnable {
	
	private float attackDMG;
	private BattleActor attacker;
	
	public AttackRunnable(BattleActor attacker) {
		this.attacker = attacker;
	}

	@Override
	public void run() {
		attackDMG = attacker.attack();
	}

	public float getAttackDMG() {
		return attackDMG;
	}
	
}
