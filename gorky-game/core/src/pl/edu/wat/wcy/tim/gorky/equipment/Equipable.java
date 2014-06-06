package pl.edu.wat.wcy.tim.gorky.equipment;

import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;

public interface Equipable {
	public void equip(CharacterAttributes ca);
	public void unequip(CharacterAttributes ca);
}
