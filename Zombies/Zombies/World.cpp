#include "World.h"
#include "Organism.h"
#include "windows.h"
World::World()
{

}
World::~World()
{

}
	//method to return organism at given coords
	Organism* World::getAt(int x, int y)
	{
		return grid[x][y];
	}

	//set given organism at given coords
	void World::setAt(int x, int y, Organism *org)
	{
		grid[x][y] = org;
	}

	//move orgs, and then breed if allowed
	void World::MoveStep(int time)
	{
		for (int i = 0; i < GRIDSIZE; i++)
		{
			for (int j = 0; j < GRIDSIZE; j++)
			{
				if (!getAt(i, j) == NULL)
				{
					Organism *org = getAt(i, j);
					if (org->getMoved() == false)
					{
						org->move();
					}
				}
			}
		}

		for (int i = 0; i < GRIDSIZE; i++)
		{
			for (int j = 0; j < GRIDSIZE; j++)
			{
				if (!getAt(i, j) == NULL)
				{
					Organism *org = getAt(i, j);
					org->setMoved(false);
					if (org->getType() == 1)
					{
						org->breed();
					}
				}
			}
		}

		for (int i = 0; i < GRIDSIZE; i++)
		{
			for (int j = 0; j < GRIDSIZE; j++)
			{
				if (!getAt(i, j) == NULL)
				{
					Organism *org = getAt(i, j);
					org->setMoved(false);
					if(org->getType()==2)
					{
						org->breed();
					}
				}
			}
		}
	}
	//display world and apply set text colors
	void World::Display()
	{
		for (int i = 0; i < GRIDSIZE; i++)
		{
			for (int j = 0; j < GRIDSIZE; j++)
			{
				if (grid[i][j] != NULL && grid[i][j]->getType() == 1)
				{
					setTextColor(ZOMBIE_COLOR);
					cout << grid[i][j];
				}
				else if (grid[i][j] != NULL && grid[i][j]->getType() == 2)
				{
					setTextColor(HUMAN_COLOR);
					cout << grid[i][j];
				}
				else
					cout << grid[i][j];
			} 
			cout << endl;
		}
	}
	//set the text colors
	void World::setTextColor(int color)
	{		
		HANDLE console;
		console = GetStdHandle(STD_OUTPUT_HANDLE);
		SetConsoleTextAttribute(console, color);
	}

	//not implemented/required
	void World::setELE(bool ele)
	{

	}
	//return whether extinction has occurred
	bool World::getELE()
	{
		int hs = 0;
		int zs = 0;

		for (int i = 0; i < GRIDSIZE; i++)
		{
			for (int ij = 0; ij < GRIDSIZE; ij++)
			{
				if (getAt(i, ij) == NULL)
				{
				}
				else if (getAt(i, ij)->getType() == 1)
				{
					zs++;
				}
				else if (getAt(i, ij)->getType() == 2)
				{
					hs++;
				}
				
			}
		}

		if ((hs == 0) || (zs == 0))
		{
			return true;
		}
		return false;
	}
	//world grid
	Organism* grid[GRIDSIZE][GRIDSIZE];
	bool ele;
