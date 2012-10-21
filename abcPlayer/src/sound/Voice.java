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
    
    @Override
    public String toString() {
    	StringBuilder newStringBuilder = new StringBuilder();
    	newStringBuilder.append("Voice("+name+")" + "\n");
    	for (Playable elementOfVoice: elementsOfVoice) {
    		newStringBuilder.append(elementOfVoice.toString() + " ");
    	}
    	return newStringBuilder.toString();
    }
}
