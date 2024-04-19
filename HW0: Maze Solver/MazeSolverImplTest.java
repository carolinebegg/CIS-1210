import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /****************************** MY TESTS *******************************/

    /*
      A null array (edge case)
      An empty array (edge case)
        A size-one array (base case)
        A size-two array with the maximum at index 0
        A size-two array with the maximum at index 1
        A size-two array with a tie for the maximum (edge case)
        A size-three array with the maximum at index 0
        A size-three array with the maximum at index 2
        A size-three array with a tie for the maximum (edge case)
        A size-four array
     */
    /**
     * IllegalArgumentException Tests
     *
     * These tests check to ensure that the mazeSolver() throws an IllegalArgumentException
     * for all cases specified in the writeup. These cases are the following:
     *      (1) The maze is null
     *      (2) The source or goal coordinates are out of bounds
     *      (3) The source or goal coordinates are on a black tile
     *      (4) m * n <= 1 (the maze dimensions are 1x1)
     */

    /***** Null Array and 1x1 Array Tests *****/
    @Test(expected = IllegalArgumentException.class)
    public void testNullArray() {
        MazeSolverImpl.solveMaze(null, new Coordinate(0, 0), new Coordinate(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test1By1Array() {
        int[][] maze = new int[1][1];
        MazeSolverImpl.solveMaze(maze, new Coordinate(0, 0), new Coordinate(0, 0));
    }

    /***** Out of Bounds Source and Goal Coordinates Tests *****/
    @Test(expected = IllegalArgumentException.class)
    public void testSourceXOutOfBoundsLow() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(-1, 0), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceXOutOfBoundsHigh() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(4, 0), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceYOutOfBoundsLow() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, -1), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSourceYOutOfBoundsHigh() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, 4), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalXOutOfBoundsLow() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, 0), new Coordinate(-1, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalXOutOfBoundsHigh() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, 0), new Coordinate(4, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalYOutOfBoundsLow() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, 0), new Coordinate(3, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGoalYOutOfBoundsHigh() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(2, 0), new Coordinate(3, 4));
    }

    /***** Source and Goal Coordinates on Blocked Tile Tests *****/
    @Test(expected = IllegalArgumentException.class)
    public void testSourceCoordOnBlockedTile() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(0, 1), new Coordinate(3, 0));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGoalCoordOnBlockedTile() {
        MazeSolverImpl.solveMaze(smallWriteupMaze, new Coordinate(0, 0), new Coordinate(2, 3));
    }

    /**
     * Individual Helper Function Tests
     *
     * These tests include checking the downCheck(), upCheck(), leftCheck(), and rightCheck()
     * functions to verify that they behave properly on open tiles, borders, and  black tiles
     *
     */

    /** downCheck() Tests **/
    @Test
    public void testDownCheckOnOpenTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertTrue(MazeSolverImpl.downCheck(maze, new Coordinate(1,1)));
    }
    @Test
    public void testDownCheckOnBottom() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.downCheck(maze, new Coordinate(1,2)));
    }
    @Test
    public void testDownCheckOnBlackTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 1, 0},

        };
        assertFalse(MazeSolverImpl.downCheck(maze, new Coordinate(1,1)));
    }

    /** upCheck() Tests **/
    @Test
    public void testUpCheckOnOpenTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertTrue(MazeSolverImpl.upCheck(maze, new Coordinate(1,1)));
    }
    @Test
    public void testUpCheckOnTop() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.upCheck(maze, new Coordinate(1,0)));
    }
    @Test
    public void testUpCheckOnBlackTile() {
        int[][] maze = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.upCheck(maze, new Coordinate(1,1)));
    }

    /** leftCheck() Tests **/
    @Test
    public void testLeftCheckOnOpenTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertTrue(MazeSolverImpl.leftCheck(maze, new Coordinate(1,1)));
    }
    @Test
    public void testLeftCheckOnWall() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.leftCheck(maze, new Coordinate(0,1)));
    }
    @Test
    public void testLeftCheckOnBlackTile() {
        int[][] maze = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.leftCheck(maze, new Coordinate(1,1)));
    }

    /** rightCheck() Tests **/
    @Test
    public void testRightCheckOnOpenTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertTrue(MazeSolverImpl.rightCheck(maze, new Coordinate(1,1)));
    }
    @Test
    public void testRightCheckOnWall() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.rightCheck(maze, new Coordinate(2,1)));
    }
    @Test
    public void testRightCheckOnBlackTile() {
        int[][] maze = {
                {0, 0, 0},
                {0, 0, 1},
                {0, 0, 0},

        };
        assertFalse(MazeSolverImpl.rightCheck(maze, new Coordinate(1,1)));
    }

    @Test
    public void testSameSourceAndGoal() {
        int[][] maze = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        int[][] solution = {
                {0, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze,
                new Coordinate(3, 0), new Coordinate(3, 0)));
    }

    /**
     * Testing Various Maze Solutions
     *
     * Simple Maze Movement Tests: 1x4 (move down and move up), 4x1 (move left and move right)
     *
     * Shapes Tested: 1x2, 2x1, 2x3, 3x2, 3x4, 4x3, 4x5, 5x4
     */

    /***** Simple Maze Movement Tests *****/
    @Test
    public void testMoveDownSimple() {
        int[][] simple = {
                {0},
                {0},
                {0},
                {0}
        };
        int[][] solution = {
                {1},
                {1},
                {1},
                {1}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(0, 3)));
    }

    @Test
    public void testMoveUpSimple() {
        int[][] simple = {
                {0},
                {0},
                {0},
                {0}
        };
        int[][] solution = {
                {1},
                {1},
                {1},
                {1}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 3), new Coordinate(0, 0)));
    }

    @Test
    public void testMoveLeftSimple() {
        int[][] simple = {
                {0, 0, 0, 0},
        };
        int[][] solution = {
                {1, 1, 1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(3, 0), new Coordinate(0, 0)));
    }

    @Test
    public void testMoveRightSimple() {
        int[][] simple = {
                {0, 0, 0, 0},
        };
        int[][] solution = {
                {1, 1, 1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(3, 0)));
    }

    /***** Maze Tests *****/
    @Test
    public void test1x2Maze() {
        int[][] simple = {
                {0},
                {0},
        };
        int[][] solution = {
                {1},
                {1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(0, 1)));
    }
    @Test
    public void test2x1Maze() {
        int[][] simple = {
                {0, 0},
        };
        int[][] solution = {
                {1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(1, 0)));
    }
    @Test
    public void test2x3Maze() {
        int[][] simple = {
                {0, 0, 0},
                {0, 0, 0},
        };
        int[][] solution = {
                {1, 1, 1},
                {1, 1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(2, 1)));
    }
    @Test
    public void test3x2Maze() {
        int[][] simple = {
                {0, 0},
                {0, 0},
                {0, 0},
        };
        int[][] solution = {
                {1, 0},
                {1, 1},
                {1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(1, 1)));
    }

    @Test
    public void test3x4Maze() {
        int[][] simple = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] solution = {
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(2, 0), new Coordinate(2, 2)));
    }
    @Test
    public void test4x3Maze() {
        int[][] simple = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
        };
        int[][] solution = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(simple,
                new Coordinate(0, 0), new Coordinate(3, 2)));
    }

    /***** 10x10 Maze with Edge Cases *****/
    @Test
    public void test10x10GoBack1() {
        int[][] maze = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
        };
        int[][] solution = {
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 1, 1},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze,
                new Coordinate(0, 0), new Coordinate(9, 9)));
    }
    @Test
    public void testBlockedIn3x3SameSourceGoal() {
        int[][] maze = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1},
        };
        int[][] solution = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
        };
        assertArrayEquals(solution, MazeSolverImpl.solveMaze(maze,
                new Coordinate(1, 1), new Coordinate(1, 1)));
    }
    @Test
    public void testBlockedInNoSolution() {
        int[][] maze = {
                {1, 1, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
        };
        assertArrayEquals(null, MazeSolverImpl.solveMaze(maze,
                new Coordinate(1, 1), new Coordinate(3, 3)));
    }
    @Test
    public void testFilledColumnNoSolution() {
        int[][] maze = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0},
        };
        assertArrayEquals(null, MazeSolverImpl.solveMaze(maze,
                new Coordinate(0, 0), new Coordinate(2, 0)));
    }
    @Test
    public void testFilledRowNoSolution() {
        int[][] maze = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
        };
        assertArrayEquals(null, MazeSolverImpl.solveMaze(maze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }
}
