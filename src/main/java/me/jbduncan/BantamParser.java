package me.jbduncan;

public class BantamParser extends Parser {
  public BantamParser(Lexer lexer) {
    super(lexer);

    // Register all the parselets for the grammar.

    // Register the ones that need special properties.
    register(TokenType.NAME, NameParselet.INSTANCE);
    register(TokenType.ASSIGN, AssignParselet.INSTANCE);
    register(TokenType.QUESTION, ConditionalParselet.INSTANCE);
    register(TokenType.LEFT_PAREN, GroupParselet.INSTANCE);
    register(TokenType.LEFT_PAREN, CallParselet.INSTANCE);

    // Register the simple operator parselets.
    prefix(TokenType.PLUS, Precedence.PREFIX);
    prefix(TokenType.MINUS, Precedence.PREFIX);
    prefix(TokenType.TILDE, Precedence.PREFIX);
    prefix(TokenType.BANG, Precedence.PREFIX);

    // For kicks, we'll make "!" both prefix and postfix, kind of like ++.
    postfix(TokenType.BANG, Precedence.POSTFIX);

    infixLeft(TokenType.PLUS, Precedence.SUM);
    infixLeft(TokenType.MINUS, Precedence.SUM);
    infixLeft(TokenType.ASTERISK, Precedence.PRODUCT);
    infixLeft(TokenType.SLASH, Precedence.PRODUCT);
    infixRight(TokenType.CARET, Precedence.EXPONENT);
  }

  /**
   * Registers a postfix unary operator parselet for the given token and
   * precedence.
   */
  public void postfix(TokenType token, int precedence) {
    register(token, new PostfixOperatorParselet(precedence));
  }

  /**
   * Registers a prefix unary operator parselet for the given token and
   * precedence.
   */
  public void prefix(TokenType token, int precedence) {
    register(token, new PrefixOperatorParselet(precedence));
  }

  /**
   * Registers a left-associative binary operator parselet for the given token
   * and precedence.
   */
  public void infixLeft(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, false));
  }

  /**
   * Registers a right-associative binary operator parselet for the given token
   * and precedence.
   */
  public void infixRight(TokenType token, int precedence) {
    register(token, new BinaryOperatorParselet(precedence, true));
  }
}
