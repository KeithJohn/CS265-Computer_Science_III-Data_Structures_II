#include <string>
#include "Email.h"
using namespace std;

Email::Email() {
	Sender = "";
	Recipient = "";
	Title = "";
}

void Email::setSender(string s) {
	Sender = s;
}

string Email::getSender() {
	return Sender;
}
void Email::setRecipient(string s) {
	Recipient = s;
}

string Email::getRecipient() {
	return Recipient;
}
void Email::setTitle(string s) {
	Title = s;
}

string Email::getTitle() {
	return Title;
}

Email& Email::operator=(const Email& ema) {
	if (this != &ema) {
		Sender = ema.Sender;
		Recipient = ema.Recipient;
		Title = ema.Title;
	}
	return *this;
}