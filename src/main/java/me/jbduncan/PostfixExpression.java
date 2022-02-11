package me.jbduncan;

import static java.util.Objects.requireNonNull;

public record PostfixExpression(Expression left, TokenType operator) implements Expression {
  public PostfixExpression {
    requireNonNull(left);
    requireNonNull(operator);
  }

  public void print(StringBuilder builder) {
    builder.append("(");
    left.print(builder);
    builder.append(operator.punctuator()).append(")");
  }
}
