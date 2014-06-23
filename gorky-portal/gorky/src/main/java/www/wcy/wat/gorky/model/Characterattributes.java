/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.wcy.wat.gorky.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Krzysztof Jedynak
 */
@Entity
@Table(name = "characterattributes")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Characterattributes.findAll", query = "SELECT c FROM Characterattributes c"),
		@NamedQuery(name = "Characterattributes.findByIdcharacterAttribute", query = "SELECT c FROM Characterattributes c WHERE c.idcharacterAttribute = :idcharacterAttribute"),
		@NamedQuery(name = "Characterattributes.findByAtk", query = "SELECT c FROM Characterattributes c WHERE c.atk = :atk"),
		@NamedQuery(name = "Characterattributes.findByDef", query = "SELECT c FROM Characterattributes c WHERE c.def = :def"),
		@NamedQuery(name = "Characterattributes.findByMagAtk", query = "SELECT c FROM Characterattributes c WHERE c.magAtk = :magAtk"),
		@NamedQuery(name = "Characterattributes.findByMagDef", query = "SELECT c FROM Characterattributes c WHERE c.magDef = :magDef"),
		@NamedQuery(name = "Characterattributes.findByHp", query = "SELECT c FROM Characterattributes c WHERE c.hp = :hp"),
		@NamedQuery(name = "Characterattributes.findByMaxHP", query = "SELECT c FROM Characterattributes c WHERE c.maxHP = :maxHP"),
		@NamedQuery(name = "Characterattributes.findByMp", query = "SELECT c FROM Characterattributes c WHERE c.mp = :mp"),
		@NamedQuery(name = "Characterattributes.findByMaxMP", query = "SELECT c FROM Characterattributes c WHERE c.maxMP = :maxMP"),
		@NamedQuery(name = "Characterattributes.findByGold", query = "SELECT c FROM Characterattributes c WHERE c.gold = :gold"),
		@NamedQuery(name = "Characterattributes.findByExp", query = "SELECT c FROM Characterattributes c WHERE c.exp = :exp"),
		@NamedQuery(name = "Characterattributes.findByLevel", query = "SELECT c FROM Characterattributes c WHERE c.level = :level") })
public class Characterattributes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_characterAttribute")
	private Integer idcharacterAttribute;
	@Column(name = "atk")
	private Integer atk;
	@Column(name = "def")
	private Integer def;
	@Column(name = "magAtk")
	private Integer magAtk;
	@Column(name = "magDef")
	private Integer magDef;
	@Column(name = "HP")
	private Integer hp;
	@Column(name = "maxHP")
	private Integer maxHP;
	@Column(name = "MP")
	private Integer mp;
	@Column(name = "maxMP")
	private Integer maxMP;
	@Column(name = "gold")
	private Integer gold;
	@Column(name = "exp")
	private Integer exp;
	@Column(name = "level")
	private Integer level;
	@JoinColumn(name = "Id_avatar", referencedColumnName = "Id_avatar")
	@ManyToOne
	private Avatar idavatar;
	@OneToMany(mappedBy = "idcharacterAttribute")
	private Collection<Avatar> avatarCollection;

	public Characterattributes() {
	}

	public Characterattributes(Integer idcharacterAttribute) {
		this.idcharacterAttribute = idcharacterAttribute;
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

	public Avatar getIdavatar() {
		return idavatar;
	}

	public void setIdavatar(Avatar idavatar) {
		this.idavatar = idavatar;
	}

	@XmlTransient
	public Collection<Avatar> getAvatarCollection() {
		return avatarCollection;
	}

	public void setAvatarCollection(Collection<Avatar> avatarCollection) {
		this.avatarCollection = avatarCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcharacterAttribute != null ? idcharacterAttribute.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Characterattributes)) {
			return false;
		}
		Characterattributes other = (Characterattributes) object;
		if ((this.idcharacterAttribute == null && other.idcharacterAttribute != null)
				|| (this.idcharacterAttribute != null && !this.idcharacterAttribute
						.equals(other.idcharacterAttribute))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Characterattributes[ idcharacterAttribute="
				+ idcharacterAttribute + " ]";
	}

}
