import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

import parser.Lexer.Symbol;
import parser.AST.BaseExpr;
import parser.AST.NumExpr;
import parser.AST.UnaryExpr;

@RunWith( Parameterized.class )
public class UnaryOprTests
{
	private Symbol opr;
	private BaseExpr e1;
	private double res;

	public 
	UnaryOprTests( Symbol opr, double d, double res )
	{
		this. opr = opr;
		this. e1 = new NumExpr( Symbol. NUMBER, d );
		this. res = res;
	}

	@Parameters
	public static Collection<Object[]>
	data( )
	{
		Object[][] vals = new Object[][]{
			{ Symbol. PIPE, 0.23 , 0.23  },
			{ Symbol. PIPE, -2.0, 2.0 },

			{ Symbol. SQR, 1.0, 1.0 },
			{ Symbol. SQR, 4.0, 2.0 },
			{ Symbol. SQR, -789.0, Double. NaN },

			{ Symbol. SIN, 0.0, 0 },
			{ Symbol. SIN, 1.5708, 1 },
			{ Symbol. SIN, 3.14159, 0 },
			{ Symbol. SIN, 4.71238, -1 },
			{ Symbol. SIN, 6.28318, 0 },
			
			{ Symbol. COS, 0.0, 1 },
			{ Symbol. COS, 1.5708, 0 },
			{ Symbol. COS, 3.14159, -1 },
			{ Symbol. COS, 4.71238, 0 },
			{ Symbol. COS, 6.28318, 1 },		
		};
		return Arrays. asList( vals );
	}

	@Test
	public void
	test_evaluate( )
	{
		BaseExpr e = new UnaryExpr( opr, e1 );
		assertEquals( res, e. evaluate( new ArrayList<Double>( ) ), 0.0001 );
	}
}
