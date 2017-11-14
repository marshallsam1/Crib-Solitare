package test;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import model.GameModel;
import model.HandStackManager.HandIndex;

public class TestGameModel {
	
	@Before
	public void setup() throws Exception{
		Field deckField = GameModel.class.getDeclaredField("deck");
		deckField.setAccessible(true);
		deckField.set(GameModel.instance(), new TestDeck());
		for(int i = 0; i < 4; i++){
			for(HandIndex idx : HandIndex.values()){
				GameModel.instance().move(idx);
			}
		}
	}
	
	@Test
	public void testCountPoints(){
		System.out.println(GameModel.instance().peek());
	
	}
}
