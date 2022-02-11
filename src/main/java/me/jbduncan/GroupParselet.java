package me.jbduncan;

/**
 * Parses parentheses used to group an expression, like "a * (b + c)".
 */
public enum GroupParselet implements PrefixParselet {
  INSTANCE;

  @Override
  public Expression parse(Parser parser, Token token) {
    var expression = parser.parseExpression();
    parser.consume(TokenType.RIGHT_PAREN);
    return expression;
  }
}
