package Main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AchievementScreen extends PlayScreen{
	public VBox achievements;
	
	public AchievementScreen(int width, int height){
		super(width, height);
		
		int hexMargin = width/32;
		
		layers.add(new VBox(hexMargin*2));
		
		
		HBox titleBox = new HBox(hexMargin*2);
		
		Hexagon backHex = new Hexagon("", hexMargin*2);
			backHex.setImage("Assets/RIGHT_ARROW.png");
			backHex.flipImage(true, false);
			backHex.setOnAction(e -> Main.setScene(Main.titleScene));
		Text titleText = new Text("Achievements");
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, hexMargin*3));
		titleBox.getChildren().addAll(backHex, titleText);
		titleBox.setTranslateX(hexMargin*2);
		titleBox.setTranslateY(hexMargin);
		
		achievements = new VBox(20);
		for(int i = 0; i < AchievementTracker.length(); i++){
		}
		
		layers.get(0).getChildren().addAll(titleBox, achievements);
		init();
	}
	
	protected void customUpdate(){
		
	}
}
