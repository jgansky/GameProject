import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.event.*;
import java.io.*;
import javafx.scene.control.*;
import java.awt.Desktop;
import javafx.application.HostServices;
import javafx.scene.text.*;
import javafx.scene.canvas.*;

public class ProjectClient extends Application
{
   //Border pane for the root
   BorderPane root = new BorderPane();

   //GridPane for the game peices
   GridPane gameBoard = new GridPane();

   //Array to hold the game peices 
   GamePane[][] ballBoard = new GamePane[4][4]; 

   public void start(Stage stage)
   {
      //Creating Balls
      for(int i = 0; i < ballBoard.length; i++)
         for(int j = 0; j < ballBoard[i].length; j++)
         {
            //Creating new ball
            ballBoard[i][j] = new GamePane(i,j);
         
            //Setting the buttons for the ball
            ballBoard[i][j].startButtons();
         
            //Drawing the ball and buttons
            ballBoard[i][j].draw();
         
            //Adding to the board
            gameBoard.add(ballBoard[i][j],i,j);
         }
      
      //Setting inital board state
      ballBoard[0][2].isVisible(false);
      setButton();
      
      //Adding the game board
      root.setCenter(gameBoard);
   
      //Adding the top banner
      updateTopBanner();
   
      //Scene for window
      Scene scene = new Scene(root, 600,600);
      stage.setScene(scene);
      stage.setTitle("BallGame");
      stage.show();
   }

   //GamePane Class
   public class GamePane extends GridPane
   {
      //Button creations
      private Button button1 = new Button();
      private Button button2 = new Button();
      private Button button3 = new Button();
      private Button button4 = new Button();
   
      //Boolean for ball visibility
      private boolean ballVisible = true;
   
      //Booleans for button visibility 
      private boolean button1Visible = false;
      private boolean button2Visible = false;
      private boolean button3Visible = false;
      private boolean button4Visible = false;
   
      //general visibile or not 
      private boolean allVisible = true; 
      
      //for location in the board 
      private int x;
      private int y;
   
      //Instansiating and setting x and y 
      public GamePane(int x, int y)
      {
         this.x = x;
         this.y = y;
      }
   
      //Setting button pref sizes and actions, adding to the pane
      public void startButtons()
      {
         //For button 1
         button1.setPrefWidth(80);
         button1.setPrefHeight(20);
         button1.setOnAction(new ButtonListener());
         getChildren().add(button1);
      
         //For button 2
         button2.setPrefWidth(80);
         button2.setPrefHeight(20);
         button2.setOnAction(new ButtonListener());
         getChildren().add(button2);
      
         //For button 3
         button3.setPrefWidth(20);
         button3.setPrefHeight(80);
         button3.setOnAction(new ButtonListener());
         getChildren().add(button3);
      
         //For button 4
         button4.setPrefWidth(20);
         button4.setPrefHeight(80);
         button4.setOnAction(new ButtonListener());
         getChildren().add(button4);
         getChildren().add(ball);
      
         //setButton();
      
      }
      
      //Drawing the buttons and ball
      public void draw()
      {
         
         button1.setVisible(button1Visible);
         setConstraints(button1,1,0);           //Top Button
         
         button2.setVisible(button2Visible);
         setConstraints(button2,1,2);           //Bottom Button
      
         button3.setVisible(button3Visible);
         setConstraints(button3,0,1);           //Left Button
      
         button4.setVisible(button4Visible);
         setConstraints(button4,2,1);           //Right Button
         
         //Drawing the ball
         addBall();

         //updateTopBanner();
      }
   
      //Ball canvas and graphics context
      Canvas ball = new Canvas();
      GraphicsContext gc = ball.getGraphicsContext2D();
   
      public void addBall()
      {
         //Drawing the canvas 
         ball.setWidth(80);
         ball.setHeight(80);
      
         //Clearing what was previously on the canvas
         gc.clearRect(0,0,80,80);
      
         //If the ball is visible, redraw the ball
         if(ballVisible)
         {
            /*
            Color col = new Color(0,0.3,0,1);
            gc.setFill(col);
            gc.fillOval(5,10,25,25);
            gc.fillOval(20,0,25,25);
            gc.fillOval(30,10,25,25);
            */

            gc.fillOval(0,0,80,80);
         }
            
      
         //Re-adding the ball 
         setConstraints(ball,1,1);
         //getChildren().add(ball);
      }
   
      //For the buttons and their actions
      public class ButtonListener implements EventHandler<ActionEvent>
      {
         public void handle(ActionEvent e)
         {
            //Sets the current ball to invisible
            isVisible(false);
         
            //If the left button is clicked
            if (y+2 < 4 && e.getSource() == button1)
            { 
               click(x,y,1);
            }
         
            //If the right button is clicked 
            if (y-2 >= 0 && e.getSource() == button2)
            {
               click(x,y,2);
            }
         
            //If the top button is clicked
            if (x+2 < 4 && e.getSource() == button3)
            {
               click(x,y,3);
            }
         
            //If the bottom button is clicked
            if (x-2 >= 0 && e.getSource() == button4)
            {
               click(x,y,4);
            }
         
            //Re-drawing after changes 
            draw();
            updateTopBanner();
         }
      }
   
      //Sets the ball's visibility 
      public void isVisible(boolean visible)
      {
      
         //Sets the general visibility
         allVisible = visible;
      
         //If its not visible, set the buttons to false
         if(visible == false)
         {
            button1Visible = false;
            button2Visible = false;
            button3Visible = false;
            button4Visible = false;
         }
         
         //Sets the ball to be visible or invisible
         ballVisible = visible; 
      
         //Re-draws after changes
         draw();
         updateTopBanner();
      }
      
