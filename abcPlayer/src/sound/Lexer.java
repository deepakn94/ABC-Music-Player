package sound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sound.Token.TokenType;

/*
Grammar

 	abc-tune ::= abc-header abc-music

	abc-header ::= field-number comment* field-title other-fields* field-key
	        
	field-number ::= "X:" DIGIT+ end-of-line
	field-title ::= "T:" text end-of-line
	other-fields ::= field-composer | field-default-length | field-meter 
		| field-tempo | field-voice | comment
	field-composer ::= "C:" text end-of-line
	field-default-length ::= "L:" note-length-strict end-of-line
	field-meter ::= "M:" meter end-of-line
	field-tempo ::= "Q:" tempo end-of-line
	field-voice ::= "V:" text end-of-line
	field-key ::= "K:" key end-of-line

	key ::= keynote ["m"]
	keynote ::= basenote [key-accidental]
	key-accidental ::= "#" | "b"
	note-length-strict ::= DIGIT+ "/" DIGIT+
	
	meter ::= "C" | "C|" | meter-fraction
	meter-fraction ::= DIGIT+ "/" DIGIT+ 
	
	tempo ::= DIGIT+ 
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	abc-music ::= abc-line+
	abc-line ::= (element+ linefeed) | mid-tune-field | comment
	playElement ::= note | chord | tuplet | rest
	
	element ::= playElement | barline | nth-repeat 
	
	note ::= pitch [note-length]
	pitch ::= [accidental] basenote [octave]
	octave ::= ("'"+) | (","+)
	note-length ::= [DIGIT+] ["/" [DIGIT+]]
	
	accidental ::= "^" | "^^" | "_" | "__" | "="
	
	basenote ::= "C" | "D" | "E" | "F" | "G" | "A" | "B"
	        | "c" | "d" | "e" | "f" | "g" | "a" | "b"
	
	rest ::= "z" [note-length]
	
	// tuplets
	tuplet ::= "(" DIGIT [ note+ ]
	
	// chords
	chord ::= "[" note+ "]"
	
	barline ::= "|" | "||" | "[|" | "|]" | ":|" | "|:"
	nth-repeat ::= "[1" | "[2"
	
	mid-tune-field- ::= field-voice
	
	comment ::= "%" text linefeed
	end-of-line ::= comment | linefeed
 */

/**
 * A lexer takes a string and splits it into tokens that are meaningful to a
 * parser.
 */
public class Lexer {
    
    private String str;
    private int index = 0;
    
    private final Matcher matcher;
    
    private static final String NOTE_EXPRESSION = "((__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]+/[0-9]+)?)";
    
	private static final Pattern REGEX = Pattern.compile(
		"(X\\s*:\\s*[0-9]+)" + //Field number
		"|" + 
		"(T\\s*:.+)" + //Field title
		"|" +
		"(C\\s*:.+)" + //Composer name
		"|" +
		"(Q\\s*:\\s*[0-9]+)" + //Tempo
		"|" +
		"(L\\s*:\\s*[0-9]+/[0-9]+)" + //Default length
		"|" +
		"(M\\s*:\\s*C\\||M:C|M:[0-9]+/[0-9]+)" + //Meter
		"|" +
		"(V\\s*:.+)" + //Voice
		"|" +
		"(K\\s*:\\s*[a-gA-G][#b]?m?)" + //Key
		"|" +
		"(z([0-9]+/[0-9]+)?)" + //Rest
		"|" +
		NOTE_EXPRESSION + //Note
		"|" +
		"(\\[" + NOTE_EXPRESSION + "+\\])" + //Chord
		"|" +
		"(\\(2" + NOTE_EXPRESSION + "{2})" + //Doublet
		"|" +
		"(\\(3" + NOTE_EXPRESSION + "{3})" + //Triplet
		"|" +
		"(\\(4" + NOTE_EXPRESSION + "{4})" + //Quadruplet
		"|" +
		"(\\|:|:\\||\\|\\]|\\|\\|?|\\[\\||)" + //Barline
		"|" +
		"(\\[[12])" //n-th repeat
	);
	
	private static final TokenType[] TOKEN_TYPE = 
	{
		TokenType.INDEX_NUMBER,
		TokenType.TITLE,
		TokenType.COMPOSER_NAME,
		TokenType.TEMPO,
		TokenType.LENGTH,
		TokenType.METER,
		TokenType.VOICE,
		TokenType.KEY,
		TokenType.REST,
		TokenType.NOTE,
		TokenType.CHORD,
		TokenType.DOUBLET,
		TokenType.TRIPLET,
		TokenType.QUADRUPLET,
		TokenType.BARLINE,
		TokenType.REPEAT
	};
	
	/**
     * Creates the lexer over the passed string. Sets the string and string length variables.
     * @param string The string to tokenize. String represents a single line in the abc file.
     */
    public Lexer(String string) {
        // Replace all runs of whitespace with a single space
        this.str = string.replaceAll("\\s+", " ");       
        this.matcher = REGEX.matcher(str);
    }

    
    public Token next() throws IllegalArgumentException {
    	if (index >= str.length())
    		return new Token("", TokenType.END_OF_PIECE);
    	
    	if (! matcher.find(index)) {
    		throw new RuntimeException("Lexer exception");
    	}
    	
    	String newToken = matcher.group(0).replaceAll("[A-Z ]+:\\s*", "");
    	System.out.println(newToken);
    	this.index = matcher.end(); //This moves the index forward
    	
    	System.out.println(matcher.groupCount());
    	for (int i=1; i<= matcher.groupCount(); ++i) {
    		if (matcher.group(i) != null) {
    			TokenType TokenType = TOKEN_TYPE[i-1];
    			return new Token(newToken, TokenType);
    		}
    	}
    	
    	//Should not reach here
    	throw new RuntimeException("Regex error - Should not reach here.");
	    
    }
}
