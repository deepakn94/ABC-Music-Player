package sound;

import static org.junit.Assert.*;

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
    
    @Test
    public void testChord() {
        String test1 = "X:1\nT:Random\nK:D\nD,,8 |";    
        Lexer lex = new Lexer(test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = "Index Number : 1\nTitle : Random\nComposer : Unspecified\nNote length : 1/8\nTempo : 100\nKey Signature : D_MAJOR\n" +
                "Voice(DEFAULT_VOICE)\nNote(D -2 8/1 ABSENT)\n";
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
    
    // Check Tuplets
    @Test
    public void CheckTuplet() {
        String test1 = "(2AB(3ABC(4ABCD|";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Tuplet(Note(A 0 3/2 ABSENT)Note(B 0 3/2 ABSENT))\n"
                + "Tuplet(Note(A 0 2/3 ABSENT)Note(B 0 2/3 ABSENT)Note(C 0 2/3 ABSENT))\n"
                + "Tuplet(Note(A 0 3/4 ABSENT)Note(B 0 3/4 ABSENT)Note(C 0 3/4 ABSENT)Note(D 0 3/4 ABSENT))\n";
        assertEquals(output1, pieceToPlay.toString());
    }
    
    // Triplet size != 3
    @Test
    public void TupletFail() {
        String test1 = "(3AB|";    
        Lexer lex = new Lexer(DEFAULT_HEADER + test1);       
        Parser parser1 = new Parser(lex);
        Piece pieceToPlay = parser1.Parse(); 
        String output1 = DEFAULT_HEADER_OUTPUT + "Note(A 0 1/1 ABSENT)\nNote(B 0 1/1 ABSENT)\n";
        assertEquals(output1, pieceToPlay.toString());
    }

    
    


}
