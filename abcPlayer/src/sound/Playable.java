package sound;
import java.util.List;

/**
 * Represents the fundamental elements of a piece of music (e.g. note, chord, etc.)
 * @author Arjun
 *
 */
public interface Playable {
	public String toString();
	public List<SequencePlayerNote> play(int startTicks, int numTicks, RatNum defaultNoteLength);
	public RatNum getLength();
}
