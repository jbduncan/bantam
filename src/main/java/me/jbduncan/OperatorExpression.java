package me.jbduncan;

import static java.util.Objects.requireNonNull;

public record OperatorExpression(Expression left, TokenType operator, Expression right) implements Expression {
  public OperatorExpression {
    requireNonNull(left);
    requireNonNull(operator);
    requireNonNull(right);
  }

  public void print(StringBuilder builder) {
    builder.append("(");
    left.print(builder);
    builder.append(" ").append(operator.punctuator()).append(" ");
    right.print(builder);
    builder.append(")");
  }
}