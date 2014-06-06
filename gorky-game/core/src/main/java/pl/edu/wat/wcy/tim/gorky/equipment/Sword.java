package pl.edu.wat.wcy.tim.gorky.equipment;

import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;

public class Sword extends Weapon {
	
	public Sword(String name, int atk) {
		super(name, atk);
	}

	@Override
	public void equip(CharacterAttributes ca) {
		ca.setAtk(ca.getAtk() + this.atk);
	}

	@Override
	public void unequip(CharacterAttributes ca) {
		ca.setAtk(ca.getAtk() - this.atk);
	}

}
