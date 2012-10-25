package sound;

import static org.junit.Assert.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

public class ParserTest {
    
    @Test
    public void testNote() {
        String test1 = "X:1\nT:Simple scale\nC:Unknown\nQ:120\nK:C\nC C2 C,3 =C4 |";
        
        Lexer lex = new Lexer(test1);
        
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = "Index Number : 1\nTitle : Simple scale\nComposer : Unknown\nNote length : 1/8\nTempo : 120\nKey Signature : C_MAJOR\n" +
        		"Voice(DEFAULT_VOICE)\nNote(C 0 1/1 ABSENT) \nNote(C 0 2/1 ABSENT) \nNote(C -1 3/1 ABSENT) \nNote(C 0 4/1 NATURAL) \n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void testChord() {
        String test1 = "X:1\nT:Random\nK:D\nD,,8 |";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = "Index Number : 1\nTitle : Random\nComposer : Unspecified\nNote length : 1/8\nTempo : 100\nKey Signature : D_MAJOR\n" +
        		"Voice(DEFAULT_VOICE)\nNote(D -2 8/1 ABSENT) \n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    // Missing Index
    @Test(expected=RuntimeException.class)
    public void MissingIndex() {
        String test1 = "T:A Song\nC:Youyang, Deepak, Arjun\nK:D\nDEFG |";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        parser1.Parse(); 
    }
       
    // Missing Index
    @Test(expected=RuntimeException.class)
    public void MissingTitle() {
        String test1 = "X:10240\nL:1/8\nC:Youyang, Deepak, Arjun\nK:D\nDEFG |";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        parser1.Parse(); 
    }
    
    // Key Signature not last
    @Test(expected=RuntimeException.class)
    public void KeySigNotLast() {
        String test1 = "X:10240\nT:A bad song\nL:1/8\nK:D\nDEFG\nC:6.005 Student\nAAAA |";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        parser1.Parse(); 
    }
}
