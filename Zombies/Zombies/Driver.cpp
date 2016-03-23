#include "Organism.h"
#include "World.h"
#include "GameSpecs.h"
#include "Human.h"
#include "Zombie.h"
#include <random>
#include "time.h"
#include "windows.h"
#include <conio.h>
void createGame();
void isStarve();

World gameWorld;

int main()
{
	int timeS = 0;

	createGame();
	gameWorld.Display();

	for (int i = 0; i < ITERATIONS; i++)
	{
		timeS++;
		//Sleep(10);

		gameWorld.MoveStep(timeS);

		isStarve();

		

		system("CLS");
		gameWorld.Display();
		if (gameWorld.getELE())
		{
			i = i + 1;
			cout << "EXTINCTION LEVEL EVENT!" << endl;
			break;
		}
		cout << timeS << endl;
	}

	cout << "Apocalypse has ended after " << timeS << " turns." << endl;
	_getch();

}

void createGame()
{
	for (int i = 0; i < ZOMBIE_STARTCOUNT; i++)
	{
		bool done = false;

		while (!done) {

			int rX = (rand() % 19);
			int rY = (rand() % 19);

			if (gameWorld.getAt(rX, rY) == NULL)
			{
				Organism *z = new Zombie(&gameWorld, rX, rY);
				gameWorld.setAt(rX, rY, z);
				done = true;
			}

		}
	}

	for (int i = 0; i < HUMAN_STARTCOUNT; i++)
	{
		bool finished = false;

		while (!finished) {

			int ranX = (rand() % 19);
			int ranY = (rand() % 19);

			if (gameWorld.getAt(ranX, ranY) == NULL)
			{
				Human *hum = new Human(&gameWorld, ranX, ranY);
				gameWorld.setAt(ranX, ranY, hum);
				finished = true;
			}

		}
	}
}

void isStarve()
{
	for (int i = 0; i < GRIDSIZE; i++)
	{
		for (int i2 = 0; i2 < GRIDSIZE; i2++)
		{
			if (!(gameWorld.getAt(i, i2) == NULL))
			{
				Organism *org = gameWorld.getAt(i, i2);
				if (org->getType() == 1)
				{
					if (org->starve())
					{
						gameWorld.setAt(i, i2, NULL);
						Human *hum = new Human(&gameWorld, i, i2);
						gameWorld.setAt(i, i2, hum);
					}
				}

			}

		}
	}
}