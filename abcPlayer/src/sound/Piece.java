package sound;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Represents a piece object containing all data necessary to play the music. 
 *
 */
public class Piece 
{   
    private final List<Voice> voices;
    private final Header header;
    
    /**
     * Initializes a new piece object with the specified header data and the full information for all the voices
     * @param voices - must not be null or empty
     * @param header - most not be null 
     */
    public Piece(List<Voice> voices, Header header)
    {
        this.voices = new ArrayList<Voice>(voices);
        this.header = header;
    }
    
    public List<Voice> getVoices()
    {
        return new ArrayList<Voice>(voices);
    }
    
    public Header getHeader() {
        return header; 
    }
    
    @Override
    public String toString() {
    	StringBuilder newString = new StringBuilder();
    	newString.append(header.toString());
    	for (Voice voice:voices) {
    		newString.append(voice.toString());
    	}
    	return newString.toString();
    }
    
    /**
     * Finds the LCM of all note denominators in all voices. 
     * @return the LCM of all note denominators in the entire piece (across all the voices) 
     */
    private int findLCMOfAllNoteDenomsAcrossVoices()
    {
        int lcm = voices.get(0).findLCMOfNoteDenoms();
        for(int i = 1; i < voices.size(); i++)
        {
            lcm = Utilities.LCM(lcm, voices.get(i).findLCMOfNoteDenoms());
        }
        
        return lcm;
    }

    /**
     * Plays and returns the current piece. 
     * @return a SequencePlayer object for this piece. 
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     */
    public SequencePlayer play() throws MidiUnavailableException, InvalidMidiDataException {
        final int NUM_TICKS_PER_QUARTER = this.findLCMOfAllNoteDenomsAcrossVoices() * this.header.getDefaultNoteLength().getDenom() * 3;
        
		int tempo = (this.header.getTempo() * this.header.getDefaultNoteLength().getNumer() * 4)/(this.header.getDefaultNoteLength().getDenom());
		SequencePlayer sequencePlayer = new SequencePlayer(tempo, NUM_TICKS_PER_QUARTER);
		for (Voice voice : voices) {
			List<SequencePlayerNote> toPlay = voice.play(this.header.getDefaultNoteLength(), NUM_TICKS_PER_QUARTER);
			for (SequencePlayerNote spn: toPlay)
			{
			    sequencePlayer.addNote(spn.getPitch().toMidiNote(), spn.getStartTicks(), spn.getNumTicks());
			}
		}
		//sequencePlayer.play();
		return sequencePlayer;
    }
}
