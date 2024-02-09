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
public class ParamBinaryOprTests
{
	private BaseExpr e1;
	private BaseExpr e2;
	private Symbol opr;
	private double res;

	public ParamBinaryOprTests( Symbol opr, double d1, double d2, double res )
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
			
			{ Symbol. DIVISION, 12.0, 0.0, Double.NaN }

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
