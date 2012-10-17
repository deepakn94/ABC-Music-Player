package sound;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sound.Token.TokenType;

public class LexerTest {

	@Test
	public void testNext() {
		Lexer testLexer = new Lexer("X: 1\n" +
									"T:Bagatelle No.25 in A, WoO.59" +
									//"C:Ludwig van Beethoven" +
									"V:1" +
									"V:2" +
									"M:3/8" +
									"L:1/16" +
									"Q:240" +
									"K:Am");
		
		TokenType[] expectedTokenTypes = new TokenType[] {TokenType.INDEX_NUMBER,
												TokenType.TITLE,
												TokenType.VOICE,
												TokenType.VOICE,
											    TokenType.METER,
												TokenType.LENGTH,
												TokenType.TEMPO,
												TokenType.KEY,
												TokenType.EOH};
		
		String[] expectedTokenValues = new String[] {"X: 1",
													"T:Bagatelle No.25 in A, WoO.59" +
													"C:Ludwig van Beethoven" +
													"V:1" +
													"V:2" +
													"M:3/8" +
													"L:1/16" +
													"Q:240" +
													"K:Am",
													""};
		
		Token currentToken = testLexer.next();
		List<TokenType> actualTokenTypes = new ArrayList<TokenType> ();
		List<String> actualTokenValues = new ArrayList<String> ();
		while (currentToken.getTokenType()!=TokenType.EOH) {
			actualTokenTypes.add(currentToken.getTokenType());
			actualTokenValues.add(currentToken.getTokenName());
			currentToken = testLexer.next();
		}
		
		assertArrayEquals(expectedTokenTypes, actualTokenTypes.toArray());
		assertArrayEquals(expectedTokenValues, actualTokenValues.toArray());
	}

}
