package pl.edu.wat.wcy.tim.gorky.util;

import java.util.Random;

import pl.edu.wat.wcy.tim.gorky.objects.CharacterAttributes;

public class GameplayFormulas {
	public static int getExpForNextLevel(int level) {
		double a = 4 * Math.pow(level, 3);
		double b = a / 3.0;
		double c = Math.ceil(b * 10);
		return (int)c;
	}
	
	public static int getExpFromEnemy(int enemyLevel) {
		return enemyLevel*10 + (new Random()).nextInt(enemyLevel*5) + 1;
	}
	
	public static int getGoldFromEnemy(int enemyLevel) {
		return enemyLevel*8 + (new Random()).nextInt(enemyLevel*5);
	}
	
	public static CharacterAttributes generateEnemyAttributes() {
		CharacterAttributes enemyAttr = new CharacterAttributes();
		
		Random r = new Random();
		
		int probability = r.nextInt(100) + 1;
		int enemyLevel = 1;
		
		// 55% szansy na potwora level 1, 35% szansy na level 2, 10% szansy na level 3
		
		if(probability > 55) {
			if(probability > 90) {
				enemyLevel = 3;
			} else {
				enemyLevel = 2;
			}
		}
		
		int atk = 7 + 2 * enemyLevel;
		int def = 0 + 2 * enemyLevel;
		int maxHP = 11 + 8 * enemyLevel;
		
		enemyAttr.setAtk(atk);
		enemyAttr.setDef(def);
		enemyAttr.setMaxHP(maxHP);
		enemyAttr.setHP(maxHP);
		
		enemyAttr.setMP(5);
		enemyAttr.setMaxMP(5);
		
		enemyAttr.setLevel(enemyLevel);
		
		return enemyAttr;
	}
}
