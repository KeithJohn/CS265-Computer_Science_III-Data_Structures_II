#ifndef ARRAYIMP_H_
#define ARRAYIMP_H_
template <typename T>
class ArrayImp {
public:
	ArrayImp(); // void constructor
	int arraySize() const { return size; } // returns the size of the array

	int check(T num); // returns index of element containg "number" or -1 if none
	void addNumber(T num);    // adds number to the array
	void removeNumber(T num); // deletes the number from the array
	void output();      // prints the values of the array

						// big three
	ArrayImp<T>(const ArrayImp<T>&); // copy constructor
	ArrayImp<T>& operator=(const ArrayImp<T>&); // overloaded assignment
	~ArrayImp(); // destructor

private:
	T *dArray; // pointer to the dynamically allocated array
	int size;   // array size
};
#include "implementation.template"
#endif /* ARRAYIMP_H_ */

