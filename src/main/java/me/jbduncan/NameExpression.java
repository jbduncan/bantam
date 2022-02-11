package me.jbduncan;

import static java.util.Objects.requireNonNull;

public record NameExpression(String name) implements Expression {
  public NameExpression {
    requireNonNull(name);
  }

  @Override
  public void print(StringBuilder builder) {
    builder.append(name);
  }
}
