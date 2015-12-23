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
	
	public PlayScreen(int width, int height){
		super();
		
		this.width = width;
		this.height = height;
		firstUpdate = true;
		lastTime = System.nanoTime();
		opacity = 1;
		layers = new ArrayList<VBox>();
	}
	
	protected void init(){
		for(VBox v: layers)
			getChildren().add(v);
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
		setOpacity(opacity);
	}
	
	protected void customUpdate(long timeElapsed){
		
	}
}