import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class MazeSolverTest {
    Coordinate src;
    Coordinate tgt;
    int[][] allZeroMaze;
    int[][] middleSquareMaze;
    int[][] onlyOnePath;
    int[][] bigWriteupMaze;
    int[][] bigWriteupMaze25;
    int[][] sixteenBySixteen;
    int[][] twoByTwo;
    int[][] threeByThree;
    int[][] tenByTen;
    int[][] twelveByTwelve;
    int[][] longRectangleMaze;
    int[][] tallRectangleMaze;
    int[][] unsolvableSquare;
    int[][] unsolvableRectangle;

    @Before
    public void setupTestMazes() {
        twoByTwo = new int[][]{
                {0, 0},
                {0, 0},
        };
        threeByThree = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };
        allZeroMaze = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        middleSquareMaze = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        onlyOnePath = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        longRectangleMaze = new int[][]{
                {1, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
        };
        tallRectangleMaze = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0},
                {0, 0, 0},

        };
        unsolvableSquare = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
        };
        unsolvableRectangle = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0},
                {0, 0, 0},
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        tenByTen = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        twelveByTwelve = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        sixteenBySixteen = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        bigWriteupMaze25 = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

    }
    /** getIndex Tests **/
    @Test
    public void testGetIndex2x2ZeroZero() {
        src = new Coordinate(0,0);
        assertEquals(0,MazeSolver.getIndex(src, 2));
    }
    @Test
    public void testGetIndex2x2OneZero() {
        src = new Coordinate(1,0);
        assertEquals(1, MazeSolver.getIndex(src, 2));
    }
    @Test
    public void testGetIndex2x2ZeroOne() {
        src = new Coordinate(0,1);
        assertEquals(2,MazeSolver.getIndex(src, 2));
    }
    @Test
    public void testGetIndex2x2OneOne() {
        src = new Coordinate(1,1);
        assertEquals(3,MazeSolver.getIndex(src, 2));
    }

    /** getCoordinate Tests **/
    @Test
    public void testGetCoordinate2x2ZeroZero() {
        src = MazeSolver.getCoordinate(0,2);
        assertEquals(0,src.getX());
        assertEquals(0,src.getY());
    }
    @Test
    public void testGetCoordinate2x2OneZero() {
        src = MazeSolver.getCoordinate(1,2);
        assertEquals(1,src.getX());
        assertEquals(0,src.getY());
    }
    @Test
    public void testGetCoordinate2x2ZeroOne() {
        src = MazeSolver.getCoordinate(2,2);
        assertEquals(0,src.getX());
        assertEquals(1,src.getY());
    }
    @Test
    public void testGetCoordinate2x2OneOne() {
        src = MazeSolver.getCoordinate(3,2);
        assertEquals(1,src.getX());
        assertEquals(1,src.getY());
    }

    /** Adjacent Tests **/
    @Test
    public void testAdjacentOutOfBoundsTop() {
        Coordinate out = new Coordinate(1,-1);
        Coordinate[] adjOut = MazeSolver.adjacent(out, 2,2);
        assertEquals(0,adjOut.length);
    }
    @Test
    public void testAdjacentOutOfBoundsBottom() {
        Coordinate out = new Coordinate(1,2);
        Coordinate[] adjOut = MazeSolver.adjacent(out, 2,2);
        assertEquals(0,adjOut.length);
    }
    @Test
    public void testAdjacentOutOfBoundsRight() {
        Coordinate out = new Coordinate(2,1);
        Coordinate[] adjOut = MazeSolver.adjacent(out, 2,2);
        assertEquals(0,adjOut.length);
    }
    @Test
    public void testAdjacentOutOfBoundsLeft() {
        Coordinate out = new Coordinate(-1,1);
        Coordinate[] adjOut = MazeSolver.adjacent(out, 2,2);
        assertEquals(0,adjOut.length);
    }
    @Test
    public void testAdjacentValid2x2() {
        Coordinate zero = new Coordinate(0,0);
        Coordinate one = new Coordinate(1,0);
        Coordinate two = new Coordinate(0,1);
        Coordinate three = new Coordinate(1,1);

        Coordinate[] adjZero = MazeSolver.adjacent(zero, 2,2);
        Coordinate[] adjOne = MazeSolver.adjacent(one, 2,2);
        Coordinate[] adjTwo = MazeSolver.adjacent(two, 2,2);
        Coordinate[] adjThree = MazeSolver.adjacent(three, 2,2);

        assertEquals(2,adjZero.length);
        assertEquals(2,adjOne.length);
        assertEquals(2,adjTwo.length);
        assertEquals(2,adjThree.length);
    }
    @Test
    public void testAdjacentValid3x3() {
        Coordinate zero = new Coordinate(0,0);
        Coordinate one = new Coordinate(1,0);
        Coordinate two = new Coordinate(2,0);
        Coordinate three = new Coordinate(0,1);
        Coordinate four = new Coordinate(1,1);
        Coordinate five = new Coordinate(2,1);
        Coordinate six = new Coordinate(0,2);
        Coordinate seven = new Coordinate(1,2);
        Coordinate eight = new Coordinate(2,2);

        Coordinate[] adjZero = MazeSolver.adjacent(zero, 3,3);
        Coordinate[] adjOne = MazeSolver.adjacent(one, 3,3);
        Coordinate[] adjTwo = MazeSolver.adjacent(two, 3,3);
        Coordinate[] adjThree = MazeSolver.adjacent(three, 3,3);
        Coordinate[] adjFour = MazeSolver.adjacent(four, 3,3);
        Coordinate[] adjFive = MazeSolver.adjacent(five, 3,3);
        Coordinate[] adjSix = MazeSolver.adjacent(six, 3,3);
        Coordinate[] adjSeven = MazeSolver.adjacent(seven, 3,3);
        Coordinate[] adjEight = MazeSolver.adjacent(eight, 3,3);

        assertEquals(2,adjZero.length);
        assertEquals(3,adjOne.length);
        assertEquals(2,adjTwo.length);
        assertEquals(3,adjThree.length);
        assertEquals(4,adjFour.length);
        assertEquals(3,adjFive.length);
        assertEquals(2,adjSix.length);
        assertEquals(3,adjSeven.length);
        assertEquals(2,adjEight.length);
    }

    /** Construct Tests **/
    @Test
    public void testConstruct2x2() {
        Graph graph = MazeSolver.construct(twoByTwo);

        assertEquals(4,graph.getSize());
        assertEquals(2,graph.outNeighbors(0).size());
        assertEquals(2,graph.outNeighbors(1).size());
        assertEquals(2,graph.outNeighbors(2).size());
        assertEquals(2,graph.outNeighbors(3).size());
    }

    @Test
    public void testConstruct4x4() {
        Graph graph = MazeSolver.construct(allZeroMaze);

        assertEquals(16,graph.getSize());
        for (int i = 0; i < 16; i++) {
            if (i == 0 || i == 3 || i == 12 || i == 15) {
                assertEquals(2,graph.outNeighbors(i).size());
            } else if (i == 5 || i == 6 || i == 9 || i == 10) {
                assertEquals(4,graph.outNeighbors(i).size());
            } else {
                assertEquals(3,graph.outNeighbors(i).size());
            }
        }
    }

    /** BFS Tests **/
    @Test
    public void testBFS2x2SRCZeroZero() {
        Graph graph = MazeSolver.construct(twoByTwo);
        src = new Coordinate(0,0);
        int[] p = MazeSolver.bfs(graph,src, twoByTwo);

        assertEquals(4,p.length);
        assertEquals(1,p[3]);
        assertEquals(0,p[2]);
        assertEquals(0,p[1]);
        assertEquals(-1,p[0]);
    }
    @Test
    public void testBFS4x4ZeroZero() {
        Graph graph = MazeSolver.construct(allZeroMaze);
        src = new Coordinate(0,0);
        int[] p = MazeSolver.bfs(graph,src, allZeroMaze);

        assertEquals(16,p.length);
        assertEquals(-1,p[0]);
        assertEquals(0,p[1]);
        assertEquals(0,p[4]);

        assertEquals(1,p[2]);
        assertEquals(1,p[5]);
        assertEquals(4,p[8]);

        assertEquals(2,p[3]);
        assertEquals(2,p[6]);
        assertEquals(5,p[9]);
        assertEquals(9,p[13]);

        assertEquals(7,p[11]);
        assertEquals(10,p[14]);

        assertEquals(6,p[10]);
        assertEquals(9,p[13]);

        assertEquals(11,p[15]);
    }

    /** MazeSolver Tests **/
    @Test
    public void testTwoByTwoTopLeftToTopRight() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(1,0);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(twoByTwo, src, tgt);
        assertEquals(2, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(1));
    }
    @Test
    public void testTwoByTwoTopLeftToBottomRight() {
        src = new Coordinate(0,0);
        Coordinate mid = new Coordinate(1,0);
        tgt = new Coordinate(1,1);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(twoByTwo, src, tgt);
        assertEquals(3, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(mid, coordinates.get(1));
        assertEquals(tgt, coordinates.get(2));
    }

    @Test
    public void testAllZeroTopLeftToBottomRight() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(3,3);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(allZeroMaze, src, tgt);
        assertEquals(7, coordinates.size());
    }

    @Test
    public void testMiddleSquareMazeTest() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(3,3);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(middleSquareMaze, src, tgt);
        assertEquals(7, coordinates.size());
    }
    @Test
    public void testTallRectangleMazeTest() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(2,3);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(tallRectangleMaze, src, tgt);
        assertEquals(6, coordinates.size());
    }
    @Test
    public void testLongRectangleMazeTest() {
        src = new Coordinate(3,0);
        tgt = new Coordinate(0,2);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(longRectangleMaze, src, tgt);
        assertEquals(6, coordinates.size());
    }
    @Test
    public void testAllZeroTopLeftToBottomLeft() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(0,3);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(allZeroMaze, src, tgt);
        assertEquals(4, coordinates.size());
    }
    @Test
    public void testOnlyOnePath() {
        src = new Coordinate(0,0);
        Coordinate mid1 = new Coordinate(1,0);
        Coordinate mid2 = new Coordinate(2,0);
        Coordinate mid3 = new Coordinate(3,0);
        Coordinate mid4 = new Coordinate(3,1);
        Coordinate mid5 = new Coordinate(3,2);
        Coordinate mid6 = new Coordinate(2,2);
        Coordinate mid7 = new Coordinate(1,2);
        Coordinate mid8 = new Coordinate(0,2);
        tgt = new Coordinate(0,3);

        List<Coordinate> coordinates = MazeSolver.getShortestPath(onlyOnePath, src, tgt);
        assertEquals(10, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(mid1, coordinates.get(1));
        assertEquals(mid2, coordinates.get(2));
        assertEquals(mid3, coordinates.get(3));
        assertEquals(mid4, coordinates.get(4));
        assertEquals(mid5, coordinates.get(5));
        assertEquals(mid6, coordinates.get(6));
        assertEquals(mid7, coordinates.get(7));
        assertEquals(mid8, coordinates.get(8));
        assertEquals(tgt, coordinates.get(9));
    }
    @Test
    public void testBigMaze10x10() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(9,9);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(bigWriteupMaze, src, tgt);
        assertEquals(19, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(18));
    }
    @Test
    public void testNoSolutionSquare() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(3,3);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(unsolvableSquare, src, tgt);
        assertTrue(coordinates.isEmpty());
    }
    @Test
    public void testNoSolutionRectangle() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(3,2);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(unsolvableRectangle, src, tgt);
        assertTrue(coordinates.isEmpty());
    }
    @Test
    public void testSingletonMaze() {
        int[][] maze = new int[1][1];
        src = new Coordinate(0,0);
        tgt = new Coordinate(0,0);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(maze, src, tgt);
        assertEquals(1, coordinates.size());
    }
    @Test
    public void testBigMaze25() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(24,24);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(bigWriteupMaze25, src, tgt);
        assertEquals(49, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(48));
    }

    @Test
    public void testSixteenBySixteen() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(15,15);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(sixteenBySixteen, src, tgt);
        assertEquals(31, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(30));
    }
    @Test
    public void testTenByTen() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(9,9);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(tenByTen, src, tgt);
        assertEquals(19, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(18));
    }
    @Test
    public void testTwelveByTwelve() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(11,11);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(twelveByTwelve, src, tgt);
        assertEquals(23, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(22));
    }
    @Test
    public void testThreeByThree() {
        src = new Coordinate(0,0);
        tgt = new Coordinate(2,2);
        List<Coordinate> coordinates = MazeSolver.getShortestPath(threeByThree, src, tgt);
        assertEquals(5, coordinates.size());
        assertEquals(src, coordinates.get(0));
        assertEquals(tgt, coordinates.get(4));
    }
}
