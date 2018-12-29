#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;
int const SIZE = 10;

void assign(int wins[]) {
	for (int i = 0; i < SIZE; i++) {
		wins[i] = -1;
	}
}

bool check(int numToCheck, int wins[]) {
	for (int i = 0; i < SIZE; i++) {
		if (numToCheck == wins[i]) {
			return true;
		}
	}
	return false;
}

void draw(int wins[]) {
	int seed = time(NULL);
	srand(seed);
	int randomNumber;
	for (int i = 0; i < SIZE; i++) {
		do {
			randomNumber = rand();
			randomNumber = randomNumber % 100;
		} while (check(randomNumber, wins));
		wins[i] = randomNumber;
	}
}

int entry() {
	cout << "Enter a number from 0-99:   ";
	int userEntry;
	cin >> userEntry;
	return userEntry;
}

void printOut(int wins[]) {
	for (int i = 0; i < SIZE; i++) {
		cout << wins[i] << " ";
	}
	cout << endl;
}

int main() {
	int wins[SIZE];

	assign(wins);
	draw(wins);
	
	int userEntry = entry();
	if (check(userEntry, wins)) {
		cout << "Congrats you won!" << endl;
	}
	else {
		cout << "Sorry you lost." << endl;
	}
	printOut(wins);
	return 0;
}