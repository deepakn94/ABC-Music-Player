package sound;

import java.util.List;
import java.util.ArrayList;

public class Chord implements Playable
{
    private List<Note> notes;
    
    public Chord(List<Note> notes)
    {
        this.notes = new ArrayList<Note>(notes);
    }
    
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

	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength) {
    	List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
		for (Note note:notes) {
			sequencePlayerNotes.addAll(note.play(startTicks, numTicks, defaultNoteLength));
		}
		return sequencePlayerNotes;
	}
    
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

	
