package player;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.ABCFileReader;
import sound.Lexer;
import sound.Parser;
import sound.Piece;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * <p>Your code <b>should not</b> exit the application abnormally using
	 * System.exit()</p>
	 * 
	 * @param file the name of input abc file
	 */
	public static void play(String file) {
        ABCFileReader file_reader = new ABCFileReader(file);
        try {
            String content = file_reader.readContent();
            //System.out.println(content);
            
            Lexer newLexer = new Lexer(content);
            Parser parser = new Parser(newLexer);
            
            Piece pieceToPlay = parser.Parse(); 
            System.out.println(pieceToPlay.getHeader());
            try {
                pieceToPlay.play();
            } catch (MidiUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidMidiDataException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
