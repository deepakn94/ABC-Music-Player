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
        String s = "";
        for (Note n : this.notes)
            s = s + n.toString();
        return s;
        
    }
}
