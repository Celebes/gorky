package pl.edu.wat.wcy.tim.gorky.game;


public class Level {
	public static final String TAG = Level.class.getName();
	
	public enum BLOCK_TYPE {
		EMPTY(255, 255, 255),					// bialy
		PLAYER_SPAWNPOINT(255, 0, 0),			// czerwony
		WALL(0, 0, 0);							// czarny
		
		private int color;
		
		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor(int color) {
			return (this.color == color);
		}
		
		public int getColor() {
			return color;
		}
	}
}
