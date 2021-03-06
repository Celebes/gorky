package pl.edu.wat.wcy.tim.gorky.equipment;

import java.util.ArrayList;
import java.util.List;

import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

import com.badlogic.gdx.Gdx;

public class Equipment {
	public static final String TAG = Equipment.class.getName();
	
	private CharacterAttributes characterAttributes;

	// noszony ekwipunek
	EquipableItem[] currentEquipment;
	
	// zawartosc plecaka
	List<Item> items;
	
	public Equipment(CharacterAttributes ca) {
		this.characterAttributes = ca;
		currentEquipment = new EquipableItem[EquipmentSlot.values().length];
		items = new ArrayList<>();
	}
	
	public void equipItem(EquipableItem item) {
		int index = item.getEquipmentSlot().ordinal();
		
		if(currentEquipment[index] != null) {
			currentEquipment[index].unequip(characterAttributes);
			System.out.println("Item \"" + currentEquipment[index].getName() + "\" has been unequipped");
		}
		
		currentEquipment[index] = item;
		currentEquipment[index].equip(characterAttributes);
		
		System.out.println("Weapon \"" + currentEquipment[index].getName() + "\" has been equipped");
		
		if(index == EquipmentSlot.WEAPON.ordinal()) {
			System.out.println("Zalozono bron!");
			SaveStatePreferences.instance.weaponEquipped = true;
			SaveStatePreferences.instance.save();
		}
		
	}
	
	public EquipableItem getEquipableItemByType(EquipmentSlot equipmentSlot) {
		return currentEquipment[equipmentSlot.ordinal()];
	}
	
	public void unequipItemByType(EquipmentSlot equipmentSlot) {
		int index = equipmentSlot.ordinal();
		currentEquipment[index].unequip(characterAttributes);
		//Gdx.app.debug(TAG, "Item \"" + currentEquipment[index].getName() + "\" has been unequipped");
		System.out.println("Item \"" + currentEquipment[index].getName() + "\" has been unequipped");
		currentEquipment[index] = null;
		
		if(index == EquipmentSlot.WEAPON.ordinal()) {
			System.out.println("Zdjeto bron!");
			SaveStatePreferences.instance.weaponEquipped = true;
			SaveStatePreferences.instance.weaponName = null;
			SaveStatePreferences.instance.weaponAtk = 0;
			SaveStatePreferences.instance.save();
		}
	}
}
