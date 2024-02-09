import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.Prs;

@RunWith( Suite.class )

@Suite.SuiteClasses({
	LexerTests.class,
	PrsTests.class,
	AstTests.class,
	ParamBinaryOprTests.class
})

public class Tester { }
