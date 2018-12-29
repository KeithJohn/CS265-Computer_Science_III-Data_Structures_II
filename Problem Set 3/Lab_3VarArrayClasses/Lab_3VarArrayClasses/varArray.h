// variable size array class
// Assignment 4.  Dr. Alnaeli + Dr. Nesterenko


#ifndef VARARRAY_H_
#define VARARRAY_H_

class varArray{
public:
	varArray(); // void constructor
	template <class T>
	T arraySize() const { return size; } // returns the size of the array
	template <class T>
	T check(T number); // returns index of element containg "number" or -1 if none
	template <class T>
	void addNumber(T);    // adds number to the array
	template <class T>
	void removeNumber(T); // deletes the number from the array
	void output();      // prints the values of the array

	// big three
	varArray(const varArray&); // copy constructor
	varArray& operator=(const varArray&); // overloaded assignment
	~varArray(); // destructor

private:
	int *dArray; // pointer to the dynamically allocated array
	int size;   // array size
};

#endif /* VARARRAY_H_ */