package sound;

import java.util.List;

public interface Playable {
	public String toString();
	public List<SequencePlayerNote> play(int startTicks, int numTicks);
	public RatNum getLength();
}
