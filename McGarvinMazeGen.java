/**
 * McGarvinMazeGen is a program that recursively generates a
 * random maze each time the program is executed.
 * 
 * Completion time: 8.5 hours
 *
 * @author Bradley McGarvin, Acuna, used GeeksforGeeks to
 * read up on 2d array as I have been using ArrayList
 * more frequently as of late.
 * @version 3/15/2019
 */
import java.util.Random;

public class McGarvinMazeGen {

   // standard console size in characters.
   private static final int  LEVEL_HEIGHT = 25;

   private static final int  LEVEL_WIDTH  = 80;

   private static final char ICON_WALL    = '#';

   private static final char ICON_BLANK   = ' ';

   /**
    * Returns a 2D array containing a statically created
    * maze with dimentions 80x24.
    * 
    * @return 2D array containing a maze
    */
   private static char[][] makeMazeStatic() {
      // the following maze was generated with the recursive
      // division method and then modified by hand.

      char level[][] = {
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#',
                  ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  '#', '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#',
                  ' ', '#', '#', ' ', '#', '#', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', '#', ' ',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#',
                  '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', '#',
                  '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#',
                  '#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#',
                  ' ', '#', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#',
                  ' ', '#', ' ', ' ', '#', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', '#', ' ',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', ' ', '#', '#', '#', ' ', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#',
                  ' ', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', '#', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ',
                  '#', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ',
                  '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', '#', '#',
                  '#', '#', ' ', '#', '#', '#', ' ', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#',
                  '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#',
                  ' ', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ',
                  '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', '#', '#', ' ', '#',
                  '#', '#', ' ', '#', ' ', '#', '#', ' ', '#', '#', '#', ' ',
                  '#', ' ', '#', ' ', '#', '#', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#',
                  ' ', ' ', ' ', '#', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ',
                  '#', ' ', ' ', ' ', ' ', '#', '#', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', ' ', '#', '#', '#', '#', ' ', '#', '#', '#', '#',
                  ' ', '#', '#', '#', ' ', '#', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', '#', '#', '#', '#',
                  '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', '#', ' ', '#', ' ', ' ', '#', ' ', '#', '#', ' ', '#',
                  '#', ' ', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', '#', '#', '#',
                  '#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', ' ', '#', '#', '#', '#' },
            { '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', '#', ' ', ' ', '#', ' ', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#',
                  '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ',
                  '#', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  '#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
                  ' ', ' ', ' ', ' ', ' ', ' ', '#' },
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
                  '#', '#', '#', '#', '#', '#', '#' } };

      return level;
   }

   /**
    * Creates a random maze in a 2D array.
    * 
    * @see <a href=
    *      "http://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_division_method">Recursive_division_method</a>
    * @return 2D array containing a maze
    */
   private static char[][] makeMaze() {
      char level[][] = createBlankLevel();

      makeMazeRecursive( level, 1, 1, LEVEL_WIDTH - 2, LEVEL_HEIGHT - 2 ); 

      return level;
   }

   /**
    * Creates an empty level of standard level height and
    * width. Level will be
    * blank but bordered with wall characters.
    * 
    * @return 2D array containing a maze
    */
   private static char[][] createBlankLevel() {
      char level[][] = new char[LEVEL_HEIGHT][LEVEL_WIDTH];

      // reset level to be entirely blank
      for ( int y = 0; y < LEVEL_HEIGHT; y++ )
         for ( int x = 0; x < LEVEL_WIDTH; x++ )
            level[y][x] = ' ';

      // top barrier
      for ( int x = 0; x < LEVEL_WIDTH; x++ )
         level[0][x] = ICON_WALL;

      // bottom barrier
      for ( int x = 0; x < LEVEL_WIDTH; x++ )
         level[LEVEL_HEIGHT - 1][x] = ICON_WALL;

      // left barrier
      for ( int y = 0; y < LEVEL_HEIGHT; y++ )
         level[y][0] = ICON_WALL;

      // left barrier
      for ( int y = 0; y < LEVEL_HEIGHT; y++ )
         level[y][LEVEL_WIDTH - 1] = ICON_WALL;

      return level;
   }

   /**
    * Method randBetween is used to select a random
    * number between the lower and upper(inclusive)
    * integer limits provided.
    *
    * @return int
    */

   private static int randBetween(int lower , int upper) {

// Create a Random class object
      Random randomNumber = new Random();

// result = random number between lower limit and upper
// limit inclusive
      int result = randomNumber.nextInt( (upper - lower) + 1 ) + lower;

      return result;

   }

   /**
    * Recursively creates a maze using recursive division by
    * randomly drawing horizontal and vertical lines, creating 4
    * subchambers. Each subchamber will be recursively called again,
    * ensuring at least 3 holes are made in different walls, until each
    * sub-chamber area is less than 3x3.
    *
    */
   private static void makeMazeRecursive(char[][] level , int startX ,
         int startY , int endX , int endY) {

      int X_LENGTH = endX - startX;
      int Y_LENGTH = endY - startY;
      int horizontalWall;
      int verticalWall;

      // base case to check 3x3
      if ( X_LENGTH >= 3 && Y_LENGTH >= 3 ) {

// create random walls between inner limits
         verticalWall = randBetween( (startX + 1), (endX - 1) );

         horizontalWall = randBetween( (startY + 1), (endY - 1) );

// For loop in order to create horizontal wall
         for ( int h = startX; h <= endX; h++ ) {

            level[horizontalWall][h] = ICON_WALL;

         }

// For loop to create vertical wall
         for ( int v = startY; v <= endY; v++ ) {

            level[v][verticalWall] = ICON_WALL;

         }

/*
 * Assigns random numbers between inner limits of random
 * walls that were created earlier in order to determine where
 * to create holes.
 */

         int horizontalWallLowerHalf = randBetween( startX, verticalWall - 1 );

         int horizontalWallUpperHalf = randBetween( verticalWall + 1, endX );

         int verticalWallLowerHalf = randBetween( startY, horizontalWall - 1 );

// select walls and add holes
         level[horizontalWall][verticalWall] = ICON_WALL;

         level[horizontalWall][horizontalWallLowerHalf] = ICON_BLANK;

         level[horizontalWall][horizontalWallUpperHalf] = ICON_BLANK;

         level[verticalWallLowerHalf][verticalWall] = ICON_BLANK;

// recursive call on upper left sub-chamber subtracting 1
// from vertical and horizontal walls to prevent double walls.
         makeMazeRecursive( level, startX, startY, verticalWall - 1,
               horizontalWall - 1 );

// recursive call on upper right sub-chamber adding 1 to
// verticalWall and subtracting 1 from horizontalWall to 
// prevent double walls.
         makeMazeRecursive( level, verticalWall + 1, startY, endX,
               horizontalWall - 1 );

// recursive call on lower left sub-chamber adding 1 to
// horizontalWall and subtracting 1 from vertical wall to prevent double walls.
         makeMazeRecursive( level, startX, horizontalWall + 1, verticalWall - 1,
               endY );

// recursive call on lower right sub-chamber subtracting 1
// from verticalWall and adding 1 to horizontalWall to prevent double walls.
         makeMazeRecursive( level, verticalWall - 1, horizontalWall + 1, endX,
               endY );

      }

   }

   /**
    * Displays a level in the console.
    * 
    * @param level
    *           2D array containing a maze
    */
   private static void drawLevel(char[][] level) {
      int y, x;

      for ( y = 0; y < LEVEL_HEIGHT; y++ ) {
         for ( x = 0; x < LEVEL_WIDTH; x++ )
            System.out.print( level[y][x] );
         System.out.println();
      }
   }

   /**
    * Entry point.
    * 
    * @param args
    *           command line arguments
    */
   public static void main(String[] args) {
      // show static maze (uncomment for sample output)
      // drawLevel(makeMazeStatic());
      // show recursive maze
      drawLevel( makeMaze() );
   }
}
