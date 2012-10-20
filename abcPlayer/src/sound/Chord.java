package sound;

import java.util.List;
import java.util.ArrayList;

public class Chord
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
}
