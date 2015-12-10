package Main;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HelpScreen extends PlayScreen{
	String[] content = {
			"",
			"",
			""
			};
	public HelpScreen(int width, int height){
		super(width, height);
		
		layers.add(new VBox(50));
		
		
		HBox titleBox = new HBox(50);
		Hexagon backHex = new Hexagon("B", true, false, 50);
			backHex.setOnAction(e -> Main.setScene(Main.titleScene));
		Text titleText = new Text("Help Screen");
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 75));
		titleBox.getChildren().addAll(backHex, titleText);
		titleBox.setTranslateX(50);
		titleBox.setTranslateY(25);
		
		VBox helpContent = new VBox(10);
		helpContent.setTranslateX(50);
		
		Text s1 = new Text("How do I play?");
		s1.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));

		Text s1Text = new Text(content[0]);
		s1Text.setTranslateX(25);
		s1Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		s1Text.setWrappingWidth(width-100);
		
		Text s2 = new Text("What is this for?");
		s2.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		Text s2Text = new Text(content[1]);
		s2Text.setTranslateX(25);
		s2Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		Text s3 = new Text("Why was this made?");
		s3.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		Text s3Text = new Text(content[2]);
		s3Text.setTranslateX(25);
		s3Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		helpContent.getChildren().addAll(s1, s1Text, s2, s2Text, s3, s3Text);
		ScrollPane contentContainer = new ScrollPane(helpContent);
		VBox.setVgrow(contentContainer, Priority.ALWAYS);
		contentContainer.setFitToWidth(true);
		contentContainer.setHbarPolicy(ScrollBarPolicy.NEVER);
		contentContainer.getStyleClass().add("screen");
		

		layers.get(0).getChildren().addAll(titleBox, contentContainer);
		init();
	}
}
