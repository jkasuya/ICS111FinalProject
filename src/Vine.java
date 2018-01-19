
/*
 * Vine.java 
 * Author: Jarrin Kasuya
 * 
 * 
 * 
 */

public class Vine extends Obstacle {

	Vine() {
		super();
		obstacle = EZ.addImage("vine3_edit.png", x, 290);
	}

	// resets objects to original position
	protected void reset() {
		x = 1350;
		obstacle.translateTo(x, 290);
	}

	// moves objects
	protected void move() {
		x -= translate_speed;
		obstacle.translateTo(x, 290);
	}
}
