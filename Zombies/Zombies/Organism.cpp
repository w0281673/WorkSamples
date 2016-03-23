#include "Organism.h"

char c;
Organism::Organism()
{

}

Organism::Organism(World *world, int x, int y)
{

}

Organism::~Organism()
{

}

void Organism::breed(){}

void Organism::move(){}

bool Organism::validMove(int xPos, int yPos)
{
	if ((xPos < 20) && (yPos < 20))
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool Organism::getMoved()
{
	return this->moved;
}

void Organism::setMoved(bool moved)
{
	this->moved = moved;
}
int Organism::getX()
{
	return x;
}
int Organism::getY()
{
	return y;
}

ostream& operator<<(ostream &output, Organism *organism)
{
	if (organism == NULL)
	{
		output << " ";
	}
	else
		output << organism->c;
	return output;
};