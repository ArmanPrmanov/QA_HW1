import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.Prs;

@RunWith( Suite.class )

@Suite.SuiteClasses({
	LexerTests.class,
	PrsTests.class,
	AstTests.class,
	BinaryOprTests.class,
	UnaryOprTests.class,
	LexerIsRealNumTests.class,
	LexerIsVarTests.class,
	LexerSymbolClassifierTests.class
})

public class Tester { }
