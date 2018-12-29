#include <iostream>
#include <string>

using namespace std;
const int CLASS_SIZE = 30;

struct studentRecord {
	string ID;
	double quiz1;
	double quiz2;
	double midterm;
	double final;
	double average;
	char grade;
};

char getLetterGrade(int);
void printStudentRecords(studentRecord[],int);

int main() {
	
	studentRecord studentRecordList[CLASS_SIZE];
	studentRecord tmpStudentRecord;
	cout << "Enter class size ";
	int Acutal_Class_Size;
	cin >> Acutal_Class_Size;
	cout << "Enter all student records then enter " << endl;
	
	for (int i = 0; i < Acutal_Class_Size; i++) {
		cout << "Student ID:   ";
		cin >> tmpStudentRecord.ID;
		
		cout << "Quiz 1 score:   ";
		cin >> tmpStudentRecord.quiz1;
		
		
		cout << "Quiz 2 score:   ";
		cin >> tmpStudentRecord.quiz2;
		
		
		cout << "Midterm score:   ";
		cin >> tmpStudentRecord.midterm;
		
		cout << "Final score:   ";
		cin >> tmpStudentRecord.final;

		tmpStudentRecord.average = (tmpStudentRecord.quiz1 + tmpStudentRecord.quiz2) * 1.25 + tmpStudentRecord.midterm * .25 + tmpStudentRecord.final *.50;
		
		tmpStudentRecord.grade = getLetterGrade((int)tmpStudentRecord.average);
		
		
		studentRecordList[i] = tmpStudentRecord;
	}
	printStudentRecords(studentRecordList, Acutal_Class_Size);
}

char getLetterGrade(int ave) {
	
	switch (ave/10)
	{
	case 10:
		return 'A';
	case 9:
		return 'A';
	case 8: 
		return 'B';
	case 7: 
		return 'C';
	case 6: 
		return 'D';
	default:
		return 'F';
	}
}

void printStudentRecords(studentRecord recordList[], int classSize) {
	for (int i = 0; i < classSize; i++) {
		cout << endl << recordList[i].ID << endl;
		cout << "Quiz 1 : " << recordList[i].quiz1 << "/10 " << endl;
		cout << "Quiz 2 : " << recordList[i].quiz2 << "/10 " << endl;
		cout << "Midterm : " << recordList[i].midterm << "/100 " << endl;
		cout << "Final : " << recordList[i].final << "/100 " << endl;
		cout << "Students' average score : " << recordList[i].average << endl;
		cout << "Student's grade : " << recordList[i].grade << endl ;
	}
}
