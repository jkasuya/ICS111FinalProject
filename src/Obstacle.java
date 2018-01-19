/*
 * 
 * Obstacle.java
 * Author: Jarrin Kasuya
 * 
 * 
 */
public class Obstacle {

	protected EZImage obstacle;
	protected static int translate_speed;
	protected int x;
	protected int y;
	protected int xcoord;
	protected int ycoord;

	public Obstacle() {
		translate_speed = 20; // speed of translation
		x = 1600; // opening x position of obstacle
		y = 800; // opening y position of obstacle
	}

	//moves function
	protected void move() {
		x -= translate_speed;
		obstacle.translateTo(x, y);

	}

	protected void reset() {
		x = 1350;
		obstacle.translateTo(x, y);
	}

	protected int getX() {
		xcoord = obstacle.getXCenter();
		return xcoord;
	}

	protected int getY() {
		ycoord = obstacle.getYCenter();
		return ycoord;
	}

	protected boolean didHit(int _x, int _y) {
		int objectX = _x;
		int objectY = _y;
		return obstacle.isPointInElement(objectX, objectY);
	}

}
