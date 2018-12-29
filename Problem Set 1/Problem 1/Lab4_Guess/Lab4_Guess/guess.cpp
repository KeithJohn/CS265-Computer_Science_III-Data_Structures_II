#include <ctime>
#include <cstdlib>
#include <iostream>

using namespace std;

int main() {
	int seed = time(NULL);
	srand(seed);
	int decision = 0; int randomNumber; int guess = -1;
	
	while (decision == 0) {
		randomNumber = rand();
		randomNumber = randomNumber % 100;
		cout << "I selected a number between 0 and 99, what is it?   ";
	
		while (guess != randomNumber) {
			
			cin >> guess;
			if (guess < randomNumber) {
				cout << "wrong. It is larger, what is it?   ";
			}else if(guess > randomNumber) {
				cout << "wrong. It is smaller, what is it?   ";
			}else {
				cout << "correct!" << endl;
			}

		}
		cout << "Enter 0 to play again   ";
		cin >> decision;

	}
	return 0;
}