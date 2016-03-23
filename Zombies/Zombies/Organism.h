#ifndef _Organism_H
#define _Organism_H

#include <iostream>
#include "World.h"
using namespace std;

class Organism{

	protected:
                           
    bool moved;                    
    int breedCounter;                 
    World *world;

	public:
		Organism();
		Organism(World *world, int x, int y);
		~Organism();
		virtual int getX();
		virtual int getY();
		virtual void breed() = 0;       
		virtual void move();        
   		virtual bool validMove(int xPos, int yPos);  
   		virtual int getType() = 0;  
		virtual bool starve() = 0; 
		friend ostream& operator<<( ostream &output, Organism *Organism );
		virtual bool getMoved();
		virtual void setMoved(bool moved);
		int x,y; 
		char c;
		Organism* checker;
		
};


#endif
