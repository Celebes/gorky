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
@Table(name = "potion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Potion.findAll", query = "SELECT p FROM Potion p"),
		@NamedQuery(name = "Potion.findByIdpotion", query = "SELECT p FROM Potion p WHERE p.idpotion = :idpotion"),
		@NamedQuery(name = "Potion.findByStat", query = "SELECT p FROM Potion p WHERE p.stat = :stat"),
		@NamedQuery(name = "Potion.findByPower", query = "SELECT p FROM Potion p WHERE p.power = :power"),
		@NamedQuery(name = "Potion.findByName", query = "SELECT p FROM Potion p WHERE p.name = :name") })
public class Potion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Id_potion")
	private Integer idpotion;
	@Size(max = 32)
	@Column(name = "stat")
	private String stat;
	@Column(name = "power")
	private Integer power;
	@Size(max = 32)
	@Column(name = "name")
	private String name;
	@JoinColumn(name = "Id_equipment", referencedColumnName = "Id_equipment")
	@ManyToOne
	private Equipment idequipment;

	public Potion() {
	}

	public Potion(Integer idpotion) {
		this.idpotion = idpotion;
	}

	public Integer getIdpotion() {
		return idpotion;
	}

	public void setIdpotion(Integer idpotion) {
		this.idpotion = idpotion;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
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
		hash += (idpotion != null ? idpotion.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Potion)) {
			return false;
		}
		Potion other = (Potion) object;
		if ((this.idpotion == null && other.idpotion != null)
				|| (this.idpotion != null && !this.idpotion
						.equals(other.idpotion))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.Potion[ idpotion=" + idpotion + " ]";
	}

}
