package Main;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayScreen extends StackPane{
	int width;
	int height;
	
	double opacity;
	long lastTime;
	boolean firstUpdate;
	protected ArrayList<VBox> layers;
	
	VBox brightnessLayer;
	
	public PlayScreen(int width, int height){
		super();
		
		this.width = width;
		this.height = height;
		firstUpdate = true;
		lastTime = System.nanoTime();
		opacity = 1;
		layers = new ArrayList<VBox>();
		brightnessLayer = new VBox();
	}
	
	protected void init(){
		for(VBox v: layers)
			getChildren().add(v);
		
		brightnessLayer.setStyle("-fx-background-color: #000;");
		brightnessLayer.setDisable(true);
		getChildren().add(brightnessLayer);
		
		this.getStyleClass().add("screen");
		lastTime = System.nanoTime();
	}
	
	public void update(long currentTime){
		long timeElapsed = firstUpdate? 0:(long)((currentTime - lastTime)/Math.pow(10, 6));
		lastTime = currentTime;
		
		innerUpdate();
		customUpdate(timeElapsed);
		
		firstUpdate = false;
	}
	
	protected void innerUpdate(){
		brightnessLayer.setOpacity(0.5 * (1 - Main.brightness));
	}
	
	protected void customUpdate(long timeElapsed){
		
	}
}