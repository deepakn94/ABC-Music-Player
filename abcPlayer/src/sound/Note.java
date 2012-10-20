package sound;

public class Note extends SimpleNote implements Playable 
{
    private final int octavesAboveMiddleC; 
    private final RatNum noteLength; 
    
    public Note(NoteType noteBase, int octaves, RatNum noteLength, Accidental accidental)
    {
        super(noteBase, accidental);
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
    }
    
    public int getOctavesAboveMiddleC()
    {
        return octavesAboveMiddleC;
    }
    
    public RatNum getNoteLength()
    {
        return noteLength; 
    }
}
