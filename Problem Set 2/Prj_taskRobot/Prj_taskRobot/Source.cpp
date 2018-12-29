#include <iostream>
#include <fstream>
#include <string>

using namespace std;
int numOfTasks;
string dashes = "------------------------";


struct Task
{
	string taskDescription;
	string time;
	string duration;
	string date;
	int priority;
};
void printTasks(Task taskList[]) {
	cout << "Number of tasks: " << numOfTasks << endl;
	cout << dashes << endl;
	for (int i = 0; i < numOfTasks; i++) {
		
		cout << "Task " << i + 1 << endl;
		cout << "description: " << taskList[i].taskDescription << endl;
		cout << "Time: " << taskList[i].time << endl;
		cout << "Duration: " << taskList[i].duration << endl;
		cout << "Date: " << taskList[i].date << endl;
		cout << "Priority: " << taskList[i].priority << endl;
		cout << dashes << endl;
	}
}
Task tmpTask;
void swap(Task &a, Task &b) {
	tmpTask = a;
	a = b;
	b = tmpTask;
}
void sortTasks(Task taskList[]) {
	for (int outerLoopCounter = 0; outerLoopCounter < numOfTasks; outerLoopCounter++) {
		for (int innerLoopCounter = outerLoopCounter + 1; innerLoopCounter < numOfTasks; innerLoopCounter++) {
			if (taskList[outerLoopCounter].priority > taskList[innerLoopCounter].priority) {
				swap(taskList[outerLoopCounter], taskList[innerLoopCounter]);
			}
		}
	}
}

int main() {
	ifstream infile;
	infile.open("logFile.txt");
	

	Task newTask; 
	
	infile >> numOfTasks;
	
	Task taskList[300];
	
	for (int i = 0; i < numOfTasks; i++) {
		getline(infile, newTask.taskDescription);
		getline(infile, newTask.taskDescription);
		getline(infile, newTask.time);
		getline(infile, newTask.duration);
		getline(infile, newTask.date);
		infile >> newTask.priority;
		taskList[i] = newTask;
	}
	sortTasks(taskList);
	printTasks(taskList);

}
