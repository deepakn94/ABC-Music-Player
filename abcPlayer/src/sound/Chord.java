package sound;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a chord in music. 
 */
public class Chord implements Playable
{
    private List<Note> notes;
    
    /**
     * Initializes a new immutable chord with the designated list of notes 
     * @param notes - Should not be null and should be of length >= 2 
     */
    public Chord(List<Note> notes)
    {
        this.notes = new ArrayList<Note>(notes);
        if (notes.size() < 2)
        {
            throw new IllegalArgumentException("A chord must contain at least two notes");
        }
    }
    
    /**
     * Gets the list of Note objects in the chord 
     * @return A copy of this chord's Note List. Does not return the actual list.  
     */
    public List<Note> getNotes()
    {
        return new ArrayList<Note>(notes); 
    }
    
    public String toString() {
        String s = "Chord(";
        for (Note n : this.notes)
            s = s + n.toString();
        s= s.trim() + ")";
        return s;
        
    }
    
    /**
     * Converts the chord into a form that a Piece can use to build itself. 
     * @param startTicks - the tick value that the final SequencePlayer will begin playing this chord at
     *                     must be nonnegative  
     * @param numTicks - the number of ticks the final SequencePlayer allocates for a default length note 
     *                      must be nonnegative 
     * @param defaultNoteLength - the length of a default note in the piece, must be non-null
     * @return a List of SequencePlayerNotes describing the start ticks and num ticks values for all notes within the Chord. 
     */
	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
    	List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
		for (Note note:notes) {
			sequencePlayerNotes.addAll(note.play(startTicks, numTicks, defaultNoteLength));
		}
		return sequencePlayerNotes;
	}
    
	/**
	 * Gets the maximum length of a note in the Chord 
	 * @return a RationalNumber representing the maximum length of a note (as a fraction of the default note length of course). 
	 */
    public RatNum getLength() {
    	double max = 0;
    	RatNum maxRatNum = null;
    	for (Note note : notes) {
    		double noteLength = (double)note.getLength().getNumer() / (double)note.getLength().getNumer();
    		if ((maxRatNum == null) || max<noteLength) {
    			maxRatNum = note.getLength();
    			max = noteLength;
    		} 
    	}
    	return maxRatNum;
    }
}

	
