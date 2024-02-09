import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import parser.Lexer;
import parser.Lexer.Symbol;

public class LexerTests 
{
	@Test
	public void
	valid_isRealNum( )
	{
		assertTrue( Lexer. isRealNum( "0", 0 ) );
		assertTrue( Lexer. isRealNum( "1", 0 ) );
		assertTrue( Lexer. isRealNum( "23.", 0 ) );
		assertTrue( Lexer. isRealNum( "12.000", 0 ) );
		assertTrue( Lexer. isRealNum( "673.445", 0 ) );
		assertTrue( Lexer. isRealNum( "0.3694", 0 ) );
		assertTrue( Lexer. isRealNum( "0.0003", 0 ) );
	}

	@Test
	public void
	invalid_isRealNum( )
	{
		assertFalse( Lexer. isRealNum( "012", 0 ) );
		assertFalse( Lexer. isRealNum( "123.k", 0 ) );
		assertFalse( Lexer. isRealNum( "23h32", 0 ) );
		assertFalse( Lexer. isRealNum( "", 0 ) );
		assertFalse( Lexer. isRealNum( "weqxdqw", 0 ) );
		assertFalse( Lexer. isRealNum( "123.45ab", 0 ) );
	}

	@Test
	public void
	valid_isVal( )
	{
		assertTrue( Lexer. isVar( "a", 0 ) );
		assertTrue( Lexer. isVar( "adhwda", 0 ) );
        assertTrue( Lexer. isVar( "ASSDAD", 0 ) );
	}

	@Test
	public void
	invalid_isVal( )
	{
		assertFalse( Lexer. isVar( "0", 0 ) );
		assertFalse( Lexer. isVar( "^", 0 ) );
		assertFalse( Lexer. isVar( "^Sas", 0 ) );
		assertFalse( Lexer. isVar( "wqeq3323^&", 0 ) );
	}

	@Test
	public void
	valid_symbolClassifier( )
	{
		assertEquals( Symbol. NUMBER, Lexer. symbolClassifier( "123" ) );
		assertEquals( Symbol. NUMBER, Lexer. symbolClassifier( "0.123" ) );

		assertEquals( Symbol. ADDITION, Lexer. symbolClassifier( "+" ) );
		assertEquals( Symbol. SUBTRACTION, Lexer. symbolClassifier( "-" ) );
		assertEquals( Symbol. MULTIPLICATION, Lexer. symbolClassifier( "*" ) );
		assertEquals( Symbol. DIVISION, Lexer. symbolClassifier( "/" ) );
		assertEquals( Symbol. POWER, Lexer. symbolClassifier( "^" ) );
		assertEquals( Symbol. OPENING_BRACKET, Lexer. symbolClassifier( "(" ) );
		assertEquals( Symbol. CLOSING_BRACKET, Lexer. symbolClassifier( ")" ) );
		assertEquals( Symbol. REMAINDER, Lexer. symbolClassifier( "%" ) );
		assertEquals( Symbol. FACTORIAL, Lexer. symbolClassifier( "!" ) );
		assertEquals( Symbol. PIPE, Lexer. symbolClassifier( "|" ) );

		assertEquals( Symbol. VARIABLE, Lexer. symbolClassifier( "xasx" ) );
		assertEquals( Symbol. VARIABLE, Lexer. symbolClassifier( "x" ) );
	}

	@Test
	public void
	invalid_symbolClassifier( )
	{
		assertEquals( Symbol. UNKNOWN, Lexer. symbolClassifier( ".123" ) );
		assertEquals( Symbol. UNKNOWN, Lexer. symbolClassifier( "eqwe123" ) );
		assertEquals( Symbol. UNKNOWN, Lexer. symbolClassifier( "^*(()" ) );
	}

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
