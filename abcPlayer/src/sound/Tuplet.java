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
            s = s + n.toString();
        s = s + ")";
        return s;   
    }
}
