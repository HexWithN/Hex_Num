package Main;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameScreen extends PlayScreen{
	private int difficulty; //~ Affects how many hexes will appear
	private int numbersShown; //~ How many numbers will be shown
	private float flashTime; //~ How long the number will flash on the screen (in milliseconds)
	private float startTime; //~ This is the count used for flashTime
	private float blankTime;
	private float timeSinceLastSolve;
	private float timeRemaining;
	private boolean gameStart; //~ Has the game started?
	private boolean solved, lost; //~ Was the puzzle solved or lost?
	private boolean[] numbersPressed; //~ Keeps track of whether or not a hexagon number was pressed
	private ArrayList<Float> solveTimes;
	private Text timeLeft, stopWatch;
	HexGrid hexGrid; // ~ The grid of hexes. Controls what hexagons are shown, and where.
	
	public GameScreen(int width, int height){
		super(width, height);
		setAlignment(Pos.CENTER);
		
		lost = false;
		solved = lost;
		difficulty = 2;
		gameStart = false;
		numbersShown = difficulty;
		timeSinceLastSolve = 0;
		solveTimes = new ArrayList<Float>();
		int hexMargin = width/32;
		hexGrid = new HexGrid(hexMargin);
		
		numbersPressed = new boolean[9];
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;

		timeRemaining = 60;
		timeLeft = new Text("" + timeRemaining);
		timeLeft.setStyle("-fx-font-size: " + hexMargin*2 + "px; -fx-fill: #fff;");
		timeLeft.setTranslateY(hexMargin);
		VBox timeBox = new VBox();
		timeBox.getChildren().add(timeLeft);
		layers.add(timeBox);
		stopWatch = new Text(timeSinceLastSolve + "     ");
		stopWatch.setStyle("-fx-font-size: " + hexMargin*2 + "px; -fx-fill: #fff;");
		stopWatch.setTranslateY(hexMargin);
		VBox stopWatchBox = new VBox();
		stopWatchBox.setAlignment(Pos.TOP_RIGHT);
		stopWatchBox.getChildren().add(stopWatch);
		layers.add(stopWatchBox);
		layers.add(hexGrid);
		hexGrid.randomizeHexes(numbersShown);
		flashTime = 1000;
		startTime = 0;
		blankTime = 500;
		
		init();
	}
	
	protected void customUpdate(long timeElapsed){
		if(true){
			timeRemaining -= timeElapsed/Math.pow(10, 3);
			System.out.println(timeRemaining);
			timeLeft.setText("     " + (int)Math.floor(timeRemaining));
			
			blankTime += timeElapsed;
			
			if(!gameStart && blankTime > 500){
				startTime += timeElapsed;
				hexGrid.showTexts();
			}
			
			if(!gameStart && startTime > flashTime){
				hexGrid.showShapes();
				hexGrid.hideTexts();
				gameStart = true;
				timeSinceLastSolve = 0;
			}
			
			if(gameStart && !solved)
				timeSinceLastSolve += timeElapsed;
			stopWatch.setText((long)timeSinceLastSolve + "      ");
			
			checkNumbersPressed();
			
			if(solved)
				onSolved();
		}
	}
	private void checkNumbersPressed(){
		numbersPressed = hexGrid.getNumbersPressed();
		checkForIncorrectSequence();
	}
	private void checkForIncorrectSequence(){
		solved = true;
		for(int i = 0; i < numbersShown; i++){
			if(!numbersPressed[i] || !numbersPressed[Math.max(0, i-1)]){
				solved = false;
			}
			if(numbersPressed[i] && !numbersPressed[Math.max(0, i-1)]){
				solved = false;
				onLost();
				break;
			}
		}
	}
	private void onSolved(){
		numbersShown = Math.min(3, numbersShown + 1);
		hexGrid.randomizeHexes(numbersShown);
		flashTime = (float) Math.max(250, flashTime*0.75);
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
		gameStart = false;
		startTime = blankTime = 0;
		solved = false;
		solveTimes.add(timeSinceLastSolve);
		
		hexGrid.hideShapes();
		hexGrid.hideTexts();
	}
	private void onLost(){
	}
}
