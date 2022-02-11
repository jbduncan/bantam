package me.jbduncan;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

public final class Lexer implements Iterator<Token> {
  public Lexer(String text) {
    this.index = 0;
    this.text = text;

    // Register all of the TokenTypes that are explicit punctuators.
    punctuators = Arrays.stream(TokenType.values())
        .filter(tokenType -> tokenType.punctuator() != null)
        .collect(toUnmodifiableMap(TokenType::punctuator, v -> v));
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Token next() {
    while (index < text.length()) {
      char c = text.charAt(index++);

      if (punctuators.containsKey(c)) {
        // Handle punctuation.
        return new Token(punctuators.get(c), Character.toString(c));
      } else if (Character.isLetter(c)) {
        // Handle names.
        int start = index - 1;
        while (index < text.length()) {
          if (!Character.isLetter(text.charAt(index))) break;
          index++;
        }

        String name = text.substring(start, index);
        return new Token(TokenType.NAME, name);
      }
      // Ignore all other characters (whitespace, etc.)
    }

    // Once we've reached the end of the string, just return EOF tokens. We'll
    // just keeping returning them as many times as we're asked so that the
    // parser's lookahead doesn't have to worry about running out of tokens.
    return new Token(TokenType.EOF, "");
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  private final Map<Character, TokenType> punctuators;
  private final String text;
  private int index;
}
