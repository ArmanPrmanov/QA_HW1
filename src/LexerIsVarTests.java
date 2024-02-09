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
public class LexerIsVarTests
{
	private String input;
	private boolean res;

	public 
	LexerIsVarTests
	( String input, boolean res )
	{
		this. input = input;
		this. res = res;
	}

	@Parameters
	public static Collection<Object[]> data( ) 
	{
		Object[][] vals = new Object[][]{
			{ "a", true },
			{ "sdhw", true },
			{ "ASSJAS", true },
			{ "H", true },
			{ "0", false },
			{ "^", false },
			{ "dad021", false },
			{ "dwad3123^&*", false }
		};
		return Arrays. asList( vals );
	}

	@Test
	public void
	testIsRealNum( )
	{ assertEquals( res, Lexer. isVar( input, 0 ) ); }
}
