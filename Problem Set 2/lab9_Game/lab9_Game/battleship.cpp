#include "battleship.h"
#include <ctime>
#include <iostream>

using namespace std;

Location::Location()
{
	x = -1;
	y = '*';
}

void Location::pick()
{
	int randomNum = -1;
	while (randomNum < 1 || randomNum>  fieldSize) {
		randomNum = rand() % 10;
	}
	x = randomNum;
	randomNum = -1;
	while (randomNum < 0 || randomNum >  fieldSize - 1) {
		randomNum = rand() % 10;
	}
	y = 'a' + randomNum;
}

void Location::fire()
{
	Location tmp;
	cout << "\nWhere would you like to fire a shot?\nY-Coordinate <a-e>: ";
	cin >> y;
	cout << "X-Coordinate <1-5>: ";
	cin >> x;
}

void Location::print() const
{
	cout << y << x << " ";
}

bool compare(const Location & loc1, const Location & loc2)
{
	if (loc1.y == loc2.y && loc1.x == loc2.x) {
		return true;
	}
	else
	{
		return false;
	}
}

Ship::Ship()
{
	sunk = false;
}

bool Ship::match(const Location & l) const
{
	if (compare(loc, l)) {
		return true;
	}
	else {
		return false;
	}
}

void Ship::sink()
{
	sunk = true;
}

void Ship::setLocation(const Location & l)
{
	loc.x = l.x;
	loc.y = l.y;
}

void Ship::printShip() const
{
	loc.print();
	if (sunk == true) {
		cout << "sunk" ;
	}
	else {
		cout << "up" ;
	}
}

void Fleet::deployFleet()
{
	int i = 0;
	Location randloc;
	while (i < fleetSize) {
		randloc.pick();
		int e = check(randloc);
		if (e == -1) {
			ships[i].setLocation(randloc);
			i++;
		}

	}
}

bool Fleet::operational() const
{
	for (int i = 0; i < fleetSize; i++)
		if (!ships[i].isSunk())
			return true;
	return false;
}

bool Fleet::isHitNSink(const Location & loc)
{
	int e = check(loc);
	if (e > -1) {
		ships[e].sink();
		return true;
	}
	return false;
}

void Fleet::printFleet() const
{
	for (int i = 0; i < fleetSize; i++) {
		ships[i].printShip();
		cout << " , ";
	}
}

int Fleet::check(const Location & loc) const
{
	for (int i = 0; i < fleetSize; i++) {
		if (ships[i].match(loc))
			return i;
	}
	return -1;
}