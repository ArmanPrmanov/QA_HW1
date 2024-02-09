import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.IllegalStateException;
import java.lang.ArithmeticException;
import java.util.ArrayList;
import java.util.Arrays;

import parser.Lexer.Symbol;
import parser.AST.*;

public class AstTests 
{
	public static final double delta = 0.0001;
	
	@Test
	public void
	numExpr_evaluate( )
	{
		BaseExpr e = new NumExpr( Symbol. NUMBER, 3.14 );	
		assertEquals( 3.14, e. evaluate( new ArrayList<Double>(  ) ), delta );
	}

	@Test
	public void
	valid_varExpr_evaluate( )	
	{
		BaseExpr e = new VarExpr( Symbol. VARIABLE, 0 );
		assertEquals( 5.69, e. evaluate( Arrays. asList( 5.69 ) ), delta );
	}

	@Test
	public void
	invalid_varExpr_evaluate( )
	{
		BaseExpr e = new VarExpr( Symbol. VARIABLE, 0 );

		assertThrows( IllegalStateException.class,
				      ( ) -> { e. evaluate( new ArrayList<Double>( ) ); });
	}	
}
