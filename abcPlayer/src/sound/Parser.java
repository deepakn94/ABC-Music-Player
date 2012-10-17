package sound;

public class Parser {

    /**
     * Creates a new parser over the lexer.  This parser will use the passed
     * lexer to get tokens--which it will then parse.
     * 
     * @param lexer The lexer.
     * @return none
     */
    
    Lexer lex;
    public Parser(Lexer lexer) {
        this.lex = lexer;     
    }
}
