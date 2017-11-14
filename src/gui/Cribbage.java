package gui;

import model.HandStackManager.HandIndex;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main application: brings the GUI elements together to launch the game.
 */
public class Cribbage extends Application{
	private static final int WIDTH = 640;
	private static final int HEIGHT = 400;
	private static final int MARGIN_OUTER = 10;
	private static final String TITLE = "Cribbage";
	private static final String VERSION = "0.1";
    
    private DeckView deckView = new DeckView();
    private CardStack[] hands = new CardStack[HandIndex.values().length];
    private ScoreView score = new ScoreView();
    
	/**
	 * Launches the application.
	 * @param pArgs : No input arguments
	 */
	public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage pPrimaryStage){
		pPrimaryStage.setTitle(TITLE + " " + VERSION ); 
           
        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: green;");
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));
        
        
        root.add(deckView, 0, 0);
        root.add(score, 0, 5);
        
        for(HandIndex idx : HandIndex.values()){
        	hands[idx.ordinal()] = new CardStack(idx);
        	root.add(hands[idx.ordinal()], 3+idx.ordinal(), 0);
        }
        
        pPrimaryStage.setResizable(false);
        pPrimaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        pPrimaryStage.show();
    }
}

