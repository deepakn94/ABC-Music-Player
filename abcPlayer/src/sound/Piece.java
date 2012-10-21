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
    
    
    public Piece(List<Voice> voices, int indexNumber, String title, Key key, String composer, RatNum len, int tempo)
    {
        this.voices = new ArrayList<Voice>(voices); 
        
        this.title = title; 
        this.indexNumber = indexNumber; 
        this.keySignature = key; 
        
        this.tempo = tempo; 
        this.noteLength = len; 
        this.composer = composer;
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
