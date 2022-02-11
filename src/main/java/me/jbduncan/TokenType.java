package me.jbduncan;

import org.jetbrains.annotations.Nullable;

public enum TokenType {
  LEFT_PAREN,
  RIGHT_PAREN,
  COMMA,
  ASSIGN,
  PLUS,
  MINUS,
  ASTERISK,
  SLASH,
  CARET,
  TILDE,
  BANG,
  QUESTION,
  COLON,
  NAME,
  EOF;

  public @Nullable Character punctuator() {
    return switch (this) {
      case LEFT_PAREN -> '(';
      case RIGHT_PAREN -> ')';
      case COMMA -> ',';
      case ASSIGN -> '=';
      case PLUS -> '+';
      case MINUS -> '-';
      case ASTERISK -> '*';
      case SLASH -> '/';
      case CARET -> '^';
      case TILDE -> '~';
      case BANG -> '!';
      case QUESTION -> '?';
      case COLON -> ':';
      default -> null;
    };
  }
}
