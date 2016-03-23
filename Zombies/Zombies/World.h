#ifndef _WORLD_H
#define _WORLD_H

#include <iostream>
#include "GameSpecs.h"

using namespace std;

class World
{

friend class Organism;

public:
    World();
    ~World();
    Organism* getAt(int x, int y);
    void setAt(int x, int y, Organism *org);
    void Display();
    void MoveStep(int t);
	void setTextColor(int color);
	void setELE(bool ele);
	bool getELE();
    
private:
    Organism* grid[GRIDSIZE][GRIDSIZE];
	bool ele;
};

#endif