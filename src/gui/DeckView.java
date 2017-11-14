package gui;

import cards.CardImages;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.GameModel;
import model.GameModelListener;

/**
 * Shows the deck, displaying the top card.
 * Updates when the top card is moved to a hand.
 */
class DeckView extends HBox implements GameModelListener{
	DeckView()	{
        final Button button = new Button();
        button.setGraphic(new ImageView(CardImages.getCard(GameModel.instance().peek())));
        button.setStyle("-fx-background-color: Blue; -fx-padding: 10, 10, 10, 10;");
        
        getChildren().add(button);
    	GameModel.instance().addListener(this);
	}
	
	@Override
	public void gameStateChanged(){
		((Button)getChildren().get(0)).setGraphic(new ImageView(CardImages.getCard(GameModel.instance().peek())));
	}
	
	public void reset(){
		getChildren().get(0).setVisible(true);
	}
}