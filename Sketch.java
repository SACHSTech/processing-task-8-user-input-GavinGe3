import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;


public class Sketch extends PApplet {
   Random rand = new Random();



	
   // Image variables
   PImage imgBluePaddle;
   PImage imgRedPaddle;
   PImage imgBackground;
   
   // ball speed, size and random location variables
   float fltCirSpeedY = 20;
   float fltCirSpeedX = 10;
 
   int intCircleX = rand.nextInt(720);
   int intCircleY = rand.nextInt(960);
 
   int intCircleSize = 25;
 
   // paddle location and speed variables
   float fltBluePaddleX = 200;
   float fltBluePaddleY = 100;
   float fltBluePaddleSpeed = 0;
   boolean boolBluePaddleLeft = false;
   boolean boolBluePaddleRight = false;
   
   // red paddle location and speed variables
   float fltRedPaddleX = 235;
   float fltRedPaddleY = 700;
   float fltRedPaddleSpeed = 10;
   
   // Score counter variables
   int intScoreRed = 0;
   int intScoreBlue = 0;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(720, 960);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
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

	// sample code, delete this stuff
  
  }


  public void game(){
    // draw and animate ball
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
    if (keyPressed){
      if (keyCode == LEFT){
        fltBluePaddleX += -12;
      }
      if (keyCode == RIGHT){
        fltBluePaddleX += 12;
      }
    }

    // Collission detection with blue paddle and wall
    if (fltBluePaddleX < 0  || fltBluePaddleX > width - 104){
      fltBluePaddleSpeed *= -1;
    }
    // draw and animate red paddle
    image(imgRedPaddle, fltRedPaddleX, fltRedPaddleY);
    if (keyPressed){
      if (key == 'a'){
        fltRedPaddleX += -12;
      }
      if (key == 'd'){
        fltRedPaddleX += 12;
      }
    }
    
    // Collission detection of red paddle and wall
    if (fltRedPaddleX < 0 || fltRedPaddleX > width - 104){
      fltRedPaddleSpeed *= -1;
    }

    // Collission detection of red paddle and ball
    if (intCircleX > fltRedPaddleX && intCircleX < fltRedPaddleX + 104){
      if (intCircleY > fltRedPaddleY -12 && intCircleY < fltRedPaddleY + 36){
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
          fltBluePaddleY = rand.nextInt(480);
          fltBluePaddleX = rand.nextInt(600);
          
        }
        if (fltBluePaddleSpeed > 0){
          fltCirSpeedX = 5;
          fltCirSpeedY *= -1;
          fltBluePaddleY = rand.nextInt(480);
          fltBluePaddleX = rand.nextInt(600);
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
  }
 

   
  

  
  

  
  
  // define other methods down here.
}


