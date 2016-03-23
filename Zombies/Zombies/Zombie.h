#ifndef _Zombie_H
#define _Zombie_H

#include "Organism.h"

class Zombie : public Organism
{
	public:
		Zombie();
		Zombie(World *world, int x, int y);
		virtual void breed();       
		virtual void move();
		virtual int getType();       
		virtual bool starve();
		virtual int getX();
		virtual int getY();
		int timeC;
		
	private:
		int starvation;
}; 

#endif
