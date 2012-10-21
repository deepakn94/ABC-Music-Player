package sound;

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
    
    //Default header field values 
    public static final int DEFAULT_TEMPO = 100;
    public static final RatNum DEFAULT_NOTE_LENGTH = new RatNum(1, 8);
    public static final String DEFAULT_COMPOSER_VAL = "UNSPECIFIED";
    
    public Header(String title, Key keySignature, int indexNumber)
    {
      
        this.title = title; 
        this.indexNumber = indexNumber; 
        this.keySignature = keySignature; 
        
        this.setTempo(DEFAULT_TEMPO); 
        this.setNoteLength(DEFAULT_NOTE_LENGTH); 
        this.setComposer(DEFAULT_COMPOSER_VAL);
    }
    
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
}
