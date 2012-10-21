package sound;

public class Note implements Playable 
{
    private final NoteType noteBase; 
    private final Accidental accidental; 
    private final int octavesAboveMiddleC; 
    private RatNum noteLength; 
    
    public Note(NoteType noteBase, int octaves, RatNum noteLength)
    {
        this.noteBase = noteBase; 
        this.octavesAboveMiddleC = octaves; 
        this.noteLength = noteLength; 
        this.accidental = Accidental.ABSENT; 
    }
    
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
    
    public void setNoteLength(RatNum noteLength)
    {
        this.noteLength = noteLength; 
    }
    
    public String toString()
    {
        return noteBase.toString() + octavesAboveMiddleC + noteLength.toString() + accidental.toString();
    }
}
