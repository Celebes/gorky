package www.wcy.wat.gorky.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import www.wcy.wat.gorky.dto.LoggedDTO;
import www.wcy.wat.gorky.managers.LoginManager;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.UserRepository;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginManager loginManager;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		model.addAttribute("loggedForm", new LoggedDTO());
		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showLoginPage(
			@Valid @ModelAttribute("loggedForm") LoggedDTO loggedDTO,
			BindingResult loggedBindingResult, RedirectAttributes redirectAttributes, Model model) {

		boolean canLogging = loginManager.isLogged(loggedDTO);
		if (canLogging) {
			User user = userRepository.findUserByLogin(loggedDTO.getLogin());
			System.out.println("Udało sie poprawnie zalogować!");
			return "redirect:/userAcount/view/" + user.getIduser();
		} else {
			System.out.println("Próba logowania nie powiodła się!");
		}
		model.addAttribute("loggedForm", loggedDTO);

		return "/login";
	}

}
