package me.jbduncan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public abstract class Parser {

  public Parser(Iterator<Token> tokens) {
    this.tokens = requireNonNull(tokens);
  }

  private final Iterator<Token> tokens;
  private final List<Token> read = new ArrayList<>();
  private final Map<TokenType, PrefixParselet> prefixParselets =
      new HashMap<>();
  private final Map<TokenType, InfixParselet> infixParselets =
      new HashMap<>();

  public Expression parseExpression() {
    return parseExpression(0);
  }

  Expression parseExpression(int precedence) {
    var token = consume();
    var prefix = prefixParselets.get(token.type());

    if (prefix == null) {
      throw new ParseException("Could not parse \"" + token.text() + "\".");
    }

    var left = prefix.parse(this, token);

    while (precedence < getPrecedence()) {
      token = consume();

      var infix = infixParselets.get(token.type());
      left = infix.parse(this, left, token);
    }

    return left;
  }

  protected void register(TokenType tokenType, PrefixParselet prefixParselet) {
    prefixParselets.put(tokenType, prefixParselet);
  }

  protected void register(TokenType tokenType, InfixParselet infixParselet) {
    infixParselets.put(tokenType, infixParselet);
  }

  boolean match(TokenType expected) {
    var token = lookAhead(0);
    if (token.type() != expected) {
      return false;
    }

    consume();
    return true;
  }

  Token consume(TokenType expected) {
    var token = lookAhead(0);
    if (token.type() != expected) {
      throw new RuntimeException(
          "Expected token " + expected + " and found " + token.type());
    }

    return consume();
  }

  private Token consume() {
    // Make sure we've read the token.
    lookAhead(0);

    return read.remove(0);
  }

  private Token lookAhead(int distance) {
    // Read in as many as needed.
    while (distance >= read.size()) {
      read.add(tokens.next());
    }

    // Get the queued token.
    return read.get(distance);
  }

  private int getPrecedence() {
    var parselet = infixParselets.get(lookAhead(0).type());
    return parselet != null ? parselet.precedence() : 0;
  }
}
