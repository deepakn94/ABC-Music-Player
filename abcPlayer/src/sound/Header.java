package sound;

/**
 * Encapsulates all the data stored in the header of an abc file. 
 * NOTE: Unlike most of the other data types we used, this objecgt is mutable. 
 * More specifically, there are setters for the optional field values. Our constructor, then 
 * takes all the required field values as parameters. This means that users will not be able to create a header without all 
 * the required data. They can then set any optional data that they want. 
 */
public class Header 
{
    //Required header fields
    private final String title; 
    private final Key keySignature;
    private final int indexNumber; 
    
    //Optional header fields
    private String composer;
    private RatNum noteLength; 
    private int tempo;  
    private String meter;
    
    //Default header field values 
    public static final int DEFAULT_TEMPO = 100;
    public static final RatNum DEFAULT_NOTE_LENGTH = new RatNum(1, 8);
    public static final String DEFAULT_COMPOSER_VAL = "UNSPECIFIED";
    public static final String DEFAULT_METER = "4/4"; 
    
    /**
     * Initializes a new header object. 
     * @param title - must not be null
     * @param keySignature - must not be null 
     * @param indexNumber 
     */
    public Header(String title, Key keySignature, int indexNumber)
    {
        this.title = title; 
        this.indexNumber = indexNumber; 
        this.keySignature = keySignature; 
        
        this.setTempo(DEFAULT_TEMPO); 
        this.setNoteLength(DEFAULT_NOTE_LENGTH); 
        this.setComposer(DEFAULT_COMPOSER_VAL);
    }
    
    //NOTE: The getters and setters are self-explanatory. Hence, specifications have not been provided. 
    
    public String getTitle()
    {
        return title; 
    }
    
    public Key getKeySignature()
    {
        return keySignature;
    }
    
    public int getIndexNumber()
    {
        return indexNumber; 
    }
    
    public String getComposer() {
        return composer;
    }


    public void setComposer(String composer) {
        this.composer = composer;
    }


    public RatNum getDefaultNoteLength() {
        return noteLength;
    }


    public void setNoteLength(RatNum noteLength) {
        this.noteLength = noteLength;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
    
    @Override 
    public String toString() {
        String result = "Index Number : " + indexNumber + "\n";
    	result += "Title : " + title + "\n";
    	result += "Composer : " + composer + "\n";
    	result += "Note length : " + noteLength + "\n";
    	result += "Tempo : " + tempo + "\n";
        result += "Key Signature : " + keySignature + "\n";
    	return result;
    }
}
