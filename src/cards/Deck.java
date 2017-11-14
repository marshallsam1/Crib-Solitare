package cards;

import java.util.Collections;
import java.util.Stack;

import cards.Card.Rank;
import cards.Card.Suit;

public class Deck{
	private Stack<Card> cards;
	
	/**
	 * Initialized as a full deck of shuffled cards.
	 */
	public Deck(){
		cards = new Stack<Card>();
		reset();
		shuffle();
	}
	
	
	/**
	 * Reinitializes the deck to a full, shuffled state.
	 */
	private void reset(){
		cards.clear();
		for(Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                cards.add( Card.get( rank, suit ));
            }
		}
	}

	/**
	 * Shuffles the deck.
	 */
	public void shuffle(){
		reset();
		Collections.shuffle( cards );
	}
	
	/**
	 * Places card on top of the deck.
	 * @param card : adds card to the top of the deck.
	 * @pre card !=null
	 */
	public void push(Card card){
		assert card != null;
		cards.push(card);
	}
	
	/**
	 * Removes and returns the top card of the deck.
	 * @return The card drawn.
	 * @pre size() > 0
	 */
	public Card draw(){
		assert size() > 0;
		return cards.pop();
	}
	
	/**
	 * @return The top card of the deck.
	 */
	public Card peek(){
		assert size() > 0;
		return cards.peek();
	}
	
	/**
	 * @return The number of cards in the deck.
	 */
	public int size(){
		return cards.size();
	}
}
