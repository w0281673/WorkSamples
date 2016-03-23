#include "Zombie.h"
#include <vector>
#include <random>

Zombie::Zombie()
{}

//Zombie constructor assigning it a world, coordinates, and a character
Zombie::Zombie(World *world, int x, int y) : Organism(world, x, y) 
{
	this->world = world;
	this->x = x;
	this->y = y;
	this->c = ZOMBIE_CH;
}

	int timeC = 0;


	int starvation;
	
	//vector storing possible attacks 
	vector<Organism*> posAtks;

	//breeding method
	void Zombie::breed()
	{
		timeC++; //increment convert time
		if (timeC >= ZOMBIE_BREED) //if the convert time is greater than breeding threshold
		{
			bool done = false;
			while (!done)
			{
				
				int rX = 1 - (rand() % 3);
				int rY = 1 - (rand() % 3);

				if (!(world->getAt((x + rX), (y + rY)) == NULL))
				{
					if ((world->getAt((x + rX), (y + rY))->getType() == 2) && ((x + rX) < 20) && ((x + rY) > -1) && ((y + rY) < 20) && ((y + rY) > -1))
					{
						Organism *z = new Zombie(world, (x + rX), (y + rY));
						world->setAt((x + rX), (y + rY), NULL);
						world->setAt((x + rX), (y + rY), z);
						timeC = 0;
						done = true;
					}
				}
				done = true;
			}
		}
	}
	//move method
	void Zombie::move()
	{
		//array storing each spot adjacent to the zombie
		//any spaces which aren't null are assigned to the possible
		//attacks vector
		Organism* checker[8] = { world->getAt(x + 1, y), world->getAt(x + 1, y + 1),
			world->getAt(x, y + 1), world->getAt(x - 1, y + 1),
			world->getAt(x - 1, y), world->getAt(x - 1, y - 1),
			world->getAt(x, y - 1), world->getAt(x + 1, y - 1)
		};
		for (Organism* org : checker)
		{
			if (!org == NULL)
			{
				posAtks.push_back(org);
			}
		}
		bool done = false;
		
		//picks a human from the vector to eat
		while (!done)
		{
			int rX, rY, rando;
			
			if (!posAtks.empty())
			{
				rando = 1 + (rand() % (posAtks.size()) - 1);
			
				if (world->getAt(posAtks.at(rando)->getX(), posAtks.at(rando)->getY())->getType() == 2)
				{
					world->setAt(posAtks.at(rando)->getX(), posAtks.at(rando)->getY(), NULL);
					world->setAt(posAtks.at(rando)->getX(), posAtks.at(rando)->getY(), this);
					world->setAt(x, y, NULL);
					this->x = posAtks.at(rando)->getX();
					this->y = posAtks.at(rando)->getY();
					this->setMoved(true);
					starvation = 0;
					posAtks.clear();
					done = true;
				}
				else
				{
					starvation++;
					posAtks.clear();
					break;
				}
			}
			//if no humans in the vector, move normally
			else
			{
				done = false;
				while (!done)
				{
					int rX = 1 - (rand() % 3);
					int rY = 1 - (rand() % 3);
					if ((world->getAt(x + rX, y + rY) == NULL) && ((x + rX) < 20) && ((x + rX) > -1) && ((y + rY) < 20) && ((y + rY) > -1))
					{
						world->setAt(x + rX, y + rY, this);
						world->setAt(x, y, NULL);
						this->x = x + rX;
						this->y = y + rY;
						this->setMoved(true);
						starvation++;
						done = true;
					}
				}
			}
		}
		
	}
	//get the type
	int Zombie::getType()
	{
		return 1;
	}
	//bool to check for starvation
	bool Zombie::starve()
	{
		if (starvation == ZOMBIE_STARVE)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//get x coordinate
	int Zombie::getX()
	{
		return x;
	}
	//get y  coordinate
	int Zombie::getY()
	{
		return y;
	}
	
