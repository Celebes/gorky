package www.wcy.wat.gorky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import www.wcy.wat.gorky.dto.UserDTO;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.UserRepository;

@Controller
@RequestMapping (value = "/")
public class UserAccoutController {
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/userAcount/view/{id_user}" , method = RequestMethod.GET)
	public String getUserDetails(@PathVariable("id_user") String userID, Model model){
		
		Integer idUser = Integer.valueOf(userID);
		User user = userRepository.findUserById(idUser);
		UserDTO userDTO = new UserDTO(user);
		model.addAttribute("userViewForm", userDTO);
		model.addAttribute("isVisibleAccount", false);
		return "/viewAccount";
	}
	
	@RequestMapping(value = "/userAcount/edit/{id_user}" , method = RequestMethod.GET)
	public String editUserDetails(@PathVariable("id_user") String userID, Model model){
		
		Integer idUser = Integer.valueOf(userID);
		User user = userRepository.findUserById(idUser);
		UserDTO userDTO = new UserDTO(user);
		model.addAttribute("userEditForm", userDTO);
		model.addAttribute("isVisibleAccount", true);
		return "/viewAccount";
	}
}
