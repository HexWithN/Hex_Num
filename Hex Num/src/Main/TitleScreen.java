package Main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitleScreen extends PlayScreen{
	
	public TitleScreen(int width, int height, String title){
		super(width, height);
		
		layers.add(new VBox(50));
		
		Text titleText = new Text(title);
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 75));
		titleText.setTranslateX(50);
		
		Text authorText = new Text("Nazaire Shabazz");
		authorText.setFont(Font.font("Helvetica", FontWeight.EXTRA_LIGHT, 25));
		authorText.setTranslateX(50);
		authorText.setTranslateY(-50);
		
		int hexMargin = 25;
		//Make a hex grid class
		HBox topHexes = new HBox(hexMargin);
		topHexes.getStyleClass().add("hexRow");
		Hexagon startHex = new Hexagon("START", true, false);
		Hexagon achievementHex = new Hexagon("ACH.", true, false);
		Hexagon websiteHex = new Hexagon("ME!", true, false);
		topHexes.getChildren().addAll(startHex, achievementHex, websiteHex);
		topHexes.setTranslateX(websiteHex.radius*0.5);
		
		HBox bottomHexes = new HBox(hexMargin);
		bottomHexes.getStyleClass().add("hexRow");
		Hexagon optionHex = new Hexagon("OPT.", true, false);
		Hexagon helpHex = new Hexagon("HELP", true, false);
			helpHex.setOnAction(e -> Main.setScene(Main.helpScene));
		Hexagon exitHex = new Hexagon("EXIT", true, false);
			exitHex.setOnAction(e -> Main.closeProgram());
		bottomHexes.getChildren().addAll(optionHex, helpHex, exitHex);
		bottomHexes.setTranslateX(-websiteHex.radius*0.5);
		
		VBox hexContainer = new VBox(-hexMargin);
		hexContainer.getChildren().addAll(topHexes, bottomHexes);
		
		layers.get(0).getChildren().addAll(titleText, authorText, hexContainer);
		
		init();
	}
}
