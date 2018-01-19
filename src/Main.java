import java.awt.Color;
import java.util.Random;

/*
*
*Main.java
*Author: Jarrin Kasuya and Matthew Yamaguchi
*CREDIT TO SABRINA HAVERLY FOR THE PICTURES
*/
public class Main {

	public static void main(String[] args) {
		// THE GAME
		EZ.initialize(1200, 868);

		// create the background for the game
		EZImage background1 = EZ.addImage("background_final.png", 600, 350);
		EZImage background2 = EZ.addImage("background_final.png", 1800, 350);

		// create the ground for the game
		EZImage ground1 = EZ.addImage("ground.png", 600, 784);
		EZImage ground2 = EZ.addImage("ground.png", 1800, 784);

		// create an image of a fox
		EZImage fox = EZ.addImage("fox_edit1.png", 100, 575);

		// create the player for the game
		Player bird = new Player(350, 640, "Run_1_edit2.png", "Jump_edit.png", "Fall2_edit.png", "Slide_edit.png",
				"Dead_edit.png"); // 200 612
		bird.hidePlayer();

		// create obstacles
		Bush bush = new Bush();
		Vine vine = new Vine();

		// create variable to check the birds current state
		boolean alive = true;
		int state;

		// create a variable to check if successfully passed object
		boolean passed = false;
		// create a variable to keep score
		int score = 0;
		EZText textbox = EZ.addText(600, 50, "Score: " + score, Color.BLACK, 64);
		textbox.setFont("orange juice 2.0.ttf");

		EZSound sound = EZ.addSound("Mus_temvillage.wav");
		EZSound quack = EZ.addSound("quack.wav");

		// Matt's note: I added some stuff to make random objects move
		Random rg = new Random();
		int obstacleNumber = 0;
		sound.loop();
		while (alive) {

			background2.moveForward(-2);
			background1.moveForward(-2);
			ground2.moveForward(-10);
			ground1.moveForward(-10);
			if (background1.getXCenter() < -600) {
				background1.moveForward(2400);
			}
			if (background2.getXCenter() < -600) {
				background2.moveForward(2400);
			}
			if (ground1.getXCenter() < -600) {
				ground1.moveForward(2400);
			}
			if (ground2.getXCenter() < -600) {
				ground2.moveForward(2400);
			}
			bird.processPlayerStates();

			// if the tree is chosen, and isnt off screen yet
			if (obstacleNumber == 0 && bush.getX() > -150) {
				// keep moving it closer off screen
				// objectX -= obstacleSpeed;
				bush.move();

				// if the vines are chosen and isn't off screen yet
			} else if (obstacleNumber == 1 && vine.getX() > -150) {
				// keep moving it closer off screen
				vine.move();

				// if the object moves off screen, reset them to their
				// original
				// position.
			} else if (bush.getX() <= -150 || vine.getX() <= -150) {
				obstacleNumber = rg.nextInt(2);
				bush.reset();
				vine.reset();
				passed = false;
			}

			state = bird.returnStatus();
			// first check if the bird hit something while walking
			if (state == 1) {
				// check if it hit a bush
				if (bush.didHit(bird.getWalkX() - 77, bird.getWalkY() - 88)
						|| bush.didHit(bird.getWalkX() + 77, bird.getWalkY() - 88)
						|| bush.didHit(bird.getWalkX() - 77, bird.getWalkY() + 88)
						|| bush.didHit(bird.getWalkX() + 77, bird.getWalkY() + 88)) {
					bird.dead();
					alive = false;

				}
				// check if it hit a vine
				if (vine.didHit(bird.getWalkX() - 77, bird.getWalkY() - 88)
						|| vine.didHit(bird.getWalkX() + 77, bird.getWalkY() - 88)
						|| vine.didHit(bird.getWalkX() - 77, bird.getWalkY() + 88)
						|| vine.didHit(bird.getWalkX() + 77, bird.getWalkY() + 88)) {
					bird.dead();
					alive = false;

				}
			}

			// check if the bird hit anything while jumping
			if (state == 2) {
				// check if it hit a bush
				if (bush.didHit(bird.getJumpX() - 35, bird.getJumpY() - 69)
						|| bush.didHit(bird.getJumpX() + 35, bird.getJumpY() - 69)
						|| bush.didHit(bird.getJumpX() - 35, bird.getJumpY() + 69)
						|| bush.didHit(bird.getJumpX() + 35, bird.getJumpY() + 69)) {
					bird.dead();
					alive = false;

				}
				// check if it hit a bush
				if (vine.didHit(bird.getJumpX() - 70, bird.getJumpY() - 99)
						|| vine.didHit(bird.getJumpX() + 70, bird.getJumpY() - 99)
						|| vine.didHit(bird.getJumpX() - 70, bird.getJumpY() + 99)
						|| vine.didHit(bird.getJumpX() + 70, bird.getJumpY() + 99)) {
					bird.dead();
					alive = false;

				}
			}
			if (state == 3) {
				// check if it hit a bush
				if (bush.didHit(bird.getFallX() - 80, bird.getFallY() - 27)
						|| bush.didHit(bird.getFallX() + 80, bird.getFallY() - 27)
						|| bush.didHit(bird.getFallX() - 80, bird.getFallY() + 27)
						|| bush.didHit(bird.getFallX() + 80, bird.getFallY() + 27)) {
					bird.dead();
					alive = false;

				}
				// check if hit a vine
				if (vine.didHit(bird.getFallX() - 100, bird.getFallY() - 52)
						|| vine.didHit(bird.getFallX() + 100, bird.getFallY() - 52)
						|| vine.didHit(bird.getFallX() - 100, bird.getFallY() + 52)
						|| vine.didHit(bird.getFallX() + 100, bird.getFallY() + 52)) {
					bird.dead();
					alive = false;

				}
			}

			if (state == 4) {
				// check if it hit a bush
				if (bush.didHit(bird.getSlideX() - 100, bird.getSlideY() - 52)
						|| bush.didHit(bird.getSlideX() + 100, bird.getSlideY() - 52)
						|| bush.didHit(bird.getSlideX() - 100, bird.getSlideY() + 52)
						|| bush.didHit(bird.getSlideX() + 100, bird.getSlideY() + 52)) {
					bird.dead();
					alive = false;

				}

			}

			// if you successfully pass an object, your score goes up
			if (passed == false) {
				if (fox.getXCenter() > bush.getX()) {
					score++;
					passed = true;
					textbox.setMsg("Score: " + score);
				}
				if (fox.getXCenter() > vine.getX()) {
					score++;
					passed = true;
					textbox.setMsg("Score: " + score);
				}
			}
			EZ.refreshScreen();
		}
		sound.stop();
		quack.play();

		textbox.setMsg("Game Over! Your score was: " + score + ".");
	}

}
