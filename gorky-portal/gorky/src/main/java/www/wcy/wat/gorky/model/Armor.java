/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package www.wcy.wat.gorky.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Krzysztof Jedynak
 */
@Entity
@Table(name = "armor")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Armor.findAll", query = "SELECT a FROM Armor a"),
		@NamedQuery(name = "Armor.findByIdarmor", query = "SELECT a FROM Armor a WHERE a.idarmor = :idarmor"),
		@NamedQuery(name = "Armor.findByDef", query = "SELECT a FROM Armor a WHERE a.def = :def"),
		@NamedQuery(name = "Armor.findByMagDef", query = "SELECT a FROM Armor a WHERE a.magDef = :magDef"),
		@NamedQuery(name = "Armor.findByName", query = "SELECT a FROM Armor a WHERE a.name = :name") })
public class Armor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_armor")
	private Integer idarmor;
	@Column(name = "def")
	private Integer def;
	@Column(name = "mag_def")
	private Integer magDef;
	@Size(max = 32)
	@Column(name = "name")
	private String name;
	@JoinColumn(name = "Id_equipment", referencedColumnName = "Id_equipment")
	@ManyToOne
	private Equipment idequipment;

	public Armor() {
	}

	public Armor(Integer idarmor) {
		this.idarmor = idarmor;
	}

	public Integer getIdarmor() {
		return idarmor;
	}

	public void setIdarmor(Integer idarmor) {
		this.idarmor = idarmor;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public Integer getMagDef() {
		return magDef;
	}

	public void setMagDef(Integer magDef) {
		this.magDef = magDef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		hash += (idarmor != null ? idarmor.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Armor)) {
			return false;
		}
		Armor other = (Armor) object;
		if ((this.idarmor == null && other.idarmor != null)
				|| (this.idarmor != null && !this.idarmor.equals(other.idarmor))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Armor[ idarmor=" + idarmor + " ]";
	}

}
