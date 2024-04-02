package misc;

import java.util.Stack;

import static java.lang.Long.parseLong;

public abstract class PrePostInFixNotation {
    enum Operator {
        Multiply,
        Divide,
        Mod,
        Add,
        Subtract;

        static Operator from(String value) {
            Operator result = null;
            switch (value) {
                case "*" -> result = Multiply;
                case "/" -> result = Divide;
                case "+" -> result = Add;
                case "-" -> result = Subtract;
                case "%" -> result = Mod;
            }
            return result;
        }
    }

    public static Long evalPrefixExpression(final String expression) {
        final Stack<Operator> operators = new Stack<>();
        final Stack<Long> operands = new Stack<>();
        String[] tokens = expression.split(" ");

        if (!isOperator(Operator.from(tokens[0]))) {
            illegalExpression(expression);
        }

        Operator lastToken = null;
        for (String token : tokens) {
            final Operator operator = Operator.from(token);
            if (isOperator(operator)) {
                // defer till we have operands
                operators.push(operator);
            }
            else if (isOperand(token)) {
                if (!isOperator(lastToken)) {
                    processOperator(operators, operands, parseLong(token));
                }
                else {
                    // defer till we have enough operands
                    operands.push(parseLong(token));
                }
            }
            else {
                illegalExpression(expression);
            }

            lastToken = operator;
        }

        // process remaining operators and operands if any
        while (!operators.isEmpty()) {
            processOperator(operators, operands, operands.pop());
        }

        // ultimately, after all operators processed, should be left with a single value on the operand stack, our result
        if (!(operands.size() == 1)) {
            illegalExpression(expression);
        }

        return operands.pop();
    }

    private static void processOperator(final Stack<Operator> operators, final Stack<Long> operands, final long rightOperand) {
        Operator operator = operators.pop();
        Long right = rightOperand;
        Long left = operands.pop();
        operands.push(evaluate(operator, left, right));
    }

    private static Long evaluate(final Operator operator, final Long left, final Long right) {
        long result = 0L;
        switch (operator) {
            case Multiply -> result = (left * right);
            case Divide -> result = (left / right);
            case Add -> result = (left + right);
            case Subtract -> result = (left - right);
            case Mod -> result = (left % right);
        }

        return result;
    }

    private static void illegalExpression(final String expression) {
        throw new IllegalArgumentException("invalid expression " + expression);
    }

    private static boolean isOperand(final String token) {
        try {
            parseLong(token);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(final Operator operator) {
        return operator != null;
    }
}
