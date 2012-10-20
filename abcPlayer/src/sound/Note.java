package sound;

public class Note implements Playable
{
    private final NoteType noteBase; 
    private final int octavesAboveMiddleC; 
    private final RatNum noteLength; 
    
    public Note(NoteType noteBase, int octaves, RatNum noteLength)
    {
        this.noteBase = noteBase;
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
    }
    
    public NoteType getBaseNoteType()
    {
        return noteBase; 
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
