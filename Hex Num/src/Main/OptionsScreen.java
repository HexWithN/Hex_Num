package Main;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OptionsScreen extends PlayScreen{
	Slider brightnessSlider, volumeSlider;
	CheckBox volumeCheckbox;
	ColorPicker colorPicker;
	
	public OptionsScreen(int width, int height){
		super(width, height);
		
		int hexMargin = width/32;
		
		layers.add(new VBox(hexMargin*2));
		
		
		HBox titleBox = new HBox(hexMargin*2);
		
		Hexagon backHex = new Hexagon("", hexMargin*2);
			backHex.setImage("Assets/RIGHT_ARROW.png");
			backHex.flipImage(true, false);
			backHex.setOnAction(e -> Main.setScene(Main.titleScene));
		Text titleText = new Text("Options");
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, hexMargin*3));
		titleBox.getChildren().addAll(backHex, titleText);
		titleBox.setTranslateX(hexMargin*2);
		titleBox.setTranslateY(hexMargin);
		
		VBox brightnessBox = new VBox(20);
		brightnessSlider = new Slider(0, 1, 1);
		Text brightnessText = new Text("Brightness");
		brightnessText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		brightnessBox.getChildren().addAll(brightnessText, brightnessSlider);
		brightnessBox.setTranslateX(hexMargin);
		brightnessBox.setMaxWidth(width - hexMargin*4);
		
		VBox volumeBox = new VBox(20);
		volumeSlider = new Slider(0, 1, 1);
		volumeCheckbox = new CheckBox();
		volumeCheckbox.setSelected(true);
		Text volumeText = new Text("Volume");
		volumeText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		HBox volumeContainer = new HBox(20);
		volumeContainer.getChildren().addAll(volumeText, volumeCheckbox);
		volumeBox.getChildren().addAll(volumeContainer, volumeSlider);
		volumeBox.setTranslateX(hexMargin);
		volumeBox.setMaxWidth(width - hexMargin*4);
		
		colorPicker = new ColorPicker(Color.web("0x55aaffff"));//Main.color));
		colorPicker.setTranslateX(hexMargin);
		colorPicker.setMaxWidth(width - hexMargin*4);
		
		layers.get(0).getChildren().addAll(titleBox, brightnessBox, volumeBox, colorPicker);
		init();
	}

	protected void customUpdate(long timeElapsed){
		Main.brightness = brightnessSlider.getValue();
		Main.volumeEnabled = volumeCheckbox.isSelected();
		Main.volume = volumeSlider.getValue();

		String pickedColor = colorPicker.getValue().toString();
		pickedColor = "#" + pickedColor.trim().substring(2, pickedColor.length() - 2);
		if(Main.window.getScene().getStylesheets().size() > 0){
			for(int i = 0; i < Main.scenes.size(); i++){
				Node root = Main.scenes.get(i).lookup(".root");
				root.setStyle("myColor: " + pickedColor + ";");
			}
		}
		Main.color = pickedColor;
	}
}
