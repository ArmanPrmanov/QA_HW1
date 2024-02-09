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

	@Test
	public void
	binaryExpr_evaluate_addition( )
	{
		BaseExpr e = new BinaryExpr( Symbol. ADDITION,
			   						 new NumExpr( Symbol. NUMBER, 1.2 ),
									 new NumExpr( Symbol. NUMBER, 3.3 ) );
		assertEquals( 4.5, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new BinaryExpr( Symbol. ADDITION,
							new NumExpr( Symbol. NUMBER, 1.2 ),
							new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( 3.4, e. evaluate( Arrays. asList( 2.2 ) ), delta );
	}

	@Test
	public void
	binaryExpr_evaluate_subtraction( )
	{
		BaseExpr e = new BinaryExpr( Symbol. SUBTRACTION,
			   						 new NumExpr( Symbol. NUMBER, 7.9 ),
									 new NumExpr( Symbol. NUMBER, 4.3 ) );
		assertEquals( 3.6, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new BinaryExpr( Symbol. SUBTRACTION,
							new NumExpr( Symbol. NUMBER, 7.9 ),
							new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( 5.7, e. evaluate( Arrays. asList( 2.2 ) ), delta );
	}

	@Test
	public void
	binaryExpr_evaluate_MULTIPLICATION( )
	{
		BaseExpr e = new BinaryExpr( Symbol. MULTIPLICATION,
			   						 new NumExpr( Symbol. NUMBER, 4.12 ),
									 new NumExpr( Symbol. NUMBER, 5.3 ) );
		assertEquals( 21.836, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new BinaryExpr( Symbol. MULTIPLICATION,
							new NumExpr( Symbol. NUMBER, 1.2 ),
							new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( 11.88, e. evaluate( Arrays. asList( 9.9 ) ), delta );
	}

	@Test
	public void
	binaryExpr_evaluate_division( )
	{
		BaseExpr e = new BinaryExpr( Symbol. DIVISION,
			   						 new NumExpr( Symbol. NUMBER, 4.23 ),
									 new NumExpr( Symbol. NUMBER, 1.44 ) );
		assertEquals( 2.9375, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new BinaryExpr( Symbol. DIVISION,
							new NumExpr( Symbol. NUMBER, 5.56 ),
							new VarExpr( Symbol. VARIABLE, 0) );
		assertEquals( 1.06309751, e. evaluate( Arrays. asList( 5.23 ) ), delta );

		/* final BaseExpr e1 = e;
		assertThrows( ArithmeticException.class,
					  ( ) -> { e1. evaluate( Arrays. asList( 0.0 ) ); }); */
	}

	@Test
	public void
	binaryExpr_evaluate_power( )
	{
		BaseExpr e = new BinaryExpr( Symbol. POWER,
			   						 new NumExpr( Symbol. NUMBER, 2.0 ),
									 new NumExpr( Symbol. NUMBER, 3.0 ) );
		assertEquals( 8.0, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new BinaryExpr( Symbol. POWER,
							new NumExpr( Symbol. NUMBER, 3.56 ),
							new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( 12.6736, e. evaluate( Arrays. asList( 2.0 ) ), delta );
	}

	@Test
	public void
	unaryExpr_evaluate_PIPE( )
	{
		BaseExpr e = new UnaryExpr( Symbol. PIPE, 
				                    new NumExpr( Symbol. NUMBER, -2.0 ) );
		assertEquals( 2.0, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new UnaryExpr( Symbol. PIPE, 
				           new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( 3.4, e. evaluate( Arrays. asList( -3.4 ) ), delta );
	}

	@Test
	public void
	unaryExpr_evaluate_SQR( )
	{
		BaseExpr e = new UnaryExpr( Symbol. SQR, 
				                    new NumExpr( Symbol. NUMBER, 4.35 ) );
		assertEquals( 2.0856653, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new UnaryExpr( Symbol. SQR, 
				           new VarExpr( Symbol. VARIABLE, 0 ) );	
		assertEquals( Float. NaN, e. evaluate( Arrays. asList( -3.4 ) ), delta );
	}

	@Test
	public void
	unaryExpr_evaluate_SIN( )
	{
		BaseExpr e = new UnaryExpr( Symbol. SIN, 
				                    new NumExpr( Symbol. NUMBER, 1.5708 ) );
		assertEquals( 1.000, e. evaluate( new ArrayList<Double>( ) ), delta );
		
		e = new UnaryExpr( Symbol. SIN, 
				           new VarExpr( Symbol. VARIABLE, 0 ) );
		assertEquals( -0.5000001943, e. evaluate( Arrays. asList( -0.523599 ) ), delta );
	}
}
