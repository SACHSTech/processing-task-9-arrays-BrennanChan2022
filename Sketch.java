import processing.core.PApplet;

/**
 * Description: A program that creates a game by defining arrays to animate a collection of circles that simulate snow falling.
 * @author: B. Chan
 */

public class Sketch extends PApplet {
	
  float[] circleY = new float[25];
  float[] circleX = new float[25];
  float playerX = 200;
  float playerY = 200;

  double circleYSpeed = 1;
  int intLives = 3;

  boolean upPressed = false;
  boolean downPressed = false;
  boolean WPressed = false;
  boolean APressed = false;
  boolean SPressed = false;
  boolean DPressed = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(50);

    fill (255, 255, 255);
    for (int i = 0; i < circleY.length; i++) {
      ellipse(circleX[i], circleY[i], 40, 40);

      circleY[i] += circleYSpeed;

      if (circleY[i] > height + 20) {
        circleY[i] = 0;
      }
    }

    if (upPressed) {
      circleYSpeed = 0.5;
    }
    if (downPressed) {
      circleYSpeed = 2;
    }

    fill (53, 171, 230);
    ellipse(playerX, playerY, 25, 25);
    if (WPressed) {
      playerY--;
    }
    if (APressed) {
      playerX--;
    }
    if (SPressed) {
      playerY++;
    }
    if (DPressed) {
      playerX++;
    }
  }
  
  // define other methods down here.

  /**
   * C
   */
  public void keyPressed() {
    if (keyCode == UP) {
      upPressed = true;
    }
    else if (keyCode == DOWN) {
      downPressed = true;
    }
    else if (key == 'w') {
      WPressed = true;
    }
    else if (key == 'a') {
      APressed = true;
    }
    else if (key == 's') {
      SPressed = true;
    }
    else if (key == 'd') {
      DPressed = true;
    }
  }
  
  /**
   * S
   */
  public void keyReleased() {
    if (keyCode == UP) {
      upPressed = false;
      circleYSpeed = 1;
    }
    else if (keyCode == DOWN) {
      downPressed = false;
      circleYSpeed = 1;
    }
    else if (key == 'w') {
      WPressed = false;
    }
    else if (key == 'a') {
      APressed = false;
    }
    else if (key == 's') {
      SPressed = false;
    }
    else if (key == 'd') {
      DPressed = false;
    }
  }
}