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
    
    private static final String NOTE_EXPRESSION = "(__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]+/[0-9]+|[0-9]+)?";
    
	private static final Pattern REGEX = Pattern.compile(
		"^(X\\s*:\\s*[0-9]+\n)" + //Field number
		"|" + 
		"(T\\s*:[A-Za-z .,0-9]+\n)" + //Field title
		"|" +
		"(C\\s*:[A-Za-z .,0-9]+\n)" + //Composer name
		"|" +
		"(Q\\s*:\\s*[0-9]+\n)" + //Tempo
		"|" +
		"(L\\s*:\\s*[0-9]+/[0-9]+\n)" + //Default length
		"|" +
		"(M\\s*:\\s*C\\||M:C|M:[0-9]+/[0-9]+\n)" + //Meter
		"|" +
		"(V\\s*:[A-Za-z .,0-9]+\n)" + //Voice
		"|" +
		"(K\\s*:\\s*[a-gA-G][#b]?m?\n)" + //Key
		"|" +
		"(?<Rest>z([0-9]+/[0-9]+|[0-9]+)?)" + //Rest
		"|" +
		"(?<NoteExpression>(" + NOTE_EXPRESSION + ")\\s*)" +//Note
		"|" +
		"(?<Chord>\\[(" + NOTE_EXPRESSION + ")+\\]([0-9]+/[0-9]+)?\\s*)" + //Chord
		"|" +
		"(?<Doublet>\\(2(" + NOTE_EXPRESSION + "){2}\\s*)" + //Doublet
		"|" +
		"(?<Triplet>\\(3(" + NOTE_EXPRESSION + "){3}\\s*)" + //Triplet
		"|" +
		"(?<Quadruplet>\\(4(" + NOTE_EXPRESSION + "){4}\\s*)" + //Quadruplet
		"|" +
		"(?<Barline>\\|:|:\\||\\|\\]|\\|\\|?|\\[\\|\\s*)" + //Barline
		"|" +
		"(?<Repeat>\\[[12]\\s*)" //n-th repeat
		, Pattern.DOTALL
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
     * @param string The string to tokenize. String represents a single line in the abc file.
     */
    public Lexer(String string) { 
    	this.str = string;
        this.matcher = REGEX.matcher(str);
    }
    
    public Token next() throws IllegalArgumentException {
    	if (index >= str.length())
    		return new Token("", TokenType.END_OF_PIECE);
    	
    	if (! matcher.find(index)) {
    		return new Token("", TokenType.END_OF_PIECE);
    	}
    	String newToken = matcher.group(0);
    	newToken = newToken.replaceAll("[A-Z ]+:\\s*", "").replace("\n", "");
    	this.index = matcher.end(); //This moves the index forward
    	
    	for (int i=1; i<= TOKEN_TYPE.length; ++i) {
    		if (matcher.group(i) != null) {
    			//System.out.println(newToken);
    			TokenType TokenType = TOKEN_TYPE[i-1];
    			return new Token(newToken, TokenType);
    		}
    	}
    	
    	if (matcher.group("NoteExpression")!= null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.NOTE);
		} 
    	
    	else if (matcher.group("Rest")!=null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.REST);
		} 
    	
    	else if (matcher.group("Chord")!=null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.CHORD);
		} 
    	
    	else if (matcher.group("Doublet")!=null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.DOUBLET);
		} 
    	
    	else if (matcher.group("Triplet")!=null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.TRIPLET);
		} 
    	
    	else if (matcher.group("Quadruplet")!=null) {
			//System.out.println(newToken);
			newToken = newToken.trim();
			return new Token(newToken, TokenType.QUADRUPLET);
		} 
    	
    	else if (matcher.group("Barline")!=null) {
			newToken = newToken.trim();
			//System.out.println(newToken);
			return new Token(newToken, TokenType.BARLINE);
		}
    	
    	else if (matcher.group("Repeat")!=null) {
			newToken = newToken.trim();
			//System.out.println(newToken);
			return new Token(newToken, TokenType.REPEAT);
		}
    	
    	//Should not reach here
    	throw new RuntimeException("Regex error - Should not reach here.");
	    
    }
}
