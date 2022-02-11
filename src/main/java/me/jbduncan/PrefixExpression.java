package me.jbduncan;

public record PrefixExpression(TokenType operator, Expression right) implements Expression {
  @Override
  public void print(StringBuilder builder) {
    builder.append("(").append(operator.punctuator());
    right.print(builder);
    builder.append(")");
  }
}
