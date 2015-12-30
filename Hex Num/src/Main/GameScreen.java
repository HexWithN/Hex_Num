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
	private boolean gameStart; //~ Has the game started?
	private boolean solved, lost; //~ Was the puzzle solved or lost?
	private boolean[] numbersPressed; //~ Keeps track of whether or not a hexagon number was pressed
	private ArrayList<Float> solveTimes;
	private Text stopWatch;
	HexGrid hexGrid; // ~ The grid of hexes. Controls what hexagons are shown, and where.
	
	public GameScreen(int width, int height){
		super(width, height);
		setAlignment(Pos.CENTER);
		
		solved = lost = false;
		difficulty = 1;
		gameStart = false;
		numbersShown = difficulty;
		timeSinceLastSolve = 0;
		solveTimes = new ArrayList<Float>();
		int hexMargin = width/32;
		hexGrid = new HexGrid(hexMargin);
		
		numbersPressed = new boolean[9];
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
		
		stopWatch = new Text(timeSinceLastSolve + "     ");
		stopWatch.setStyle("-fx-font-size: " + hexMargin*2 + "px; -fx-fill: #fff;");
		stopWatch.setTranslateY(hexMargin);
		VBox stopWatchBox = new VBox();
		stopWatchBox.setAlignment(Pos.TOP_RIGHT);
		stopWatchBox.getChildren().add(stopWatch);
		layers.add(stopWatchBox);
		layers.add(hexGrid);
		hexGrid.randomizeHexes(numbersShown);
		flashTime = 500;
		startTime = 0;
		blankTime = 500;
		
		init();
	}
	
	protected void customUpdate(long timeElapsed){
		blankTime += timeElapsed;
		
		if(!lost){			
			blankTime += timeElapsed;
			
			if(blankTime < 500 && (int)blankTime/10%5 == 0)
				hexGrid.toggleClass("green");
			
			if(solved && blankTime > 500)
				onSolved();
			
			if(!solved && !gameStart && blankTime > 1000){
				startTime += timeElapsed;
				hexGrid.showTexts();
			}
			
			if(!solved && !gameStart && startTime > flashTime){
				hexGrid.showShapes();
				hexGrid.hideTexts();
				gameStart = true;
				timeSinceLastSolve = 0;
				hexGrid.resetNumbersPressed();
			}
			
			if(!solved && gameStart){
				timeSinceLastSolve += timeElapsed;
				stopWatch.setText((long)timeSinceLastSolve + "      ");
				checkNumbersPressed();
			}
		}else{
			if(blankTime < 1500 && (int)blankTime/10%5 == 0)
				hexGrid.toggleClass("red");
			
			if(blankTime > 1500){
				onSolved();
				blankTime = 501;
			}
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
		
		if(solved || lost)
			blankTime = 0;
	}
	private void onSolved(){
		difficulty++;
		numbersShown = Math.min((difficulty+2)/3, 5);
		flashTime = (float) Math.max(125 + 2.5*difficulty, flashTime*0.9 + 2.5*difficulty);
		hexGrid.randomizeHexes(numbersShown);
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
		gameStart = false;
		startTime = 0;
		solved = lost = false;
		solveTimes.add(timeSinceLastSolve);
		
		hexGrid.hideShapes();
		hexGrid.hideTexts();
		hexGrid.removeClass("green");
		hexGrid.removeClass("red");
	}
	private void onLost(){
		lost = true;
	}
}
