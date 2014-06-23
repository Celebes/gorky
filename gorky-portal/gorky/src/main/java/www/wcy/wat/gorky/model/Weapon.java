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
@Table(name = "weapon")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Weapon.findAll", query = "SELECT w FROM Weapon w"),
		@NamedQuery(name = "Weapon.findByIdweapon", query = "SELECT w FROM Weapon w WHERE w.idweapon = :idweapon"),
		@NamedQuery(name = "Weapon.findByName", query = "SELECT w FROM Weapon w WHERE w.name = :name"),
		@NamedQuery(name = "Weapon.findByAtk", query = "SELECT w FROM Weapon w WHERE w.atk = :atk"),
		@NamedQuery(name = "Weapon.findByMagAtk", query = "SELECT w FROM Weapon w WHERE w.magAtk = :magAtk") })
public class Weapon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_weapon")
	private Integer idweapon;
	@Size(max = 32)
	@Column(name = "name")
	private String name;
	@Column(name = "atk")
	private Integer atk;
	@Column(name = "mag_atk")
	private Integer magAtk;
	@JoinColumn(name = "Id_equipment", referencedColumnName = "Id_equipment")
	@ManyToOne
	private Equipment idequipment;

	public Weapon() {
	}

	public Weapon(Integer idweapon) {
		this.idweapon = idweapon;
	}

	public Integer getIdweapon() {
		return idweapon;
	}

	public void setIdweapon(Integer idweapon) {
		this.idweapon = idweapon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	public Integer getMagAtk() {
		return magAtk;
	}

	public void setMagAtk(Integer magAtk) {
		this.magAtk = magAtk;
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
		hash += (idweapon != null ? idweapon.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Weapon)) {
			return false;
		}
		Weapon other = (Weapon) object;
		if ((this.idweapon == null && other.idweapon != null)
				|| (this.idweapon != null && !this.idweapon
						.equals(other.idweapon))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Weapon[ idweapon=" + idweapon + " ]";
	}

}
