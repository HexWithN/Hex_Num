package Main;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayScreen extends StackPane{
	int width;
	int height;
	
	double opacity;
	protected ArrayList<VBox> layers;
	
	public PlayScreen(int width, int height){
		super();
		
		this.width = width;
		this.height = height;
		
		opacity = 1;
		layers = new ArrayList<VBox>();
	}
	
	protected void init(){
		for(VBox v: layers)
			getChildren().add(v);
		this.getStyleClass().add("screen");
	}
	
	public void update(){
		innerUpdate();
		customUpdate();
	}
	
	protected void innerUpdate(){
		setOpacity(opacity);
	}
	
	protected void customUpdate(){
		
	}
}