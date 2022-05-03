import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * A program that allows two users to play a game of Pong
 * @author: G. Ge
 */

public class Sketch extends PApplet {
   Random rand = new Random();

   // Image variables
   PImage imgBluePaddle;
   PImage imgRedPaddle;
   PImage imgBackground;
   
   // ball speed, size, color and random location variables
   float fltCirSpeedY = 12;
   float fltCirSpeedX = 6;
 
   int intCircleX = rand.nextInt(720);
   int intCircleY = rand.nextInt(960);
 
   int intCircleSize = 25;

   int ballColorOne = 255;
   int ballColorTwo = 255;
   int ballColorThree = 255;
 
   // paddle location, speed and bool variables
   float fltBluePaddleX = 200;
   float fltBluePaddleY = 100;
   float fltBluePaddleSpeed = 10;
   boolean boolBluePaddleLeft = false;
   boolean boolBluePaddleRight = false;
   
   // red paddle location, speed, and bool variables
   float fltRedPaddleX = 235;
   float fltRedPaddleY = 700;
   float fltRedPaddleSpeed = 10;
   boolean boolRedPaddleLeft = false;
   boolean boolRedPaddleRight = false; 
   
   // Score counter variables
   int intScoreRed = 0;
   int intScoreBlue = 0;
	
  /**
   * Called once at the beginning of execution, declare size
   */
  public void settings() {
	// put your size call here
    size(720, 960);
  }

  /** 
   * Called once at the beginning of execution.  Contains  initial set up values
   * 
   */
  public void setup() {
    background(210, 255, 173);
    imgBluePaddle = loadImage("paddleBlu.png");
    imgRedPaddle = loadImage("paddleRed.png");
    imgBackground = loadImage("background.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    image(imgBackground, 0, 0);
    game();
	
  }

  /**
   *  A game of pong
   * 
   */

  public void game(){

    // draw and animate ball
    fill(ballColorOne, ballColorTwo, ballColorThree);
    ellipse(intCircleX, intCircleY, intCircleSize, intCircleSize);
    intCircleY += fltCirSpeedY;
    intCircleX += fltCirSpeedX;

    // Collision detection of ball and walls and score updater
    if (intCircleY > height - 5) {
      intCircleY = height - 5;
      fltCirSpeedY *= -1;
      intScoreBlue += 1;
    }
    if (intCircleY < 0 + 5){
      intCircleY = 5;
      fltCirSpeedY *= -1;
      intScoreRed += 1;
    }
    if (intCircleX > width - 12.5 || intCircleX < 0 + 12.5){
      fltCirSpeedX *= -1;
    }

    // draw and animate blue paddle
    image(imgBluePaddle, fltBluePaddleX, fltBluePaddleY);
    if (boolBluePaddleRight){
      fltBluePaddleX += 15;
      fltBluePaddleSpeed = 20;
    }
    if (boolBluePaddleLeft){
      fltBluePaddleX -= 15;
      fltBluePaddleSpeed = -20;
    }

    // Collission detection with blue paddle and wall
    if (fltBluePaddleX < 0){
      fltBluePaddleX = 0;
    }
    if (fltBluePaddleX > width -104){
      fltBluePaddleX = width - 104;
    }
    // draw and animate red paddle
    image(imgRedPaddle, fltRedPaddleX, fltRedPaddleY);
    if (boolRedPaddleRight){
      fltRedPaddleX += 15;
      fltRedPaddleSpeed = 20;
    }
    
    if (boolRedPaddleLeft){
      fltRedPaddleX -= 15;
      fltRedPaddleSpeed = -20;
    }
  
    // Collission detection of red paddle and wall
    if (fltRedPaddleX < 0){
      fltRedPaddleX = 0;
    }
    if (fltRedPaddleX > width -104){
      fltRedPaddleX = width - 104;
    }

    // Collission detection of red paddle and ball
    if (intCircleX > fltRedPaddleX && intCircleX < fltRedPaddleX + 104){
      if (intCircleY > fltRedPaddleY -12 && intCircleY < fltRedPaddleY + 36){
        if (fltRedPaddleSpeed == 0){
          fltCirSpeedY *= -1;
        }
        if (fltRedPaddleSpeed < 0 ){
          fltCirSpeedX = -5;
          fltCirSpeedY *= -1;
        }
        if (fltRedPaddleSpeed > 0){
          fltCirSpeedX = 5;
          fltCirSpeedY *= -1;
        }
        if (intCircleY > fltRedPaddleY + 12){
          intCircleY += 20;
        }
        if (intCircleY < fltRedPaddleY + 12){
          intCircleY -= 20;
      }
    }
  }

    // Collision detection of blue paddle and ball
    if (intCircleX > fltBluePaddleX && intCircleX < fltBluePaddleX + 104){
      if (intCircleY > fltBluePaddleY -12 && intCircleY < fltBluePaddleY + 36){
        if (fltBluePaddleSpeed == 0){
          fltCirSpeedY *= -1;
        }
        if (fltBluePaddleSpeed < 0){
          fltCirSpeedX = -5;
          fltCirSpeedY *= -1;
        }
        if (fltBluePaddleSpeed > 0){
          fltCirSpeedX = 5;
          fltCirSpeedY *= -1;
        }
        if (intCircleY > fltBluePaddleY + 12){
          intCircleY += 20;
        }
        if (intCircleY < fltBluePaddleY + 12){
          intCircleY -= 20;
        }
      }
    }

    // print red and blue points
    textSize(50);
    fill(255,255,255);
    text(intScoreRed, 340, 550);
    text(intScoreBlue, 340, 430);

    // Detects if Mouse pressed and changes the ball color
    if (mousePressed) {
      ballColorOne = rand.nextInt(255);
      ballColorTwo = rand.nextInt(255);
      ballColorThree = rand.nextInt(255);
    }
  }

  /**
   * If mouse is dragged, attaches ball to mouse cursor
   */

  public void mouseDragged(){
    intCircleX = mouseX;
    intCircleY = mouseY;

  }
  /**
   * If correct keyPressed, changes boolean values controlling paddle movement for true
   */

  public void keyPressed(){
    if (keyCode == LEFT){
      boolBluePaddleLeft = true;
    }
    if (keyCode == RIGHT){
      boolBluePaddleRight = true;
    }
    if (key == 'a'){
      boolRedPaddleLeft = true;
    }
    if (key == 'd'){
      boolRedPaddleRight = true;
      
    }
  }

  /**
   * If keyReleased, changes boolean values controlling paddle movement to false
   */

 public void keyReleased(){
   if (keyCode == LEFT){
     boolBluePaddleLeft = false;
     fltBluePaddleSpeed = 0;
   }
   if (keyCode == RIGHT){
    boolBluePaddleRight = false;
    fltBluePaddleSpeed = 0;
    
  }
  if (key == 'a'){
    boolRedPaddleLeft = false;
    fltRedPaddleSpeed = 0;
  }
  if (key == 'd'){
    boolRedPaddleRight = false;
    fltRedPaddleSpeed = 0;
  }
 }
}


