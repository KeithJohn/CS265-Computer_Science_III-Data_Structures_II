#include <iostream>
using namespace std;

template <class E> class SLinkedList;

template <class E>
class SNode {

public:
	const E& getElem() const { return elem; };
	SNode E getNext() const { return next; };
private:
	E elem;
	SNode<E>* next;
	friend class SLinkedList<E>;
};

template <class E>
class SLinkedList {
public:
	SLinkedList();
	SLinkedList(SLinkedList<E>&);
	SLinkedList();
	bool empty() const;
	const E& front() const;
	void addFront(const E& e);
	void SLinkedList<E>::addBack(constE& e);
	void removeFront();
	SNode<E>* getHead() const { return head; };
	SNode<E>* getTail() const { return tail; };

private:
	SNode<E>* head;
	SNode<E>* tail;
};

template<class E>
SLinkedList<E>::SLinkedList()
	: head(NULL), tail(NULL){ }

template<class E>
bool SLinkedList::empty()const {
	return head == NULL;
}

template<class E>
const E& SLinkedList<E>::front()const {
	return head->elem;
}

template<class E>
SLinkedList<E>::SLinkedList() {
	while (!empty()) removeFront();
}

template<class E>
void SLinkedList<E>::addFront(const E& e) {

	SNode<E>* V = new SNode<E>;
	v->elem = e;
	v->next = head;
	head = v;
	if (tail == NULL) {
		tail = v;
	}
}

template<class E>
void SLinkedList<E>::addBack(const E& e) {
	SNode* newNode = new SNode<E>;
	newNode->elem = e;
	newNode->next = NULL;
	if (tail == NULL) {
		newNode->next = tail;
		head = newNode;
	}else{
		tail->next = newNode;
		tail = tail->next;
	}
}

template<class E>
void SLinkedList<E>::removeFront() {
	SNode* old = head;
	head = old->next;
	delete old;
}

template<class E>
SLinkedList<E>::SlinkedList(const SLinkedList<E>& obj) :head(NULL), tail(NULL) {
	*this = obj;
}

template<class E>
SLinkedList<E>& SLinkedList<E>::operator=(SLinkedList<E> &rhs) {
	if (this != rhs) {
		while (head != null)
			removeFront();

		for (SNode<E>* curr = rhs.head; curr != NULL; curr->getNext()) {
			addBack(curr->getElem());
		}
	}
	return *this;
}
