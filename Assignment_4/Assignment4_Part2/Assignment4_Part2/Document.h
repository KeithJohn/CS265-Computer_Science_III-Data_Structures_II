#ifndef DOCUMENT_H_
#define DOCUMENT_H_
#include <string>
using namespace std;
class Document {

public:
	Document();							   
	string getText();
	void setText(string s);
	Document& operator=(const Document&); 

private:
	string text;

};

#endif /*DOCUMENT_H_*/