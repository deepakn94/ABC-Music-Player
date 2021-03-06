package sound;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sound.Token.TokenType;

public class LexerTest {
	@Test
	public void testIndexNumber() {
		Lexer testLexer = new Lexer("X:1\n");
		TokenType expectedTokenType = TokenType.INDEX_NUMBER;
		String expectedTokenName ="1";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testTitle() {
		Lexer testLexer = new Lexer("T:Bagatelle No.25 in A, WoO.59\n");
		TokenType expectedTokenType = TokenType.TITLE;
		String expectedTokenName ="Bagatelle No.25 in A, WoO.59";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testComposerName() {
		Lexer testLexer = new Lexer("C:Ludwig van Beethoven\n");
		TokenType expectedTokenType = TokenType.COMPOSER_NAME;
		String expectedTokenName ="Ludwig van Beethoven";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testVoice() {
		Lexer testLexer = new Lexer("V:1\n");
		TokenType expectedTokenType = TokenType.VOICE;
		String expectedTokenName ="1";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testMeter() {
		Lexer testLexer = new Lexer("M:3/8\n");
		TokenType expectedTokenType = TokenType.METER;
		String expectedTokenName ="3/8";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testLength() {
		Lexer testLexer = new Lexer("L:1/16\n");
		TokenType expectedTokenType = TokenType.LENGTH;
		String expectedTokenName ="1/16";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testTempo() {
		Lexer testLexer = new Lexer("Q: 240\n");
		TokenType expectedTokenType = TokenType.TEMPO;
		String expectedTokenName ="240";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
	@Test
	public void testKey() {
		Lexer testLexer = new Lexer("K :Am\n");
		TokenType expectedTokenType = TokenType.KEY;
		String expectedTokenName ="Am";
		Token currentToken = testLexer.next();
		assertEquals(expectedTokenType, currentToken.getTokenType());
		assertEquals(expectedTokenName, currentToken.getTokenName());
	}
	
   @Test
    public void testSimpleNote1() {
        Lexer testLexer = new Lexer("C2");
        TokenType expectedTokenType = TokenType.NOTE;
        String expectedTokenName ="C2";
        Token currentToken = testLexer.next();
        assertEquals(expectedTokenType, currentToken.getTokenType());
        assertEquals(expectedTokenName, currentToken.getTokenName());
    }
   
   @Test
   public void testSimpleNotes2() {
       Lexer testLexer = new Lexer("C2 D6 | c B'");
       String expectedTokenName ="C2 D6 | c B' ";
       String tokenName = "";
       for (int i=0; i<5; i++) {
           Token currentToken = testLexer.next();
           tokenName += currentToken.getTokenName() + " ";
       }
       assertEquals(expectedTokenName, tokenName);
   }
   
   @Test
   public void testNotes() {
       Lexer testLexer = new Lexer("X:8628\nT:Some Bach Song\nC:Bach\nM:4/4\nL:1/16\nGceGcez");
       String expectedTokenName ="8628 Some Bach Song Bach 4/4 1/16 G c e G c e z ";
       String tokenName = "";
       for (int i=0; i<12; i++) {
           Token currentToken = testLexer.next();
           tokenName += currentToken.getTokenName() + " ";
       }
       assertEquals(expectedTokenName, tokenName);
   }
	
}
