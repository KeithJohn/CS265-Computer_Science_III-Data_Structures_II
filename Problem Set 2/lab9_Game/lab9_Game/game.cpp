#include "battleship.h"
#include <iostream>
#include <ctime>

using namespace std;

int main() {
	srand(time(NULL));
	Fleet myFleet;
	myFleet.deployFleet();
	while (myFleet.operational()) {
		cout << "Do you want the ships positions and status printed? y/n: ";
		char userAnswer;
		cin >> userAnswer;
		if (userAnswer == 'y')
			myFleet.printFleet();
		Location myLoc;
		myLoc.fire();
		bool hit = myFleet.isHitNSink(myLoc);
		if( hit == true) {
			cout << "Hit! Ship sunk." << endl;
		}
		else {
			cout << "Miss :(" << endl;
		}
		
	}
}