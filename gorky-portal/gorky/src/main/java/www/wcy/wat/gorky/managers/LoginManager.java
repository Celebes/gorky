package www.wcy.wat.gorky.managers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.wcy.wat.gorky.dto.LoggedAndroidDTO;
import www.wcy.wat.gorky.dto.LoggedDTO;
import www.wcy.wat.gorky.model.User;
import www.wcy.wat.gorky.repositories.UserRepository;


@Component
public class LoginManager {
	
	@Autowired
	UserRepository userRepository;
	
	private final static Logger logger = Logger.getLogger( LoginManager.class );
	
	public boolean isLogged(LoggedDTO loggedDTO)
	{

		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getLogin().trim().equalsIgnoreCase(loggedDTO.getLogin().trim())) {
				logger.debug("Znaleziono uzytkownika o podanym loginie!");
				if (user.getPassword().equalsIgnoreCase(loggedDTO.getPassword().trim())) {
					logger.debug("Podano nieprawidłowe hasło!");
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public boolean isLogged(LoggedAndroidDTO loggedAndroidDTO)
	{

		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getLogin().trim().equalsIgnoreCase(loggedAndroidDTO.getLogin().trim())) {
				logger.debug("Znaleziono uzytkownika o podanym loginie!");
				if (user.getPassword().equalsIgnoreCase(loggedAndroidDTO.getPassword().trim())) {
					logger.debug("Podano nieprawidłowe hasło!");
					return true;
				}
			}
		}
		
		return false;
	}

}
