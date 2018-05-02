package game;



import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DiceRoll extends Application {
	
	public int rand;
	public Random random = new Random();
	public Label randomResult;
	
	public int cirPos[][] = new int[10][10];
	public int ladderPos[][] = new int[6][3];
	
	public Circle player1;
	public Circle player2;
	
	public int player1Position = 1;
	public int player2Position = 1;
	
	public boolean player1Turn = true;
	public boolean player2Turn = true;
	
	public static int player1XPos = 40;
	public static int player1YPos = 760;
	
	public static int player2XPos = 40;
	public static int player2YPos = 760;
	
	public int posCir1 = 1;
	public int posCir2 = 1;

	
	
	public boolean gameStart = true;
	public Button gameButton ;
	
	public static final int Tile_Size = 80;
	public static final int width = 10;
	public static final int height = 10;
	
	private int max = 10;
	private int min = 1;
	
	private Group tileGroup = new Group();
	
	private Parent createBoard() {
		StackPane root = new StackPane();
		root.setPrefSize(width * Tile_Size, height * Tile_Size);
		root.getChildren().addAll(tileGroup);
		
		for (int i =0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Tile tile = new Tile(Tile_Size,Tile_Size);
				tile.setTranslateX(j * Tile_Size);
				tile.setTranslateY(i * Tile_Size);
				tileGroup.getChildren().add(tile);
				
				cirPos[i][j] = j*(Tile_Size - 40);
			}
		}
		
		for (int i = 0 ; i< height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(cirPos[i][j]+ " ");
			}
			System.out.println();
		}
		
		player1 = new Circle(40);
		player1.setId("player1");
		player1.setFill(Color.BLUE);
		player1.getStyleClass().add("game/style.css");
		player1.setTranslateX(player1XPos);
		player1.setTranslateY(player1YPos);
		
		player2 = new Circle(40);
		player2.setId("player2");
		player1.setFill(Color.RED);
		player2.getStyleClass().add("game/style.css");
		player2.setTranslateX(player2XPos);
		player2.setTranslateY(player2YPos);
		
		Button buttonPlayer2 = new Button("Player2");
		buttonPlayer2.setTranslateX(700);
		buttonPlayer2.setTranslateY(820);
		buttonPlayer2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(gameStart) {
					if(player2Turn) {
						getDiceValue();
						randomResult.setText(String.valueOf(rand));
						movePlayer2 ();
						translatePlayer(player2XPos, player2YPos, player2);
						player1Turn = true;
						player2Turn = false;
						player2Position += rand;
						
						if(player2XPos == 40 &&  player2YPos == 760) translatePlayer(player2XPos = 200, player2YPos = 520, player1);
//						if(player2XPos == 240 &&  player2YPos == 760) translatePlayer(player2XPos = 80, player2YPos = 680, player1);	
//						if(player2XPos == 280 &&  player2YPos == 760) translatePlayer(player2XPos = 280, player2YPos = 520, player1);
//						if(player2XPos == 200 &&  player2YPos == 680) translatePlayer(player2XPos = 200, player2YPos = 600, player1);

					}
				}
			}
		});
		
		Button buttonPlayer1 = new Button("Player1");
		buttonPlayer1.setTranslateX(40);
		buttonPlayer1.setTranslateY(820);
		buttonPlayer1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(gameStart) {
					if(player1Turn) {
						getDiceValue();
						randomResult.setText(String.valueOf(rand));
						movePlayer1();
						translatePlayer(player1XPos, player1YPos, player1);
						player1Turn = false;
						player2Turn = true;
						player1Position += rand;
						
						if(player1XPos == 120 && player1YPos == 760) translatePlayer(player1XPos = 200, player1YPos = 520, player1);
						if(player1XPos == 520 && player1YPos == 760) translatePlayer(player1XPos = 520, player1YPos = 680, player1);	
						if(player1XPos == 600 && player1YPos == 760) translatePlayer(player1XPos = 760, player1YPos = 520, player1);
						if(player1XPos == 440 && player1YPos == 680) translatePlayer(player1XPos = 440, player1YPos = 600, player1);
						if(player1XPos == 360 && player1YPos == 680) translatePlayer(player1XPos = 440, player1YPos = 760, player1);
						if(player1XPos == 360 && player1YPos == 520) translatePlayer(player1XPos = 280, player1YPos = 440, player1);
						if(player1XPos == 760 && player1YPos == 360) translatePlayer(player1XPos = 520, player1YPos = 280, player1);
						if(player1XPos == 680 && player1YPos == 440) translatePlayer(player1XPos = 760, player1YPos = 680, player1);
						if(player1XPos == 440 && player1YPos == 440) translatePlayer(player1XPos = 360, player1YPos = 600, player1);
						if(player1XPos == 120 && player1YPos == 280) translatePlayer(player1XPos = 120, player1YPos = 680, player1);
						if(player1XPos == 280 && player1YPos == 280) translatePlayer(player1XPos = 40, player1YPos = 360, player1);



					}
				}
			}
		});
		
		gameButton = new Button("Start Game");
		gameButton.setTranslateX(360);
		gameButton.setTranslateY(820);
		gameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				gameButton.setText("Game Started");
				
				player1XPos = 40;
				player1YPos = 760;
				player2XPos = 40;
				player2YPos = 760;
		
				player1.setTranslateX(player1XPos);
				player1.setTranslateY(player1YPos);
				player2.setTranslateX(player2XPos);
				player2.setTranslateY(player2YPos);
				
				gameStart = true; 
				player1Turn = true;
				player2Turn = false;
				
				translatePlayer(player1XPos = 40, player1YPos = 360, player1);
				
			}
		});
		
		
		randomResult = new Label ("0");
		randomResult.setTranslateX(220);
		randomResult.setTranslateY(820);
		
		Image img = new Image("bg.jpg");
		ImageView bgImage = new ImageView();
		bgImage.setImage(img);
		bgImage.setFitHeight(800);
		bgImage.setFitWidth(800);
	
		
		tileGroup.getChildren().addAll(bgImage, player1, player2, buttonPlayer2, buttonPlayer1, gameButton, randomResult);
		return root;
	}
	
	private void movePlayer1() {
		for (int i = 0; i < rand; i++) {
			if(posCir1 % 2 == 1)  player1XPos += 80;
			if (posCir1 % 2 == 0) player1XPos -= 80;
			if(player1XPos > 760) {
				player1YPos -= 80;
				player1XPos -= 80;
				posCir1++;
			}
			
			if(player1XPos < 40) {
				player1YPos -= 80;
				player1XPos += 80;
				posCir1++;
			}
	
			if(player1XPos < 40 || player1YPos < 40) {
				player1XPos = 40;
				player1YPos = 40;
				randomResult.setText("Player One Won");
				gameButton.setText("start again");
			}
		}
	}
	
	private void movePlayer2() {
		for (int i = 0; i < rand; i++) {
			if(posCir2 % 2 == 1)  player2XPos += 80;
			if (posCir2 % 2 == 0) player2XPos -= 80;
			if(player2XPos > 760) {
				player2YPos -= 80;
				player2XPos -= 80;
				posCir2++;
			}
			
			if(player2XPos < 40) {
				player2YPos -= 80;
				player2XPos += 80;
				posCir2++;
			}
	
			if(player2XPos < 40 || player2YPos < 40) {
				player2XPos = 40;
				player2YPos = 40;
				randomResult.setText("Player Two Won");
				gameButton.setText("start");
			}
		}
	}
	
	
	
	private void getDiceValue() {
		rand = (int) random.nextInt((max - min) + 1) + min;
	}
	
	private void translatePlayer(int x, int y, Circle b) {
		TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
		animate.setToX(x);
		animate.setToY(y);
		animate.setAutoReverse(false);
		animate.play();
	}
	
	/**
	 * setting up the user interface.
	 */
	@Override
	public void start(Stage primaryStage) {
	try {
		

		Scene scene = new Scene(createBoard());
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.setTitle("Snake And Ladder");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Exception creating scene: "+ e.getMessage());
	}
}
/**
 * Launch the program
 * @param args
 */
public static void main(String[] args) {
	launch(args);
}
	
}
