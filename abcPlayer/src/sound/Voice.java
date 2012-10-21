package sound;

import java.util.ArrayList;
import java.util.List;

public class Voice
{
    public static final String DEFAULT_VOICE_NAME = "DEFAULT_VOICE";
    
    private final List<Playable> elementsOfVoice; 
    private final String name;
    
    public Voice(String name, List<Playable> elements)
    {
        this.name = name;
        elementsOfVoice = new ArrayList<Playable>(elements); 
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public List<Playable> getElementsOfVoice()
    {
        return new ArrayList<Playable>(elementsOfVoice); 
    }
}
