import java.util.Scanner;
import java.util.Random;

/*
Author: Kevin Hinson

Description: Conway's game of life program.

Compile: javac conway.java
Execute: java conway

*/


class conway{
	public static void main(String args[]){
		
		Scanner userin = new Scanner(System.in);//for user input
		Random rand = new Random();
		//vars
		int numgen = 0;//number of generations
		int matrixSize = 0;//Size of the matrix, the matrix will be square so the col and row will be equal
		int rando = rand.nextInt(20) + 16;//this will be a random number to decide how many cells will live initially
		int randcol = 0;//for getting a random column number later
		int randrow = 0;//for getting a random row number later
		int countNeighbors = 0;//For counting the number of neighbors
		
		
		System.out.println("Enter size of the square matrix:");
			matrixSize = userin.nextInt();
		System.out.println("Enter number of generations");
			numgen = userin.nextInt();
		
		//matrix declaration
		int [][] cells = new int[matrixSize][matrixSize]; 
		int [][] nextgen = new int[matrixSize][matrixSize];//this records which cells live and die and which will be applied to cells
		
		//initialize cells. Java may already intialize these to 0 so consider deleting
		/*for(int i = 0; i < matrixSize; i++)
		{
			for(int j = 0; j < matrixSize; j++)
			{
				cells[i][j] = 0;
				nextgen[i][j] = 0;
			}
		}*/
		
		//randomly set some cells to live
		for(int i = 0; i < rando; i++)//note that this will set already live cells to alive(should be fixed)
		{
			randcol = rand.nextInt(matrixSize-1) + 0;//gets a random column number
			randrow = rand.nextInt(matrixSize-1) + 0;//gets a random row number
				
				cells[randrow][randcol] = 1;
				nextgen[randrow][randcol] = 1;//remember nextgen is a copy of cells used for deciding which cells live and die & apply those changes to cells
		}
		
		//this sets up a spinner
		/*cells[4][4] = 1;
		cells[4][3] = 1;
		cells[4][2] = 1;
		
		nextgen[4][4] = 1;
		nextgen[4][3] = 1;
		nextgen[4][2] = 1;*/
		
		for(int iter = 0; iter < numgen; iter++)//for how many generations there are apply the games rules
		{
		
				for(int i = 0; i < matrixSize; i++)//for each row
				{
					for(int j = 0; j < matrixSize; j++)//for each column
					{
					
						//counting neighbors on the top row
						for(int t = 0; t < 3; t++)
						{
							if((toperr(i,j,t,matrixSize)) && (cells[i-1][j-1+t] == 1))//checking cells on the top row
								countNeighbors++;
						}
						
						//counting neighbors on the bottom
						for(int b = 0; b < 3; b++)
						{
							if((bottomerr(i,j,b,matrixSize)) && (cells[i+1][j-1+b] == 1))
								countNeighbors++;
						}
						
						//counting neighbors to the left and right
						if((j-1 >= 0) && (cells[i][j-1] == 1))//cell on the left
						{
							countNeighbors++;
						}
						
						if((j+1 <= matrixSize-1) && (cells[i][j+1] == 1))//cell on the right
						{
							countNeighbors++;
						}
						
						//death conditions are checked here
						if((countNeighbors > 3) || (countNeighbors < 2))//For cells with more than 3 neighbors or less than 2 neighbors, death
						{
							nextgen[i][j] = 0;
						}	
						//Set to live cell conditions here
						if(countNeighbors == 3)//for dead cells with exactly 3 neighbors they become alive
						{
							nextgen[i][j] = 1;
						}
							
						countNeighbors = 0;//re-initialize neighbors counted for next cell
					}
				}
				
				//Applies all changes at once here and prints them
				System.out.println("===============================================================================================================");
				
				for(int i = 0; i < matrixSize; i++)
					for(int j = 0; j < matrixSize; j++)
					{
						if(cells[i][j] == 1)
						{
						 System.out.print("*");
						}
						else{
							System.out.print(" ");
						}
						
						if(j == matrixSize-1)
							System.out.println("");//Starts new line when we reach the end of a row
						
						cells[i][j] = nextgen[i][j];//changes are applied on this line, the next generation is applied here 
						nextgen[i][j] = cells[i][j];//nextgen becomes a copy of cells and we apply the rules on nextgen
					}
				System.out.println("===============================================================================================================");
		}
		
	}
	
//-------------------------------------------------------METHODS-----------------------------------------------------------------------------------------------------------

	//checking for error bounds on the cell whose neighbors are being counted on the top row
	public static boolean toperr(int r, int c, int s, int matrixSize)//r row, c column, s for checking each cell
	{
		if((r-1>=0)&&(c-1+s>=0)&&(c-1+s<=matrixSize-1))
		{
			return true;
		}	
	
		return false;
	}
	
	//checking for error bounds on the cell whose neighbors are being counted on the  bottom row
	public static boolean bottomerr(int r, int c, int s, int matrixSize)
	{
		if((r+1<=(matrixSize-1))&&(c-1+s>=0)&&(c-1+s<=matrixSize-1))
		{
			return true;
		}	
	
		return false;
	}
}
	/*
RULES:
1. Any live cell with less than 2 neighbors dies
2. Any live cell with 2 or 3 live neighbors lives
3. Any live cell with more than 3 live neighbors dies
4. Any dead cell with 3 live neighbors becomes alive

	Format:Cell(c) neighbors(n)
	
	nnn
	ncn
	nnn
*/
