package player;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.ABCFileReader;
import sound.Lexer;
import sound.Parser;
import sound.Piece;
import sound.SequencePlayer;

public class MainTest {
	
    /*
     * These are sequence player tests that we made to make sure all the notes are playing correctly 
     * and any errors are caught. We made three additional pieces, three of which are playable and two of which 
     * would cause an exception.
     */
	@Test
	public void testNationalAnthem() {
	    // We made this one for testing
	    SequencePlayer sp = play("sample_abc/national_anthem.abc");
	    // Main.play plays the music out loud. If it is commented we won't hear anything.
	    Main.play("sample_abc/national_anthem.abc");
	    String notes = "Event: NOTE_ON  Pitch: 67  Tick: 0Event: NOTE_OFF Pitch: 67  Tick: 36Event: NOTE_ON  Pitch: 71  Tick: 36Event: NOTE_OFF Pitch: 71  Tick: 48Event: NOTE_ON  Pitch: 74  Tick: 48Event: NOTE_OFF Pitch: 74  Tick: 72Event: NOTE_ON  Pitch: 74  Tick: 72Event: NOTE_OFF Pitch: 74  Tick: 96Event: NOTE_ON  Pitch: 76  Tick: 96Event: NOTE_OFF Pitch: 76  Tick: 144Event: NOTE_ON  Pitch: 74  Tick: 144Event: NOTE_OFF Pitch: 74  Tick: 192Event: NOTE_ON  Pitch: 71  Tick: 192Event: NOTE_OFF Pitch: 71  Tick: 228Event: NOTE_ON  Pitch: 67  Tick: 228Event: NOTE_OFF Pitch: 67  Tick: 240Event: NOTE_ON  Pitch: 74  Tick: 240Event: NOTE_OFF Pitch: 74  Tick: 256Event: NOTE_ON  Pitch: 74  Tick: 256Event: NOTE_OFF Pitch: 74  Tick: 272Event: NOTE_ON  Pitch: 74  Tick: 272Event: NOTE_OFF Pitch: 74  Tick: 288Event: NOTE_ON  Pitch: 71  Tick: 288Event: NOTE_OFF Pitch: 71  Tick: 336Event: NOTE_ON  Pitch: 67  Tick: 336Event: NOTE_OFF Pitch: 67  Tick: 384Event: NOTE_ON  Pitch: 62  Tick: 384Event: NOTE_OFF Pitch: 62  Tick: 400Event: NOTE_ON  Pitch: 62  Tick: 400Event: NOTE_OFF Pitch: 62  Tick: 416Event: NOTE_ON  Pitch: 62  Tick: 416Event: NOTE_OFF Pitch: 62  Tick: 432Event: NOTE_ON  Pitch: 62  Tick: 432Event: NOTE_OFF Pitch: 62  Tick: 448Event: NOTE_ON  Pitch: 62  Tick: 448Event: NOTE_OFF Pitch: 62  Tick: 464Event: NOTE_ON  Pitch: 62  Tick: 464Event: NOTE_OFF Pitch: 62  Tick: 480Event: NOTE_ON  Pitch: 67  Tick: 480Event: NOTE_OFF Pitch: 67  Tick: 528Event: NOTE_ON  Pitch: 62  Tick: 552Event: NOTE_OFF Pitch: 62  Tick: 576Event: NOTE_ON  Pitch: 67  Tick: 576Event: NOTE_OFF Pitch: 67  Tick: 648Event: NOTE_ON  Pitch: 67  Tick: 648Event: NOTE_OFF Pitch: 67  Tick: 672Event: NOTE_ON  Pitch: 67  Tick: 672Event: NOTE_OFF Pitch: 67  Tick: 708Event: NOTE_ON  Pitch: 67  Tick: 708Event: NOTE_OFF Pitch: 67  Tick: 720Event: NOTE_ON  Pitch: 62  Tick: 720Event: NOTE_OFF Pitch: 62  Tick: 744Event: NOTE_ON  Pitch: 64  Tick: 744Event: NOTE_OFF Pitch: 64  Tick: 756Event: NOTE_ON  Pitch: 66  Tick: 756Event: NOTE_OFF Pitch: 66  Tick: 768Event: NOTE_ON  Pitch: 67  Tick: 768Event: NOTE_OFF Pitch: 67  Tick: 816Event: NOTE_ON  Pitch: 67  Tick: 816Event: NOTE_OFF Pitch: 67  Tick: 864Event: NOTE_ON  Pitch: 71  Tick: 888Event: NOTE_OFF Pitch: 71  Tick: 912Event: NOTE_ON  Pitch: 67  Tick: 912Event: NOTE_OFF Pitch: 67  Tick: 936Event: NOTE_ON  Pitch: 69  Tick: 936Event: NOTE_OFF Pitch: 69  Tick: 948Event: NOTE_ON  Pitch: 71  Tick: 948Event: NOTE_OFF Pitch: 71  Tick: 960Event: NOTE_ON  Pitch: 74  Tick: 960Event: NOTE_OFF Pitch: 74  Tick: 1008Event: NOTE_ON  Pitch: 74  Tick: 1008Event: NOTE_OFF Pitch: 74  Tick: 1056Event: NOTE_ON  Pitch: 71  Tick: 1056Event: NOTE_OFF Pitch: 71  Tick: 1092Event: NOTE_ON  Pitch: 71  Tick: 1092Event: NOTE_OFF Pitch: 71  Tick: 1104Event: NOTE_ON  Pitch: 67  Tick: 1104Event: NOTE_OFF Pitch: 67  Tick: 1140Event: NOTE_ON  Pitch: 71  Tick: 1140Event: NOTE_OFF Pitch: 71  Tick: 1152Event: NOTE_ON  Pitch: 74  Tick: 1152Event: NOTE_OFF Pitch: 74  Tick: 1188Event: NOTE_ON  Pitch: 71  Tick: 1188Event: NOTE_OFF Pitch: 71  Tick: 1200Event: NOTE_ON  Pitch: 69  Tick: 1200Event: NOTE_OFF Pitch: 69  Tick: 1248Event: NOTE_ON  Pitch: 69  Tick: 1248Event: NOTE_OFF Pitch: 69  Tick: 1344***** End of track *****   Tick: 1344";
		assertEquals(sp.toString().replace("\n", ""), notes);        
	}

