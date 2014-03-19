package pl.edu.wat.tim.gorky.helpers;

import java.awt.Point;

public class IsometricHelper {
	
	
	/**
	 * Konwertuje wspolrzedne izometryczne na 2D (kartezjanskie)
	 */
	public static Point isometricToCartesian(Point p) {
		Point pointCartesian = new Point(0, 0);
		
		pointCartesian.x = (2 * p.y + p.x) / 2;
		pointCartesian.y = (2 * p.y - p.x) / 2;
		
		return pointCartesian;
	}
	
	/**
	 * Konwertuje wspolrzedne 2D (kartezjanskie) na izometryczne
	 */
	public static Point CartesianToIsometric(Point p) {
		Point pointIsometric = new Point(0, 0);
		
		pointIsometric.x = p.x - p.y;
		pointIsometric.y = (p.x + p.y)/2;
		
		return pointIsometric;
	}

}