package me.jbduncan;

/**
 * Parselet for the condition or "ternary" operator, like "a ? b : c".
 */
public enum ConditionalParselet implements InfixParselet {
  INSTANCE;

  @Override
  public Expression parse(Parser parser, Expression left, Token token) {
    var thenArm = parser.parseExpression();
    parser.consume(TokenType.COLON);
    var elseArm = parser.parseExpression(Precedence.CONDITIONAL - 1);

    return new ConditionalExpression(left, thenArm, elseArm);
  }

  @Override
  public int precedence() {
    return Precedence.CONDITIONAL;
  }
}
