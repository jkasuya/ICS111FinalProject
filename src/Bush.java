
/*
 * Tree.java
 * Author: Jarrin Kasuya
 * 
 */


public class Bush extends Obstacle{

	Bush() {
		super();
		obstacle = EZ.addImage("bush_edit.png", x, 640); //604
	}
	
	//resets object to its original position
	protected void reset() {
		x = 1350;
		obstacle.translateTo(x, 640);
	}
	
	//moves object
	protected void move() {
		x -= translate_speed;
		obstacle.translateTo(x, 640);
	}

}
