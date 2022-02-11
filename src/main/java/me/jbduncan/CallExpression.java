package me.jbduncan;

import java.util.List;

import static java.util.Objects.requireNonNull;

public record CallExpression(Expression function, List<Expression> args) implements Expression {
  public CallExpression {
    requireNonNull(function);
    args = List.copyOf(args);
  }

  @Override
  public void print(StringBuilder builder) {
    function.print(builder);
    builder.append("(");
    for (int i = 0; i < args.size(); i++) {
      args.get(i).print(builder);
      if (i < args.size() - 1) {
        builder.append(", ");
      }
    }
    builder.append(")");
  }
}
