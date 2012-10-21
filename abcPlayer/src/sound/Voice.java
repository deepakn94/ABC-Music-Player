package sound;

import java.util.ArrayList;
import java.util.List;

public class Voice
{
    public static final String DEFAULT_VOICE_NAME = "DEFAULT_VOICE";
    
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
