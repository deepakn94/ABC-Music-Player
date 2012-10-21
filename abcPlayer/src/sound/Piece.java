package sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Piece 
{   
    private final List<Voice> voices;
  
    //Required header fields
    private final String title; 
    private final Key keySignature;
    private final int indexNumber; 
    
    //Optional header fields
    private String composer;
    private RatNum noteLength; 
    private int tempo;  
    
    //Default header field values 
    private static final int DEFAULT_TEMPO = 100;
    private static final RatNum DEFAULT_NOTE_LENGTH = new RatNum(1, 8);
    private static final String DEFAULT_COMPOSER_VAL = "UNSPECIFIED";
    
    public Piece(List<Voice> voices, int indexNumber, String title, Key key)
    {
        this.voices = new ArrayList<Voice>(voices); 
        
        //Required fields
        this.title = title; 
        this.indexNumber = indexNumber; 
        this.keySignature = key; 
        
        this.setTempo(DEFAULT_TEMPO); 
        this.setDefaultNoteLength(DEFAULT_NOTE_LENGTH); 
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
   
    public List<Voice> getVoices()
    {
        return new ArrayList<Voice>(voices);
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


    public void setDefaultNoteLength(RatNum noteLength) {
        this.noteLength = noteLength;
    }


    public int getTempo() {
        return tempo;
    }


    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
