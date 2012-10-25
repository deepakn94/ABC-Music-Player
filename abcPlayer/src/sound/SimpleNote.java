package sound;

public class SimpleNote 
{
    private final NoteType noteType;
    private final int numOctavesAboveMiddleC;
    
    public SimpleNote(NoteType noteType, int numOctavesAboveMiddleC)
    {
        this.noteType = noteType; 
        this.numOctavesAboveMiddleC = numOctavesAboveMiddleC;
    }
    
    public NoteType getNoteType()
    {
        return noteType;
    }
    
    public int getNumOctavesAboveMiddleC()
    {
        return numOctavesAboveMiddleC;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof SimpleNote))
        {
            throw new IllegalArgumentException("Incompatabile type");
        }
        
        SimpleNote toCompare = (SimpleNote) o;
        return toCompare.noteType == this.noteType && toCompare.numOctavesAboveMiddleC == this.numOctavesAboveMiddleC;
    }
    
    @Override
    public int hashCode()
    {
        int result = 17;
        int noteTypeHashCode = noteType.ordinal();
        int numOctavesHashCode = numOctavesAboveMiddleC;
        result = 31 * result + noteTypeHashCode;
        result = 31 * result + numOctavesHashCode; 
        
        return result; 
    }
}
