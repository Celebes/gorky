package www.wcy.wat.gorky.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Krzysztof Jedynak
 * 
 */
public class LoggedDTO {

	@NotBlank(message="Login jest wymagany!")
	private String login;
	@NotBlank(message= "Hasło jest wymagane!")
	private String password;

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

	@Override
	public String toString() {
		return "LoggedDTO [login=" + login + ", password=" + password + "]";
	}
}
