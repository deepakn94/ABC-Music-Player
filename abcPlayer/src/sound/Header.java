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
    private String meter;
    
    //Default header field values 
    public static final int DEFAULT_TEMPO = 100;
    public static final RatNum DEFAULT_NOTE_LENGTH = new RatNum(1, 8);
    public static final String DEFAULT_COMPOSER_VAL = "UNSPECIFIED";
    public static final String DEFAULT_METER = "4/4"; 
    
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

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
}
