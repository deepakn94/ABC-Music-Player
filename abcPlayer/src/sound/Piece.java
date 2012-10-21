package sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Piece 
{   
    private final List<Voice> voices;
    
    public Piece(List<Voice> voices)
    {
        this.voices = new ArrayList<Voice>(voices); 
    }
    
    public List<Voice> getVoices()
    {
        return new ArrayList<Voice>(voices);
    }
}
