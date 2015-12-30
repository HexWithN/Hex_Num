package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HexGrid extends VBox{
	ArrayList<Hexagon> shuffledHexagons;
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

		shuffledHexagons = new ArrayList<Hexagon>();
		
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
	public void resetNumbersPressed(){
		for(int i = 0; i < numbersPressed.length; i++)
			numbersPressed[i] = false;
	}
	public void toggleClass(String cssClass){
		if(getStyleClass().contains(cssClass))
			getStyleClass().remove(cssClass);
		else
			getStyleClass().add(cssClass);
	}
	public void removeClass(String cssClass){
		if(getStyleClass().contains(cssClass))
			getStyleClass().remove(cssClass);
	}
	public void randomizeHexes(int numHexes){
		for(Hexagon h:hexagons){
			h.hideShape();
			h.showText();
		}
		
		shuffledHexagons.clear();
		for(int i = 0; i < numHexes; i++)
			shuffledHexagons.add(hexagons[i]);
		
		long seed = System.nanoTime();
		Collections.shuffle(shuffledHexagons, new Random(seed));
		
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
			row1.getChildren().add(shuffledHexagons.get(0));
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
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1));
			row1.setTranslateX(0);
			hexRows.getChildren().add(row1);
			hexRows.setSpacing(0);
			break;
		case 1:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(0);
			row2.getChildren().add(shuffledHexagons.get(1));
			row2.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2);
			hexRows.setSpacing(hexMargin);
			break;
		default:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(hexMargin*2);
			row2.getChildren().add(shuffledHexagons.get(1));
			row2.setTranslateX(-hexMargin*2);
			hexRows.getChildren().addAll(row1, row2);
			hexRows.setSpacing(-hexMargin);
			break;
		}
	}
	private void case3(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*3);
		switch(pattern){
		case 0:
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			hexRows.getChildren().add(row1);
			hexRows.setSpacing(hexMargin);
			break;
		case 1:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(0);
			row2.getChildren().add(shuffledHexagons.get(1));
			row2.setTranslateX(0);
			row3.getChildren().add(shuffledHexagons.get(2));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(hexMargin);
			break;
		default:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(hexMargin*2.5);
			row2.getChildren().add(shuffledHexagons.get(1));
			row2.setTranslateX(0);
			row3.getChildren().add(shuffledHexagons.get(2));
			row3.setTranslateX(-hexMargin*2.5);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(-hexMargin);
			break;
		}
	}
	private void case4(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*4);
		switch(pattern){
		case 0:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(1), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			row3.getChildren().add(shuffledHexagons.get(2));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(0);
			break;
		case 1:
			row1.getChildren().add(shuffledHexagons.get(0));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(1), shuffledHexagons.get(2), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(hexMargin);
			break;
		default:
			boolean offset = Math.random() < 0.5;
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(offset ? hexMargin*2 : 0);
			row2.getChildren().addAll(shuffledHexagons.get(2), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(offset ? -hexMargin*2 : 0);
			hexRows.getChildren().addAll(row1, row2);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(offset ? -hexMargin : hexMargin);
			break;
		}
	}
	private void case5(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*2);
		switch(pattern){
		case 0:
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(4));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(1), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			row3.getChildren().add(shuffledHexagons.get(2));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(0);
			break;
		default:
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(4));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(1), shuffledHexagons.get(2), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(-hexMargin);
			break;
		}
	}
	private void case6(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*3);
		switch(pattern){
		case 0:
			boolean offset = Math.random() < 0.5;
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(offset ? -hexMargin : 0);
			row2.getChildren().addAll(shuffledHexagons.get(3), shuffledHexagons.get(4), shuffledHexagons.get(5));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(offset ? hexMargin : 0);
			hexRows.getChildren().addAll(row1, row2);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(offset ? -hexMargin : hexMargin);
			break;
		case 1:
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(2), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			row3.getChildren().addAll(shuffledHexagons.get(4), shuffledHexagons.get(5));
			FXCollections.shuffle(row3.getChildren(), new Random(seed));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(hexMargin);
			break;
		default:
			boolean upsideDown = Math.random() < 0.5;
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(3), shuffledHexagons.get(4));
			row2.setTranslateX(0);
			row3.getChildren().add(shuffledHexagons.get(5));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(upsideDown ? row1 : row3, row2, upsideDown ? row3 : row1);
			hexRows.setSpacing(-hexMargin);
			break;
		}
	}
	private void case7(){
		long seed = System.nanoTime();
		int pattern = (int)(Math.random()*2);
		switch(pattern){
		case 0:
			boolean offset = Math.random() < 0.5;
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(offset ? -hexMargin : 0);
			row2.getChildren().addAll(shuffledHexagons.get(3), shuffledHexagons.get(4), shuffledHexagons.get(5));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(offset ? hexMargin : 0);
			row3.getChildren().add(shuffledHexagons.get(6));
			row3.setTranslateX(offset ? -hexMargin : 0);
			hexRows.getChildren().addAll(row1, row2, row3);
			if(!offset)
				FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(offset ? -hexMargin : hexMargin);
			break;
		default:
			row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1));
			FXCollections.shuffle(row1.getChildren(), new Random(seed));
			row1.setTranslateX(0);
			row2.getChildren().addAll(shuffledHexagons.get(2), shuffledHexagons.get(3));
			FXCollections.shuffle(row2.getChildren(), new Random(seed));
			row2.setTranslateX(0);
			row3.getChildren().addAll(shuffledHexagons.get(4), shuffledHexagons.get(5), shuffledHexagons.get(6));
			FXCollections.shuffle(row3.getChildren(), new Random(seed));
			row3.setTranslateX(0);
			hexRows.getChildren().addAll(row1, row2, row3);
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
			hexRows.setSpacing(hexMargin);
			break;
		}
	}
	private void case8(){
		long seed = System.nanoTime();
		boolean offset = Math.random() < 0.5;
		row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
		FXCollections.shuffle(row1.getChildren(), new Random(seed));
		row1.setTranslateX(offset ? -hexMargin : 0);
		row2.getChildren().addAll(shuffledHexagons.get(3), shuffledHexagons.get(4), shuffledHexagons.get(5));
		FXCollections.shuffle(row2.getChildren(), new Random(seed));
		row2.setTranslateX(offset ? hexMargin : 0);
		row3.getChildren().addAll(shuffledHexagons.get(6), shuffledHexagons.get(7));
		FXCollections.shuffle(row3.getChildren(), new Random(seed));
		row3.setTranslateX(offset ? -hexMargin : 0);
		hexRows.getChildren().addAll(row1, row2, row3);
		if(!offset)
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
		hexRows.setSpacing(offset ? -hexMargin : hexMargin);
	}
	private void case9(){
		long seed = System.nanoTime();
		boolean offset = Math.random() < 0.5;
		row1.getChildren().addAll(shuffledHexagons.get(0), shuffledHexagons.get(1), shuffledHexagons.get(2));
		FXCollections.shuffle(row1.getChildren(), new Random(seed));
		row1.setTranslateX(offset ? -hexMargin : 0);
		row2.getChildren().addAll(shuffledHexagons.get(3), shuffledHexagons.get(4), shuffledHexagons.get(5));
		FXCollections.shuffle(row2.getChildren(), new Random(seed));
		row2.setTranslateX(offset ? hexMargin : 0);
		row3.getChildren().addAll(shuffledHexagons.get(6), shuffledHexagons.get(7), shuffledHexagons.get(8));
		FXCollections.shuffle(row3.getChildren(), new Random(seed));
		row3.setTranslateX(offset ? -hexMargin : 0);
		hexRows.getChildren().addAll(row1, row2, row3);
		if(!offset)
			FXCollections.shuffle(hexRows.getChildren(), new Random(seed));
		hexRows.setSpacing(offset ? -hexMargin : hexMargin);
	}
	
	public boolean[] getNumbersPressed(){
		return numbersPressed;
	}
}
