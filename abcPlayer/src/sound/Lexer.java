package sound;

/*
Grammar

 	abc-tune ::= abc-header abc-music

	abc-header ::= field-number comment* field-title other-fields* field-key
	        
	field-number ::= "X:" [0-9]+ end-of-line
	field-title ::= "T:" text end-of-line
	other-fields ::= field-composer | field-default-length | field-meter 
		| field-tempo | field-voice | comment
	field-composer ::= "C:" text end-of-line
	field-default-length ::= "L:" note-length-strict end-of-line
	field-meter ::= "M:" meter end-of-line
	field-tempo ::= "Q:" tempo end-of-line
	field-voice ::= "V:" text end-of-line
	field-key ::= "K:" key end-of-line
	
	key ::= keynote "m"?
	keynote ::= basenote [key-accidental]
	key-accidental ::= "#" | "b"
	
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

public class Lexer {
	
}
