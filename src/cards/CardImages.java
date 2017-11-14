package cards;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * A class to store and manage images of the 52 cards.
 */
public final class CardImages{
	private static final String IMAGE_LOCATION = "";
	private static final String IMAGE_SUFFIX = ".gif";
	private static final String[] RANK_CODES = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
	private static final String[] SUIT_CODES = {"c", "d", "h", "s"};	
	
	private static Map<String, Image> cards = new HashMap<String, Image>();
	
	private CardImages(){}
	
	/**
	 * The card image.
	 * @param card : card who's image is to be returned.
	 * @return An image of card.
	 * @pre card != null
	 */
	public static Image getCard( Card card ){
		assert card != null;
		return getCard( getCode( card ) );
	}
	
	/**
	 * Returns the back of card image.
	 * @return An image of the card back.
	 */
	public static Image getBack(){
		return getCard( "b" );
	}
	
	/**
	 * Returns the blank space image.
	 * @return An image of a blank space.
	 */
	public static Image getBlank(){
		return getCard("blank");
	}
	
	private static String getCode( Card card ){
		return RANK_CODES[ card.getRank().ordinal() ] + SUIT_CODES[ card.getSuit().ordinal() ];		
	}
	
	private static Image getCard( String cardCode ){
		Image image = (Image) cards.get( cardCode );
		if( image == null ){
			image = new Image(CardImages.class.getClassLoader().getResourceAsStream( IMAGE_LOCATION + cardCode + IMAGE_SUFFIX ));
			cards.put( cardCode, image );
		}
		return image;
	}
}
