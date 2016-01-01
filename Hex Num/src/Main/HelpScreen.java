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
			"Getting started is easy. Once you've started a game, numbers while flash"+
					" on the screen, then be replaced by Hexagons. To win, you just remember"+
					" the numbers' positions and tap their respective Hexagons in sequential order.",
			"This is as much a challenging game as it is a memory trainer. The point of this game"+
					" is primarily to train your memory reflex. This means your ability to scan"+
					" something and remember it's information. I could go on a tangent and write a"+
					" paper about memory and how this game will positively affect it, but the results"+
					" can speak for themselves.",
			"There were two reasons this game was made:"+
					"\n     1. To get acquainted with programming in Java/JavaFX."+
					"\n     2. I was inspired by an awesome video of a monkey."+
					"\n        (YouTube 'Amazing Monkey Memory Test')"
			};
	public HelpScreen(int width, int height){
		super(width, height);
		
		int hexMargin = width/32;
		
		layers.add(new VBox(hexMargin*2));
		
		HBox titleBox = new HBox(hexMargin*2);
		
		Hexagon backHex = new Hexagon("", hexMargin*2);
			backHex.setImage("Assets/RIGHT_ARROW.png");
			backHex.flipImage(true, false);
			backHex.setOnAction(e -> Main.setScene(Main.titleScene));
		Text titleText = new Text("Help Screen");
		titleText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, hexMargin*3));
		titleBox.getChildren().addAll(backHex, titleText);
		titleBox.setTranslateX(hexMargin*2);
		titleBox.setTranslateY(hexMargin);
		
		VBox helpContent = new VBox(10);
		helpContent.setTranslateX(hexMargin*2);
		
		Text s1 = new Text("How do I play?");
		s1.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));

		Text s1Text = new Text(content[0]);
		s1Text.setTranslateX(hexMargin);
		s1Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		s1Text.setWrappingWidth(width-hexMargin*4);
		
		Text s2 = new Text("What is this for?");
		s2.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		Text s2Text = new Text(content[1]);
		s2Text.setTranslateX(hexMargin);
		s2Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		s2Text.setWrappingWidth(width-hexMargin*4);
		
		Text s3 = new Text("Why was this made?");
		s3.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		
		Text s3Text = new Text(content[2]);
		s3Text.setTranslateX(hexMargin);
		s3Text.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
		s3Text.setWrappingWidth(width-hexMargin*4);
		Text emptyText = new Text();
		
		helpContent.getChildren().addAll(s1, s1Text, s2, s2Text, s3, s3Text, emptyText);
		ScrollPane contentContainer = new ScrollPane(helpContent);
		VBox.setVgrow(contentContainer, Priority.ALWAYS);
		contentContainer.setFitToWidth(true);
		contentContainer.setHbarPolicy(ScrollBarPolicy.NEVER);
		contentContainer.getStyleClass().add("helpScreen");
		

		layers.get(0).getChildren().addAll(titleBox, contentContainer);
		init();
	}
}
