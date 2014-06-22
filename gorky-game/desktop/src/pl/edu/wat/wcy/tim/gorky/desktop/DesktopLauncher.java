package pl.edu.wat.wcy.tim.gorky.desktop;

import pl.edu.wat.wcy.tim.gorky.GorkyGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;;

public class DesktopLauncher {
	
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = false;
	
	public static void main (String[] arg) {
		
		if(rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			TexturePacker2.process(settings, "assets-raw/images", "../android/assets/images", "gorky.pack");
			TexturePacker2.process(settings, "assets-raw/images-ui", "../android/assets/images", "gorky-ui.pack");
			TexturePacker2.process(settings, "assets-raw/images-battle", "../android/assets/images", "gorky-battle.pack");
			TexturePacker2.process(settings, "assets-raw/images-knight", "../android/assets/images", "gorky-knight.pack");
			TexturePacker2.process(settings, "assets-raw/images-orc", "../android/assets/images", "gorky-orc.pack");
			TexturePacker2.process(settings, "assets-raw/images-heal-spell", "../android/assets/images", "gorky-heal-spell.pack");
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GORKY";
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new GorkyGame(), cfg);
	}
}