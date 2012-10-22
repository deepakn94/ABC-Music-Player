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
    		newStringBuilder.append(elementOfVoice.toString() + "\n");
    	}
    	return newStringBuilder.toString();
    }
    
    public List<SequencePlayerNote> play(int numTicks) {
    	int ticks = 0;
    	List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
    	for (Playable elementOfVoice: elementsOfVoice) {
    		sequencePlayerNotes.addAll(elementOfVoice.play(ticks, numTicks)); //Need to handle timing here
    		RatNum length = elementOfVoice.getLength();
    		ticks += (numTicks * (length.getNumer()/length.getDenom()));
    	}
    	return sequencePlayerNotes;
    }
}
