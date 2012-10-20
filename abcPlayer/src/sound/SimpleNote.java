package sound;

public class SimpleNote 
{
    private final NoteType noteType;
    private final Accidental accidental;
    
    public SimpleNote(NoteType noteType, Accidental accidental)
    {
        this.noteType = noteType; 
        this.accidental = accidental;
    }
    
    public NoteType getNoteType()
    {
        return noteType;
    }
    
    public Accidental getAccidental()
    {
        return accidental;
    }
}
