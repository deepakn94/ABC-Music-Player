package sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Piece 
{   
    private final List<Voice> voices;
    private final Header header;

    
    public Piece(List<Voice> voices, Header header)
    {
        this.voices = new ArrayList<Voice>(voices);
        this.header = header;
    }
    
    public List<Voice> getVoices()
    {
        return new ArrayList<Voice>(voices);
    }
    
    public Header getHeader()
    {
        return header; 
    }
}
