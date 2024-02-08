package parser;

import java.util.List;
import parser.Lexer.*;
import parser.AST.*;

import static parser.Lexer.tokenizer;

public class Prs {

    public static void checktok(List<Token> tokens, Symbol sym) {
        if (tokens.isEmpty() || tokens.get(0).getSymbol() != sym)
            throw new IllegalStateException("Unexpected or missing token");

        tokens.remove(0);
    }

    public static BaseExpr parse(String str) {
        List<Token> tokens = tokenizer(str);
        BaseExpr e = prs_expr(tokens);

        if (tokens.isEmpty())
            return e;
        throw new IllegalStateException("parse: Unparsed token(s) in the list");
    }

    public static BaseExpr prs_expr(List<Token> tokens) {
        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.FUNCTION) {
            checktok(tokens, Symbol.FUNCTION);
            BaseExpr e = prs_expr(tokens);
            return new FuncExpr(Symbol.FUNCTION, e);
        }
        BaseExpr e1 = prs_expr1(tokens);

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.ADDITION) {
            checktok(tokens, Symbol.ADDITION);
            BaseExpr e2 = prs_expr(tokens);
            return new BinaryExpr(Symbol.ADDITION, e1, e2);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.SUBTRACTION) {
            checktok(tokens, Symbol.SUBTRACTION);
            BaseExpr e2 = prs_expr(tokens);
            return new BinaryExpr(Symbol.SUBTRACTION, e1, e2);
        }

        return e1;
    }

    public static BaseExpr prs_expr1(List<Token> tokens) {
        BaseExpr e1 = prs_expr2(tokens);

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.MULTIPLICATION) {
            checktok(tokens, Symbol.MULTIPLICATION);
            BaseExpr e2 = prs_expr1(tokens);
            return new BinaryExpr(Symbol.MULTIPLICATION, e1, e2);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.DIVISION) {
            checktok(tokens, Symbol.DIVISION);
            BaseExpr e2 = prs_expr1(tokens);
            return new BinaryExpr(Symbol.DIVISION, e1, e2);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.REMAINDER) {
            checktok(tokens, Symbol.REMAINDER);
            BaseExpr e2 = prs_expr1(tokens);
            return new BinaryExpr(Symbol.REMAINDER, e1, e2);
        }

        return e1;
    }

    public static BaseExpr prs_expr2(List<Token> tokens) {
        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.FACTORIAL) {
            checktok(tokens, Symbol.FACTORIAL);
            BaseExpr e = prs_expr2(tokens);
            return new UnaryExpr(Symbol.FACTORIAL, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.SUBTRACTION) {
            checktok(tokens, Symbol.SUBTRACTION);
            BaseExpr e = prs_expr2(tokens);
            return new UnaryExpr(Symbol.SUBTRACTION, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.SQR) {
            checktok(tokens, Symbol.SQR);
            checktok(tokens, Symbol.OPENING_BRACKET);
            BaseExpr e = prs_expr2(tokens);
            checktok(tokens, Symbol.CLOSING_BRACKET);
            return new UnaryExpr(Symbol.SQR, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.PIPE) {
            checktok(tokens, Symbol.PIPE);
            BaseExpr e = prs_expr(tokens);
            checktok(tokens, Symbol.PIPE);
            return new UnaryExpr(Symbol.PIPE, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.SIN) {
            checktok(tokens, Symbol.SIN);
            checktok(tokens, Symbol.OPENING_BRACKET);
            BaseExpr e = prs_expr2(tokens);
            checktok(tokens, Symbol.CLOSING_BRACKET);
            return new UnaryExpr(Symbol.SIN, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.COS) {
            checktok(tokens, Symbol.COS);
            checktok(tokens, Symbol.OPENING_BRACKET);
            BaseExpr e = prs_expr2(tokens);
            checktok(tokens, Symbol.CLOSING_BRACKET);
            return new UnaryExpr(Symbol.COS, e);
        }

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.TAN) {
            checktok(tokens, Symbol.TAN);
            checktok(tokens, Symbol.OPENING_BRACKET);
            BaseExpr e = prs_expr2(tokens);
            checktok(tokens, Symbol.CLOSING_BRACKET);
            return new UnaryExpr(Symbol.TAN, e);
        }

        BaseExpr e1 = prs_expr3(tokens);

        if (!tokens.isEmpty() && tokens.get(0).getSymbol() == Symbol.POWER) {
            checktok(tokens, Symbol.POWER);
            BaseExpr e2 = prs_expr2(tokens);
            return new BinaryExpr(Symbol.POWER, e1, e2);
        }

        return e1;
    }

    public static BaseExpr prs_expr3(List<Token> tokens) {
        if (tokens.isEmpty())
            throw new IllegalStateException("prs_expr3: Unexpected token");

        if (tokens.get(0).getSymbol() == Symbol.OPENING_BRACKET) {
            checktok(tokens, Symbol.OPENING_BRACKET);
            BaseExpr e = prs_expr(tokens);
            checktok(tokens, Symbol.CLOSING_BRACKET);
            return e;
        }

        if (tokens.get(0).getSymbol() == Symbol.VARIABLE) {
            checktok(tokens, Symbol.VARIABLE);
            return new VarExpr(Symbol.VARIABLE, 0);
        }

        String str = tokens.get(0).getLexeme();
        double num = Double.parseDouble(str);

        if (num != Double.POSITIVE_INFINITY && num != Double.NEGATIVE_INFINITY && num != Double.NaN) {
            checktok(tokens, Symbol.NUMBER);
            return new NumExpr(Symbol.NUMBER, num);
        }

        throw new IllegalStateException("prs_expr3: Unexpected token");
    }
}
