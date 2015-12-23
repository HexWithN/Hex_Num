package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Hexagon extends StackPane{
	int radius = 75;
	boolean pressed;

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
		this.hexText = new Text(txt);
		this.radius = 75;
		init();
	}
	public Hexagon(String txt, int radius){
		super();
		this.hexText = new Text(txt);
		this.radius = radius;
		init();
	}
	
	private void init(){
		hexText.setStyle("-fx-font-size: " + this.radius/2 + ";");
		
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
		
		hexButton = new Button("");
		
		hexButton.setMinWidth(radius * Math.sin(2 * Math.PI * 1 / 6) -
					radius * Math.sin(2 * Math.PI * 4 / 6));
		hexButton.setMinHeight(radius * Math.cos(2 * Math.PI * 0 / 6) -
				radius * Math.cos(2 * Math.PI * 3 / 6));
		
		hexButton.getStyleClass().add("hexButton");
		getChildren().add(hexButton);
	}

	public void showText(){
		if(hexText.getStyleClass().contains("hidden"))
			hexText.getStyleClass().remove("hidden");
	}
	public void showShape(){
		if(hexagonShape.getStyleClass().contains("hidden"))
			hexagonShape.getStyleClass().remove("hidden");
	}
	public void hideText(){
		if(!hexText.getStyleClass().contains("hidden"))
			hexText.getStyleClass().add("hidden");
	}
	public void hideShape(){
		if(!hexagonShape.getStyleClass().contains("hidden"))
			hexagonShape.getStyleClass().add("hidden");
	}

	public void setText(String text){
		hexText.setText(text);
	}
	public void setSize(int size){
		radius = size;
		init();
	}
	public String getText(){
		return hexText.getText();
	}
	public int getSize(){
		return radius;
	}
	public final void setOnAction(EventHandler<ActionEvent> e){
		hexButton.setOnAction(e);
	}
}
