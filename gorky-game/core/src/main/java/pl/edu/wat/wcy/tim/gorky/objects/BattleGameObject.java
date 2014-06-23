package pl.edu.wat.wcy.tim.gorky.objects;

import java.util.Random;

import pl.edu.wat.wcy.tim.gorky.equipment.Equipment;
import pl.edu.wat.wcy.tim.gorky.util.GameplayFormulas;
import pl.edu.wat.wcy.tim.gorky.util.SaveStatePreferences;

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
		
		System.out.println("ATK = " + characterAttributes.getAtk() + " | CALCULATED DMG = " + calculatedDMG);
		
		return calculatedDMG;
	}
	
	/*
	 * Zwraca rzeczywiscie otrzymane obrazenia z uwzglednieniem obrony itp
	 */
	public int receiveDamage(float dmg) {
		int calculatedHPDecrease = (int)(dmg - characterAttributes.getDef());
		float hpBefore = characterAttributes.getHP();
		characterAttributes.setHP((int)(hpBefore - calculatedHPDecrease));
		
		return (calculatedHPDecrease < 0 ? 1 : calculatedHPDecrease);
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
	
	/*
	 * Zarzadza zdobywaniem poziomow - dostaje EXP, sprawdza czy zwiekszyc poziom doswiadczenia itp
	 */
	public boolean increaseExperience(int exp) {
		boolean result = false;
		
		int currentEXP = characterAttributes.getExp();
		int currentLevel = characterAttributes.getLevel();
		int expToLevelUp = GameplayFormulas.getExpForNextLevel(currentLevel);
		int expToBeAdded = exp;
		
		if(currentEXP + exp > expToLevelUp) {
			System.out.println("LEVEL UP!");
			result = true;
			
			expToBeAdded = (currentEXP + exp) % expToLevelUp;
			
			// zwieksz poziom
			characterAttributes.setLevel(currentLevel + 1);
			
			// dopisz doswiadczenie modulo poprzedni poziom
			characterAttributes.setExp(expToBeAdded);
			
			// losuj nowe atrybuty
			Random r = new Random();
			
			int newAtk = characterAttributes.getAtk() + (r.nextInt(3) + 3);
			int newDef = characterAttributes.getDef() + (r.nextInt(3) + 2);
			int newMagAtk = characterAttributes.getMagAtk() + (r.nextInt(3) + 5);
			int newMagDef = characterAttributes.getMagDef() + (r.nextInt(3) + 2);
			int newMaxHP = characterAttributes.getMaxHP() + (r.nextInt(10) + 10);
			int newMaxMP = characterAttributes.getMaxMP() + (r.nextInt(5) + 5);
			
			characterAttributes.setAtk(newAtk);
			characterAttributes.setDef(newDef);
			characterAttributes.setMagAtk(newMagAtk);
			characterAttributes.setMagDef(newMagDef);
			characterAttributes.setMaxHP(newMaxHP);
			characterAttributes.setMaxMP(newMaxMP);
			
			// odnow HP
			characterAttributes.setHP(characterAttributes.getMaxHP());
			characterAttributes.setMP(characterAttributes.getMaxMP());
		} else {
			characterAttributes.setExp(currentEXP + expToBeAdded); 
		}
		
		// zapisz stan gry
		saveCharacterStats();
		
		System.out.println("Zwiekszono doswiadczenie gracza o " + exp + "!");
		
		if(result == true) {
			System.out.println("Gracz osiagnal nowy poziom: " + characterAttributes.getLevel());
		}
		
		// zwroc TRUE jesli zwiekszono poziom
		return result;
	}
	
	private void saveCharacterStats() {
		SaveStatePreferences.instance.atk = characterAttributes.getAtk();
		SaveStatePreferences.instance.def = characterAttributes.getDef();
		SaveStatePreferences.instance.magAtk = characterAttributes.getMagAtk();
		SaveStatePreferences.instance.magDef = characterAttributes.getMagDef();
		SaveStatePreferences.instance.maxHP = characterAttributes.getMaxHP();
		SaveStatePreferences.instance.maxMP = characterAttributes.getMaxMP();
		SaveStatePreferences.instance.HP = characterAttributes.getHP();
		SaveStatePreferences.instance.MP = characterAttributes.getMP();
		
		SaveStatePreferences.instance.exp = characterAttributes.getExp();
		SaveStatePreferences.instance.level = characterAttributes.getLevel();
		SaveStatePreferences.instance.gold = characterAttributes.getGold();
		
		SaveStatePreferences.instance.save();
	}

	public void increaseGold(int gold) {
		int currentGold = characterAttributes.getGold();
		characterAttributes.setGold(currentGold + gold);
		
		SaveStatePreferences.instance.gold = characterAttributes.getGold();
		SaveStatePreferences.instance.save();
	}
	
	/*
	 * Zmniejsza doswiadczenie o polowe w przypadku smierci
	 */
	public void deathExpPenalty() {
		int currentEXP = characterAttributes.getExp();
		characterAttributes.setExp(currentEXP/2);
		
		SaveStatePreferences.instance.exp = characterAttributes.getExp();
		SaveStatePreferences.instance.save();
	}
	
	public float calculateHpPercent() {
		return this.characterAttributes.getHP() / (this.characterAttributes.getMaxHP()*1.0f);
	}
	
	public float calculateMpPercent() {
		return this.characterAttributes.getMP() / (this.characterAttributes.getMaxMP()*1.0f);
	}

}
