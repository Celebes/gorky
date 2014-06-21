package pl.edu.wat.wcy.tim.gorky.util;

import java.util.Random;

public class GameplayFormulas {
	public static int getExpForNextLevel(int level) {
		double a = 4 * Math.pow(level, 3);
		double b = a / 3.0;
		double c = Math.ceil(b * 10);
		return (int)c;
	}
	
	public static int getExpFromEnemy(int enemyLevel) {
		return enemyLevel*5 + (new Random()).nextInt(enemyLevel*3) + 1;
	}
	
	public static int getGoldFromEnemy(int enemyLevel) {
		return enemyLevel*8 + (new Random()).nextInt(enemyLevel*5);
	}
}
