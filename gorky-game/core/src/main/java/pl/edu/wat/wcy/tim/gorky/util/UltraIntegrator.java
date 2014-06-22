package pl.edu.wat.wcy.tim.gorky.util;

public class UltraIntegrator {
	public static final UltraIntegrator instance = new UltraIntegrator();
	
	private UltraIntegrator() {
		// singleton
	}
	
	public boolean checkLoginData(String login, String password) {
		boolean result = false;
		
		if(login.equals("asd") && password.equals("asd")) {
			result = true;
		}
		
		return result;
	}
}
