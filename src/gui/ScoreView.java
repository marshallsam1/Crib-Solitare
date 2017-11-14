package gui;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.GameModel;
import model.GameModelListener;

/**
 * GUI element to show the score.
 */
public class ScoreView extends Text implements GameModelListener{
	
	ScoreView(){
		
		buildLayout();
		GameModel.instance().addListener(this);
		
	}
	
	/**
	 * Draws the score counter.
	 */
	void buildLayout(){
		setText("Score: " + GameModel.instance().getPoints());
		setX(10);
		setY(10);
		setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
	}

	@Override
	public void gameStateChanged(){
		buildLayout();
		
	}

}
