import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import java.util.*;
import javafx.scene.paint.Color;

public class ProjectClient extends Application
{
   private BorderPane root = new BorderPane();
   private GridPane gamePane = new GridPane();
   
   private Balls[][] board = new Balls[4][4];
   
   public void start(Stage stage)
   {
      
      root.setAlignment(gamePane, Pos.CENTER);
      
      for (int i = 0; i < 4; i++)
         for (int j = 0; j < 4; j++)
         {
            board[i][j] = new Balls();
            
            gamePane.add(board[i][j], i, j);
         }
      
      root.setCenter(gamePane);
      
      FlowPane banner = new FlowPane();
      banner.setAlignment(Pos.CENTER);
      banner.getChildren().add(new Label("The test feild!!!"));
      
      root.setTop(banner);
      
      
      FlowPane space1 = new FlowPane();
      space1.setPrefWidth(20);
      
      FlowPane space2 = new FlowPane();
      space2.setPrefWidth(20);
      
      root.setLeft(space1);
      root.setRight(space2);
      
      
      
      //600 - 4(160)
		Scene scene = new Scene(root, 600, 600);
      stage.setScene(scene);
      stage.setTitle("Ball Game");
      stage.show();
   }
   
	public static void main(String [] args)
	{
      launch(args);
	}
}
