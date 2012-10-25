package sound;

/**
 * A simple intermediate object that is used to store data necessary to construct a SequencePlayer for the piece. 
 */
public class SequencePlayerNote {
	private Pitch notePitch;
	private int startTicks;
	private int numTicks;
	
	/**
	 * Initializes a new SequencePlayerNote with the following pieces of data
	 * @param notePitch - must not be null 
	 * @param startTicks - must be greater than or equal to 0 
	 * @param numTicks - must be greater than 0
	 */
	public SequencePlayerNote(Pitch notePitch, int startTicks, int numTicks) {
		this.notePitch = notePitch;
		this.startTicks = startTicks;
		this.numTicks = numTicks;
	}
	
	//Note: The following methods are trivial and don't need specifications. 
	
	public Pitch getPitch() {
		return this.notePitch;
	}
	
	public int getStartTicks() {
		return this.startTicks;
	}
	
	public int getNumTicks() {
		return this.numTicks;
	}
}
