package pl.edu.wat.wcy.tim.gorky.dto;

public class IntegrationDTO {

	// user details
	private Integer iduser;
	private String login;
	private String password;
	private String nameUser;
	private String surname;
	private Integer old;
	private String country;
	private String city;
	private String email;
	private String description;

	// vatar details
	private Integer idavatar;
	private String nameAvatar;
	private Float positionX;
	private Float positionY;
	private String currentMapLevel;

	// characteristic attribute details
	private Integer idcharacterAttribute;
	private Integer atk;
	private Integer def;
	private Integer magAtk;
	private Integer magDef;
	private Integer hp;
	private Integer maxHP;
	private Integer mp;
	private Integer maxMP;
	private Integer gold;
	private Integer exp;
	private Integer level;

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getOld() {
		return old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdavatar() {
		return idavatar;
	}

	public void setIdavatar(Integer idavatar) {
		this.idavatar = idavatar;
	}

	public String getNameAvatar() {
		return nameAvatar;
	}

	public void setNameAvatar(String nameAvatar) {
		this.nameAvatar = nameAvatar;
	}

	public Float getPositionX() {
		return positionX;
	}

	public void setPositionX(Float positionX) {
		this.positionX = positionX;
	}

	public Float getPositionY() {
		return positionY;
	}

	public void setPositionY(Float positionY) {
		this.positionY = positionY;
	}

	public String getCurrentMapLevel() {
		return currentMapLevel;
	}

	public void setCurrentMapLevel(String currentMapLevel) {
		this.currentMapLevel = currentMapLevel;
	}

	public Integer getIdcharacterAttribute() {
		return idcharacterAttribute;
	}

	public void setIdcharacterAttribute(Integer idcharacterAttribute) {
		this.idcharacterAttribute = idcharacterAttribute;
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public Integer getMagAtk() {
		return magAtk;
	}

	public void setMagAtk(Integer magAtk) {
		this.magAtk = magAtk;
	}

	public Integer getMagDef() {
		return magDef;
	}

	public void setMagDef(Integer magDef) {
		this.magDef = magDef;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(Integer maxHP) {
		this.maxHP = maxHP;
	}

	public Integer getMp() {
		return mp;
	}

	public void setMp(Integer mp) {
		this.mp = mp;
	}

	public Integer getMaxMP() {
		return maxMP;
	}

	public void setMaxMP(Integer maxMP) {
		this.maxMP = maxMP;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "IntegrationDTO [iduser=" + iduser + ", login=" + login
				+ ", password=" + password + ", nameUser=" + nameUser
				+ ", surname=" + surname + ", old=" + old + ", country="
				+ country + ", city=" + city + ", email=" + email
				+ ", description=" + description + ", idavatar=" + idavatar
				+ ", nameAvatar=" + nameAvatar + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", currentMapLevel="
				+ currentMapLevel + ", idcharacterAttribute="
				+ idcharacterAttribute + ", atk=" + atk + ", def=" + def
				+ ", magAtk=" + magAtk + ", magDef=" + magDef + ", hp=" + hp
				+ ", maxHP=" + maxHP + ", mp=" + mp + ", maxMP=" + maxMP
				+ ", gold=" + gold + ", exp=" + exp + ", level=" + level + "]";
	}

}
