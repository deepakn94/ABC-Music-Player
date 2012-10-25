package sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates all data in a single voice of a musical piece. 
 */
public class Voice
{
    public static final String DEFAULT_VOICE_NAME = "DEFAULT_VOICE";
    
    private final List<Playable> elementsOfVoice; 
    private final String name;
    
    /**
     * Initializes a new voice with the given name and list of elements. 
     * @param name - must not be null or empty
     * @param elements - must be nonempty
     */
    public Voice(String name, List<Playable> elements)
    {
        this.name = name;
        elementsOfVoice = new ArrayList<Playable>(elements);
    }
    
    /**
     * @return the name of the Voice. 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gives all musical elements of the voice (Notes, Tuplets, etc.)
     * @return a List of all musical elements in the voice. 
     */
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
    
    /**
     * @return Finds the LCM of all the denominators of all the note lengths in this voice. 
     */
    public int findLCMOfNoteDenoms()
    {
        int lcm = -1; 
        for(int i = 0; i < elementsOfVoice.size(); i++)
        {
            int denomOfElement = elementsOfVoice.get(i).getLength().getDenom();
            if (i == 0)
            {
                lcm = denomOfElement;
            }
            
            else 
            {
                lcm = Utilities.LCM(lcm, denomOfElement); 
            }
        }
        
        return lcm;
    }
    
    /**
     * Puts together all the elements into a form that a Piece object can use to play itself. 
     * @param defaultNoteLength - the length of a default note in the piece, must be non-null
     * @param numTicksPerQuarter
     * @return List of SequencePlayerNote objects containing data necessary to play everything in the Voice. 
     */
    public List<SequencePlayerNote> play(RatNum defaultNoteLength, int numTicksPerQuarter) {
        
        int ticks = 0;
    	List<SequencePlayerNote> sequencePlayerNotes = new ArrayList<SequencePlayerNote> ();
    	for (Playable elementOfVoice: elementsOfVoice) {
    		sequencePlayerNotes.addAll(elementOfVoice.play(ticks, numTicksPerQuarter, defaultNoteLength)); //Need to handle timing here
    		RatNum length = elementOfVoice.getLength();
    		ticks += (numTicksPerQuarter * length.getNumer() * 4 * defaultNoteLength.getNumer())
    		        / (length.getDenom() * defaultNoteLength.getDenom());
    	}
    	return sequencePlayerNotes;
    }
}
