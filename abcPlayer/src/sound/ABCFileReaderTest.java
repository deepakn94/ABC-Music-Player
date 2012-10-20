package sound;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ABCFileReaderTest {

	@Test
	public void testReadContent() {
		String file_name = "C:\\Users\\Deepak\\Documents\\git\\project1\\deepakn-yygu-arjunnar\\abcPlayer\\sample_abc\\piece1.abc";
		ABCFileReader file_reader = new ABCFileReader(file_name);
		String actualString = "";
		String expectedString = "X: 1\n" +
					"T:Piece No.1\n" +
					"M:4/4\n" +
					"L:1/4\n" +
					"Q: 140\n" +
					"K:C\n" +
					"C C C3/4 D/4 E | E3/4 D/4 E3/4 F/4 G2 |\n" + 
					"c1/3 c1/3 c1/3 G1/3 G1/3 G1/3 E1/3 E1/3 E1/3 C1/3 C1/3 C1/3 |\n" +
					"G3/4 F1/4 E3/4 D1/4 C2/1\n";
		try {
			actualString = file_reader.readContent();
			assertEquals(actualString, expectedString);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
	}

}
