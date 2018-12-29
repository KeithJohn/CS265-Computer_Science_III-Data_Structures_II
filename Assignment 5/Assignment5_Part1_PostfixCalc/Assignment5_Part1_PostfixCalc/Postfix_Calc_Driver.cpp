#include <iostream>
#include <fstream>
#include <string>
#include "Postfix_Calc.h"

using namespace std;

int main() {
	string expression = " ";
	while (expression != "0") {
		cout << "Enter a postfix expression to evaluate or enter 0 to quit: ";
		cin >> expression;
		if (expression != "0") {
			evaluateExpression(expression);
		}
		
	}
	return 0;
}