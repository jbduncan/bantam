package me.jbduncan;

public sealed interface Expression permits AssignExpression, CallExpression, ConditionalExpression, NameExpression, OperatorExpression, PostfixExpression, PrefixExpression {
  void print(StringBuilder builder);
}
