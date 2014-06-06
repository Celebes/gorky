package pl.edu.wat.wcy.tim.gorky.equipment;

import static org.junit.Assert.*;
import org.junit.Test;

import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;
import pl.edu.wat.wcy.tim.gorky.objects.Player;

public class EquipmentTest {

	@Test
	public void test() {
		CharacterAttributes ca = new CharacterAttributes();
		Equipment eq = new Equipment(ca);
		Sword sword = new Sword("sword", 5);
		
		ca.setAtk(5);
		
		assertTrue(sword.getName().equals("sword"));
		assertTrue(ca.getAtk() == 5);
		
		eq.equipItem(sword);
		
		assertTrue(ca.getAtk() == 10);
		
		eq.unequipItemByType(EquipmentSlot.WEAPON);
		
		assertTrue(ca.getAtk() == 5);
		
	}

}
