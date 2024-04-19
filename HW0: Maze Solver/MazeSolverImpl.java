import java.util.LinkedList;

public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    static LinkedList<Coordinate> path;
    static Coordinate source;
    static Coordinate goal;
    static int[][] solved;
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {

        if ((maze == null) || (maze.length == 0) || (maze[0].length == 0)) {
            throw new IllegalArgumentException("The maze cannot be null");
        }

        int n = maze.length;
        int m = maze[0].length;

        path = new LinkedList<>();
        source = sourceCoord;
        goal = goalCoord;
        solved = new int[n][m];

        if ((sourceCoord.getX() < 0 || sourceCoord.getX() > m - 1) || (sourceCoord.getY() < 0
                || sourceCoord.getY() > n - 1)) {
            throw new IllegalArgumentException("Source coordinates are out of bounds");
        } else if ((goalCoord.getX() < 0 || goalCoord.getX() > m - 1) || (goalCoord.getY() < 0
                || goalCoord.getY() > n - 1)) {
            throw new IllegalArgumentException("Goal coordinates are out of bounds");
        }
        if (maze[sourceCoord.getY()][sourceCoord.getX()] == 1) {
            throw new IllegalArgumentException("The source coordinates cannot be a blocked tile");
        } else if (maze[goalCoord.getY()][goalCoord.getX()] == 1) {
            throw new IllegalArgumentException("The goal coordinates cannot be a blocked tile");
        }
        if (m * n <= 1) {
            throw new IllegalArgumentException("The maze must have greater dimensions than 1x1");
        }

        //path.add(source);
        return move(maze, source);
    }

    /*You bumped into a blocked cell. You should TRY ANOTHER DIRECTION
        You bumped into a cell you visited already. You should GO BACK ONE AND TRY A NEW DIRECTION.
        You found the goal (G) position. You should return solved.
        If none of these work, RETURN NULL.*/

    //*********** Helper Functions ***********//
    public static int[][] move(int[][] maze, Coordinate curr) {
        if (curr == null) {
            return null;
        }

        path.add(curr);

        if (curr.equals(goal)) {
            solved[goal.getY()][goal.getX()] = 1;
            return solved;
        }

        Coordinate recent = path.getLast();
        maze[recent.getY()][recent.getX()] = 1;
        solved[recent.getY()][recent.getX()] = 1;

        if (downCheck(maze, curr)) {
            curr = new Coordinate(curr.getX(), curr.getY() + 1);
            move(maze, curr);
        } else if (upCheck(maze, curr)) {
            curr = new Coordinate(curr.getX(), curr.getY() - 1);
            move(maze, curr);
        } else if (leftCheck(maze, curr)) {
            curr = new Coordinate(curr.getX() - 1, curr.getY());
            move(maze, curr);
        } else if (rightCheck(maze, curr)) {
            curr = new Coordinate(curr.getX() + 1, curr.getY());
            move(maze, curr);
        } else {
            Coordinate last = path.pollLast();

            if (last.equals(source)) {
                return (solved = null);
            }
            maze[last.getY()][last.getX()] = 1;
            solved[last.getY()][last.getX()] = 0;
            curr = path.pollLast();
            move(maze, curr);
        }
        return solved;
    }
    public static boolean downCheck(int[][] maze, Coordinate curr) {
        return (((curr.getY() < maze.length - 1)) && (maze[curr.getY() + 1][curr.getX()]) != 1);
    }
    public static boolean upCheck(int[][] maze, Coordinate curr) {
        return (((curr.getY() > 0)) && (maze[curr.getY() - 1][curr.getX()]) != 1);
    }
    public static boolean leftCheck(int[][] maze, Coordinate curr) {
        return ((curr.getX() > 0) && (maze[curr.getY()][curr.getX() - 1]) != 1);
    }
    public static boolean rightCheck(int[][] maze, Coordinate curr) {
        return ((curr.getX() < maze[0].length - 1) && (maze[curr.getY()][curr.getX() + 1]) != 1);
    }
}
