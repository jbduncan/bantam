package me.jbduncan;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BantamParserTests {
  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          'a()', 'a()'
          'a(b)', 'a(b)'
          'a(b, c)', 'a(b, c)'
          'a(b)(c)', 'a(b)(c)'
          'a(b) + c(d)', '(a(b) + c(d))'
          'a(b ? c : d, e + f)', 'a((b ? c : d), (e + f))'
          """)
  void functionCall(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          '~!-+a', '(~(!(-(+a))))'
          'a!!!', '(((a!)!)!)'
          """)
  void unaryPrecedence(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          '-a * b', '((-a) * b)'
          '!a + b', '((!a) + b)'
          '~a ^ b', '((~a) ^ b)'
          '-a!', '(-(a!))'
          '!a!', '(!(a!))'
          """)
  void unaryAndBinaryPrecedence(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          'a = b + c * d ^ e - f / g', '(a = ((b + (c * (d ^ e))) - (f / g)))'
          """)
  void binaryPrecedence(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          'a = b = c', '(a = (b = c))'
          'a + b - c', '((a + b) - c)'
          'a * b / c', '((a * b) / c)'
          'a ^ b ^ c', '(a ^ (b ^ c))'
          """)
  void binaryAssociativity(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          'a ? b : c ? d : e', '(a ? b : (c ? d : e))'
          'a ? b ? c : d : e', '(a ? (b ? c : d) : e)'
          'a + b ? c * d : e / f', '((a + b) ? (c * d) : (e / f))'
          """)
  void conditionalOperator(String input, String output) {
    test(input, output);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          'a + (b + c) + d', '((a + (b + c)) + d)'
          'a ^ (b + c)', '(a ^ (b + c))'
          '(!a)!', '((!a)!)'
          """)
  void grouping(String input, String output) {
    test(input, output);
  }

  private void test(String input, String output) {
    StringBuilder result = new StringBuilder();
    new BantamParser(new Lexer(input)).parseExpression().print(result);
    assertEquals(output, result.toString());
  }
}