#pragma once
#include <string>
#include <stack>

using namespace std;

void evaluateExpression(string);
void evaluateOpr(stack<int> &, char, string);
void discardExp(stack<int> & , string);
void printResult(stack<int> &);