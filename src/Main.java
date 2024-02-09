import parser.Lexer;
import parser.Prs;
import parser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static parser.Lexer.tokenizer;

public class Main {

    public static void main( String[] args ) {
		if( args. length == 0 )
		{
			System.err.println( "calc: error: no input given" );
			return;
		}

        List<Lexer.Token> tokens = tokenizer( args[0] );
        
		for (Lexer.Token token : tokens) {
            System.out.println( token. toString( ) );
        }

        AST.BaseExpr e = Prs.parse( args[0] );

        List<Double> xs = Arrays.asList( 1.0, 2.0, 3.0, 4.0, 5.0 );
        for( Double x : xs )
            System. out. println( "x=" + x + "\t" + "y=" + e.evaluate( Arrays. asList( x ) ) );
    }
}
