package sound;

import java.util.HashMap;
import java.util.HashSet;

public class Piece 
{
    public static HashMap<Key, HashSet<SimpleNote>> keyMappings = 
            new HashMap<Key, HashSet<SimpleNote>>()
            {
        
            };
    
    public Piece()
    {
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
        keyMappings.put(Key.C_MAJOR, new HashSet<SimpleNote>(){});
        keyMappings.put(Key.A_MINOR, new HashSet<SimpleNote>());
        
        HashSet<SimpleNote> set = new HashSet<SimpleNote>();
        set.add(new SimpleNote(NoteType.B, Accidental.FLAT));
        keyMappings.put(Key.B_MINOR, new HashSet<SimpleNote>());
       
        keyMappings.put(Key.F_MAJOR, set);
        keyMappings.put(Key.D_MINOR, new HashSet<SimpleNote>(set));
        
        //keyMappings.put(Key.C_MAJOR, new HashSet<SimpleNote>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
        //keyMappings.put(Key.C_MAJOR, new HashSet<Accidental>());
       
        
        
    }
}
