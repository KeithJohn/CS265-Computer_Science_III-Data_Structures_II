#include <string>
#include <iostream>
using namespace std;
class Father {
public:
	Father();
	string toString();
};
//-----------------------
class Son : public Father {
public:
	Son();
	string toString();
};

//------------------------
Father::Father() {}
string Father::toString() {
	return "I am the Father";
}

Son::Son() {}
string Son::toString() {
	return "I am the Son";
}

Father *people[7];
people[0] = new Father();
people[1] = new Son();
cout << (*people[0]).toString() << endl << (*people[1]).toString() << endl;