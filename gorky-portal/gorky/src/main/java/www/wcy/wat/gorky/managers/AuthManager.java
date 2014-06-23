package www.wcy.wat.gorky.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.wcy.wat.edu.gorky.utils.CommonVariables;
import www.wcy.wat.gorky.dto.LoggedAndroidDTO;
import www.wcy.wat.gorky.dto.ReturnCodeDTO;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.UserRepository;

@Component
public class AuthManager {
	
	@Autowired
	LoginManager loginManager;
	
	@Autowired
	UserRepository userRepository;
	
	public ReturnCodeDTO canLogged(LoggedAndroidDTO loggedAndroidDTO){
		ReturnCodeDTO returnCodeDTO = new ReturnCodeDTO();
		boolean canLogging = loginManager.isLogged(loggedAndroidDTO);
		if (canLogging) {
			User user = userRepository.findUserByLogin(loggedAndroidDTO.getLogin());
			System.out.println("Udało sie poprawnie zalogować!");
			returnCodeDTO.setIsCanLogged(user.getIduser());
		} else {
			returnCodeDTO.setIsCanLogged(CommonVariables.ERROR_CODE_AUTH);
		}
		
		return returnCodeDTO;
	}

}
