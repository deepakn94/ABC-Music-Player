package sound;


import java.util.ArrayList;
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
    
    public Piece Parse()
    {
        ArrayList pieceSoFar = new ArrayList<Playable>();
        for (Token tok = this.lex.next(); tok.getTokenType() != Token.TokenType.END_OF_PIECE; tok = this.lex.next()) {
            switch (tok.getTokenType()) {
            case NOTE:
                pieceSoFar.add(parseNote(tok.getTokenName()));
                break;
            case REST:
                pieceSoFar.add(parseRest(tok.getTokenName()));
                break;
            case CHORD:
                pieceSoFar.add(parseChord(tok.getTokenName()));
                break;
            case DOUBLET:
                pieceSoFar.add(parseDoublet(tok.getTokenName()));
                break;
            case TRIPLET:
                pieceSoFar.add(parseTriplet(tok.getTokenName()));
                break;
            case QUADRUPLET:
                pieceSoFar.add(parseQuadruplet(tok.getTokenName()));
                break;
            case BARLINE:
                break;
            case VOICE_CHANGE:
                break;
            case REPEAT:
                break;
                
                          
            }
        }
        
    }
    
  
    public Rest parseRest(String tok) {
        
    }
    
    private Note parseNote(String noteToken) {
        Accidental noteAccidental = getAccidental(noteToken);
        NoteType noteName = getNote(noteToken);
        int octave = getOctave(noteToken);
        RatNum noteLength = getLength(noteToken);
        Note parsedNote = (noteAccidental == Accidental.ABSENT) ? new Note(noteName, octave, noteLength) 
                            : new Note(noteName, octave, noteLength, noteAccidental);
        return parsedNote;
    }
    
    private Chord parseChord(String noteToken) {
        final String NOTE_EXPRESSION = "(__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]+/[0-9]+|[0-9]+)?";
        Pattern accidentalPattern = Pattern.compile(NOTE_EXPRESSION);
        Matcher accidentalMatcher = accidentalPattern.matcher(noteToken);
        
        int groupMatch = 0;
        for (int i=1; i<=accidentalMatcher.groupCount(); ++i) {
            if (accidentalMatcher.group(i) != null) {
                groupMatch = i;
                break;
            }
        }
    }
    
    public Tuplet parseDoublet(String tok) {
        
    }

    public Tuplet parseTriplet(String tok) {
        
    }
    
    public Tuplet parseQuadruplet(String tok) {
        
    }
    
    
    private Accidental getAccidental(String noteToken) {
    	String ACCIDENTAL_REGEX = "((__)|(_)|(\\^\\^)|(\\^)|(=))";
    	Pattern accidentalPattern = Pattern.compile(ACCIDENTAL_REGEX);
    	Matcher accidentalMatcher = accidentalPattern.matcher(noteToken);
    	
    	int groupMatch = 0;
    	for (int i=1; i<=accidentalMatcher.groupCount(); ++i) {
    		if (accidentalMatcher.group(i) != null) {
    			groupMatch = i;
    			break;
    		}
    	}
    	
    	switch (groupMatch) {
    		case 1: return Accidental.FLAT; 
    		case 2: return Accidental.DOUBLEFLAT;
    		case 3: return Accidental.SHARP;
    		case 4: return Accidental.DOUBLESHARP;
    		case 5: return Accidental.NATURAL;
    		default: return Accidental.ABSENT;
    	}
    }
    
    private NoteType getNote(String noteToken) {
    	String NOTE_REGEX = "(([Aa])|([Bb])|([Cc])|([Dd])|([Ee])|([Ff])|([Gg]))";
    	Pattern notePattern = Pattern.compile(NOTE_REGEX);
    	Matcher noteMatcher = notePattern.matcher(noteToken);
    	
    	int groupMatch = 0;
    	for (int i=1; i<=noteMatcher.groupCount(); ++i) {
    		if (noteMatcher.group(i) != null) {
    			groupMatch = i;
    			break;
    		}
    	}
    	switch (groupMatch) {
    		case 1:
    			return NoteType.A; 
    		case 2:
    			return NoteType.B;
    		case 3:
    			return NoteType.C;
    		case 4:
    			return NoteType.D;
    		case 5:
    			return NoteType.E;
    		case 6:
    			return NoteType.F;
    		case 7:
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
    

}