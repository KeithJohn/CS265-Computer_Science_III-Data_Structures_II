#include "Postfix_Calc.h"
#include <string>
#include <stack>
#include <iostream>

using namespace std;

void evaluateExpression(string expression)
{
	stack<int> expStack;
	for (int i = 0; i < expression.size(); i++) {
		if (isdigit(expression[i])) {
			expStack.push(expression[i]-'0');
			
		}
		else {
			evaluateOpr(expStack, expression[i], expression);
			if (expStack.empty()) {
				return;
			}
		}
	}
	printResult(expStack);
}

void evaluateOpr(stack<int> & expStack, char opr, string expression)
{
	if (opr == '=') {
		return;
	}
	if (expStack.size() < 2 | expStack.size() > 2) {
		discardExp(expStack, expression);
		cout << "operands error" << endl;
		return;
	}
	else {
		int n2 = expStack.top();
		
		expStack.pop();
		int n1 = expStack.top();
		
		expStack.pop();
		switch (opr) {
		case '+':
			n1 = n1 + n2;
			
			expStack.push(n1);
			return;
		case '-':
			n1 = n1 - n2;
			expStack.push(n1);
			return;
		case '*':
			n1 = n1*n2;
			expStack.push(n1);
			return;
		case '/':
			n1 = n1 / n2;
			expStack.push(n1);
			return;
		}
	}
	discardExp( expStack, expression);
	cout << "illegal character error \n";
}

void discardExp(stack<int> & expStack, string expression)
{
	cout << "Error found in expression: " << endl << expression << endl;
	while (expStack.empty() == false) {
		expStack.pop();
	}

}

void printResult(stack<int> & expStack)
{
	cout << "The answer is " << expStack.top() << endl;
	expStack.pop();
}
