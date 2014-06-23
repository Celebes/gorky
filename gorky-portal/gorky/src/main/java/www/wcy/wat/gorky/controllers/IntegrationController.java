package www.wcy.wat.gorky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import www.wcy.wat.gorky.dto.IntegrationDTO;
import www.wcy.wat.gorky.dto.LoggedAndroidDTO;
import www.wcy.wat.gorky.dto.ReturnCodeDTO;
import www.wcy.wat.gorky.managers.AuthManager;
import www.wcy.wat.gorky.managers.IntegrationManager;

/*
 * Controller integration Android app with database 
 * by JSON Objects
 */

@Controller
@RequestMapping(value="/integration")
public class IntegrationController {
	
	@Autowired
	private IntegrationManager integrationManager;
	
	@Autowired
	private AuthManager authManager;
	
	/**
	 * Get json model data for user id
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/model/{idUser}", produces={"application/json; charset=UTF-8"})
	public @ResponseBody IntegrationDTO findModelByUserId(@PathVariable int idUser) {

		return integrationManager.createJSonModelObjectForUsrerId(idUser);
	}
	
	/**
	 * Authentication user login and password
	 * @param loggedAndroidDTO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login", produces={"application/json; charset=UTF-8"})
	public @ResponseBody ReturnCodeDTO loginAuthenticationAndroid(@RequestBody LoggedAndroidDTO loggedAndroidDTO) {
	    return authManager.canLogged(loggedAndroidDTO);
	}

}
