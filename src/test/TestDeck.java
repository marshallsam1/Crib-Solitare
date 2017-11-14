package test;

import java.lang.reflect.Field;
import java.util.Stack;

import cards.Card;
import cards.Card.Rank;
import cards.Card.Suit;
import cards.Deck;

public class TestDeck extends Deck{
	@Override
	public void shuffle(){
		try {
			Field deckField = Deck.class.getDeclaredField("aCards");
			deckField.setAccessible(true);
			
			Stack<Card> tester = new Stack<>();
			
			tester.push(Card.get(Rank.FIVE,Suit.SPADES));
			
			tester.push(Card.get(Rank.ACE,Suit.SPADES));
			tester.push(Card.get(Rank.FIVE, Suit.CLUBS));
			tester.push(Card.get(Rank.FOUR, Suit.DIAMONDS));
			tester.push(Card.get(Rank.SIX, Suit.CLUBS));
			tester.push(Card.get(Rank.THREE, Suit.DIAMONDS));
			
			tester.push(Card.get(Rank.TWO, Suit.DIAMONDS));
			tester.push(Card.get(Rank.SEVEN, Suit.HEARTS));
			tester.push(Card.get(Rank.TEN, Suit.DIAMONDS));
			tester.push(Card.get(Rank.QUEEN, Suit.CLUBS));
			tester.push(Card.get(Rank.THREE, Suit.CLUBS));
			
			tester.push(Card.get(Rank.FOUR, Suit.CLUBS));
			tester.push(Card.get(Rank.EIGHT, Suit.CLUBS));
			tester.push(Card.get(Rank.SEVEN, Suit.DIAMONDS));
			tester.push(Card.get(Rank.TEN, Suit.CLUBS));
			tester.push(Card.get(Rank.TWO, Suit.HEARTS));
			
			tester.push(Card.get(Rank.THREE, Suit.SPADES));
			tester.push(Card.get(Rank.JACK, Suit.SPADES));
			tester.push(Card.get(Rank.ACE, Suit.DIAMONDS));
			tester.push(Card.get(Rank.SEVEN, Suit.SPADES));
			tester.push(Card.get(Rank.FOUR, Suit.SPADES));
			
			deckField.set(this,tester);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Method reset;
//		try {
//			reset = Deck.class.getDeclaredMethod("reset");
//			reset.setAccessible(true);
//			reset.invoke(this, new Object[0]);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
