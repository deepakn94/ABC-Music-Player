package sound;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {
    
    @Test
    public void testNote() {
        String test1 = "_C";
        
        Lexer lex = new Lexer(test1);
        
        Parser parser1 = new Parser(lex);
        String output1 = "C01";
        assertEquals(output1, parser1.parseNote(test1).toString());
    }
    
    @Test
    public void testChord() {
        String test1 = "CDEFG";
        
        Lexer lex = new Lexer(test1);
        
        Parser parser1 = new Parser(lex);
        String output1 = "C";
        assertEquals(output1, parser1.parseChord(test1).toString());
    }


}
