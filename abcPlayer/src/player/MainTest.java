package player;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void testBarbieGirl() {
		Main.play("sample_abc/barbie_girl.abc");
	}

	@Test
	public void testFurElisePlay() {
		Main.play("sample_abc/fur_elise.abc");
	}
	
	@Test
	public void testInventionPlay() {
		Main.play("sample_abc/invention.abc");
	}

}
