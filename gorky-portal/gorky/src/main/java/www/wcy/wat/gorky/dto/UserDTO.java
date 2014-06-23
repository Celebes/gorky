package www.wcy.wat.gorky.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import www.wcy.wat.gorky.model.User;

/**
 * 
 * @author Krzysztof Jedynak
 * 
 */
public class UserDTO {

	// @NotBlank(message = "Login jest wymagany!")
	@NotNull(message = "Login jest wymagany!")
	@Length(min = 6, max = 32, message = "Login powinien składać się z minimum 6 i max 32 znaków")
	private String login;
	@NotBlank(message = "Hasło jest wymagane!")
	// @NotNull(message = "Hasło jest wymagane!")
	@Length(max = 32, min = 6, message = "Hasło powinno się składać z minimum 6 i maksimum 32 znaków!")
	private String password;
	@Length(max = 32, message = "Imię powinno się składać z max32 znaków!")
	@NotBlank(message = "Imię jest wymagane!")
	// @NotNull(message = "Imię jest wymagane!")
	private String name;
	@Length(max = 32, message = "Nazwisko powinno się składać z max32 znaków!")
	@NotBlank(message = "Nazwisko jest wymagane!")
	// @NotNull(/*message = "Nazwisko jest wymagane!"*/)
	private String surname;
	private Integer old;
	private String country;
	private String city;
	@NotBlank(message = "Adres email jest wymagany!")
	// @NotNull(message = "Adres email jest wymagany!")
	@Email(message = "Adres email posiada niewłaściwy format!")
	private String email;
	private String description;

	public UserDTO() {

	}

	public UserDTO(User user) {
		this.city = user.getCity();
		this.country = user.getCountry();
		this.description = user.getDescription();
		this.email = user.getEmail();
		this.login = user.getLogin();
		this.name = user.getName();
		this.old = user.getOld();
		this.surname = user.getSurname();
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

	@Override
	public String toString() {
		return "RegisterController [login=" + login + ", password=" + password
				+ ", name=" + name + ", surname=" + surname + ", old=" + old
				+ ", country=" + country + ", city=" + city + ", email="
				+ email + ", description=" + description + "]";
	}

}
