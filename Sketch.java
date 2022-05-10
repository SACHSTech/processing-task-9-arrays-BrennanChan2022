import processing.core.PApplet;

/**
 * Description: A program that creates a game by defining arrays to animate a collection of circles that simulate snow falling.
 * @author: B. Chan
 */

public class Sketch extends PApplet {
	
  float[] circleY = new float[45];
  float[] circleX = new float[45];
  boolean[] ballHideStatus = new boolean[45];

  double dblSnowSpeed = 2;

  float playerX = 250;
  float playerY = 250;
  boolean playerAlive = true;
  int intPlayerLives = 3;

  boolean WPressed = false;
  boolean APressed = false;
  boolean SPressed = false;
  boolean DPressed = false;
  boolean mouseClicked = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(500, 500);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (playerAlive == true) {
      background(50);

      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == true) {
          fill (50);
          noStroke();
          ellipse(circleX[i], circleY[i], 40, 40);
          circleY[i] += dblSnowSpeed;
        }
      }

      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == false) {
          stroke(0,0,0);
          strokeWeight(1);
          fill (255, 255, 255);
          ellipse(circleX[i], circleY[i], 40, 40);
          circleY[i] += dblSnowSpeed;
        }

        if (circleY[i] > height + 20) {
          circleY[i] = 0;
          ballHideStatus[i] = false;
        }

        if (dist(playerX, playerY, circleX[i], circleY[i]) <= (12.5 + 20) && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intPlayerLives--;
        }
        else if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 20 && mouseClicked) {
          ballHideStatus[i] = true;
        }
      }

      fill (53, 171, 230);
      ellipse(playerX, playerY, 25, 25);
      if (WPressed) {
        playerY += -3;
      }
      if (APressed) {
        playerX += -3;
      }
      if (SPressed) {
        playerY += 3;
      }
      if (DPressed) {
        playerX += 3;
      }

      for (int i = 1; i <= intPlayerLives; i++) {
        fill(255, 0 ,0);
        rect(320 + i * 45, 5, 40, 40);
      }

      if (intPlayerLives == 0) {
        playerAlive = false;
      }
    } 

    else if (playerAlive == false){
      background(255, 255, 255);
    }
  }
  
  // define other methods down here.

  /**
   * C
   */
  public void keyPressed() {
    if (keyCode == UP) {
      dblSnowSpeed = 1;
    }
    else if (keyCode == DOWN) {
      dblSnowSpeed = 3;
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
      dblSnowSpeed = 2;
    }
    else if (keyCode == DOWN) {
      dblSnowSpeed = 2;
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

  /**
   * S
   */
  public void mousePressed() {
    mouseClicked = true;
  }

  /**
   * S
   */
  public void mouseReleased() {
    mouseClicked = false;
  }
}