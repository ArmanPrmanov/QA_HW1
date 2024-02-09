import org.junit.Test;
import static org.junit.Assert.*;

import parser.Prs;
import parser.Lexer.*;
import parser.AST.*;

public class PrsTests {

    @Test(expected = IllegalStateException.class)
    public void testParseExpressionWithUnparsedTokens() {
        String expression = "2 + ( 3 * 4 ) -";
        Prs.parse(expression);
    }

    @Test(expected = IllegalStateException.class)
    public void testParseIncompleteExpression() {
        String expression = "3 + ";
        Prs.parse(expression);
    }
}
