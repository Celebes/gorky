package pl.edu.wat.wcy.tim.gorky.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import pl.edu.wat.wcy.tim.gorky.dto.IntegrationDTO;
import pl.edu.wat.wcy.tim.gorky.dto.LoggedAndroidDTO;
import pl.edu.wat.wcy.tim.gorky.dto.ReturnCodeDTO;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;

public class UltraIntegrator {
	public static final UltraIntegrator instance = new UltraIntegrator();
	
    private HttpClient client = new DefaultHttpClient();
    private StringBuilder builder;
    private String json = "";
    private StringEntity entity;
    private HttpResponse response;
	
	private UltraIntegrator() {
		// singleton
	}
	
	public boolean checkLoginData(String login, String password) {
		boolean result = false;
		
		// przygotuj http
		/*try {
			httpPost = new HttpPost(Constants.LOGIN_URL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("START, URL = " + httpPost.getURI());
		Gdx.app.log("HEHE", "START, URL = " + httpPost.getURI());*/
		
		// przygotuj DTO
		LoggedAndroidDTO ladto = new LoggedAndroidDTO();
		
		ladto.setLogin(login);
		ladto.setPassword(password);
		
		System.out.println("LoggedAndroidDTO = " + ladto.toString());
		Gdx.app.log("HEHE", "LoggedAndroidDTO = " + ladto);
		
		// utworz JSON
		Gson gson = new Gson();
		String json = gson.toJson(ladto);
		
		System.out.println("LoggedAndroidDTO JSON = " + json);
		Gdx.app.log("HEHE", "LoggedAndroidDTO JSON = " + json);
		
		// wyslij JSON na usluge i dostan idUser

		String idUserJSON = wykonajZadanieHttp(Constants.LOGIN_URL, json);
		
		System.out.println("idUserJSON = " + idUserJSON);
		Gdx.app.log("HEHE", "idUserJSON = " + idUserJSON);
		
		if(idUserJSON == null || idUserJSON.equals("")) {
			System.out.println("idUserJSON JEST PUSTY LUB NULL, ZWRACAM FALSE");
			Gdx.app.log("HEHE", "idUserJSON JEST PUSTY LUB NULL, ZWRACAM FALSE");
			return false;
		}
		
		ReturnCodeDTO rcdto = gson.fromJson(idUserJSON, ReturnCodeDTO.class);
		
		int idUser = rcdto.getIsCanLogged();
		
		System.out.println("Otrzymano idUser = " + idUser);
		Gdx.app.log("HEHE", "Otrzymano idUser = " + idUser);
		
		if(idUser > 0) {
			LoginPreferences.instance.loggedIn = true;
			LoginPreferences.instance.login = login;
			LoginPreferences.instance.password = password;
			LoginPreferences.instance.idUser = idUser;
			
			LoginPreferences.instance.save();
			
			System.out.println("Pomyslnie zalogowano uzytkownika: " + LoginPreferences.instance.login + " o ID: " + LoginPreferences.instance.idUser);
			Gdx.app.log("HEHE", "Pomyslnie zalogowano uzytkownika: " + LoginPreferences.instance.login + " o ID: " + LoginPreferences.instance.idUser);
			result = true;
		}
		
		return result;
	}
	
	public void saveDataOnServer() {
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

		// wyslij na serwer
		
		String errorCode = wykonajZadanieHttp(Constants.SAVE_GAME_URL, json);
		
		ReturnCodeDTO rcdto = gson.fromJson(errorCode, ReturnCodeDTO.class);
		
		Gdx.app.log("HEHE", "Otrzymano z Save kod: " + rcdto.getIsCanLogged());
		
		Gdx.app.log("HEHE", "Wyslano JSON w metodzie SaveGame(): " + json);
	}
	
	public void loadDataFromServer() {
		// przygotuj http
		
		// pobierz JSON z serwera
		String json = wykonajZadanieHttp(Constants.LOAD_GAME_URL + LoginPreferences.instance.idUser, "");
		
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
		
		// nadpisz tym co pobrales
		SaveStatePreferences.instance.save();
		
		Gdx.app.log("HEHE", "pomyslnie wczytano dane z serwera");
	}
	
	private String wykonajZadanieHttp(String url, String json)
    {
        

        
        
        try
        {
        	builder = new StringBuilder();
            HttpPost request = new HttpPost( url );
            
        	request.setHeader( "Accept", "application/json" );
            request.setHeader( "Content-type", "application/json" );
            
            entity = new StringEntity( json );
            request.setEntity( entity );

            response = client.execute( request );
            response.setHeader( "Content-type", "application/json" );
            response.setHeader( "Accept", "application/json" );
            HttpEntity entityResult = response.getEntity();
            InputStream content = entityResult.getContent();
            BufferedReader reader = new BufferedReader( new InputStreamReader( content ) );
            String line;
            builder = new StringBuilder();
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( line );
            }
            entityResult.consumeContent();
            content.close();
            Gdx.app.log( "HEHE", builder.toString() );
            
        }
        catch ( ClientProtocolException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        } catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return builder.toString();
    }
}
