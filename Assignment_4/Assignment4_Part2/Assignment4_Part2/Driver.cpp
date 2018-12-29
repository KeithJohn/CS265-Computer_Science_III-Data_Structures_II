#include <string>
#include <iostream>
#include "Document.h"
#include "Email.h"
#include "File.h"

using namespace std;
bool ContainsKeyword(Document& docObject, string keyword);

int main() {
	Document newDoc;
	Email newEmail;
	File newFile;

	newDoc.setText("Keith is very cool");
	newEmail.setText("Prof Saleh is very smart!");
	newFile.setText("CPS is my favorite class");

	if (ContainsKeyword(newDoc, "cool")) {
		cout << "True" << endl;
	}
	if (ContainsKeyword(newEmail, "Saleh")) {
		cout << "True" << endl;
	}
	if (ContainsKeyword(newFile, "dog")) {
		cout << "True" << endl;
	}

}

bool ContainsKeyword(Document& docObject, string keyword)
{
	if (docObject.getText().find(keyword) != string::npos)
		return true;
	return false;
}