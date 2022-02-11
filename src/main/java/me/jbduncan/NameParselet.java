package me.jbduncan;

/**
 * Simple parselet for a named variable like "abc".
 */
public enum NameParselet implements PrefixParselet {
  INSTANCE;

  @Override
  public Expression parse(Parser parser, Token token) {
    return new NameExpression(token.text());
  }
}
