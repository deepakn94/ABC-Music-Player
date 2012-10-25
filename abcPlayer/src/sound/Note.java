package sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single note in a music piece.  
 * NOTE: This object was chosen to be mutable because it was convenient when handling Tuplets. 
 * We needed to do a base parsing and then modify the length to make sure it was valid.  
 * Also, the cost of adding the one setter was not very large. The code was still very easy to reason about. 
 * @author Arjun
 *
 */
public class Note implements Playable 
{
    private final NoteType noteBase; 
    private final Accidental accidental; 
    private final int octavesAboveMiddleC; 
    private RatNum noteLength; 
    
    /**
     * Initializes a new note with the specified properties. 
     * @param noteBase - represents the basic note letter, must not be null
     * @param octaves - represents the octaves above/below the octave containing middle C. 
     *                  0 indicates the middle C octave, negative integers correspond to octaves below and positive integers 
     *                  correspond to octaves above.  
     * @param noteLength - must not be null 
     */
    public Note(NoteType noteBase, int octaves, RatNum noteLength)
    {
        this.noteBase = noteBase; 
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
        this.accidental = Accidental.ABSENT; 
    }
    
    /**
     * An overloaded constructor for a note containing an accidental.
     * @param noteBase - represents the basic note letter, must not be null
     * @param octaves - represents the octaves above/below the octave containing middle C. 
     *                  0 indicates the middle C octave, negative integers correspond to octaves below and positive integers 
     *                  correspond to octaves above 
     * @param noteLength - must not be null 
     * @param Accidental - must not be null 
     */
    public Note(NoteType noteBase, int octaves, RatNum noteLength, Accidental accidental)
    {
        this.noteBase = noteBase; 
        this.accidental = accidental;
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
    }
    
    /**
     * Gives the letter of the note
     * @return a NoteType variable representing the letter of the note. 
     */
    public NoteType getBaseNoteType()
    {
        return noteBase;
    }
    
    /**
     * Gives the accidental
     * @return an Accidental variable representing the Accidental. Will be Accidental.ABSENT 
     * if the note does not contain an Accidental.
     */
    public Accidental getAccidental()
    {
        return accidental;
    }
    
    /**
     * Gives the number of octaves the note is below/above the octave containing middle c. 
     * @return an integer variable representing the number of octaves above/below middle c.
     *         0 indicates the middle C octave, negative integers correspond to octaves below and positive integers 
     *         correspond to octaves above 
     */
    public int getOctavesAboveMiddleC()
    {
        return octavesAboveMiddleC;
    }
    
    /**
     * Sets the note length to a new value. 
     * @param noteLength - must not be null 
     */
    public void setNoteLength(RatNum noteLength)
    {
        this.noteLength = noteLength; 
    }
    
    @Override
    public String toString()
    {
        return "Note(" + noteBase.toString() + " " + octavesAboveMiddleC + " " + noteLength.toString() + " " + accidental.toString() + ")";
    }
    
    /**
     * Converts the note into a form that a Piece can use to build itself. 
     * @param startTicks - the tick value that the final SequencePlayer will begin playing this note at
     *                     must be nonnegative  
     * @param numTicks - the number of ticks the final SequencePlayer allocates for a default length note 
     *                      must be nonnegative 
     * @param defaultNoteLength - the length of a default note in the piece, must be non-null
     * @return a List of SequencePlayerNotes containing data needed to play this note (the start ticks and the duration of the ticks)
     */
    public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
    	List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
    	int numer = this.noteLength.getNumer();
    	int denom = this.noteLength.getDenom();
    	int defaultDenom = defaultNoteLength.getDenom(); 
    	int defaultNum = defaultNoteLength.getNumer(); 
    	int ticks = (numer * 4 * numTicks * defaultNum)/(denom * defaultDenom);
    	
    	Pitch notePitch;
    	switch (noteBase) {
    	case C:
    		notePitch = new Pitch('C');
    		break;
    	case D:
    		notePitch = new Pitch('D');
    		break;
    	case E:
    		notePitch = new Pitch('E');
    		break;
    	case F:
    		notePitch = new Pitch('F');
    		break;
    	case G:
    		notePitch = new Pitch('G');
    		break;
    	case A:
    		notePitch = new Pitch('A');
    		break;
    	case B:
    		notePitch = new Pitch('B');
    		break;
    	default:
    		throw new RuntimeException("Illegal note");
    		
    	}
    	int numTranspose = Pitch.OCTAVE * octavesAboveMiddleC;
    	
    	switch (accidental) {
    	case SHARP:
    		numTranspose += 1;
    		break;
    	case DOUBLESHARP:
    		numTranspose += 2;
    		break;
    	case FLAT:
    		numTranspose -= 1;
    		break;
    	case DOUBLEFLAT:
    		numTranspose -= 2;
    		break;
    	case NATURAL:
    	case ABSENT:
    		break;
    	default:
    		throw new IllegalArgumentException("Illegal accidental");
    	}
    	
    	sequencePlayerNotes.add(new SequencePlayerNote(notePitch.transpose(numTranspose), startTicks, ticks));
		return sequencePlayerNotes;
    }
    
    /**
     * Gets the length of the note. 
     * @return a RationalNumber representing the note length (as a fraction of the default note length of course). 
     */
    public RatNum getLength() {
    	return this.noteLength;
    }
}
