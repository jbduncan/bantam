package me.jbduncan;

import static java.util.Objects.requireNonNull;

public record ConditionalExpression(
    Expression condition, Expression thenArm, Expression elseArm) implements Expression {
  public ConditionalExpression {
    requireNonNull(condition);
    requireNonNull(condition);
    requireNonNull(condition);
  }

  @Override
  public void print(StringBuilder builder) {
    builder.append("(");
    condition.print(builder);
    builder.append(" ? ");
    thenArm.print(builder);
    builder.append(" : ");
    elseArm.print(builder);
    builder.append(")");
  }
}
