package me.jbduncan;

import static java.util.Objects.requireNonNull;

public record AssignExpression(String name, Expression right) implements Expression {
  public AssignExpression {
    requireNonNull(name);
    requireNonNull(right);
  }

  public void print(StringBuilder builder) {
    builder.append("(").append(name).append(" = ");
    right.print(builder);
    builder.append(")");
  }
}
