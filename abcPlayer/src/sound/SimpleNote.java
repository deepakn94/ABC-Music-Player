package sound;

/**
 * Represents just the letter and octave value of a note. This is used to keep track of accidentals in the piece. 
 * (If we see an accidental on a specific note, we need to keep track of that note's letter and octave level until the end of the bar). 
 * NOTE: We could have had the Note class inherit from this class. However, we wanted to keep them completely separate since they are used for totally 
 * different purposes. 
 * @author Arjun
 *
 */
public class SimpleNote 
{
    private final NoteType noteType;
    private final int numOctavesAboveMiddleC;
    
    /**
     * Creates a new SimpleNote object with the specified note letter and octave level. 
     * @param noteType - must not be null
     * @param numOctavesAboveMiddleC - negative numbers correspond to octaves below middle C and positive 
     * numbers correspond to octaves above middle C. 
     */
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
