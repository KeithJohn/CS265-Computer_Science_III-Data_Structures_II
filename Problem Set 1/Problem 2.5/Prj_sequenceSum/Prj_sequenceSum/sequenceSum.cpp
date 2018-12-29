#include <iostream>
#include <iomanip>

using namespace std;

int main() {
	cout << "Enter a real number   ";
	double userNum;
	cin >> userNum;
	double sum = 0;
	double counter = 1;
	double num = 1;
	if (userNum < 1.0) {
		return 0;
	}
	else {
		while (sum < userNum) {
			num = 1 / counter;
			sum += num;
			counter++;
		}
		cout << "1";
		for (int j = 2; j < counter; j++) {
			cout << " + 1/" << j;
		}
		cout << fixed;
		cout << " = " << setprecision(3) << sum << endl;
	}
	return 0;
}