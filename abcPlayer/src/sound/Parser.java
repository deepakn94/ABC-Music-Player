package sound;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final Lexer lex;
    
    /**
     * Creates a new parser over the lexer.  This parser will use the passed
     * lexer to get tokens--which it will then parse.
     * 
     * @param lexer The lexer.
     * @return none
     */
    
    public static HashMap<Key, HashMap<NoteType, Accidental>> keyMappings = 
            new HashMap<Key, HashMap<NoteType, Accidental>>();
    
    public Parser(Lexer lexer) {
        this.lex = lexer;  
        Parser.initializeKeyMappings();
    }
    
    private static void initializeKeyMappings()
    {   
        //C major and A Minor 
        keyMappings.put(Key.C_MAJOR, new HashMap<NoteType, Accidental>());
        keyMappings.put(Key.A_MINOR, new HashMap<NoteType, Accidental>());
        
        //G major and E minor
        HashMap<NoteType, Accidental> map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.F,  Accidental.SHARP);
        keyMappings.put(Key.G_MAJOR, map);
        keyMappings.put(Key.E_MINOR, new HashMap<NoteType, Accidental>(map));
        
        //F major and D minor
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.B, Accidental.FLAT);
        keyMappings.put(Key.F_MAJOR, map);
        keyMappings.put(Key.D_MINOR, new HashMap<NoteType, Accidental>(map));
        
        //D major and B minor
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.C, Accidental.SHARP);
        map.put(NoteType.F, Accidental.SHARP);
        keyMappings.put(Key.D_MAJOR, map); 
        keyMappings.put(Key.B_MINOR, new HashMap<NoteType, Accidental>(map));
        
        //G minor 
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.B, Accidental.FLAT);
        map.put(NoteType.E, Accidental.FLAT);
        keyMappings.put(Key.G_MINOR, map); 
        
        //A major 
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.C, Accidental.SHARP);
        map.put(NoteType.F, Accidental.SHARP);
        map.put(NoteType.G,  Accidental.SHARP);
        keyMappings.put(Key.A_MAJOR, map); 
        
        //C minor
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.A, Accidental.FLAT);
        map.put(NoteType.B, Accidental.FLAT);
        map.put(NoteType.E, Accidental.FLAT);
        keyMappings.put(Key.C_MINOR, map); 
        
        //E major
        map = new HashMap<NoteType, Accidental>(); 
        map.put(NoteType.C, Accidental.SHARP);
        map.put(NoteType.D, Accidental.SHARP);
        map.put(NoteType.F, Accidental.SHARP);
        map.put(NoteType.G, Accidental.SHARP);
        keyMappings.put(Key.E_MAJOR, map);
        
        //F minor
        map = new HashMap<NoteType, Accidental>();
        map.put(NoteType.A, Accidental.FLAT);
        map.put(NoteType.B, Accidental.FLAT);
        map.put(NoteType.D, Accidental.FLAT);
        map.put(NoteType.E, Accidental.FLAT);
        keyMappings.put(Key.F_MINOR, map); 
        
        //B major
        map = new HashMap<NoteType, Accidental>();
        map.put(NoteType.A, Accidental.SHARP);
        map.put(NoteType.C, Accidental.SHARP);
        map.put(NoteType.D, Accidental.SHARP);
        map.put(NoteType.F, Accidental.SHARP);
        map.put(NoteType.G, Accidental.SHARP);
        keyMappings.put(Key.B_MAJOR, map);    
    }
    
    public Piece Parse()
    {
        final int DEFAULT_TEMPO = 100;
        final RatNum DEFAULT_NOTE_LENGTH = new RatNum(1, 8);
        final String DEFAULT_COMPOSER_VAL = "UNSPECIFIED";
        
        List<Playable> pieceSoFar = new ArrayList<Playable>();
        int startRepeatIndex = 0;
        int endRepeatIndex = -1;
        int firstRepeatIndex = -1;
        int secondRepeatIndex = -1;
        String title = null, meter = null;
        List<Voice> voices = new ArrayList<Voice>();
        int index = -1;
        Key keySig = null;
        
        // Default values:
        String composer = DEFAULT_COMPOSER_VAL;
        int tempo = DEFAULT_TEMPO;
        RatNum length = DEFAULT_NOTE_LENGTH;

        
        for (Token tok = this.lex.next(); tok.getTokenType() != Token.TokenType.END_OF_PIECE; tok = this.lex.next()) {
            switch (tok.getTokenType()) {
            
            //Header Tokens
            case TITLE:
                title = tok.getTokenName();
            case COMPOSER_NAME:
                composer = tok.getTokenName();
            case METER:
                meter = tok.getTokenName();
            case TEMPO:
                tempo = Integer.parseInt(tok.getTokenName());
            case VOICE:
                // Implement voice later
                break;
            case KEY:
                keySig = parseKey(tok.getTokenName());
                break;
            case INDEX_NUMBER:
                index = Integer.parseInt(tok.getTokenName());
                break;
            case LENGTH:
                length = parseNoteLength(tok.getTokenName());
                
                
            // Body Tokens
            case NOTE:
                pieceSoFar.add(parseNote(tok.getTokenName()));
                break;
            case REST:
                pieceSoFar.add(parseRest(tok.getTokenName()));
                break;
            case CHORD:
                pieceSoFar.add(parseChord(tok.getTokenName()));
                break;
            case DUPLET:
                pieceSoFar.add(parseDuplet(tok.getTokenName()));
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
            case START_REPEAT:
                startRepeatIndex = pieceSoFar.size();
                break; 
            case END_REPEAT:
                endRepeatIndex = pieceSoFar.size();

                if (firstRepeatIndex != -1) {
                    for (int i = startRepeatIndex; i<firstRepeatIndex; i++) {
                        pieceSoFar.add(pieceSoFar.get(i));
                    }
                    if (secondRepeatIndex != -1) {
                        for (int i = secondRepeatIndex; i<endRepeatIndex; i++) {
                            pieceSoFar.add(pieceSoFar.get(i));
                        }
                    }
                    firstRepeatIndex = -1;
                    secondRepeatIndex = -1;
                }
                else {
                    for (int i = startRepeatIndex; i<endRepeatIndex; i++) {
                        pieceSoFar.add(pieceSoFar.get(i));
                    }
                }                 
                break; 
            case REPEAT_FIRST_ENDING:
                firstRepeatIndex = pieceSoFar.size();
                secondRepeatIndex = -1;
                break;
            case REPEAT_SECOND_ENDING:
                while (firstRepeatIndex != -1 && tok.getTokenType() != Token.TokenType.END_REPEAT) {
                    tok = this.lex.next();
                }
                secondRepeatIndex = pieceSoFar.size();
                break;
            default:
                break;
            }
            
        }
        if (index == -1 || keySig == null || title == null)
            throw new IllegalArgumentException("These three options are required: X, T, K");
        return new Piece(voices, index, title, keySig, composer, length, tempo);  
        
    }
    
    public Key parseKey(String noteToken) {
        // Need to implement correct key regex, because I'm not very good with regexes lol.:
        String KEY_REGEX = "(([ABDEFG][m]* | [C]))";
        Pattern notePattern = Pattern.compile(KEY_REGEX);
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
                return Key.A_MINOR; 
            case 2:
                return Key.B_MINOR;
            // Finish this
            default:
                throw new IllegalArgumentException("Illegal Key");
        }
    }
    
    public RatNum parseNoteLength(String noteToken) {
        
        int i = 0;
        while (noteToken.charAt(i) != '/') {
            if (i++ > noteToken.length())
                return new RatNum(Integer.parseInt(noteToken), 1);     
        }
        return new RatNum(Integer.parseInt(noteToken.substring(0, i)), Integer.parseInt(noteToken.substring(i)));
    }
  
    public Rest parseRest(String noteToken) {
        RatNum restLength = getLength(noteToken);
        Rest rest = new Rest(restLength);
        return rest;
    }
    
    public Note parseNote(String noteToken) {
        Accidental noteAccidental = getAccidental(noteToken);
        NoteType noteName = getNote(noteToken);
        int octave = getOctave(noteToken);
        RatNum noteLength = getLength(noteToken);
        Note parsedNote = (noteAccidental == Accidental.ABSENT) ? new Note(noteName, octave, noteLength) 
                            : new Note(noteName, octave, noteLength, noteAccidental);
        return parsedNote;
    }
    final String NOTE_EXPRESSION = "(__?|\\^\\^?|=)?[A-Ga-g]['+,+]*([0-9]+/[0-9]+|[0-9]+)?";
    
    public Chord parseChord(String noteToken) {
        List<Note> chords = new ArrayList<Note>();
        
        Pattern chordPattern = Pattern.compile(NOTE_EXPRESSION);
        Matcher chordMatcher = chordPattern.matcher(noteToken);
        int index = 0;
        while (chordMatcher.find(index)) {
            chords.add(parseNote(chordMatcher.group(0)));
            index = chordMatcher.end();
        } 
        
        return new Chord(chords);
    }
    
    public Tuplet parseDuplet(String noteToken) {
        List<Note> duplet = new ArrayList<Note>();

        Pattern notePattern = Pattern.compile(NOTE_EXPRESSION);
        Matcher noteMatcher = notePattern.matcher(noteToken);
        int index = 0;
        
        while (noteMatcher.find(index)) {
            Note dupletNote = parseNote(noteMatcher.group(0));
            RatNum newNoteLength = new RatNum(dupletNote.getNoteLength().getNumer()*3, dupletNote.getNoteLength().getDenom()*2);
            dupletNote.setNoteLength(newNoteLength);
            duplet.add(dupletNote);
            index = noteMatcher.end();
        }
        if (duplet.size() != 2)
            throw new RuntimeException("Duplet does not contain 2 notes");
        
        return new Tuplet(TupletType.DUPLET, duplet);
    }

    public Tuplet parseTriplet(String noteToken) {
        List<Note> triplet = new ArrayList<Note>();

        Pattern notePattern = Pattern.compile(NOTE_EXPRESSION);
        Matcher noteMatcher = notePattern.matcher(noteToken);
        int index = 0;
        
        while (noteMatcher.find(index)) {
            Note tripletNote = parseNote(noteMatcher.group(0));
            RatNum newNoteLength = new RatNum(tripletNote.getNoteLength().getNumer()*2, tripletNote.getNoteLength().getDenom()*3);
            tripletNote.setNoteLength(newNoteLength);
            triplet.add(tripletNote);
            index = noteMatcher.end();
        }
        if (triplet.size() != 3)
            throw new RuntimeException("Triplet does not contain 3 notes");
        
        return new Tuplet(TupletType.TRIPLET, triplet);
    }
    
    public Tuplet parseQuadruplet(String noteToken) {
        List<Note> quadruplet = new ArrayList<Note>();

        Pattern notePattern = Pattern.compile(NOTE_EXPRESSION);
        Matcher noteMatcher = notePattern.matcher(noteToken);
        int index = 0;
        
        while (noteMatcher.find(index)) {
            Note quadrupletNote = parseNote(noteMatcher.group(0));
            RatNum newNoteLength = new RatNum(quadrupletNote.getNoteLength().getNumer()*2, quadrupletNote.getNoteLength().getDenom()*3);
            quadrupletNote.setNoteLength(newNoteLength);
            quadruplet.add(quadrupletNote);
            index = noteMatcher.end();
        }
        if (quadruplet.size() != 4)
            throw new RuntimeException("Quadruplet does not contain 4 notes");
        
        return new Tuplet(TupletType.QUADRUPLET, quadruplet);
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
    		case 1: return Accidental.DOUBLEFLAT; 
    		case 2: return Accidental.FLAT;
    		case 3: return Accidental.DOUBLESHARP;
    		case 4: return Accidental.SHARP;
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