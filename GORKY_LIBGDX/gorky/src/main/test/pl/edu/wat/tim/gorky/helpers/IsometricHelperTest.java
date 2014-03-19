package pl.edu.wat.tim.gorky.helpers;

import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Test;

public class IsometricHelperTest {

	@Test
	public void testIsometricToCartesian() {
		Point isometricPoint = new Point(100, 100);
		Point cartesianPoint = IsometricHelper.isometricToCartesian(isometricPoint);
		
		assert cartesianPoint.equals(new Point(0, 100));
	}

	@Test
	public void testCartesianToIsometric() {
		Point cartesianPoint = new Point(0, 100);
		Point isometricPoint = IsometricHelper.CartesianToIsometric(cartesianPoint);
		
		assert isometricPoint.equals(new Point(100, 100));
	}

}
