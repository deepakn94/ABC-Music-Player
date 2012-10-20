package sound;

import java.util.ArrayList;
import java.util.List;

public class Voice
{
    private final List<Playable> elementsOfVoice; 
    
    public Voice(List<Playable> elements)
    {
        elementsOfVoice = new ArrayList<Playable>(elements); 
    }
    
    public List<Playable> getElementsOfVoice()
    {
        return new ArrayList<Playable>(elementsOfVoice); 
    }
}
