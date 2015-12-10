package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Hexagon extends StackPane{
	int radius = 75;
	boolean hasButton;

	Text hexText;
	Polygon hexagonShape;
	Button hexButton;

	public Hexagon(){
		super();
		hexText = new Text("");
		init();
	}
	public Hexagon(String txt){
		super();
		hexText = new Text(txt);
		init();
	}
	public Hexagon(String txt, boolean hasButton, boolean isGameHex){
		super();
		this.hasButton = hasButton;
		hexText = new Text(txt);
		init();
	}
	public Hexagon(String txt, boolean hasButton, boolean isGameHex, int radius){
		super();
		this.hasButton = hasButton;
		hexText = new Text(txt);
		resetSize(radius);
	}
	
	private void init(){
		hexText.setStyle("-fx-font-size: " + radius/2 + ";");
		
		//Base equation for polygon thanks to "deinst" of StackOverflow
		hexagonShape = new Polygon();
		hexagonShape.getPoints().addAll(new Double[]{
				radius * Math.sin(2 * Math.PI * 0 / 6), radius * Math.cos(2 * Math.PI * 0 / 6),
				radius * Math.sin(2 * Math.PI * 1 / 6), radius * Math.cos(2 * Math.PI * 1 / 6),
				radius * Math.sin(2 * Math.PI * 2 / 6), radius * Math.cos(2 * Math.PI * 2 / 6),
				radius * Math.sin(2 * Math.PI * 3 / 6), radius * Math.cos(2 * Math.PI * 3 / 6),
				radius * Math.sin(2 * Math.PI * 4 / 6), radius * Math.cos(2 * Math.PI * 4 / 6),
				radius * Math.sin(2 * Math.PI * 5 / 6), radius * Math.cos(2 * Math.PI * 5 / 6)
		});
		
		getStyleClass().add("hex");
		hexText.getStyleClass().add("hexText");
		hexagonShape.getStyleClass().add("hexShape");
		getChildren().clear();
		getChildren().addAll(hexagonShape, hexText);
		
		if(hasButton){
			hexButton = new Button("");
			
			hexButton.setMinWidth(radius * Math.sin(2 * Math.PI * 1 / 6) -
						radius * Math.sin(2 * Math.PI * 4 / 6));
			hexButton.setMinHeight(radius * Math.cos(2 * Math.PI * 0 / 6) -
					radius * Math.cos(2 * Math.PI * 3 / 6));
			
			hexButton.getStyleClass().add("hexButton");
			getChildren().add(hexButton);
		}
	}
	
	public void setText(String text){
		hexText.setText(text);
	}
	public void resetSize(int size){
		radius = size;
		init();
	}
	
	public final void setOnAction(EventHandler<ActionEvent> e){
		if(hasButton)
			hexButton.setOnAction(e);
	}
}
