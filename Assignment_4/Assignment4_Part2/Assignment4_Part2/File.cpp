#include <string>
#include "File.h"
using namespace std;

File::File() {
	PathName = "";
}

void File::setPathName(string s) {
	PathName = s;
}

string File::getPathName() {
	return PathName;
}

File& File::operator=(const File& file) {
	if (this != &file) {
		PathName = file.PathName;
	}
	return *this;
}