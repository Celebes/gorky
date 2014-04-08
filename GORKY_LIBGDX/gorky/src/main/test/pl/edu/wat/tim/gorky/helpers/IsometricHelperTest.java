package pl.edu.wat.tim.gorky.helpers;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class IsometricHelperTest {

	@Test
	public void testIsometricToCartesian() {
		Vector2 isometricVector2 = new Vector2(100, 100);
		Vector2 cartesianVector2 = IsometricHelper.isometricToCartesian(isometricVector2);
		
		assert cartesianVector2.equals(new Vector2(0, 100));
	}

	@Test
	public void testCartesianToIsometric() {
		Vector2 cartesianVector2 = new Vector2(0, 100);
		Vector2 isometricVector2 = IsometricHelper.cartesianToIsometric(cartesianVector2);
		
		assert isometricVector2.equals(new Vector2(100, 100));
	}

}
