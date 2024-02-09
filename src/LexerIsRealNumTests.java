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
public class LexerIsRealNumTests
{
	private String input;
	private boolean res;

	public LexerIsRealNumTests( String input, boolean res )
	{
		this. input = input;
		this. res = res;
	}

	@Parameters
	public static Collection<Object[]> data( ) 
	{
		Object[][] vals = new Object[][]{
			{ "0", true },
			{ "1", true },
			{ "23.", true },
			{ "12.000", true },
			{ "673.231", true },
			{ "0.2354", true },
			{ "0.00032", true },
			{ "", false },
			{ "012", false },
			{ "123.l", false },
			{ "75k34l", false },
			{ "akcaw", false },
			{ "12.32adw", false },
		};
		return Arrays. asList( vals );
	}

	@Test
	public void
	testIsRealNum( )
	{ assertEquals( res, Lexer. isRealNum( input, 0 ) ); }
}
