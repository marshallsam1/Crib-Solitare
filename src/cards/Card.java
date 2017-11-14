package cards;

/**
 * Simple card representation.
 * Immutable: flyweight implementation
 */
public final class Card{
	// Ordered by suit, then rank
	private static final Card[][] CARDS = new Card[Suit.values().length][];
	
	static{
		for( Suit suit : Suit.values() ){
			CARDS[suit.ordinal()] = new Card[Rank.values().length];
			for( Rank rank : Rank.values() ){
				CARDS[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
			}
		}
	}
	
	/**
	 * Card rank
	 */
	public enum Rank{ 
		ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING 
	}
	
	/**
	 * Card suit
	 */
	public enum Suit { 
		CLUBS, DIAMONDS, HEARTS, SPADES 
	}
	
	private final Rank rank;
	private final Suit suit;
	
	
	/**
	 * @param rank
	 * @param suit
	 */
	private Card(Rank rank, Suit suit ){
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * @param rank : rank of card.
	 * @param suit : suit of card.
	 * @return the card with rank and suit of the input.
	 */
	public static Card get(Rank rank, Suit suit){
		assert rank != null && suit != null;
		return CARDS[suit.ordinal()][rank.ordinal()];
	}
	
	/**
	 * @return the rank of the card.
	 */
	public Rank getRank(){
		return rank;
	}
	
	/**
	 * @return the suit of the card. 
	 */
	public Suit getSuit(){
		return suit;
	}
	
	@Override
	public String toString(){
		return rank + " of " + suit;
	}
}
