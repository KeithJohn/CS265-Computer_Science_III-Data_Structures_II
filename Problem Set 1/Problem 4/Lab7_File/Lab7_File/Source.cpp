#include <iostream>
#include <fstream>
#include <string>

using namespace std;

struct Check {
	string Date;
	string firstName;
	string lastName;
	int ammountDollars;
	int amountCents;
	string Payee;
};

string numberSpell(Check check) {
	int firstNumber = check.ammountDollars / 10;
	int secondNumber = check.ammountDollars % 10;
	string numberToWord;
	switch (firstNumber)
	{
	case 1:
		switch (secondNumber) {
		case 1:
			numberToWord.append("eleven");
			return numberToWord;
		case 2:
			numberToWord.append("twelve");
			return numberToWord;
		case 3:
			numberToWord.append("thirteen");
			return numberToWord;
		case 4:
			numberToWord.append("fourteen");
			return numberToWord;
		case 5:
			numberToWord.append("fifteen");
			return numberToWord;
		case 6:
			numberToWord.append("sixteen");
			return numberToWord;
		case 7:
			numberToWord.append("seventeen");
			return numberToWord;
		case 8:
			numberToWord.append("eighteen");
			return numberToWord;
		case 9:
			numberToWord.append("nineteen");
			return numberToWord;
		case 0:
			numberToWord.append("ten");
			return numberToWord;
		}
	case 2:
		numberToWord.append("twenty ");
		break;
	case  3:
		numberToWord.append("thirty ");
		break;
	case  4:
		numberToWord.append("fourty ");
		break;
	case 5:
		numberToWord.append("fifty ");
		break;
	case 6:
		numberToWord.append("sixty ");
		break;
	case 7:
		numberToWord.append("seventy ");
		break;
	case 8:
		numberToWord.append("eighty ");
		break;
	case 9:
		numberToWord.append("ninety ");
		break;
	}
	switch (secondNumber) {
	case 1:
		numberToWord.append("one");
		return numberToWord;
	case 2:
		numberToWord.append("two");
		return numberToWord;
	case 3:
		numberToWord.append("three");
		return numberToWord;
	case 4:
		numberToWord.append("four");
		return numberToWord;
	case 5:
		numberToWord.append("five");
		return numberToWord;
	case 6:
		numberToWord.append("six");
		return numberToWord;
	case 7:
		numberToWord.append("seven");
		return numberToWord;
	case 8:
		numberToWord.append("eight");
		return numberToWord;
	case 9:
		numberToWord.append("nine");
		return numberToWord;
	case 0:
		numberToWord.append("");
		return numberToWord;
	}
}

void saveCheck(Check check) {
	ofstream outfile;
	outfile.open("check.txt");
	
	outfile << "your check:" << endl << endl;
	outfile << check.firstName << " " << check.lastName << "						" << check.Date << endl;
	outfile << "pay to:   " << check.Payee << "				$" << check.ammountDollars << ".";
	if (check.amountCents < 10) {
		outfile << "0" << check.amountCents << endl;
	}
	else {
		outfile << check.amountCents << endl;
	}
	outfile << numberSpell(check) << " and " << check.amountCents << "/100 				dollars" << endl;
}

int main() {
	Check newCheck;

	ifstream infile;
	infile.open("Checkdatabase.txt");
	
	string tmp;

	infile >> tmp >> newCheck.Date;

	infile >> tmp >> newCheck.firstName >> newCheck.lastName;

	infile >> tmp  >> tmp >> newCheck.ammountDollars;

	infile >> tmp >> newCheck.amountCents;
	
	infile >> tmp;
	string payee = ""; string newPayee = "";
	while (!infile.eof()) {
		infile >> newPayee;
		payee.append(newPayee + " ");
	}
	newCheck.Payee = payee;
	saveCheck(newCheck);

	cout << "Check saved to check.txt" << endl;
	return 0;
}