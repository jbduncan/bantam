package me.jbduncan;

/**
 * Generic infix parselet for an unary arithmetic operator. Parses postfix
 * unary "?" expressions.
 */
public final class PostfixOperatorParselet implements InfixParselet {
  private final int precedence;

  public PostfixOperatorParselet(int precedence) {
    this.precedence = precedence;
  }

  @Override
  public Expression parse(Parser parser, Expression left, Token token) {
    return new PostfixExpression(left, token.type());
  }

  @Override
  public int precedence() {
    return precedence;
  }
}