	@Test
	public void testScale() {
	    // We made this one for testing too
	    Main.play("sample_abc/arjun.abc");
	    String notes = "Event: NOTE_ON  Pitch: 64  Tick: 0Event: NOTE_OFF Pitch: 64  Tick: 12Event: NOTE_ON  Pitch: 66  Tick: 12Event: NOTE_OFF Pitch: 66  Tick: 24Event: NOTE_ON  Pitch: 68  Tick: 24Event: NOTE_OFF Pitch: 68  Tick: 36Event: NOTE_ON  Pitch: 69  Tick: 36Event: NOTE_OFF Pitch: 69  Tick: 48Event: NOTE_ON  Pitch: 69  Tick: 48Event: NOTE_OFF Pitch: 69  Tick: 60Event: NOTE_ON  Pitch: 71  Tick: 60Event: NOTE_OFF Pitch: 71  Tick: 72Event: NOTE_ON  Pitch: 61  Tick: 72Event: NOTE_OFF Pitch: 61  Tick: 84Event: NOTE_ON  Pitch: 63  Tick: 84Event: NOTE_OFF Pitch: 63  Tick: 96Event: NOTE_ON  Pitch: 64  Tick: 96Event: NOTE_OFF Pitch: 64  Tick: 108Event: NOTE_ON  Pitch: 66  Tick: 108Event: NOTE_OFF Pitch: 66  Tick: 120Event: NOTE_ON  Pitch: 68  Tick: 120Event: NOTE_OFF Pitch: 68  Tick: 132Event: NOTE_ON  Pitch: 69  Tick: 132Event: NOTE_OFF Pitch: 69  Tick: 144Event: NOTE_ON  Pitch: 71  Tick: 144Event: NOTE_OFF Pitch: 71  Tick: 156Event: NOTE_ON  Pitch: 60  Tick: 156Event: NOTE_OFF Pitch: 60  Tick: 168Event: NOTE_ON  Pitch: 63  Tick: 168Event: NOTE_OFF Pitch: 63  Tick: 180Event: NOTE_ON  Pitch: 64  Tick: 180Event: NOTE_OFF Pitch: 64  Tick: 192***** End of track *****   Tick: 192";
	    SequencePlayer sp = play("sample_abc/arjun.abc");
	    assertEquals(sp.toString().replace("\n", ""), notes);
	}
	
    // This is a bad piece of music that should throw an exception because the key is not last in the header
    @Test(expected=RuntimeException.class)
    public void MisindexedKey() {
        play("sample_abc/bad_piece.abc");
    }
    
    // This is a bad piece of music that should throw an exception because the voice is not specified
    @Test(expected=RuntimeException.class)
    public void UnrecognizedVoice() {
        Main.play("sample_abc/bad_piece2.abc");
    }

	// The tests below just plays music. Enjoy!
    
    @Test
    public void PlaySymNo5() {
        // Transcribed roughly
        Main.play("sample_abc/youyang.abc");
    }
    
	@Test
	public void PlayFurElise() {
	    // Uncomment to listen to how nice this sounds
		Main.play("sample_abc/fur_elise.abc");
	}
	
	@Test
	public void PlayInvention() {
		Main.play("sample_abc/invention.abc");
	}
	


	
	private SequencePlayer play(String file) {
        ABCFileReader file_reader = new ABCFileReader(file);
        try {
            String content = file_reader.readContent();
            
            Lexer newLexer = new Lexer(content);
            Parser parser = new Parser(newLexer);
            
            Piece pieceToPlay = parser.Parse(); 
            try {
                SequencePlayer sp = pieceToPlay.play();
                return sp;
                
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
