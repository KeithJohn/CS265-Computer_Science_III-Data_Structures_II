#ifndef EMAIL_H_
#define EMAIL_H_
#include <string>
#include "Document.h"

using namespace std;

class Email : public Document {

public:

	Email();
	void setSender(string s);
	string getSender();
	void setRecipient(string s);
	string getRecipient();
	void setTitle(string s);
	string getTitle();
	Email& operator=(const Email&);

private:
	string Sender;
	string Recipient;
	string Title;
};

#endif /*EMAIL_H_*/