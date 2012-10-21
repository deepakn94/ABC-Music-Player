package sound;

import java.util.ArrayList;
import java.util.List;

public class Tuplet implements Playable 
{
    private final TupletType type; 
    private final List<Note> notes; 
    
    public Tuplet(TupletType type, List<Note> notes)
    {
        this.type = type; 
        this.notes = new ArrayList<Note>(notes);
    }
    
    public List<Note> getNotes()
    {
        return new ArrayList<Note>(notes);
    }
    
    public TupletType getType()
    {
        return type; 
    }
    
    public String toString() {
        String s = "Tuplet(" ;
        for (Note n : this.notes)
            s = s + n.toString() + " ";
        s = s.trim() + ")\n";
        return s;   
    }

	@Override
	public List<SequencePlayerNote> play(int startTicks, int numTicks) {
		List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
		int ticks = startTicks;
		for (Note note:notes) {
			sequencePlayerNotes.addAll(note.play(ticks, numTicks));
			ticks += (numTicks * (note.getNoteLength().getNumer()/note.getNoteLength().getDenom()));
		}
		return sequencePlayerNotes;
	}
}
