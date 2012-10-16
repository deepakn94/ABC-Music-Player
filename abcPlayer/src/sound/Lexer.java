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
    private int index;
    
    private final Matcher headerMatcher;
    
	private static final Pattern HEADER_REGEX 
	= Pattern.compile(
		"(X:[0-9]+)" + //Field number
		"|" + 
		"(T:[a-zA-Z ]+)" + //Field title
		"|" +
		"(C:[a-zA-z ]+)" + //Composer name
		"|" +
		"(Q:[0-9]+)" + //Tempo
		"|" +
		"(L:[0-9]+/[0-9]+)" + //Default length
		"|" +
		"(M:C\\||M:C|M:[0-9]+/[0-9]+)" + //Meter
		"|" +
		"(V:[a-zA-z ]+)" + //Voice
		"|" +
		"(K:[a-gA-G][#b]?m?)" //Key
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
		TokenType.KEY
	};
	
	/**
     * Creates the lexer over the passed string. Sets the string and string length variables.
     * @param string The string to tokenize.
     */
    public Lexer(String string) {
        // Replace all runs of whitespace with a single space
        this.str = string.replaceAll("\\s+", " ");;       
        this.headerMatcher = HEADER_REGEX.matcher(string);
    }

    
    public Token next() throws IllegalArgumentException{
    	String newToken = headerMatcher.group(0);
    	this.index = headerMatcher.end(); //This moves the index forward
    	
    	for (int i=1; i<= headerMatcher.groupCount(); ++i) {
    		if (headerMatcher.group(i) != null) {
    			TokenType TokenType = TOKEN_TYPE[i-1];
    			return new Token(newToken, TokenType);
    		}
    	}
    	
    	//Should not reach here
    	throw new RuntimeException("Regex error - Should not reach here.");
    }
}
