package pl.edu.wat.wcy.tim.gorky.equipment;


/*
 * Przedmiot, ktory postac moze zalozyc
 */

public abstract class EquipableItem extends Item implements Equipable {

	protected EquipmentSlot equipmentSlot;
	
	public EquipableItem(String name, EquipmentSlot equipmentSlot) {
		super(name);
		this.equipmentSlot = equipmentSlot;
	}

	public EquipmentSlot getEquipmentSlot() {
		return equipmentSlot;
	}

	public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
		this.equipmentSlot = equipmentSlot;
	}
	
}
