package www.wcy.wat.gorky.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import www.wcy.wat.gorky.dto.UserDTO;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.UserRepository;

@Component
public class ValidateManager {

	@Autowired
	private UserRepository userRepository;

	public BindingResult validateRegistrationForm(BindingResult result,
			UserDTO userDTO) {
		result = this.validateUniqueLogin(result, userDTO);
		result = this.validateUniqueEmail(result, userDTO);
		return result;
	}

	public BindingResult validateUniqueEmail(BindingResult result,
			UserDTO userDTO) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getEmail().trim().equalsIgnoreCase(userDTO.getEmail())) {
				result.addError(new FieldError("email", "Unique", "Podany adres emial istnieje!"));
			}
		}

		return result;
	}

	public BindingResult validateUniqueLogin(BindingResult result,
			UserDTO userDTO) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getLogin().trim().equalsIgnoreCase(userDTO.getLogin())) {
				result.addError(new FieldError("login", "Unique", "Podany login istnieje!"));
			}
		}

		return result;
	}

}
