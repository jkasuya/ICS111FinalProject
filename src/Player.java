/*
 * 
 * Player.java
 * Author: Matthew Yamaguchi
 * 
 * 
 */
public class Player {
	// member variables
	EZImage playerWalk;
	EZImage playerJump;
	EZImage playerFall;
	EZImage playerSlide;
	EZImage playerDeath;

	// variables for finite states
	static final int WALK = 1;
	static final int JUMP = 2;
	static final int FALL = 3;
	static final int SLIDE = 4;
	static final int DEAD = 5;

	static final int JUMPHEIGHT = 25;

	// set the original state to walk
	int playerState = WALK;

	// keeps track of how high the bird is in its jump
	int jumpCounter = 0;

	// coordinates for images
	int posx = 0;
	int posy = 0;

	// keep track of bird states, walk = 1, jump = 2, fall = 3, slide = 4,
	int status;

	// Constructor for the player
	Player(int x, int y, String p_Walk, String p_Jump, String p_Fall, String p_Slide, String p_Death) {
		posx = x;
		posy = y;

		// variables for each player state
		playerWalk = EZ.addImage(p_Walk, posx, posy);
		playerJump = EZ.addImage(p_Jump, posx, posy);
		playerFall = EZ.addImage(p_Fall, posx, posy);
		playerSlide = EZ.addImage(p_Slide, posx, posy);
		playerDeath = EZ.addImage(p_Death, posx, posy);

	}

	void hidePlayer() {
		// hide animations
		playerWalk.hide();
		playerJump.hide();
		playerFall.hide();
		playerSlide.hide();
		playerDeath.hide();
	}

	// functions that get the coordinates for each image
	int getWalkX() {
		return playerWalk.getXCenter();
	}

	int getWalkY() {
		return playerWalk.getYCenter();
	}

	int getJumpX() {
		return playerJump.getXCenter();
	}

	int getJumpY() {
		return playerJump.getYCenter();
	}

	int getFallX() {
		return playerFall.getXCenter();
	}

	int getFallY() {
		return playerFall.getYCenter();
	}

	int getSlideX() {
		return playerSlide.getXCenter();
	}

	int getSlideY() {
		return playerSlide.getYCenter();
	}

	int returnStatus() {
		return status;
	}

	// show death animation
	protected void dead() {
		hidePlayer();
		playerDeath.show();
	}

	// run through the states for the player
	void processPlayerStates() {

		switch (playerState) {

		// if the player is walking...
		case WALK:
			status = 1;
			hidePlayer();
			playerWalk.show();
			returnStatus();

			// if a key is pressed, show the new animation
			if (EZInteraction.wasKeyPressed('w')) {
				playerState = JUMP;
				jumpCounter = 0;
				hidePlayer();
				playerJump.show();
			}
			if (EZInteraction.wasKeyPressed('s')) {
				playerState = SLIDE;
				hidePlayer();
				playerSlide.show();
			}
			break;
		// if the player is jumping...
		case JUMP:
			status = 2;
			jumpCounter++;
			if (jumpCounter > JUMPHEIGHT) {
				playerState = FALL;
				hidePlayer();
				playerFall.show();
			} else {
				posy -= 15;
				playerJump.translateTo(posx, posy);
				playerDeath.translateTo(posx, posy);
			}
			break;
		// if the player is falling...
		case FALL:
			status = 3;
			jumpCounter--;
			if (jumpCounter <= 0) {
				playerState = WALK;
				playerWalk.show();

			} else {
				posy += 15;
				playerFall.translateTo(posx, posy);
				playerDeath.translateTo(posx, posy);
			}
			break;
		// if the player is sliding...
		case SLIDE:
			status = 4;
			if (EZInteraction.isKeyDown('s')) {
				hidePlayer();
				playerSlide.show();
			} else {
				playerState = WALK;
			}
			break;
		// if the player is dead
		case DEAD:
			// alive = false;
			hidePlayer();
			playerDeath.show();
		}

	}
}
