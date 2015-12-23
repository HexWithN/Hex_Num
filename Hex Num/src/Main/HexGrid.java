package Main;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HexGrid extends VBox{
	Hexagon[] hexagons; // The Array of hexagons
	VBox hexRows; // The ArrayList of rows. Gets final say in what is shown
	HBox row1, row2, row3; // The rows that will hold the hexagons. Decides what hexes are shown
	
	int hexMargin; // The margin between hexagons/rows
	boolean[] numbersPressed; // Boolean Array that gathers what numbers where chosen in game 
	boolean solved;
	
	public HexGrid(int hexMargin){
		super();

		this.hexMargin = hexMargin;
		this.setSpacing(-hexMargin);
		this.setAlignment(Pos.CENTER);
		
		hexRows = new VBox(-hexMargin);
		
		numbersPressed = new boolean[9];
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
		
		hexagons = new Hexagon[9];
		for(int i = 0; i < 9; i++){
			int index = i;
			
			Hexagon h = new Hexagon("" + (index+1), hexMargin*3);
			hexagons[index] = h;
			h.setOnAction(e -> {
				hexagons[index].hideShape();
				hexagons[index].showText();
				numbersPressed[index] = true;
			});
			h.getStyleClass().add("gameHex");
		}
		
		row1 = new HBox(hexMargin);
			row1.setAlignment(Pos.CENTER);
		row2 = new HBox(hexMargin);
			row2.setAlignment(Pos.CENTER);
		row3 = new HBox(hexMargin);
			row3.setAlignment(Pos.CENTER);
		
		getChildren().add(hexRows);
	}

	public void showText(int index){
		hexagons[index].showText();
	}
	public void showShape(int index){
		hexagons[index].showShape();
	}
	public void hideTexts(){
		for(Hexagon h:hexagons)
			h.hideText();
	}
	public void showTexts(){
		for(Hexagon h:hexagons)
			h.showText();
	}
	public void hideShapes(){
		for(Hexagon h:hexagons)
			h.hideShape();
	}
	public void showShapes(){
		for(Hexagon h:hexagons)
			h.showShape();
	}
	
	public void randomizeHexes(int numHexes){
		for(Hexagon h:hexagons){
			h.hideShape();
			h.showText();
		}
		
		hexRows.getChildren().clear();
		row1.getChildren().clear();
		row2.getChildren().clear();
		row3.getChildren().clear();

		showPattern(numHexes);
		
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
	}
	public void showPattern(int numHexes){
		switch(numHexes){
		case 1:
			row1.getChildren().add(hexagons[0]);
			hexRows.getChildren().add(row1);
			break;
		case 2:
			case2();
			break;
		case 3:
			case3();
			break;
		case 4:
			case4();
			break;
		case 5:
			case5();
			break;
		case 6:
			case6();
			break;
		case 7:
			case7();
			break;
		case 8:
			case8();
			break;
		case 9:
			case9();
			break;
		}
	}
	private void case2(){
		int pattern = (int)(Math.random()*3);
		switch(pattern){
		case 0:
			row1.getChildren().addAll(hexagons[0], hexagons[1]);
			row1.setTranslateX(0);
			hexRows.getChildren().add(row1);
			hexRows.setSpacing(hexMargin*0.5);
			break;
		case 1:
			row1.getChildren().add(hexagons[0]);
			row1.setTranslateX(0);
			row2.getChildren().add(hexagons[1]);
			row2.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2);
			hexRows.setSpacing(hexMargin);
			break;
		default:
			row1.getChildren().add(hexagons[0]);
			row1.setTranslateX(hexMargin*2);
			row2.getChildren().add(hexagons[1]);
			row2.setTranslateX(-hexMargin*2);
			hexRows.getChildren().addAll(row1, row2);
			break;
		}
	}
	private void case3(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*3);
		switch(pattern){
		case 0:
			row1.getChildren().addAll(hexagons[0], hexagons[1], hexagons[2]);
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			row1.setSpacing(hexMargin);
			hexRows.getChildren().add(row1);
			hexRows.setSpacing(hexMargin);
			break;
		case 1:
			row1.getChildren().add(hexagons[0]);
			row1.setTranslateX(0);
			row2.getChildren().add(hexagons[1]);
			row2.setTranslateX(0);
			row3.getChildren().add(hexagons[2]);
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(hexMargin);
			break;
		default:
			row1.getChildren().add(hexagons[0]);
			row1.setTranslateX(hexMargin*2.5);
			row2.getChildren().add(hexagons[1]);
			row2.setTranslateX(0);
			row3.getChildren().add(hexagons[2]);
			row3.setTranslateX(-hexMargin*2.5);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(-hexMargin);
			break;
		}
	}
	private void case4(){
		
	}
	private void case5(){
		
	}
	private void case6(){
		
	}
	private void case7(){
		
	}
	private void case8(){
		
	}
	private void case9(){
		
	}
	public boolean[] getNumbersPressed(){
		return numbersPressed;
	}
}
