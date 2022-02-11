package me.jbduncan;

import java.util.ArrayList;

/**
 * Parselet to parse a function call like "a(b, c, d)".
 */
public enum CallParselet implements InfixParselet {
  INSTANCE;

  @Override
  public Expression parse(Parser parser, Expression left, Token token) {
    // Parse the comma-separated arguments until we hit ")".
    var args = new ArrayList<Expression>();

    // There may be no arguments at all.
    if (!parser.match(TokenType.RIGHT_PAREN)) {
      do {
        args.add(parser.parseExpression());
      } while (parser.match(TokenType.COMMA));
      parser.consume(TokenType.RIGHT_PAREN);
    }

    return new CallExpression(left, args);
  }

  @Override
  public int precedence() {
    return Precedence.CALL;
  }
}
