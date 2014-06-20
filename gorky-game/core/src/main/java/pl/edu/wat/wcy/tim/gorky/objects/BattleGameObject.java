package pl.edu.wat.wcy.tim.gorky.objects;

import java.util.Random;

import pl.edu.wat.wcy.tim.gorky.equipment.Equipment;

public abstract class BattleGameObject extends AbstractGameObject {
	
	// statystyki
	protected CharacterAttributes characterAttributes;
	
	// equipment
	protected Equipment equipment;
	
	/*
	 * Zwraca obrazenia, ktore maja zostac zadane, na podstawie ataku, sily, ekwipunku itp
	 */
	public float getCalculatedDamage() {
		Random r = new Random();
		
		float calculatedDMG = characterAttributes.getAtk() + (int)Math.ceil(r.nextInt(characterAttributes.getAtk() + 1) / 4.0);
		
		return calculatedDMG;
	}
	
	/*
	 * Zwraca rzeczywiscie otrzymane obrazenia z uwzglednieniem obrony itp
	 */
	public int receiveDamage(float dmg) {
		int calculatedHPDecrease = (int)(dmg - characterAttributes.getDef());
		float hpBefore = characterAttributes.getHP();
		characterAttributes.setHP((int)(hpBefore - calculatedHPDecrease));
		
		return (calculatedHPDecrease < 0 ? 0 : calculatedHPDecrease);
	}
	
	public CharacterAttributes getCharacterAttributes() {
		return characterAttributes;
	}

	public void setCharacterAttributes(CharacterAttributes characterAttributes) {
		this.characterAttributes = characterAttributes;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
