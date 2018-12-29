#include <iostream>
#include <string>

using namespace std;

struct Student{
	string firstName;
	string lastName;
	string email;
	double GPA;
};

int numOfStudents; int numOfClasses; double GPA = 0; double tmpGPA = 0; 

Student tmpStudent;

void printStudent(Student studentArr[] ){
	cout << endl;
	for(int printCounter = 0; printCounter< numOfStudents; printCounter++){
		cout << studentArr[printCounter].firstName << " " << studentArr[printCounter].lastName << " " << studentArr[printCounter].email << " " << studentArr[printCounter].GPA << endl;

	}
	cout << endl;
}
void swap(Student &a, Student &b){
	tmpStudent = a;
	a = b;
	b = tmpStudent;
}
void sortStudentList(Student studentArr[]){
	for (int outerLoopCounter = 0; outerLoopCounter < numOfStudents; outerLoopCounter++){
		for (int innerLoopCounter = outerLoopCounter +1; innerLoopCounter < numOfStudents; innerLoopCounter++){
			if (studentArr[outerLoopCounter].GPA < studentArr[innerLoopCounter].GPA){
				swap(studentArr[outerLoopCounter], studentArr[innerLoopCounter]);
			}
		}
	}
}

int main(){
	Student studentList[100];
	cout << "Enter the number of students   ";
	cin >> numOfStudents;
	for (int i = 0; i < numOfStudents; i++){
		cout << "First Name:   ";
		cin >> tmpStudent.firstName;
		cout << "Last Name:   ";
		cin >> tmpStudent.lastName;
		cout << "Email:   ";
		cin >> tmpStudent.email;
		do{
			cout << "Enter the number of classes   ";
			cin >> numOfClasses;

		} while (numOfClasses < 1 || numOfClasses>5);
		for (int j = 0; j < numOfClasses; j++){
			cout << "GPA for class:   ";
			cin >> tmpGPA;
			GPA += tmpGPA;
		}
		tmpGPA = GPA / numOfClasses;
		GPA = tmpGPA;
		tmpStudent.GPA = GPA;
		studentList[i] = tmpStudent;
		GPA = 0;
	}
	sortStudentList(studentList);
	printStudent(studentList);

	return 0;
}