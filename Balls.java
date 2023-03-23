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
import java.net.*;
import java.awt.Desktop;
import javafx.application.HostServices;
import javafx.scene.text.*;
import javafx.scene.canvas.*;

public class Balls extends GridPane
{
   
   
   private boolean ballVisible = true;
   
   private boolean[][] buttonVisible = new boolean[3][3];
   
   private Button[][] buttonArray = new Button[3][3];
   
   public Balls()
   {
      setPrefWidth(140);
      setPrefHeight(140);
      draw();  
   }
   
   public void draw()
   {
      Canvas ballCanvas = new Canvas();
      GraphicsContext gc = ballCanvas.getGraphicsContext2D();
      
      ballCanvas.setWidth(80);
      ballCanvas.setHeight(80);
      
      gc.clearRect(0,0,80,80);
      
      if (ballVisible)
         gc.fillOval(0,0,80,80);
      else
         gc.clearRect(0,0,80,80);
      
      for(int i = 0; i < buttonArray.length; i++)
         for(int j = 0; j < buttonArray[i].length; j++)
         {
            if( i == 1 && j == 1)
               add(ballCanvas,i,j);
            else if( i != j && (i + j) != 2)
            {
               buttonArray[i][j] = new Button();
               buttonVisible[i][j] = true;
               buttonArray[i][j].setVisible(buttonVisible[i][j]);
               buttonArray[i][j].setOnAction(new ButtonListener());
               
               if(i == 1)
               {
                  buttonArray[i][j].setPrefWidth(80);
                  buttonArray[i][j].setPrefHeight(20);
               }
               else
               {
                  buttonArray[i][j].setPrefWidth(20);
                  buttonArray[i][j].setPrefHeight(80);
               }
               
               
              
               add(buttonArray[i][j],i,j);
            }
           
         }
   }
   
   public void setBallVisible(boolean visible)
   {
      ballVisible = visible;
   }
   
   public void setButtonVisible(int x, int y, boolean visible)
   {
      buttonVisible[x][y] = visible;
   }
   
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
      
       //Scanning the code to see what button was selected 
         for(int i = 0; i < buttonArray.length; i++)
         {
            for(int j = 0; j  < buttonArray[i].length; j++)
            {
               if(e.getSource() == buttonArray[i][j])
               {
                  ballVisible = false;
                  draw();
               }
            }
         }
      }
   }
}