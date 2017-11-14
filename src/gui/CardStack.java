package gui;

import cards.Card;
import cards.CardImages;
import model.GameModel;
import model.HandStackManager.HandIndex;
import model.GameModelListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * GUI element to show hands.
 */
public class CardStack extends StackPane implements GameModelListener{
	private static final int PADDING = 5;
	private static final int Y_OFFSET = 17;
	
	private HandIndex index;
	private static boolean visible; // for crib use only.
	
	/**
	 * @param idx : hand index
	 */
	CardStack(HandIndex idx)	{
		index = idx;
		visible = false;
		setPadding(new Insets(PADDING));
    	setAlignment(Pos.TOP_CENTER);
    	buildLayout();
    	GameModel.instance().addListener(this);
	}
	
	/**
	 * Draws the hand that this CardStack represents.
	 */
	private void buildLayout(){
		getChildren().clear();
		
		int offset = 0;
		Card[] hand = GameModel.instance().getHand(index);
		
		// Spacer
		if( hand.length == 0 ){
			ImageView image = new ImageView(CardImages.getBlank());
			image.setVisible(true);
			getChildren().add(image);
			
			setOnMouseClicked(createClickDetectedHandler());
			
			return;
		}
		
		// Draws each card.
		for( Card card : hand){
			final ImageView image;
			
			if(index.equals(HandIndex.CRIB) && !visible){
				image = new ImageView(CardImages.getBack());
			}
			else{
				image = new ImageView(CardImages.getCard(card));
			}
			
        	image.setTranslateY(Y_OFFSET * offset);
        	offset++;
        	
        	getChildren().add(image);
        
        	setOnMouseClicked(createClickDetectedHandler());
    		
		}
    }
	
	/**
	 * Moves the next card in the deck to this CardStack.
	 * @return EventHandler to move the top card of the deck to this CardStack. 
	 */
	private EventHandler<MouseEvent> createClickDetectedHandler(){
		return new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent){
				GameModel.instance().move(index);
				mouseEvent.consume();
			}
		};
	}
	
	public static void flipVisible(){
		visible = !visible;
	}

	@Override
	public void gameStateChanged(){
		buildLayout();
	}
}