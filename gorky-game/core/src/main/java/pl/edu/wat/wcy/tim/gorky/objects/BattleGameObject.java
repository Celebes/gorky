package pl.edu.wat.wcy.tim.gorky.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BattleGameObject extends AbstractGameObject {
	
	// statystyki
	protected CharacterAttributes characterAttributes;
	
	public CharacterAttributes getCharacterAttributes() {
		return characterAttributes;
	}

	public void setCharacterAttributes(CharacterAttributes characterAttributes) {
		this.characterAttributes = characterAttributes;
	}

	public float dealDamage() {
		// zwroc ilosc zadanych obrazen
		float dmgDealt = characterAttributes.getAtk();
		return dmgDealt;
	}
	
	public float receiveDamage(float dmg) {
		// zwroc ilosc utraconych punktow zycia (?)
		return dmg;
	}
	
	public float calculateReceivedDamage(float dmg) {
		// zwroc ilosc otrzymanych punktow obrazen
		return dmg;
	}

}
