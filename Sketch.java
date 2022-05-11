import processing.core.PApplet;

/**
 * Description: A program that creates a game by defining arrays to animate a collection of circles that simulate snow falling.
 * @author: B. Chan
 */

public class Sketch extends PApplet {
  // Declare global variables
  float[] circleY = new float[50];
  float[] circleX = new float[50];
  boolean[] ballHideStatus = new boolean[50];

  double dblSnowSpeed = 2;

  float playerX = 250;
  float playerY = 475;
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
    // Create random coordinates for the falling snowballs, begin 200 pixels away from the bottom
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(-200, height - 200);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // If the player is stil alive, continue the gameplay
    if (playerAlive == true) {
      background(50);

      // Draws the hidden snowballs as the same colour of the background to hide them and to keep consistent respawn time
      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == true) {
          fill (50);
          noStroke();
          ellipse(circleX[i], circleY[i], 40, 40);
          circleY[i] += dblSnowSpeed;
        }
      }
      
      // Draws snowballs to the screen if ballHideStatus is false
      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == false) {
          stroke(0,0,0);
          strokeWeight(1);
          fill (255, 255, 255);
          ellipse(circleX[i], circleY[i], 40, 40);
          circleY[i] += dblSnowSpeed;
        }
        
        // Resets snowballs back to top of screen when the entire snowball leaves the screen, and sets ballHideStatus to false so hidden snowballs respawn back to the top of the screen
        if (circleY[i] > height + 20) {
          circleY[i] = 0;
          ballHideStatus[i] = false;
        }
        
        // If player-circle makes contact with a non-hidden snowball, hide the snowball and remove a player life
        if (dist(playerX, playerY, circleX[i], circleY[i]) <= (12.5 + 20) && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intPlayerLives--;
        }
        // If the player clicks on a snowball with the mouse, hide the snowball
        else if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 20 && mouseClicked) {
          ballHideStatus[i] = true;
        }
      }

      // Draw the player-circle to the screen
      fill (53, 171, 230);
      ellipse(playerX, playerY, 25, 25);
      // Keyboard controls of the player-circle: W = up, S = down, A = left, D = right
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
      
      // Draw 3 red squares to indicate the number of player lives at the top right of screen
      for (int i = 1; i <= intPlayerLives; i++) {
        fill(255, 0 ,0);
        rect(320 + i * 45, 5, 40, 40);
      }
      
      // When player uses all 3 lives, set corresponding boolean value to false
      if (intPlayerLives == 0) {
        playerAlive = false;
      }
    } 
    
    // If the player runs out of lives, end the game by clearing the screen to white
    else if (playerAlive == false){
      background(255, 255, 255);
    }
  }
  
  // define other methods down here.

  /**
   * If user holds Up arrow, decrease falling speed of snowballs, and if Down arrow is held, increase falling speed of snowballs. Changes player-circle's X and Y coordinates when user presses/holds WASD controls, by setting the corresponding boolean value to true.
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
   * When Up/Down arrow is released, reset falling speed of snowballs back to normal. Stops the player-circle's X and Y coordinates changing when the user releases the WASD controls, by setting the corresponding boolean value to false.
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
   * Sets mouseClicked boolean to true whenever the mouse is clicked to allow snowballs to be removed by the user if the snowball is in range
   */
  public void mousePressed() {
    mouseClicked = true;
  }

  /**
   * Sets mouseClicked boolean to false whenever the mouse is released to stop the removal of snowballs from the screen
   */
  public void mouseReleased() {
    mouseClicked = false;
  }
}