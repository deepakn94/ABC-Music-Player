package sound;

public class SequencePlayerNote {
	private Pitch notePitch;
	private int startTicks;
	private int numTicks;
	
	public SequencePlayerNote(Pitch notePitch, int startTicks, int numTicks) {
		this.notePitch = notePitch;
		this.startTicks = startTicks;
		this.numTicks = numTicks;
	}
	
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
