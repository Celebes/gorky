package pl.edu.wat.wcy.tim.gorky.helpers;

import com.badlogic.gdx.math.Vector2;

public class IsometricHelper {

	/**
	 * Konwertuje wspolrzedne izometryczne na 2D (kartezjanskie)
	 */
	public static Vector2 isometricToCartesian(Vector2 p) {
		Vector2 pointCartesian = new Vector2(0, 0);
		
		pointCartesian.x = (2 * p.y + p.x) / 2;
		pointCartesian.y = (2 * p.y - p.x) / 2;
		
		return pointCartesian;
	}
	
	/**
	 * Konwertuje wspolrzedne 2D (kartezjanskie) na izometryczne
	 */
	public static Vector2 cartesianToIsometric(Vector2 p) {
		Vector2 pointIsometric = new Vector2(0, 0);
		
		pointIsometric.x = p.x - p.y;
		pointIsometric.y = (p.x + p.y)/2;
		
		return pointIsometric;
	}
	
	/**
	 * Konwertuje wspolrzedne 2D (kartezjanskie) na konkretne pole (wiersz/kolumna)
	 */
	public static Vector2 getTileCoordinates(Vector2 p, int tileHeight) {
		Vector2 tileCoordinates = new Vector2(0, 0);
		
		tileCoordinates.x = (float) Math.floor(p.x / tileHeight);
		tileCoordinates.y = (float) Math.floor(p.y / tileHeight);
		
		return tileCoordinates;		
	}
	
	/**
	 * Konwertuje konkretne pole (wiersz/kolumna) na wspolrzedne 2d (kartezjanskie)
	 */
	public static Vector2 getCartesianCoordinatesOfTile(Vector2 p, int tileHeight) {
		Vector2 tileCoordinates = new Vector2(0, 0);
		
		tileCoordinates.x = p.x * tileHeight;
		tileCoordinates.y = p.y * tileHeight;
		
		return tileCoordinates;		
	}
	

}