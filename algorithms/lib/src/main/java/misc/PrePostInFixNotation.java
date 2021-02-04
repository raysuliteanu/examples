package misc;

import java.util.Stack;

import static java.lang.Long.parseLong;

public abstract class PrePostInFixNotation {
    public static Long evalPrefixExpression(final String expression) {
        final Stack<String> operators = new Stack<>();
        final Stack<Long> operands = new Stack<>();
        String[] tokens = expression.split(" ");

        if (!isOperator(tokens[0])) {
            throw new IllegalArgumentException("invalid expression " + expression);
        }

        String lastToken = "";
        for (String token : tokens) {
            if (isOperator(token)) {
                operators.push(token);
            }
            else if (isOperand(token)) {
                if (!isOperator(lastToken)) {
                    String operator = operators.pop();
                    Long right = parseLong(token);
                    Long left = operands.pop();
                    operands.push(evaluate(operator, left, right));
                }
                else {
                    operands.push(parseLong(token));
                }
            }
            else {
                throw new IllegalArgumentException("invalid expression " + expression);
            }

            lastToken = token;
        }

        while (!operators.isEmpty()) {
            String operator = operators.pop();
            Long right = operands.pop();
            Long left = operands.pop();
            operands.push(evaluate(operator, left, right));
        }

        if (!(operands.size() == 1)) {
            throw new IllegalArgumentException("invalid expression " + expression);
        }

        return operands.pop();
    }

    private static Long evaluate(final String operator, final Long left, final Long right) {
        long result = 0L;
        switch (operator) {
            case "*" -> result = (left * right);
            case "/" -> result = (left / right);
            case "+" -> result = (left + right);
            case "-" -> result = (left - right);
            case "%" -> result = (left % right);
        }

        return result;
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

    private static boolean isOperator(final String token) {
        return token.equals("*") ||
                token.equals("/") ||
                token.equals("+") ||
                token.equals("-") ||
                token.equals("%");
    }
}
