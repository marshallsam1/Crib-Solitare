package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text t = new Text();
		t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		t.setX(50);
		t.setY(130);
		t.setText("Hello World");
		
		Group root = new Group(t);
		
		Scene scene = new Scene(root, 600,300);
		
		primaryStage.setTitle("Setting Font");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
}
