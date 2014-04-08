package pl.edu.wat.tim.gorky;

import pl.edu.wat.tim.gorky.GorkyGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "gorky";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		//test
		new LwjglApplication(new GorkyGame(), cfg);
	}
}
