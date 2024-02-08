import org.junit.jupiter.api.Test;
import parser.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {

    @Test
    void testTokenizer_EmptyString() {
        String input = "";
        List<Lexer.Token> tokens = Lexer.tokenizer(input);
        assertTrue(tokens.isEmpty());
    }

    @Test
    void testTokenizer_SimpleExpression() {
        String input = "3.14 + sqr(4) - sin(30)";
        List<Lexer.Token> tokens = Lexer.tokenizer(input);
        assertEquals(11, tokens.size());
        assertEquals("3.14", tokens.get(0).getLexeme());
        assertEquals(Lexer.Symbol.NUMBER, tokens.get(0).getSymbol());
        assertEquals("+", tokens.get(1).getLexeme());
        assertEquals(Lexer.Symbol.ADDITION, tokens.get(1).getSymbol());
        assertEquals("sqr", tokens.get(2).getLexeme());
        assertEquals(Lexer.Symbol.SQR, tokens.get(2).getSymbol());
    }

    @Test
    void testTokenizer_InvalidExpression() {
        String input = "abc * 123 - !@#$%";
        List<Lexer.Token> tokens = Lexer.tokenizer(input);
        assertTrue(tokens.isEmpty());
    }
}
