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
import parser.AST.BinaryExpr;

@RunWith( Parameterized.class )
public class BinaryOprTests
{
	private BaseExpr e1;
	private BaseExpr e2;
	private Symbol opr;
	private double res;

	public BinaryOprTests( Symbol opr, double d1, double d2, double res )
	{
		this. e1 = new NumExpr( Symbol. NUMBER, d1 );
		this. e2 = new NumExpr( Symbol. NUMBER, d2 );
		this. opr = opr;
		this. res = res;
	}

	@Parameters
	public static Collection<Object[]> data( )
	{
		Object[][] vals = new Object[][]{
			{ Symbol. ADDITION, 1.5, 2.3, 3.8 },
			{ Symbol. ADDITION, 1.45, 0.23, 1.68 },
			{ Symbol. ADDITION, 3.42, 6.27, 9.69 },
			{ Symbol. ADDITION, 12.0, 2.4 , 14.4 },
			{ Symbol. ADDITION, 3.0, -3.0 , 0.0 },
			
			{ Symbol. SUBTRACTION, 7.9, 3.4 , 4.5 },
			{ Symbol. SUBTRACTION, 0.0, 23.0, -23.0 },

			{ Symbol. MULTIPLICATION, 4.12, 5.3, 21.836 },
			{ Symbol. MULTIPLICATION, -1.2, -9.9, 11.88 },

			{ Symbol. DIVISION, 12.0, 0.0, Double.NaN },
			{ Symbol. DIVISION, 4.23, 1.44, 2.9375 },
			{ Symbol. DIVISION, 5.56, -5.23, -1.06309751 },

			{ Symbol. POWER, 2.0, 3.0, 8.0 },
			{ Symbol. POWER, 3.56, 2.0, 12.6736 }
		};
		return Arrays. asList( vals );
	}

	@Test
	public void test( )
	{
		BaseExpr e = new BinaryExpr( opr, e1, e2 );
		assertEquals( res, e. evaluate( new ArrayList<Double>( ) ), 0.0001 );
	}
}
