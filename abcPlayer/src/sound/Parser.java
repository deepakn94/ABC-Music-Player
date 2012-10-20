package sound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Lexer lex;
    
    /**
     * Creates a new parser over the lexer.  This parser will use the passed
     * lexer to get tokens--which it will then parse.
     * 
     * @param lexer The lexer.
     * @return none
     */
    public Parser(Lexer lexer) {
        this.lex = lexer;     
    }
    
    public void Parse()
    {
        
        
    }
    
    private Accidental getAccidental(String noteToken) {
    	String ACCIDENTAL_REGEX = "\\^\\^?|__?|=";
    	Pattern accidentalPattern = Pattern.compile(ACCIDENTAL_REGEX);
    	Matcher accidentalMatcher = accidentalPattern.matcher(noteToken);
    	String accidental = accidentalMatcher.find() ? accidentalMatcher.group(0) : "";
    	switch (accidental) {
    		case "_": return Accidental.FLAT; 
    		case "__": return Accidental.DOUBLEFLAT;
    		case "^": return Accidental.SHARP;
    		case "^^": return Accidental.DOUBLESHARP;
    		case "=": return Accidental.NATURAL;
    		default: return Accidental.ABSENT;
    	}
    }
    
    private NoteType getNote(String noteToken) {
    	String NOTE_REGEX = "[A-Ga-g]";
    	Pattern notePattern = Pattern.compile(NOTE_REGEX);
    	Matcher noteMatcher = notePattern.matcher(noteToken);
    	String note = noteMatcher.find() ? noteMatcher.group(0) : "";
    	switch (note) {
    		case "A": 
    		case "a":
    			return NoteType.A; 
    		case "B": 
    		case "b":
    			return NoteType.B;
    		case "C":
    		case "c":
    			return NoteType.C;
    		case "D": 
    		case "d":
    			return NoteType.D;
    		case "E":
    		case "e":
    			return NoteType.E;
    		case "F":
    		case "f":
    			return NoteType.F;
    		case "G":
    		case "g":
    			return NoteType.G;
    		default:
    			throw new IllegalArgumentException("Illegal Note");
    	}
    }
    
    private int getOctave(String noteToken) {
    	String HIGHER_OCTAVE_REGEX = "[a-g]";
    	String OCTAVE_REGEX = "('+)|(,+)";
    	Pattern higherOctave = Pattern.compile(HIGHER_OCTAVE_REGEX);
    	Pattern octavePattern = Pattern.compile(OCTAVE_REGEX);
    	
    	Matcher higherOctaveMatcher = higherOctave.matcher(noteToken);
    	Matcher octaveMatcher = octavePattern.matcher(noteToken);
    	
    	int octave = higherOctaveMatcher.find() ? 1 : 0;
    	String octaves = octaveMatcher.find() ? octaveMatcher.group(0) : "";
    	
    	if (octaveMatcher.group(1) != null) octave += octaves.length();
    	if (octaveMatcher.group(2) != null) octave -= octaves.length();
    	
    	return octave;
    }
    
    private RatNum getLength(String noteToken) {
    	String LENGTH_REGEX = "([0-9]+/[0-9]+)|([0-9]+)";
    	Pattern lengthPattern = Pattern.compile(LENGTH_REGEX);
    	
    	Matcher lengthMatcher = lengthPattern.matcher(noteToken);
    	String length = lengthMatcher.find() ? lengthMatcher.group(0) : "";

    	if (lengthMatcher.group(1) != null) {
    		String[] rational = length.split("'");
    		if (rational.length != 2) throw new RuntimeException("Should not occur");
    		return new RatNum(Integer.parseInt(rational[0]), Integer.parseInt(rational[1]));
    	}
    	
    	if (lengthMatcher.group(2) != null) {
    		return new RatNum(Integer.parseInt(length));
    	}
    	
    	if (length == "") {
    		return new RatNum(1);
    	}
    	
    	throw new RuntimeException("Should not reach here.");
    }
    
    public void ParseNote(String noteToken) {
    	Accidental noteAccidental = getAccidental(noteToken);
    	NoteType noteName = getNote(noteToken);
    	int octave = getOctave(noteToken);
    	RatNum length = getLength(noteToken);
    }
    
}