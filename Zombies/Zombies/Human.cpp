#include "Human.h"


Human::Human()
{

}
//human constructor, setting the world, coords, and character
Human::Human(World *world, int x, int y) 		
{
	this->world = world;
	this->x = x;
	this->y = y;
	this->c = HUMAN_CH;
}
	int recC = 0; //recruitment time

	//human recruitment method
	void Human::breed()
	{
		if (recC == HUMAN_BREED) //if recruitment time reaches recruitment threshold
		{
			bool done = false;
			int atmp = 0;
			while (!done)
			{
				int rX = 1 - (rand() % 3);
				int rY = 1 - (rand() % 3);

				if ((world->getAt(x + rX, y + rY) == NULL) && ((x + rX) < 20) && ((x + rX) > -1) && ((y + rY) < 20) && ((y + rY) > -1))
				{
					Organism *h = new Human(world, (x + rX), (y + rY));
					world->setAt((x + rX), (y + rY), h);
					done = true;
				}
				else if (atmp == 9)
				{
					done = true;
				}
				atmp++;
				recC = 0;
			}
		}
		else
		{
			recC++;
		}
	}
	//get type
	int Human::getType()
	{
		return 2;
	}
	//get x coords
	int Human::getX()
	{
		return x;
	}
	//get y coords
	int Human::getY()
	{
		return y;
	}
	//starve, returns false, humans can't starve
	bool Human::starve()
	{
		return false;
	}

	//human move method
	void Human::move()
	{
		//array holding possible moves
		Organism* checker[4] = { world->getAt(x + 1, y),
			world->getAt(x, y + 1),
			world->getAt(x - 1, y),
			world->getAt(x, y - 1)
		};

		//randomly selects a 
		//move from the array
		for (Organism *org : checker)
		{
			if (!org == NULL)
			{
				bool done = false;
				while (!done)
				{
					int rX = (1 - (rand() % 3));
					int rY = (1 - (rand() % 3));
					if (((x + rX) < 20) && ((x + rX) > -1) && ((y + rY) < 20) && ((y + rY) > -1))
					{
						if ((world->getAt(x, y + rY) == NULL))
						{
							world->setAt(x, y + rY, NULL);
							world->setAt(x, y + rY, this);
							world->setAt(x, y, NULL); 
							this->y = y + rY;
							this->setMoved(true);

							done = true;
						}
						else if ((world->getAt(x + rX, y) == NULL))
						{
							world->setAt(x + rX, y, NULL);
							world->setAt(x + rX, y, this);
							world->setAt(x, y, NULL); 
							this->x = x + rX;
							this->setMoved(true);

							done = true;
						}
						else
						{ 
							done = true; 
						}
					}
				}
			}
		}
		this->setMoved(true);
	}
	
