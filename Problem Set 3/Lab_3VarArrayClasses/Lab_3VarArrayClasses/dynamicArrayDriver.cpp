#include "varArray.h"
#include <iostream>
using namespace std;

int main() {

	char userInput;
	int userNum;
	varArray arr;

	cout << "Operations: " << endl
		<< "a: add a number" << endl
		<< "r: remove a number" << endl
		<< "q: quit program" << endl;

	cout << "enter operation: <a/r/q/> ";
	cin >> userInput;
	while (userInput != 'q' && userInput != 'Q') {
		cout << "Enter a Number: ";
		cin >> userNum;
		if (userInput == 'a' || userInput == 'A') {
			arr.addNumber(userNum);
		}else if(userInput == 'r' || userInput == 'R') {
			arr.removeNumber(userNum);
		}
		arr.output();
		cout << "enter operation: <a/r/q/> ";
		cin >> userInput;
	}
}