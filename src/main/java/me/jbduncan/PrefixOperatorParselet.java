package me.jbduncan;

/**
 * Generic prefix parselet for an unary arithmetic operator. Parses prefix
 * unary "-", "+", "~", and "!" expressions.
 */
public final class PrefixOperatorParselet implements PrefixParselet {
  private final int precedence;

  public PrefixOperatorParselet(int precedence) {
    this.precedence = precedence;
  }

  @Override
  public Expression parse(Parser parser, Token token) {
    // To handle right-associative operators like "^", we allow a slightly
    // lower precedence when parsing the right-hand side. This will let a
    // parselet with the same precedence appear on the right, which will then
    // take *this* parselet's result as its left-hand argument.
    var right = parser.parseExpression(precedence);

    return new PrefixExpression(token.type(), right);
  }
}