      public void allButtonInvisible()
      {
         button1Visible = false;
         button2Visible = false;
         button3Visible = false;
         button4Visible = false;
         
         draw();
         updateTopBanner();
      }
   
      //Returns x location
      public int getX()
      {
         return x;
      }    
      
      //Returns y location
      public int getY()
      {
         return y;
      }
      
      //Returns general visibility
      public boolean getVisible()
      {
         return allVisible;
      }
   
      //Mutator for button 1 visibility
      public void setButton1Visible(boolean visible)
      {
         button1Visible = visible;
         updateTopBanner();
      }
   
      //Mutator for button 2 visibility
      public void setButton2Visible(boolean visible)
      {
         button2Visible = visible;
         updateTopBanner();
      }
   
      //Mutator for button 3 visibility
      public void setButton3Visible(boolean visible)
      {
         button3Visible = visible;
         updateTopBanner();
      }
   
      //Mutator for button 4 visibility
      public void setButton4Visible(boolean visible)
      {
         button4Visible = visible;
         updateTopBanner();
      }

      public int getButtonCount()
      {
        int buttonCount = 0;

        if(button1Visible)
            buttonCount+=1;
        if(button2Visible)
            buttonCount+=1;
        if(button3Visible)
            buttonCount+=1;
        if(button4Visible)
            buttonCount+=1;
        
        return buttonCount;
      }
      
   }

   //For when a button is clicked
   public void click(int x, int y, int selection)
   {
      //For button 1
      if (selection == 1)
      {
        ballBoard[x][y+1].isVisible(false);   //sets the next ball invisible
        ballBoard[x][y+2].isVisible(true);    //sets the second ball to visible
      }
   
      //For button 2
      if (selection == 2)
      {
        ballBoard[x][y-1].isVisible(false);   //sets the next ball invisible
        ballBoard[x][y-2].isVisible(true);    //sets the second ball to visible
      }
   
      //For button 3
      if (selection == 3)
      {
        ballBoard[x+1][y].isVisible(false);   //sets the next ball invisible
        ballBoard[x+2][y].isVisible(true);    //sets the second ball to visible
      }
   
      //For button 4
      if (selection == 4)
      {
        ballBoard[x-1][y].isVisible(false);   //sets the next ball invisible
        ballBoard[x-2][y].isVisible(true);    //sets the second ball to visible
      }
   
      setButton();
      updateTopBanner();
      
   }

   //Sets the active button for movement
   public void setButton()
   {
      for (int i = 0; i < ballBoard.length; i++)
         for(int j = 0; j < ballBoard[i].length; j++)
         {
            ballBoard[i][j].allButtonInvisible();

            //If the space above is visible, and the next one is invisible
            if (((j-2) >= 0) && !(ballBoard[i][j-2].getVisible()) && (ballBoard[i][j-1].getVisible()) && (ballBoard[i][j].getVisible()))
            {
               ballBoard[i][j].setButton2Visible(true);   //Sets the bottom button visible
               ballBoard[i][j].draw();
               updateTopBanner();
            }
            
            //If the space below is visible, and the next one is invisible
            if (((j+2) < 4) && !(ballBoard[i][j+2].getVisible()) && (ballBoard[i][j+1].getVisible()) && (ballBoard[i][j].getVisible()))
            {
               ballBoard[i][j].setButton1Visible(true);   //Sets the top button visible
               ballBoard[i][j].draw();
               updateTopBanner();
            }
            
            //If the space to the left is visible, and the next one is invisible
            if (((i-2) >= 0) && !(ballBoard[i-2][j].getVisible()) && (ballBoard[i-1][j].getVisible()) && (ballBoard[i][j].getVisible()))
            {
               ballBoard[i][j].setButton4Visible(true); //Sets the right button visible
               ballBoard[i][j].draw();
               updateTopBanner();
            }
            
            //If the space to the right is visible, and the next one is invisible
            if (((i+2) < 4) && !(ballBoard[i+2][j].getVisible()) && (ballBoard[i+1][j].getVisible()) && (ballBoard[i][j].getVisible()))
            {
               ballBoard[i][j].setButton3Visible(true);   //Sets the left button visible
               ballBoard[i][j].draw();
               updateTopBanner();
            }
         }
         updateTopBanner();
   }


   public void updateTopBanner()
   {
      int totalBalls = 0;
      int totalMoves = 0;
      
      
      for(int i = 0; i < ballBoard.length; i++)
          for(int j = 0; j < ballBoard[i].length; j++)
          {
            if(ballBoard[i][j].getVisible())
                  totalBalls+=1;
            totalMoves += ballBoard[i][j].getButtonCount();
          } 
   
      FlowPane topBanner = new FlowPane();
      topBanner.setAlignment(Pos.CENTER);
      if(totalBalls == 1)
      {
        Label topLabel = new Label("You Win!");
        topBanner.getChildren().add(topLabel);
      }
      else if(totalMoves == 0)
      {
        Label topLabel = new Label("You Lose!");
        topBanner.getChildren().add(topLabel);
      }
      else
      {
        Label topLabel = new Label("Balls Left: " + totalBalls + "\t" + "Possible Moves: " + totalMoves);
        topBanner.getChildren().add(topLabel);
      }
   
      root.setTop(topBanner);
   }

   public static void main(String [] args)
   {
      launch (args);
   }
}
