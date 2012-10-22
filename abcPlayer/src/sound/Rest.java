package sound;

import java.util.ArrayList;
import java.util.List;

public class Rest implements Playable
{
    private final RatNum length; 
    
    public Rest(RatNum length)
    {
        this.length = length; 
    }
    
    public RatNum getLength()
    {
        return length; 
    }
    
    @Override
    public String toString() {
    	String rest = "Rest(" + (length.toString()) + ")";
    	return rest;
    }

	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
		return new ArrayList<SequencePlayerNote> ();
	}
}
