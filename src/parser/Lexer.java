package parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public enum Symbol {
        UNKNOWN,
        NUMBER,
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        POWER,
        OPENING_BRACKET,
        CLOSING_BRACKET,
        REMAINDER,
        FACTORIAL,
        PIPE,
        SQR,
        SIN,
        COS,
        TAN,
        COT,
        VARIABLE,
        FUNCTION
    }

    public static boolean isRealNum(String word, int debugLevel) {
        int i = 0;
        if (i < word.length() && word.charAt(i) == '0') {
            if (debugLevel > 0)
                System.out.println("q0 0 q1");
            ++i;
            return q1(word, i, debugLevel);
        }
        if (i < word.length() && '1' <= word.charAt(i) && word.charAt(i) <= '9') {
            if (debugLevel > 0)
                System.out.printf("q0 %c q2\n", word.charAt(i));
            ++i;
            return q2(word, i, debugLevel);
        }
        if (debugLevel > 0)
            System.out.println("q0 rejected");
        return false;
    }

    private static boolean q1(String word, int i, int debugLevel) {
        if (i >= word.length()) {
            if (debugLevel > 0)
                System.out.println("q1 accepted");
            return true;
        }
        if (word.charAt(i) == '.') {
            if (debugLevel > 0)
                System.out.println("q1 . q3");
            ++i;
            return q3(word, i, debugLevel);
        }
        if (debugLevel > 0)
            System.out.println("q1 rejected");
        return false;
    }

    private static boolean q2(String word, int i, int debugLevel) {
        if (i >= word.length()) {
            if (debugLevel > 0)
                System.out.println("q2 accepted");
            return true;
        }
        if ('0' <= word.charAt(i) && word.charAt(i) <= '9') {
            if (debugLevel > 0)
                System.out.printf("q2 %c q2\n", word.charAt(i));
            i++;
            return q2(word, i, debugLevel);
        }
        if (word.charAt(i) == '.') {
            if (debugLevel > 0)
                System.out.println("q2 . q3");
            ++i;
            return q3(word, i, debugLevel);
        }
        if (debugLevel > 0)
            System.out.println("q2 rejected");
        return false;
    }

    private static boolean q3(String word, int i, int debugLevel) {
        if (i >= word.length()) {
            if (debugLevel > 0)
                System.out.println("q3 accepted");
            return true;
        }
        if ('0' <= word.charAt(i) && word.charAt(i) <= '9') {
            if (debugLevel > 0)
                System.out.printf("q3 %c q3\n", word.charAt(i));
            ++i;
            return q3(word, i, debugLevel);
        }
        if (debugLevel > 0)
            System.out.println("q3 rejected");
        return false;
    }

    private static boolean isVar(String word, int debugLevel) {
        int i = 0;
        if( i >= word. length( ) )
        {
            if( debugLevel > 0 )
                System.out.println("a0 rejected");
            return false;
        }
        if( ('a' <= word. charAt(i) && word. charAt(i) <= 'z') ||
                ('A' <= word. charAt(i) && word. charAt(i) <= 'Z') )
        {
            if( debugLevel > 0 )
                System.out.printf("a0 %c a1\n", word.charAt(i));
            i++;
            return a1(word, i, debugLevel);
        }
        if( debugLevel > 0 )
            System.out.println("a0 rejected");
        return false;
    }

    private static boolean a1(String word, int i, int debugLevel) {
        if( i >= word. length( ) )
        {
            if( debugLevel > 0 )
                System.out.println("q1 accepted\n");
            return true;
        }
        if( ('a' <= word. charAt(i) && word. charAt(i) <= 'z') ||
                ('A' <= word. charAt(i) && word. charAt(i) <= 'Z') )
        {
            if( debugLevel > 0 )
                System.out.printf("a1 %c a1\n", word.charAt(i));
            i++;
            return a1(word, i, debugLevel);
        }
        if( debugLevel > 0 )
            System.out.println("a1 rejected");
        return false;
    }

    public static Symbol symbolClassifier(String sym) {
        switch (sym) {
            case ":=":
                return Symbol.FUNCTION;
            case "+":
                return Symbol.ADDITION;
            case "-":
                return Symbol.SUBTRACTION;
            case "*":
                return Symbol.MULTIPLICATION;
            case "/":
                return Symbol.DIVISION;
            case "^":
                return Symbol.POWER;
            case "(":
                return Symbol.OPENING_BRACKET;
            case ")":
                return Symbol.CLOSING_BRACKET;
            case "%":
                return Symbol.REMAINDER;
            case "!":
                return Symbol.FACTORIAL;
            case "|":
                return Symbol.PIPE;
            case "sqr":
                return Symbol.SQR;
            case "sin":
                return Symbol.SIN;
            case "cos":
                return Symbol.COS;
            case "tan":
                return Symbol.TAN;
            default:
                if (isVar(sym, 0))
                    return Symbol.VARIABLE;
                if (isRealNum(sym, 0))
                    return Symbol.NUMBER;
                return Symbol.UNKNOWN;
        }
    }

    public static List<Token> tokenizer(String str) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder sym = new StringBuilder();
        String longestMatch = "";
        Symbol longestTok = Symbol.UNKNOWN;
        char c;

        for (int i = 0; i < str.length(); ++i) {
            c = str.charAt(i);

            if (c == ' ' || c == '\t' || c == '\f' || c == '\n' || c == '\r')
                continue;

            for (; i < str.length(); ++i) {
                c = str.charAt(i);
                sym.append(c);
                Symbol tok = symbolClassifier(sym.toString());

                if (tok != Symbol.UNKNOWN) {
                    longestMatch = sym.toString();
                    longestTok = tok;
                } else if (!longestMatch.isEmpty()) {
                    tokens.add(new Token(longestMatch, longestTok));
                    sym.setLength(0);
                    longestMatch = "";
                    longestTok = Symbol.UNKNOWN;
                    --i;
                    break;
                }
            }
        }
        if (!longestMatch.isEmpty())
            tokens.add(new Token(longestMatch, longestTok));

        return tokens;
    }

    public static class Token {
        private String lexeme;
        private Symbol symbol;

        public Token(String lexeme, Symbol symbol) {
            this.lexeme = lexeme;
            this.symbol = symbol;
        }

        public String getLexeme() {
            return lexeme;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        @Override
        public String toString() {
            return "(" + lexeme + " " + symbol.toString() + ")";
        }
    }
}
