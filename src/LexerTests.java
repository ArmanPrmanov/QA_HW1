import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import parser.Lexer;
import parser.Lexer.Symbol;

public class LexerTests 
{	
    @Test
    public void 
	empty_string_tokenizer( )
	{
        List<Lexer.Token> tokens = Lexer. tokenizer( "" );
        assertTrue( tokens.isEmpty( ) );
    }

	@Test
	public void
	valid_tokenizer( )
	{ 
		List<Lexer.Token> tokens = Lexer. tokenizer( "3.14 + sqr(4) - sin(30)" );
        assertEquals( 11, tokens. size( ) );
        
		assertEquals( "3.14", tokens. get(0). getLexeme ( ) );
        assertEquals( Symbol. NUMBER, tokens. get(0). getSymbol( ) );

        assertEquals( "+", tokens. get(1). getLexeme ( ) );
        assertEquals( Symbol. ADDITION, tokens. get(1). getSymbol( ) );

        assertEquals( "sqr", tokens. get(2). getLexeme( ) );
        assertEquals( Symbol. SQR, tokens. get(2). getSymbol( ) );

		assertEquals( "(", tokens. get(3). getLexeme( ) );
        assertEquals( Symbol. OPENING_BRACKET, tokens. get(3). getSymbol( ) );

		assertEquals( "4", tokens. get(4). getLexeme( ) );
        assertEquals( Symbol. NUMBER, tokens. get(4). getSymbol( ) );
		
		assertEquals( ")", tokens. get(5). getLexeme( ) );
        assertEquals( Symbol. CLOSING_BRACKET, tokens. get(5). getSymbol( ) );

		assertEquals( "-", tokens. get(6). getLexeme( ) );
        assertEquals( Symbol. SUBTRACTION, tokens. get(6). getSymbol( ) );

		assertEquals( "sin", tokens. get(7). getLexeme( ) );
        assertEquals( Symbol. SIN, tokens. get(7). getSymbol( ) );

		assertEquals( "(", tokens. get(8). getLexeme( ) );
        assertEquals( Symbol. OPENING_BRACKET, tokens. get(8). getSymbol( ) );

		assertEquals( "30", tokens. get(9). getLexeme( ) );
        assertEquals( Symbol. NUMBER, tokens. get(9). getSymbol( ) );

		assertEquals( ")", tokens. get(10). getLexeme( ) );
        assertEquals( Symbol. CLOSING_BRACKET, tokens. get(10). getSymbol( ) );
    }

	@Test
	public void
	valid_with_trailign_unknown_symbol_tokenizer( )
	{
		List<Lexer.Token> tokens = Lexer. tokenizer( "3.14 * x @#" );
		assertEquals( 3, tokens. size( ) );
		
		assertEquals( "3.14", tokens. get(0). getLexeme ( ) );
        assertEquals( Symbol. NUMBER, tokens. get(0). getSymbol( ) );

        assertEquals( "*", tokens. get(1). getLexeme ( ) );
        assertEquals( Symbol. MULTIPLICATION, tokens. get(1). getSymbol( ) );

        assertEquals( "x", tokens. get(2). getLexeme( ) );
        assertEquals( Symbol. VARIABLE, tokens. get(2). getSymbol( ) );

	}
}
