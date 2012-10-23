package sound;

/*
 * Tokens used
 * 
 * HeaderTokens -->
 * 
 * Title
 * Composer's name
 * Meter
 * Tempo 
 * Voice 
 * Key
 * Index Number
 * Length
 * 
 * 
 * BodyTokens -->
 * 
 * Note
 * Rest
 * Chords
 * Doublets
 * Triplets
 * Quadruplets
 * Repeat-first ending
 * Repeat-second ending
 * Start repeat
 * End repeat
 * Barlines
 * End of piece
 * 
 */
public class Token {
	public static enum TokenType {
		//Header Tokens
		TITLE,
		COMPOSER_NAME,
		METER,
		TEMPO,
		VOICE,
		KEY,
		INDEX_NUMBER,
		LENGTH,
	
		//Body Tokens
		NOTE,
		REST,
		CHORD,
		DUPLET,
		TRIPLET,
		QUADRUPLET,
		REPEAT_FIRST_ENDING,
		REPEAT_SECOND_ENDING,
		START_REPEAT,
		END_REPEAT,
		BARLINE,
		END_OF_PIECE
	}
	
	private String tokenName;
	private TokenType tokenType;
	
	public Token(String tokenName, TokenType tokenType) {
		this.tokenName = tokenName;
		this.tokenType = tokenType;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	
	public TokenType getTokenType() {
		return tokenType;
	}
	
	
	
	
}
