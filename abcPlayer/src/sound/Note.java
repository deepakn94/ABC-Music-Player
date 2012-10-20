package sound;

public class Note implements Playable 
{
    private final NoteType noteBase; 
    private final Accidental accidental; 
    private final int octavesAboveMiddleC; 
    private final RatNum noteLength; 
    
    public Note(NoteType noteBase, int octaves, RatNum noteLength, Accidental accidental)
    {
        this.noteBase = noteBase; 
        this.accidental = accidental;
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
    }
    
    public NoteType getBaseNoteType()
    {
        return noteBase;
    }
    
    public Accidental getAccidental()
    {
        return accidental;
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
