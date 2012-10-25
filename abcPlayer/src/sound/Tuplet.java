package sound;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a tuplet object in music. 
 * @author Arjun
 *
 */
public class Tuplet implements Playable 
{
    private final TupletType type; 
    private final List<Note> notes; 
    
    /**
     * Creates a new tuplet object with the following data. 
     * @param type - must not be null 
     * @param notes - must be of size >= 2
     */
    public Tuplet(TupletType type, List<Note> notes)
    {
        this.type = type; 
        this.notes = new ArrayList<Note>(notes);
    }
    
    /** Gives all notes within the tuplet. 
     * @return a copy of this Tuplet's list of notes. Does not return the actual list. 
     */
    public List<Note> getNotes()
    {
        return new ArrayList<Note>(notes);
    }
    
    /**
     * Gets the type of the tuplet 
     * @return what type the tuplet is (triplet, duplet, etc.)
     */
    public TupletType getType()
    {
        return type; 
    }
    
    public String toString() {
        String s = "Tuplet(" ;
        for (Note n : this.notes)
            s = s + n.toString();
        s = s.trim() + ")";
        return s;   
    }
    
    /**
     * Converts the tuplet into a form that a Piece can use to build itself. 
     * @param startTicks - the tick value that the final SequencePlayer will begin playing this tuplet at
     *                     must be nonnegative  
     * @param numTicks - the number of ticks the final SequencePlayer allocates for a default length note 
     *                      must be nonnegative 
     * @param defaultNoteLength - the length of a default note in the piece, must be non-null
     * @returns List of SequencePlayerNote objects containing data necessary to play the tuplet. 
     */
	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
		List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
		int ticks = startTicks;
		for (Note note:notes) {
			sequencePlayerNotes.addAll(note.play(ticks, numTicks, defaultNoteLength));
			int noteTicks = (numTicks * note.getLength().getNumer() * defaultNoteLength.getNumer() * 4)/
			        (note.getLength().getDenom() * defaultNoteLength.getDenom());
			ticks += noteTicks;
		}
		return sequencePlayerNotes;
	}
	
	/**
	 * Gives the totaled length of all notes in the tuplet 
	 * @return a RatNum object representing the combined length of all the notes in the tuplet. 
	 */
	public RatNum getLength() {
		RatNum tupletLength = new RatNum(0,1);
		for (Note note: notes) {
			int numer1 = tupletLength.getNumer();
			int denom1 = tupletLength.getDenom();
			int numer2 = note.getLength().getNumer();
			int denom2 = note.getLength().getDenom();
			tupletLength = new RatNum((numer1*denom2 + numer2*denom1), denom1*denom2);
		}
		return tupletLength;
	}
}
