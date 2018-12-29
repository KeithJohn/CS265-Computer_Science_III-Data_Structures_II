#include "varArray.h"
#include <iostream>

using namespace std;

varArray::varArray()
{

}

int varArray::check(int number)
{
	for (int i = 0; i < size; i++) {
		if (dArray[i] == number) {
			return i;
		}
	}
	return -1;
}

void varArray::addNumber(int number)
{
	if (check(number) == -1) {
		int *tmpArray;
		++size;
		tmpArray = new int[size];
		for (int i = 0; i < size - 1; i++) {
			tmpArray[i] = dArray[i];
		}
		tmpArray[size - 1] = number;
		delete dArray;
		dArray = tmpArray;
	}
}

void varArray::removeNumber(int number)
{
	if (check(number) != -1) {
		int *tmpArray;
		--size;
		tmpArray = new int[size];
		int j = 0;
		for (int i = 0; i < size + 1; i++) {
			if (dArray[i] != number) {
				tmpArray[j] = dArray[i];
				++j;
			}
		}
		delete dArray;
		dArray = tmpArray;
	}
}

void varArray::output()
{
	cout << endl;
	for (int i = 0; i < size; i++) {
		cout << dArray[i] << " ";
	}
	cout << endl;
}

varArray::varArray(const varArray &org)
{
	size = org.size;
	dArray = new int[size];
	for (int i = 0; i < size; i++) {
		dArray[i] = org.dArray[i];
	}
}


varArray & varArray::operator=(const varArray &rhs)
{
	if (this != &rhs) {
		size = rhs.size;
		delete[] dArray;
		dArray = new int[size];
		for (int i = 0; i < size; i++) {
			dArray[i] = rhs.dArray[i];
		}
	}
	return *this;
}

varArray::~varArray()
{
	delete[] dArray;
}


