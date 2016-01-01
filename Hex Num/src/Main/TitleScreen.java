package Main;

import java.awt.Desktop;
import java.net.URI;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitleScreen extends PlayScreen{
	Alert alert;
	
	public TitleScreen(int width, int height, String title){
		super(width, height);

		alert = new Alert(AlertType.CONFIRMATION, "This will open a link to my website, okay?");
		alert.setHeaderText(null);
		
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
		Hexagon achievementHex = new Hexagon("", hexMargin*3);
			achievementHex.setOnAction(e -> Main.setScene(Main.achievementScene));
			achievementHex.setImage("Assets/ACHIEVEMENTS.png");
		Hexagon websiteHex = new Hexagon("", hexMargin*3);
			websiteHex.setImage("Assets/ME.png");
			websiteHex.setOnAction(e -> {
				Optional<ButtonType> result = alert.showAndWait();
				 if (result.isPresent() && result.get() == ButtonType.OK) {
					if(Desktop.isDesktopSupported()){
						try{
							Desktop.getDesktop().browse(new URI("www.nqshabazz.me"));
						}catch (Exception e1){
							e1.printStackTrace();
						}
					}
				 }
			});
		topHexes.getChildren().addAll(startHex, achievementHex, websiteHex);
		topHexes.setTranslateX(websiteHex.radius*0.5);
		
		HBox bottomHexes = new HBox(hexMargin);
		bottomHexes.getStyleClass().add("hexRow");
		Hexagon optionHex = new Hexagon("", hexMargin*3);
			optionHex.setImage("Assets/OPTIONS.png");
			optionHex.setOnAction(e -> Main.setScene(Main.optionsScene));
		Hexagon helpHex = new Hexagon("", hexMargin*3);
			helpHex.setImage("Assets/HELP.png");
			helpHex.setOnAction(e -> Main.setScene(Main.helpScene));
		Hexagon exitHex = new Hexagon("", hexMargin*3);
			exitHex.setImage("Assets/EXIT.png");
			exitHex.setOnAction(e -> Main.closeProgram());
		bottomHexes.getChildren().addAll(optionHex, helpHex, exitHex);
		bottomHexes.setTranslateX(-websiteHex.radius*0.5);
		
		VBox hexContainer = new VBox(-hexMargin);
		hexContainer.getChildren().addAll(topHexes, bottomHexes);
		
		layers.get(0).getChildren().addAll(titleText, authorText, hexContainer);
		
		init();
	}
}
