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
	public static enum HeaderTokenTypes {
		TITLE,
		COMPOSER_NAME,
		METER,
		TEMPO,
		VOICE,
		KEY,
		INDEX_NUMBER
	}
	
	public static enum BodyTokenTypes {
		NOTE,
		REST,
		CHORD,
		TUPLET,
		REPEAT,
		BARLINE,
		VOICE_CHANGE,
		END_OF_LINE
	}
}
