package sound;

import static org.junit.Assert.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

public class ParserTest {
    
    final static String DEFAULT_HEADER = "X:1\nT:Simple scale\nC:Unknown\nQ:120\nK:C\n";
    final static String DEFAULT_HEADER_OUTPUT = "Index Number : 1\nTitle : Simple scale\nComposer : Unknown\nNote length : 1/8\nTempo : 120\n" +
            "Key Signature : C_MAJOR\nVoice(DEFAULT_VOICE)\n";
    
    @Test
    public void testNote() {
        String test1 = "X:1\nT:Simple scale\nC:Unknown\nQ:120\nK:C\nC C2 C,3 =C4 |";
        
        Lexer lex = new Lexer(test1);
        
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = "Index Number : 1\nTitle : Simple scale\nComposer : Unknown\nNote length : 1/8\nTempo : 120\nKey Signature : C_MAJOR\n" +
        		"Voice(DEFAULT_VOICE)\nNote(C 0 1/1 ABSENT)\nNote(C 0 2/1 ABSENT)\nNote(C -1 3/1 ABSENT)\nNote(C 0 4/1 NATURAL)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    // Check note lengths
    @Test
    public void NoteLengthCheck() {
        String test1 = "A1/4 A/4 A/ A A2 A3 A4 A6 z8";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Note(A 0 1/4 ABSENT)\nNote(A 0 1/4 ABSENT)\nNote(A 0 1/2 ABSENT)\nNote(A 0 1/1 ABSENT)\n"
                + "Note(A 0 2/1 ABSENT)\nNote(A 0 3/1 ABSENT)\nNote(A 0 4/1 ABSENT)\nNote(A 0 6/1 ABSENT)\nRest(8/1)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void testSameNoteDifferentPitchesWithAccidental()
    {
        String test1 = "X:10240\nT:A bad song\nL:1/4\nK:G\n =fFFf | fF";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        Piece p = parser1.Parse();
        String output1 = "Index Number : 10240\nTitle : A bad song\nComposer : Unspecified\nNote length : 1/4\nTempo : 100\nKey Signature : G_MAJOR\n" +
                "Voice(DEFAULT_VOICE)\nNote(F 1 1/1 NATURAL)\nNote(F 0 1/1 SHARP)\nNote(F 0 1/1 SHARP)\nNote(F 1 1/1 NATURAL)\nNote(F 1 1/1 SHARP)\n"
                + "Note(F 0 1/1 SHARP)\n";
        assertEquals(output1, p.toString());
    }
    
    @Test
    public void testChord() {
        String test1 = "[A2BC]_D";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Chord(Note(A 0 2/1 ABSENT)Note(B 0 1/1 ABSENT)Note(C 0 1/1 ABSENT))\nNote(D 0 1/1 FLAT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void testRepeat() {
        String test1 = "AB:|C";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Note(A 0 1/1 ABSENT)\nNote(B 0 1/1 ABSENT)\nNote(A 0 1/1 ABSENT)\n" +
        "Note(B 0 1/1 ABSENT)\nNote(C 0 1/1 ABSENT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void testRepeatWithDifferentEnding() {
        String test1 = "AE[1BC:|[2D";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Note(A 0 1/1 ABSENT)\nNote(E 0 1/1 ABSENT)\nNote(B 0 1/1 ABSENT)\nNote(C 0 1/1 ABSENT)\n" +
        "Note(A 0 1/1 ABSENT)\nNote(E 0 1/1 ABSENT)\nNote(D 0 1/1 ABSENT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void testRepeatWithDifferentEnding2() {
        String test1 = "A|:E[1BC:|[2D";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Note(A 0 1/1 ABSENT)\nNote(E 0 1/1 ABSENT)\nNote(B 0 1/1 ABSENT)\nNote(C 0 1/1 ABSENT)\n" +
        "Note(E 0 1/1 ABSENT)\nNote(D 0 1/1 ABSENT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void EMajorScale() {
        String header = "X:1\nT:Simple scale\nC:Unknown\nQ:120\nK:E\n";
        String header_output = "Index Number : 1\nTitle : Simple scale\nComposer : Unknown\nNote length : 1/8\nTempo : 120\n" +
            "Key Signature : E_MAJOR\nVoice(DEFAULT_VOICE)\n";
        String test1 = "EFGABCDe";    
        Lexer lex = new Lexer(header + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = header_output + "Note(E 0 1/1 ABSENT)\nNote(F 0 1/1 SHARP)\nNote(G 0 1/1 SHARP)\nNote(A 0 1/1 ABSENT)\n"
                + "Note(B 0 1/1 ABSENT)\nNote(C 0 1/1 SHARP)\nNote(D 0 1/1 SHARP)\nNote(E 1 1/1 ABSENT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    @Test
    public void TwoVoices() {
        String header = "X:1\nT:Simple scale\nC:Unknown\nQ:120\nV:1\nV:2\nK:C\n";
        String header_output = "Index Number : 1\nTitle : Simple scale\nComposer : Unknown\nNote length : 1/8\nTempo : 120\n" +
            "Key Signature : C_MAJOR\nVoice(1)\nVoice(2)\n";
        String test1 = "EFGABCDe";    
        Lexer lex = new Lexer(header + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = header_output + "Note(E 0 1/1 ABSENT)\nNote(F 0 1/1 SHARP)\nNote(G 0 1/1 SHARP)\nNote(A 0 1/1 ABSENT)\n"
                + "Note(B 0 1/1 ABSENT)\nNote(C 0 1/1 SHARP)\nNote(D 0 1/1 SHARP)\nNote(E 1 1/1 ABSENT)\n";
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
       
    // Title not 2nd
    @Test(expected=RuntimeException.class)
    public void MissingTitle() {
        String test1 = "X:10240\nL:1/8\nT:A Song\nC:Youyang, Deepak, Arjun\nK:D\nDEFG |";    
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
