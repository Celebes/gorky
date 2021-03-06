package pl.edu.wat.wcy.tim.gorky.objects;

public class CharacterAttributes {
	
	private int atk;			// sila ataku
	private int def;			// obrona przeciwko atakom fizycznym
	private int magAtk;			// sila ataku magicznego
	private int magDef;			// obrona przeciwko atakom magicznym
	private int HP;				// aktualne punkty zycia
	private int maxHP;			// max punkty zycia
	private int MP;				// aktualne punkty many
	private int maxMP;			// max punkty many
	
	private int gold;			// ilosc posiadanego zlota
	private int exp;			// ilosc posiadanego doswiadczenia
	private int level;			// aktualny poziom postaci
	
	public CharacterAttributes(int atk, int def, int magAtk, int magDef, int hP, int maxHP, int mP, int maxMP, int gold, int exp, int expForNextLevel, int level) {
		super();
		this.atk = atk;
		this.def = def;
		this.magAtk = magAtk;
		this.magDef = magDef;
		this.HP = hP;
		this.maxHP = maxHP;
		this.MP = mP;
		this.maxMP = maxMP;
		this.exp = exp;
		this.gold = gold;
		this.level = level;
	}

	public CharacterAttributes() {
		
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getMagAtk() {
		return magAtk;
	}

	public void setMagAtk(int magAtk) {
		this.magAtk = magAtk;
	}

	public int getMagDef() {
		return magDef;
	}

	public void setMagDef(int magDef) {
		this.magDef = magDef;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public int getMaxMP() {
		return maxMP;
	}

	public void setMaxMP(int maxMP) {
		this.maxMP = maxMP;
	}
	
}
