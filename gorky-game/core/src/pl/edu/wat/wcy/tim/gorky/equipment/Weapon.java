package pl.edu.wat.wcy.tim.gorky.equipment;

public abstract class Weapon extends EquipableItem {
	
	protected int atk;
	
	public Weapon(String name, int atk) {
		super(name, EquipmentSlot.WEAPON);
		this.atk = atk;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}
	
}
