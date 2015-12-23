package Main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitleScreen extends PlayScreen{
	
	public TitleScreen(int width, int height, String title){
		super(width, height);

		int hexMargin = width/32;
		
		layers.add(new VBox(hexMargin*2));
		
		Text titleText = new Text(title);
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, hexMargin*3));
		titleText.setTranslateX(hexMargin*2);
		
		Text authorText = new Text("Nazaire Shabazz");
		authorText.setFont(Font.font("Helvetica", FontWeight.EXTRA_LIGHT, hexMargin));
		authorText.setTranslateX(hexMargin*2);
		authorText.setTranslateY(-hexMargin*2);
		
		//Make a hex grid class
		HBox topHexes = new HBox(hexMargin);
		topHexes.getStyleClass().add("hexRow");
		Hexagon startHex = new Hexagon("START", hexMargin*3);
			startHex.setOnAction(e -> Main.setScene(Main.gameScene));
		Hexagon achievementHex = new Hexagon("ACH.", hexMargin*3);
		Hexagon websiteHex = new Hexagon("ME!", hexMargin*3);
		topHexes.getChildren().addAll(startHex, achievementHex, websiteHex);
		topHexes.setTranslateX(websiteHex.radius*0.5);
		
		HBox bottomHexes = new HBox(hexMargin);
		bottomHexes.getStyleClass().add("hexRow");
		Hexagon optionHex = new Hexagon("OPT.", hexMargin*3);
		Hexagon helpHex = new Hexagon("HELP", hexMargin*3);
			helpHex.setOnAction(e -> Main.setScene(Main.helpScene));
		Hexagon exitHex = new Hexagon("EXIT", hexMargin*3);
			exitHex.setOnAction(e -> Main.closeProgram());
		bottomHexes.getChildren().addAll(optionHex, helpHex, exitHex);
		bottomHexes.setTranslateX(-websiteHex.radius*0.5);
		
		VBox hexContainer = new VBox(-hexMargin);
		hexContainer.getChildren().addAll(topHexes, bottomHexes);
		
		layers.get(0).getChildren().addAll(titleText, authorText, hexContainer);
		
		init();
	}
}
