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
    
    private final Matcher headerMatcher;
    private boolean headerFlag = false;
    
	private static final Pattern HEADER_REGEX = Pattern.compile(
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
		"(V\\s*:\\s*[0-9]+)" + //Voice
		"|" +
		"(K\\s*:\\s*[a-gA-G][#b]?m?)" //Key
	);
	
	private static final String NOTE_EXPRESSION = "((__?|\\^\\^?|=)?[A-Ga-g]('+|,+)?([0-9]+/[0-9]+)?)";
	
	private static final Pattern BODY_REGEX = Pattern.compile(
		"(z)" + //Rest
		"|" +
		NOTE_EXPRESSION + //Note
		"|" +
		"(\\[" + NOTE_EXPRESSION + "+\\])" + //Chord
		"|" +
		"(\\([2-4]" + NOTE_EXPRESSION + "*)" //Tuplet  
	);
	
	private static final TokenType[] HEADER_TOKEN_TYPE = 
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
	
	private static final TokenType[] BODY_TOKEN_TYPE = 
	{
		TokenType.REST,
		TokenType.NOTE,
		TokenType.CHORD
	};
	
	/**
     * Creates the lexer over the passed string. Sets the string and string length variables.
     * @param string The string to tokenize. String represents a single line in the abc file.
     */
    public Lexer(String string) {
        // Replace all runs of whitespace with a single space
        this.str = string.replaceAll("\\s+", " ");       
        this.headerMatcher = HEADER_REGEX.matcher(str);
    }

    
    public Token next() throws IllegalArgumentException {
    	if (headerFlag == true) {
    		return new Token("", TokenType.EOH);
    	}
    	
    	if (! headerMatcher.find(index)) {
    		throw new RuntimeException("Lexer exception");
    	}
    	
    	String newToken = headerMatcher.group(0).replaceAll("[A-Za ]+:\\s*", "");
    	System.out.println(newToken);
    	this.index = headerMatcher.end(); //This moves the index forward
    	
    	for (int i=1; i<= headerMatcher.groupCount(); ++i) {
    		if (headerMatcher.group(i) != null) {
    			if (i == headerMatcher.groupCount()) {
    				headerFlag = true;
    			}
    			TokenType TokenType = HEADER_TOKEN_TYPE[i-1];
    			return new Token(newToken, TokenType);
    		}
    	}
    	
    	//Should not reach here
    	throw new RuntimeException("Regex error - Should not reach here.");
    }
}
