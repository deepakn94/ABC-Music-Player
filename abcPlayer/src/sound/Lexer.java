package sound;

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
    
	private static final Pattern REGEX 
	= Pattern.compile(
		"(X:[0-9]+)" + //Field number
		"|" + 
		"(T:[a-zA-Z ]+)" + //Field title
		"|" +
		"(C:[a-zA-z ]+)" + //Composer name
		"|" +
		"(Q:[0-9]+)" //Tempo
	);
	
	/**
     * Creates the lexer over the passed string. Sets the string and string length variables.
     * @param string The string to tokenize.
     */
    public Lexer(String string) {
        // Replace all runs of whitespace with a single space
        this.str = string.replaceAll("\\s+", " ");;       
    }
    
    Token.TokenType type;
    public Token next() throws IllegalArgumentException{
        // Match pattern here
        throw new UnsupportedOperationException();
    }
	

private static final TokenType[] TOKEN_TYPE = 
{
	TokenType.INDEX_NUMBER,
	TokenType.TITLE,
	TokenType.COMPOSER_NAME,
	TokenType.TEMPO
};
}
