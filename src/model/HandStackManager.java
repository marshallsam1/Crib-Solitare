package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cards.Card;
import cards.Card.Suit;

/**
 * Manages the hands of the game.
 */
public class HandStackManager {
	
	private Map<HandIndex,Stack<Card>> hands = new HashMap<>();
	
	/**
	 * Creates the hands with one card in them.
	 */
	HandStackManager(){
		for( HandIndex idx : HandIndex.values()){
			hands.put(idx, new Stack<Card>());
		}
	}
	
	/**
	 * Checks if the index hand has a size at most equal to the smallest hand
	 * size.
	 * @param idx : index of the hand to get a card.
	 * @return true if the index hand can gain another card.
	 * @pre idx != null
	 */
	boolean legalHandMove(HandIndex idx){
		assert idx != null;
		int handSize = hands.get(idx).size();
		if(idx.equals(HandIndex.CRIB)){
			for(HandIndex handIdx : HandIndex.values()){
				if(handIdx.equals(idx)) continue;
				if(handSize >= hands.get(handIdx).size() || handSize > 3) return false;
			}
		}
		for(HandIndex handIdx : HandIndex.values()){
			if(handIdx.equals(idx)) continue;
			if(handIdx.equals(HandIndex.CRIB)) continue;
			if(handSize > hands.get(handIdx).size() || handSize > 3) return false;
		}
		return true;
	}
	
	/**
	 * @param idx : the index of the hand to be returned.
	 * @return An array of cards of the index hand.
	 * @pre idx != null
	 */
	Card[] getHand(HandIndex idx){
		assert idx != null;
		return hands.get(idx).toArray(new Card[hands.get(idx).size()]);
	}
	
	/**
	 * @param card : Card to be added to index hand.
	 * @param idx : Index of the hand to get the card.
	 * @pre card != null, idx != null 
	 */
	void push(Card card, HandIndex idx){
		assert card != null && idx != null;
		hands.get(idx).push(card);
	}
	
	/**
	 * @param idx : Index of the hand to take a card from.
	 * @return The card on the top of index hand.
	 * @pre idx != null
	 */
	Card pop(HandIndex idx){
		assert idx != null;
		return hands.get(idx).pop();
	}
	
	/**
	 * @return The number of points currently in all hands.
	 */
	int countPoints(){
		int pointValue = 0;
		Card topCard = GameModel.instance().peek();
		
		// TODO: crib flush
		// TODO: right jack
		for(HandIndex idx : HandIndex.values()){
			
			if(flush(getHand(idx))){
				pointValue += 4;
				if(topCard.getSuit().equals(getHand(idx)[0].getSuit())){
					pointValue += 1;
				}
			}
			
			push(topCard, idx);
			Card[] hand = getHand(idx);
			int fifteenTotal2 = 0, fifteenTotal3 = 0, fifteenTotal4 = 0, fifteenTotal5 = 0;
			
			for(Card card1 : hand){
				
				for(Card card2 : hand){
					if(card2.equals(card1)) continue;
					
					if(card1.getRank().equals(card2.getRank())) pointValue += 1;
					
					Card[] toTest = {card1,card2};
					if(fifteen(toTest)){
						fifteenTotal2 += 2;
					}
					
					for(Card card3 : hand){
						if(card3.equals(card1)) continue;
						if(card3.equals(card2)) continue;
						
						toTest = new Card[]{card1,card2,card3};
						if(run(toTest)){
							pointValue += 3;
						}
						if(fifteen(toTest)){
							fifteenTotal3 += 2;
						}
						
						for(Card card4 : hand){
							if(card4.equals(card1)) continue;
							if(card4.equals(card2)) continue;
							if(card4.equals(card3)) continue;
							
							toTest = new Card[]{card1,card2,card3,card4};
							if(fifteen(toTest)){
								fifteenTotal4 += 2;
							}
							
							if(run(toTest)){
								pointValue -= 2;
							}
							
							for(Card card5 : hand){
								if(card5.equals(card1)) continue;
								if(card5.equals(card2)) continue;
								if(card5.equals(card3)) continue;
								if(card5.equals(card4)) continue;
								
								toTest = new Card[]{card1,card2,card3,card4,card5};
								if(fifteen(toTest)){
									fifteenTotal5 += 2;
								}
								if(run(toTest)){
									pointValue += 0;
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
			fifteenTotal2 = fifteenTotal2 / 2;
			fifteenTotal3 = fifteenTotal3 / 6;
			fifteenTotal4 = fifteenTotal4 / 24;
			fifteenTotal5 = fifteenTotal5 / 120;
//			System.out.println(fifteenTotal2 + " " + fifteenTotal3 + " " + fifteenTotal4 + " " + fifteenTotal5 + " ");
			pointValue = pointValue + fifteenTotal2 + fifteenTotal3 + fifteenTotal4 + fifteenTotal5;
		}
		return pointValue;
	}
	
	/**
	 * @param toTest : Set of cards from a hand.
	 * @return True if the cards in toTest are sequential in rank.
	 */
	private boolean run(Card[] toTest){
		int runCount = 1;
		Card lastCard = null;
		for(Card card : toTest){
			if(lastCard == null){
				lastCard = card;
				continue;
			}
			if(lastCard.getRank().ordinal() == card.getRank().ordinal()+1) runCount++;
			lastCard = card;
		}
		if(runCount == toTest.length) return true;
		else return false;
	}
	
	/**
	 * @param toTest : Set of cards from a hand.
	 * @return True if the sum of the cards is 15.
	 */
	private boolean fifteen(Card[] toTest){
		int sum = 0;
		for(Card card : toTest){
			sum += Math.min(card.getRank().ordinal()+1,10);
		}
		if(sum == 15) return true;
		else return false;
	}
	
	/**
	 * @param toTest : Set of cards from a hand.
	 * @return True if all cards in toTest are of the same suit.
	 */
	private boolean flush(Card[] toTest){
		int flushCount = 0;
		Suit suit = toTest[0].getSuit();
		for(Card card : toTest){
			if(card.getSuit().equals(suit)) flushCount++;			
		}
		if(flushCount == toTest.length) return true;
		else return false;
	}
	
	/**
	 * Removes all cards from the hands.
	 */
	void reset(){
		for(HandIndex idx : HandIndex.values()){
			hands.get(idx).clear();
		}
	}
	
	/**
	 * 
	 */
	public enum HandIndex{ 
		FIRST, SECOND, THIRD, FOURTH, CRIB
	}
}
