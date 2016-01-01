package Main;

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
	public static double brightness, volume;
	public static boolean volumeEnabled;
	
	private static Stage window;
	
	public static Scene titleScene, gameScene, achievementScene, optionsScene, helpScene;
	private static TitleScreen titleScreen;
	private static GameScreen gameScreen;
	private static AchievementScreen achievementScreen;
	private static OptionsScreen optionsScreen;
	private static HelpScreen helpScreen;
	private static PlayScreen currentScreen;
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		brightness = 1;
		
		window = primaryStage;
		window.setTitle(TITLE);
		
		titleScreen = new TitleScreen(WIDTH, HEIGHT, TITLE);
		titleScene = new Scene(titleScreen, WIDTH, HEIGHT);
		titleScene.getStylesheets().add("stylesheets/titleStyle.css");

		gameScreen = new GameScreen(WIDTH, HEIGHT);
		gameScene = new Scene(gameScreen, WIDTH, HEIGHT);
		gameScene.getStylesheets().add("stylesheets/titleStyle.css");

		achievementScreen = new AchievementScreen(WIDTH, HEIGHT);
		achievementScene = new Scene(achievementScreen, WIDTH, HEIGHT);
		achievementScene.getStylesheets().add("stylesheets/titleStyle.css");
		
		optionsScreen = new OptionsScreen(WIDTH, HEIGHT);
		optionsScene = new Scene(optionsScreen, WIDTH, HEIGHT);
		optionsScene.getStylesheets().add("stylesheets/titleStyle.css");
		
		helpScreen = new HelpScreen(WIDTH, HEIGHT);
		helpScene = new Scene(helpScreen, WIDTH, HEIGHT);
		helpScene.getStylesheets().add("stylesheets/titleStyle.css");
		
		currentScreen = titleScreen;
		
		window.setScene(titleScene);
		window.initStyle(StageStyle.UNDECORATED);
		
		startTime = System.nanoTime();
		update();
		
		window.show();
	}
	private void update(){
		new AnimationTimer(){
			public void handle(long currentTime){
				
				currentScreen.update(currentTime);
				
				if(currentTime - startTime > 60*Math.pow(10, 9))
					closeProgram();
			}
		}.start();
	}
	public static void setScene(Scene scene){
		window.setScene(scene);

		if(window.getScene().equals(gameScene))
			currentScreen = gameScreen;
		else if(window.getScene().equals(achievementScene))
			currentScreen = achievementScreen;
		else if(window.getScene().equals(optionsScene))
			currentScreen = optionsScreen;
		else
			currentScreen = helpScreen;
	}
	public static void closeProgram(){
		window.close();
	}
}
