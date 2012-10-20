package sound;

import java.util.HashMap;
import java.util.HashSet;

public class Piece 
{
    public static HashMap<Key, HashSet<SimpleNote>> keyMappings = 
            new HashMap<Key, HashSet<SimpleNote>>();
    
    public Piece()
    {
        initializeKeyMappings();
    }
    
    /*
     * G - F#
     * D - F#, C# 
     * A - F#, C#, G#
     * E - F#, C#, G#, D#
     * B - C#, D#, F#, G#, A#, 
     */
    private static void initializeKeyMappings()
    {   
        //C major and A Minor 
        keyMappings.put(Key.C_MAJOR, new HashSet<SimpleNote>());
        keyMappings.put(Key.A_MINOR, new HashSet<SimpleNote>());
        
        //G major and E minor
        HashSet<SimpleNote> set = new HashSet<SimpleNote>();
        set.add(new SimpleNote(NoteType.F, Accidental.SHARP));
        keyMappings.put(Key.G_MAJOR, set);
        keyMappings.put(Key.E_MINOR, new HashSet<SimpleNote>(set));
        
        //F major and D minor
        set = new HashSet<SimpleNote>();
        set.add(new SimpleNote(NoteType.B, Accidental.FLAT));
        keyMappings.put(Key.F_MAJOR, set);
        keyMappings.put(Key.D_MINOR, new HashSet<SimpleNote>(set));
        
        //D major and B minor
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.C, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.F, Accidental.SHARP));
        keyMappings.put(Key.D_MAJOR, set); 
        keyMappings.put(Key.B_MINOR, new HashSet<SimpleNote>(set));
        
        //G minor 
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.B, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.E, Accidental.FLAT));
        keyMappings.put(Key.G_MINOR, set); 
        
        //A major 
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.C, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.F, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.G, Accidental.SHARP));
        keyMappings.put(Key.A_MAJOR, set); 
        
        //C minor
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.A, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.B, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.E, Accidental.FLAT));
        keyMappings.put(Key.C_MINOR, set); 
        
        //E major
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.C, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.D, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.F, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.G, Accidental.SHARP));
        keyMappings.put(Key.E_MAJOR, set); 
        
        //F minor
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.A, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.B, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.D, Accidental.FLAT));
        set.add(new SimpleNote(NoteType.E, Accidental.FLAT));
        keyMappings.put(Key.F_MINOR, set); 
        
        //B major
        set = new HashSet<SimpleNote>(); 
        set.add(new SimpleNote(NoteType.A, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.C, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.D, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.F, Accidental.SHARP));
        set.add(new SimpleNote(NoteType.G, Accidental.SHARP));
        keyMappings.put(Key.B_MAJOR, set);    
    }
}
