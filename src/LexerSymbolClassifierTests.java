import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

import parser.Lexer;
import parser.Lexer.*;

@RunWith( Parameterized.class )
public class LexerSymbolClassifierTests
{
	private Symbol sym;
	private String str;

	public
	LexerSymbolClassifierTests
	( Symbol sym, String str )
	{
		this. sym = sym;
		this. str = str;
	}

	@Parameters
	public static Collection<Object[]>
	data( )
	{
		Object[][] vals = new Object[][]{
			{ Symbol. NUMBER, "123" },
			{ Symbol. NUMBER, "0.123" },

			{ Symbol. ADDITION, "+" },
			{ Symbol. SUBTRACTION, "-" },
			{ Symbol. DIVISION, "/" },
			{ Symbol. MULTIPLICATION, "*" },
			{ Symbol. POWER, "^" },
			{ Symbol. OPENING_BRACKET, "(" },
			{ Symbol. CLOSING_BRACKET, ")" },
			{ Symbol. REMAINDER, "%" },
			{ Symbol. FACTORIAL, "!" },
			{ Symbol. PIPE, "|" },

			{ Symbol. VARIABLE, "eyqe" },
			{ Symbol. VARIABLE, "X" },

			{ Symbol. UNKNOWN, ".123" },
			{ Symbol. UNKNOWN, "ewqe123" },
			{ Symbol. UNKNOWN, "^*(()" }
		};
		return Arrays. asList( vals );
	}

	@Test
	public void
	testSymbolClassifier( )
	{ assertEquals( sym, Lexer. symbolClassifier( str ) ); }
}
