package sound;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleNoteTest 
{
    @Test 
    public void testHashCode()
    {
        SimpleNote note = new SimpleNote(NoteType.C, 1); 
        SimpleNote note2 = new SimpleNote(NoteType.C, 1); 
        assertEquals(note.hashCode(), note2.hashCode());
    }
    
    @Test
    public void testHashCodeNegativeOctave()
    {
        SimpleNote note = new SimpleNote(NoteType.D, -1); 
        SimpleNote note2 = new SimpleNote(NoteType.D, -1); 
        assertEquals(note.hashCode(), note2.hashCode());
    }
    
    @Test
    public void testUnequalHashCodes()
    {
        SimpleNote note = new SimpleNote(NoteType.E, -1); 
        SimpleNote note2 = new SimpleNote(NoteType.D, -1); 
        assertTrue(note.hashCode() != note2.hashCode());
    }

}
