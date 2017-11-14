package model;

import model.GameModelListener;
import model.HandStackManager.HandIndex;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.Deck;
import gui.CardStack;

/**
 * 
 */
public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	
	private Deck deck = new Deck();
	private HandStackManager handStacks = new HandStackManager();
	private int points = 30;
	private List<GameModelListener> listeners = new ArrayList<>();
	
	private GameModel(){
		reset();
	}
	
	
	/**
	 * @return Singleton instance of this class. 
	 */
	public static GameModel instance(){
		return INSTANCE;
	}
	
	/**
	 * Resets the game state to the start of a round.
	 */
	private void reset(){
		deck.shuffle();
		handStacks.reset();
		
		for(HandIndex idx : HandIndex.values()){
			move(idx);
		}
		
		notifyListeners();
	}
	
	/**
	 * Resets the game state to the start of a game.
	 */
	public void newGame(){
		points = 30;
		reset();
	}
	
	/**
	 * @return Current point value of the game.
	 */
	public int getPoints(){
		return points;
	}
	
	/**
	 * Pushes the game to a new round.
	 */
	private void nextRound(){
		points += handStacks.countPoints() - 30;
		CardStack.flipVisible();
		
		notifyListeners();
				
		if(points >= 60 || points <= 0){
			newGame();
			return;
		}
		CardStack.flipVisible();
		reset();
	}
	
	/**
	 * @return the top card of the deck.
	 */
	public Card peek(){
		if(deck.size() != 0) return deck.peek();
		else return null;
	}
	
	/**
	 * Removes and returns the top card of the deck.
	 * @return The top card of the deck.
	 */
	public Card draw(){
		if(deck.size() != 0) return deck.draw();
		else return null;
	}
	
	private int cardPlace = 0;
	
	/**
	 * Moves the next card from the deck to the hand specified by idx.
	 * @param idx : Index of hand to move card to.
	 * @pro idx != null
	 */
	public void move(HandIndex idx){
		assert idx != null;
		if(handStacks.legalHandMove(idx)){
			handStacks.push(deck.draw(), idx);
			cardPlace++;
			
			if(cardPlace == 4){
				move(HandIndex.CRIB);
				cardPlace = 0;
			}
			
			if(deck.size() == 32){
				nextRound();
				return;
			}
		}
		
		notifyListeners();
	}
	

	/**
	 * Adds listener to observer list.
	 * @param listener : listener to add to observer list.
	 */
	public void addListener(GameModelListener listener){
		listeners.add(listener);
	}
	
	private void notifyListeners(){
		for(GameModelListener listener : listeners){
			listener.gameStateChanged();
		}
	}

	/**
	 * Returns an array of cards in the index hand.
	 * @param idx : index of the hand to be returned.
	 * @return an array of cards in the index hand.
	 * @pre idx != null
	 */
	public Card[] getHand(HandIndex idx){
		assert idx != null;
		return handStacks.getHand(idx);
	}
	
}
