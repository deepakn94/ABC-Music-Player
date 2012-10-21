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
 * End of Header
 * 
 * 
 * BodyTokens -->
 * 
 * Note
 * Rest
 * Chords
 * Tuplets
 * Repeats
 * Barlines
 * Change of voice token
 * End of line
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
		DOUBLET,
		TRIPLET,
		QUADRUPLET,
		FIRST_REPEAT,
		SECOND_REPEAT,
		BARLINE,
		VOICE_CHANGE,
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
