#include <string>
#include "Document.h"
using namespace std;


Document::Document() {
	text = "";
}

string Document::getText() {
	return text;
}

void Document::setText(string s) {
	text = s;
}

Document& Document::operator=(const Document& doc) {
	if (this != &doc) {
		text = doc.text;
	}
	return *this;
}