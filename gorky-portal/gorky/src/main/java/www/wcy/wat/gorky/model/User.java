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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByIduser", query = "SELECT u FROM User u WHERE u.iduser = :iduser"),
		@NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
		@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
		@NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname"),
		@NamedQuery(name = "User.findByOld", query = "SELECT u FROM User u WHERE u.old = :old"),
		@NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
		@NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByDescription", query = "SELECT u FROM User u WHERE u.description = :description") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_user")
	private Integer iduser;
	@Size(max = 32)
	@Column(name = "login")
	private String login;
	@Size(max = 32)
	@Column(name = "password")
	private String password;
	@Size(max = 32)
	@Column(name = "name")
	private String name;
	@Size(max = 32)
	@Column(name = "surname")
	private String surname;
	@Column(name = "old")
	private Integer old;
	@Size(max = 32)
	@Column(name = "country")
	private String country;
	@Size(max = 32)
	@Column(name = "city")
	private String city;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Invalid email")//if the field contains email address consider
	// using this annotation to enforce field validation
	@Size(max = 32)
	@Column(name = "email")
	private String email;
	@Size(max = 1000)
	@Column(name = "description")
	private String description;
	@OneToMany(mappedBy = "iduser")
	private Collection<Avatar> avatarCollection;

	public User() {
	}

	public User(Integer iduser) {
		this.iduser = iduser;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		hash += (iduser != null ? iduser.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.iduser == null && other.iduser != null)
				|| (this.iduser != null && !this.iduser.equals(other.iduser))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "www.wcy.wat.gorky.model.User[ iduser=" + iduser + " ]";
	}

}
