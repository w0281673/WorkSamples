#ifndef _Human_H
#define _Human_H

#include "Organism.h"

class Human : public Organism
{
	public:
		Human();
		Human(World *world, int x, int y);
		virtual void breed();
		virtual int getType();  
		virtual bool starve();
		virtual void move();
		virtual int getX();
		virtual int getY();
		int recC;
};

#endif
