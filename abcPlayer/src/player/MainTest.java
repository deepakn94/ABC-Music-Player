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
	
	@Test
	public void testNationalAnthem() {
	    SequencePlayer sp = play("sample_abc/national_anthem.abc");
	    Main.play("sample_abc/national_anthem.abc");
	    String notes = "Event: NOTE_ON  Pitch: 67  Tick: 0Event: NOTE_OFF Pitch: 67  Tick: 36Event: NOTE_ON  Pitch: 71  Tick: 36Event: NOTE_OFF Pitch: 71  Tick: 48Event: NOTE_ON  Pitch: 74  Tick: 48Event: NOTE_OFF Pitch: 74  Tick: 72Event: NOTE_ON  Pitch: 74  Tick: 72Event: NOTE_OFF Pitch: 74  Tick: 96Event: NOTE_ON  Pitch: 76  Tick: 96Event: NOTE_OFF Pitch: 76  Tick: 144Event: NOTE_ON  Pitch: 74  Tick: 144Event: NOTE_OFF Pitch: 74  Tick: 192Event: NOTE_ON  Pitch: 71  Tick: 192Event: NOTE_OFF Pitch: 71  Tick: 228Event: NOTE_ON  Pitch: 67  Tick: 228Event: NOTE_OFF Pitch: 67  Tick: 240Event: NOTE_ON  Pitch: 74  Tick: 240Event: NOTE_OFF Pitch: 74  Tick: 256Event: NOTE_ON  Pitch: 74  Tick: 256Event: NOTE_OFF Pitch: 74  Tick: 272Event: NOTE_ON  Pitch: 74  Tick: 272Event: NOTE_OFF Pitch: 74  Tick: 288Event: NOTE_ON  Pitch: 71  Tick: 288Event: NOTE_OFF Pitch: 71  Tick: 336Event: NOTE_ON  Pitch: 67  Tick: 336Event: NOTE_OFF Pitch: 67  Tick: 384Event: NOTE_ON  Pitch: 62  Tick: 384Event: NOTE_OFF Pitch: 62  Tick: 400Event: NOTE_ON  Pitch: 62  Tick: 400Event: NOTE_OFF Pitch: 62  Tick: 416Event: NOTE_ON  Pitch: 62  Tick: 416Event: NOTE_OFF Pitch: 62  Tick: 432Event: NOTE_ON  Pitch: 62  Tick: 432Event: NOTE_OFF Pitch: 62  Tick: 448Event: NOTE_ON  Pitch: 62  Tick: 448Event: NOTE_OFF Pitch: 62  Tick: 464Event: NOTE_ON  Pitch: 62  Tick: 464Event: NOTE_OFF Pitch: 62  Tick: 480Event: NOTE_ON  Pitch: 67  Tick: 480Event: NOTE_OFF Pitch: 67  Tick: 528Event: NOTE_ON  Pitch: 62  Tick: 552Event: NOTE_OFF Pitch: 62  Tick: 576Event: NOTE_ON  Pitch: 67  Tick: 576Event: NOTE_OFF Pitch: 67  Tick: 648Event: NOTE_ON  Pitch: 67  Tick: 648Event: NOTE_OFF Pitch: 67  Tick: 672Event: NOTE_ON  Pitch: 67  Tick: 672Event: NOTE_OFF Pitch: 67  Tick: 708Event: NOTE_ON  Pitch: 67  Tick: 708Event: NOTE_OFF Pitch: 67  Tick: 720Event: NOTE_ON  Pitch: 62  Tick: 720Event: NOTE_OFF Pitch: 62  Tick: 744Event: NOTE_ON  Pitch: 64  Tick: 744Event: NOTE_OFF Pitch: 64  Tick: 756Event: NOTE_ON  Pitch: 66  Tick: 756Event: NOTE_OFF Pitch: 66  Tick: 768Event: NOTE_ON  Pitch: 67  Tick: 768Event: NOTE_OFF Pitch: 67  Tick: 816Event: NOTE_ON  Pitch: 67  Tick: 816Event: NOTE_OFF Pitch: 67  Tick: 864Event: NOTE_ON  Pitch: 71  Tick: 888Event: NOTE_OFF Pitch: 71  Tick: 912Event: NOTE_ON  Pitch: 67  Tick: 912Event: NOTE_OFF Pitch: 67  Tick: 936Event: NOTE_ON  Pitch: 69  Tick: 936Event: NOTE_OFF Pitch: 69  Tick: 948Event: NOTE_ON  Pitch: 71  Tick: 948Event: NOTE_OFF Pitch: 71  Tick: 960Event: NOTE_ON  Pitch: 74  Tick: 960Event: NOTE_OFF Pitch: 74  Tick: 1008Event: NOTE_ON  Pitch: 74  Tick: 1008Event: NOTE_OFF Pitch: 74  Tick: 1056Event: NOTE_ON  Pitch: 71  Tick: 1056Event: NOTE_OFF Pitch: 71  Tick: 1092Event: NOTE_ON  Pitch: 71  Tick: 1092Event: NOTE_OFF Pitch: 71  Tick: 1104Event: NOTE_ON  Pitch: 67  Tick: 1104Event: NOTE_OFF Pitch: 67  Tick: 1140Event: NOTE_ON  Pitch: 71  Tick: 1140Event: NOTE_OFF Pitch: 71  Tick: 1152Event: NOTE_ON  Pitch: 74  Tick: 1152Event: NOTE_OFF Pitch: 74  Tick: 1188Event: NOTE_ON  Pitch: 71  Tick: 1188Event: NOTE_OFF Pitch: 71  Tick: 1200Event: NOTE_ON  Pitch: 69  Tick: 1200Event: NOTE_OFF Pitch: 69  Tick: 1248Event: NOTE_ON  Pitch: 69  Tick: 1248Event: NOTE_OFF Pitch: 69  Tick: 1344***** End of track *****   Tick: 1344";
		System.out.println(sp.toString().replace("\n", ""));
		assertEquals(sp.toString().replace("\n", ""), notes);        
	}

	@Test
	public void testFurElisePlay() {
		Main.play("sample_abc/fur_elise.abc");
	}
	
	@Test
	public void testInventionPlay() {
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
