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
@Table(name = "equipment")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
		@NamedQuery(name = "Equipment.findByIdequipment", query = "SELECT e FROM Equipment e WHERE e.idequipment = :idequipment") })
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_equipment")
	private Integer idequipment;
	@OneToMany(mappedBy = "idequipment")
	private Collection<Weapon> weaponCollection;
	@OneToMany(mappedBy = "idequipment")
	private Collection<Armor> armorCollection;
	@OneToMany(mappedBy = "idequipment")
	private Collection<Potion> potionCollection;
	@JoinColumn(name = "Id_avatar", referencedColumnName = "Id_avatar")
	@ManyToOne
	private Avatar idavatar;
	@OneToMany(mappedBy = "idequipment")
	private Collection<Avatar> avatarCollection;

	public Equipment() {
	}

	public Equipment(Integer idequipment) {
		this.idequipment = idequipment;
	}

	public Integer getIdequipment() {
		return idequipment;
	}

	public void setIdequipment(Integer idequipment) {
		this.idequipment = idequipment;
	}

	@XmlTransient
	public Collection<Weapon> getWeaponCollection() {
		return weaponCollection;
	}

	public void setWeaponCollection(Collection<Weapon> weaponCollection) {
		this.weaponCollection = weaponCollection;
	}

	@XmlTransient
	public Collection<Armor> getArmorCollection() {
		return armorCollection;
	}

	public void setArmorCollection(Collection<Armor> armorCollection) {
		this.armorCollection = armorCollection;
	}

	@XmlTransient
	public Collection<Potion> getPotionCollection() {
		return potionCollection;
	}

	public void setPotionCollection(Collection<Potion> potionCollection) {
		this.potionCollection = potionCollection;
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
		hash += (idequipment != null ? idequipment.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Equipment)) {
			return false;
		}
		Equipment other = (Equipment) object;
		if ((this.idequipment == null && other.idequipment != null)
				|| (this.idequipment != null && !this.idequipment
						.equals(other.idequipment))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Equipment[ idequipment=" + idequipment
				+ " ]";
	}

}
