package Main;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	private static String TITLE = "Hex_Num";
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static long startTime;
	public static boolean inGame, paused;
	
	private static Stage window;
	
	private ArrayList<Scene> scenes;
	public static Scene titleScene, gameScene, helpScene;
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		inGame = paused = false;
		
		window = primaryStage;
		window.setTitle(TITLE);
		
		scenes = new ArrayList<Scene>();

		titleScene = new Scene(new TitleScreen(WIDTH, HEIGHT, TITLE), WIDTH, HEIGHT);
		titleScene.getStylesheets().add("stylesheets/titleStyle.css");
		helpScene = new Scene(new HelpScreen(WIDTH, HEIGHT), WIDTH, HEIGHT);
		helpScene.getStylesheets().add("stylesheets/titleStyle.css");
		scenes.add(helpScene);
		
		window.setScene(titleScene);
		window.initStyle(StageStyle.UNDECORATED);
		
		startTime = System.nanoTime();
		update();
		
		window.show();
	}
	public static void update(){
		new AnimationTimer(){
			public void handle(long currentTime){
				inGame = window.getScene().equals(gameScene);
				
				if(currentTime - startTime > 15*Math.pow(10, 9))
					closeProgram();
			}
		}.start();
	}
	public static void setScene(Scene scene){
		window.setScene(scene);
	}
	public static void closeProgram(){
		window.close();
	}
}
