import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] circleY = new float[25];
  float circleYSpeed = 1;

  boolean upPressed = false;
  boolean downPressed = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(300, 300);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(50);

    for (int i = 0; i < circleY.length; i++) {
      float circleX = width * i / circleY.length;
      ellipse(circleX, circleY[i] * circleYSpeed, 25, 25);

      circleY[i]++;

      if (circleY[i] > height) {
        circleY[i] = 0;
      }
    }

    if (upPressed) {
      circleYSpeed -= 0.5;
    }
    if (downPressed) {
      circleYSpeed += 0.5;
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
  }
}