package parser;

import java.util.List;
import parser.Lexer.Symbol;

public class AST {
    public static abstract class BaseExpr {
        protected Symbol sym;

        public BaseExpr(Symbol sym) {
            this.sym = sym;
        }

        public abstract double evaluate(List<Double> vals);

        public abstract String ToString(int depth);
    }

    private static String indent(int depth) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < depth; i++)
            str.append(" ");
        return str.toString();
    }

    public static class FuncExpr extends BaseExpr {
        private BaseExpr e;

        public FuncExpr(Symbol sym, BaseExpr e) {
            super(sym);
            this.e = e;
        }

        @Override
        public double evaluate(List<Double> vals) {
            return e.evaluate(vals);
        }

        @Override
        public String ToString(int depth) {
            StringBuilder str = new StringBuilder(indent(depth));
            str.append("f(x) =\n").append(e.ToString(depth + 1));
            return str.toString();
        }
    }

    public static class BinaryExpr extends BaseExpr {
        private BaseExpr e1;
        private BaseExpr e2;

        public BinaryExpr(Symbol sym, BaseExpr e1, BaseExpr e2) {
            super(sym);
            this.e1 = e1;
            this.e2 = e2;
        }

        @Override
        public double evaluate(List<Double> vals) {
            double v1 = e1.evaluate(vals);
            double v2 = e2.evaluate(vals);

            switch (sym) {
                case ADDITION:
                    return v1 + v2;
                case SUBTRACTION:
                    return v1 - v2;
                case MULTIPLICATION:
                    return v1 * v2;
                case DIVISION:
                    if (v2 == 0)
                        throw new ArithmeticException("division by zero");
                    return v1 / v2;
                case POWER:
                    return Math.pow(v1, v2);
                case REMAINDER:
                    return Math.IEEEremainder(v1, v2);
                default:
                    throw new IllegalStateException("Unexpected operation " + sym + " applied in Expr.evaluate");
            }
        }

        @Override
        public String ToString(int depth) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < depth; i++)
                str.append(" ");
            str.append(sym).append('\n');
            str.append(e1.ToString(depth + 1));
            str.append(e2.ToString(depth + 1));
            return str.toString();
        }
    }

    public static class UnaryExpr extends BaseExpr {
        private BaseExpr e;

        public UnaryExpr(Symbol sym, BaseExpr e) {
            super(sym);
            this.e = e;
        }

        @Override
        public double evaluate(List<Double> vals) {
            double v = e.evaluate(vals);

            switch (sym) {
                case FACTORIAL:
                    return v; // Implement factorial
                case SUBTRACTION:
                    return -v;
                case PIPE:
                    return Math.abs(v);
                case SQR:
                    return Math.sqrt(v);
                case SIN:
                    return Math.sin(v);
                case COS:
                    return Math.cos(v);
                case TAN:
                    return Math.tan(v);
                default:
                    throw new IllegalStateException("Unexpected operation " + sym + " applied in Expr.evaluate");
            }
        }

        @Override
        public String ToString(int depth) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < depth; i++)
                str.append(" ");
            str.append(sym).append('\n');
            str.append(e.ToString(depth + 1));
            return str.toString();
        }
    }

    public static class VarExpr extends BaseExpr {
        private int i;

        public VarExpr(Symbol sym, int i) {
            super(sym);
            this.i = i;
        }

        @Override
        public double evaluate(List<Double> vals) {
            if (i < vals.size())
                return vals.get(i);
            throw new IllegalStateException("Uninitialized variable " + i);
        }

        @Override
        public String ToString(int depth) {
            StringBuilder str = new StringBuilder(indent(depth));
            return str.append("[").append(i).append("]\n").toString();
        }
    }

    public static class NumExpr extends BaseExpr {
        private double d;

        public NumExpr(Symbol sym, double d) {
            super(sym);
            this.d = d;
        }

        @Override
        public double evaluate(List<Double> vals) {
            return d;
        }

        @Override
        public String ToString(int depth) {
            StringBuilder str = new StringBuilder(indent(depth));
            return str.append(d).append('\n').toString();
        }
    }
}

