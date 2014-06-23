package www.wcy.wat.gorky.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import www.wcy.wat.gorky.dto.UserDTO;
import www.wcy.wat.gorky.managers.ValidateManager;
import www.wcy.wat.gorky.repositories.UserRepository;

/**
 * 
 * @author Krzysztof Jedynak
 * 
 */
@Controller
@RequestMapping(value = "/")
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ValidateManager validateManager;

	@RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
	public String getRegisterPage(Model model) {

		model.addAttribute("registerForm", new UserDTO());
		return "/register";

	}

	@RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
	public String registerUser(
			@Valid @ModelAttribute("registerForm") UserDTO userDto,
			BindingResult registerBindingResult, Model model) {
		validateManager.validateRegistrationForm(registerBindingResult, userDto);
		if (!registerBindingResult.hasErrors()) {
			try {
				userRepository.saveUser(userDto);
				return "/register-success";
			} catch (DataAccessException e) {
				e.getLocalizedMessage();
			}
		}
		return "/register";

	}

}
