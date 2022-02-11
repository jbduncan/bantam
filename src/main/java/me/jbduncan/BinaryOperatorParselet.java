package me.jbduncan;

/**
 * Generic infix parselet for a binary arithmetic operator. The only
 * difference when parsing, "+", "-", "*", "/", and "^" is precedence and
 * associativity, so we can use a single parselet class for all of those.
 */
public final class BinaryOperatorParselet implements InfixParselet {
  private final int precedence;
  // TODO: Consider changing into an enum: Associativity.{LEFT, RIGHT}
  private final boolean right;

  public BinaryOperatorParselet(int precedence, boolean right) {
    this.precedence = precedence;
    this.right = right;
  }

  @Override
  public Expression parse(Parser parser, Expression left, Token token) {
    // To handle right-associative operators like "^", we allow a slightly
    // lower precedence when parsing the right-hand side. This will let a
    // parselet with the same precedence appear on the right, which will then
    // take *this* parselet's result as its left-hand argument.
    var right = parser.parseExpression(precedence - (this.right ? 1 : 0));

    return new OperatorExpression(left, token.type(), right);
  }

  @Override
  public int precedence() {
    return precedence;
  }
}
