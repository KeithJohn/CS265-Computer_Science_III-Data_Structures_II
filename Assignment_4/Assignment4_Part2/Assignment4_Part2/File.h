#ifndef FILE_H_
#define FILE_H_
#include <string>
#include "Document.h"

using namespace std;

class File : public Document {

public:
	File();
	void setPathName(string s);
	string getPathName();
	File& operator=(const File&);

private:
	string PathName;
};


#endif /*FILE_H_*/