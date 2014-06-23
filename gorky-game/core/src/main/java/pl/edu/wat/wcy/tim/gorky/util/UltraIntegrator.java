package pl.edu.wat.wcy.tim.gorky.util;

import pl.edu.wat.wcy.tim.gorky.dto.IntegrationDTO;
import pl.edu.wat.wcy.tim.gorky.dto.LoggedAndroidDTO;

import com.google.gson.Gson;

public class UltraIntegrator {
	public static final UltraIntegrator instance = new UltraIntegrator();
	
	private UltraIntegrator() {
		// singleton
	}
	
	public boolean checkLoginData(String login, String password) {
		boolean result = false;
		
		// przygotuj DTO
		LoggedAndroidDTO ladto = new LoggedAndroidDTO();
		
		ladto.setLogin(login);
		ladto.setPassword(password);
		
		// utworz JSON
		Gson gson = new Gson();
		String json = gson.toJson(ladto);
		
		// wyslij JSON na usluge i dostan idUser
		int idUser = 1;
		
		System.out.println("Otrzymano idUser = ");
		
		if(idUser != -1) {
			LoginPreferences.instance.loggedIn = true;
			LoginPreferences.instance.login = login;
			LoginPreferences.instance.password = password;
			LoginPreferences.instance.idUser = idUser;
			
			LoginPreferences.instance.save();
			
			System.out.println("Pomyslnie zalogowano uzytkownika: " + LoginPreferences.instance.login + " o ID: " + LoginPreferences.instance.idUser);
			
			
			result = true;
		}
		
		return result;
	}
	
	public String saveDataOnServer() {
		// zapisz na zapas gre
		SaveStatePreferences.instance.save();
		
		// przygotuj DTO
		IntegrationDTO idto = new IntegrationDTO();
		
		idto.setLogin(LoginPreferences.instance.login);
		idto.setPassword(LoginPreferences.instance.password);
		idto.setIduser(LoginPreferences.instance.idUser);
		
		idto.setNameAvatar(SaveStatePreferences.instance.avatarName);
		
		idto.setPositionX(SaveStatePreferences.instance.playerPositionX);
		idto.setPositionY(SaveStatePreferences.instance.playerPositionY);
		idto.setCurrentMapLevel(SaveStatePreferences.instance.currentLevel);
		
		idto.setAtk(SaveStatePreferences.instance.atk);
		idto.setDef(SaveStatePreferences.instance.def);
		idto.setHp(SaveStatePreferences.instance.HP);
		idto.setMp(SaveStatePreferences.instance.MP);
		idto.setMaxHP(SaveStatePreferences.instance.maxHP);
		idto.setMaxMP(SaveStatePreferences.instance.maxMP);
		
		idto.setGold(SaveStatePreferences.instance.gold);
		idto.setExp(SaveStatePreferences.instance.exp);
		idto.setLevel(SaveStatePreferences.instance.level);
		
		// utworz JSON
		Gson gson = new Gson();
		String json = gson.toJson(idto);
		
		// tymczasowo
		return json;

		// wyslij na serwer
	}
	
	public void loadDataFromServer() {
		// pobierz JSON z serwera
		String json = saveDataOnServer();
		
		// utworz z niego obiekt
		Gson gson = new Gson();
		IntegrationDTO idto = gson.fromJson(json, IntegrationDTO.class);
		
		// zapisz wartosci w preferences
		LoginPreferences.instance.login = idto.getLogin();
		LoginPreferences.instance.password = idto.getPassword();
		LoginPreferences.instance.idUser = idto.getIduser();
		
		SaveStatePreferences.instance.avatarName = idto.getNameAvatar();
		
		SaveStatePreferences.instance.playerPositionX = idto.getPositionX();
		SaveStatePreferences.instance.playerPositionY = idto.getPositionY();
		SaveStatePreferences.instance.currentLevel = idto.getCurrentMapLevel();
		
		SaveStatePreferences.instance.atk = idto.getAtk();
		SaveStatePreferences.instance.def = idto.getDef();
		SaveStatePreferences.instance.HP = idto.getHp();
		SaveStatePreferences.instance.MP = idto.getMp();
		SaveStatePreferences.instance.maxHP = idto.getMaxHP();
		SaveStatePreferences.instance.maxMP = idto.getMaxMP();
		
		SaveStatePreferences.instance.gold = idto.getGold();
		SaveStatePreferences.instance.exp = idto.getExp();
		SaveStatePreferences.instance.level = idto.getLevel();
		
		SaveStatePreferences.instance.save();
	}
}
