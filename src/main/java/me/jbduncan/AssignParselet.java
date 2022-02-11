package me.jbduncan;

/**
 * Parses assignment expressions like "a = b". The left side of an assignment
 * expression must be a simple name like "a", and expressions are
 * right-associative. (In other words, "a = b = c" is parsed as "a = (b = c)").
 */
public enum AssignParselet implements InfixParselet {
  INSTANCE;

  @Override
  public Expression parse(Parser parser, Expression left, Token token) {
    Expression right = parser.parseExpression(Precedence.ASSIGNMENT - 1);

    if (!(left instanceof NameExpression name)) throw new ParseException(
        "The left-hand side of an assignment must be a name.");

    return new AssignExpression(name.name(), right);
  }

  @Override
  public int precedence() {
    return Precedence.ASSIGNMENT;
  }
}
