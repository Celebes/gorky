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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Krzysztof Jedynak
 */
@Entity
@Table(name = "avatar")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Avatar.findAll", query = "SELECT a FROM Avatar a"),
		@NamedQuery(name = "Avatar.findByIdavatar", query = "SELECT a FROM Avatar a WHERE a.idavatar = :idavatar"),
		@NamedQuery(name = "Avatar.findByName", query = "SELECT a FROM Avatar a WHERE a.name = :name"),
		@NamedQuery(name = "Avatar.findByPositionX", query = "SELECT a FROM Avatar a WHERE a.positionX = :positionX"),
		@NamedQuery(name = "Avatar.findByPositionY", query = "SELECT a FROM Avatar a WHERE a.positionY = :positionY"),
		@NamedQuery(name = "Avatar.findByCurrentMapLevel", query = "SELECT a FROM Avatar a WHERE a.currentMapLevel = :currentMapLevel") })
public class Avatar implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_avatar")
	private Integer idavatar;
	@Size(max = 32)
	@Column(name = "name")
	private String name;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "positionX")
	private Float positionX;
	@Column(name = "positionY")
	private Float positionY;
	@Size(max = 32)
	@Column(name = "current_map_level")
	private String currentMapLevel;
	@OneToMany(mappedBy = "idavatar")
	private Collection<Characterattributes> characterattributesCollection;
	@OneToMany(mappedBy = "idavatar")
	private Collection<Equipment> equipmentCollection;
	@JoinColumn(name = "Id_user", referencedColumnName = "Id_user")
	@ManyToOne
	private User iduser;
	@JoinColumn(name = "Id_characterAttribute", referencedColumnName = "Id_characterAttribute")
	@ManyToOne
	private Characterattributes idcharacterAttribute;
	@JoinColumn(name = "Id_equipment", referencedColumnName = "Id_equipment")
	@ManyToOne
	private Equipment idequipment;

	public Avatar() {
	}

	public Avatar(Integer idavatar) {
		this.idavatar = idavatar;
	}

	public Integer getIdavatar() {
		return idavatar;
	}

	public void setIdavatar(Integer idavatar) {
		this.idavatar = idavatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@XmlTransient
	public Collection<Characterattributes> getCharacterattributesCollection() {
		return characterattributesCollection;
	}

	public void setCharacterattributesCollection(
			Collection<Characterattributes> characterattributesCollection) {
		this.characterattributesCollection = characterattributesCollection;
	}

	@XmlTransient
	public Collection<Equipment> getEquipmentCollection() {
		return equipmentCollection;
	}

	public void setEquipmentCollection(Collection<Equipment> equipmentCollection) {
		this.equipmentCollection = equipmentCollection;
	}

	public User getIduser() {
		return iduser;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public Characterattributes getIdcharacterAttribute() {
		return idcharacterAttribute;
	}

	public void setIdcharacterAttribute(Characterattributes idcharacterAttribute) {
		this.idcharacterAttribute = idcharacterAttribute;
	}

	public Equipment getIdequipment() {
		return idequipment;
	}

	public void setIdequipment(Equipment idequipment) {
		this.idequipment = idequipment;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idavatar != null ? idavatar.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Avatar)) {
			return false;
		}
		Avatar other = (Avatar) object;
		if ((this.idavatar == null && other.idavatar != null)
				|| (this.idavatar != null && !this.idavatar
						.equals(other.idavatar))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Avatar[ idavatar=" + idavatar + " ]";
	}

}
