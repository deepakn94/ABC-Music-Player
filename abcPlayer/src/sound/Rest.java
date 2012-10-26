package sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rest object in music. 
 * @author Arjun
 *
 */
public class Rest implements Playable
{
    private final RatNum length; 
    
    /**
     * Creates a rest of the specified length. 
     * @param length - must not be null 
     */
    public Rest(RatNum length)
    {
        this.length = length; 
    }
    
    /**
     * Returns the length of the rest. 
     * @return a RatNum object representing the length. 
     */
    public RatNum getLength()
    {
        return length; 
    }
    
 
    @Override
    public String toString() {
    	String rest = "Rest(" + (length.toString()) + ")";
    	return rest;
    }

    /**
     * Converts the rest into a form that a Piece can use to build itself. 
     * @param startTicks - the tick value that the final SequencePlayer will begin playing this rest at
     *                     must be nonnegative  
     * @param numTicks - the number of ticks the final SequencePlayer allocates for a default length note 
     *                      must be nonnegative 
     * @param defaultNoteLength - the length of a default note in the piece, must be non-null
     * @returns List of SequencePlayerNote objects containing data necessary to play the rest. 
     */
	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
		return new ArrayList<SequencePlayerNote>();
	}
}
