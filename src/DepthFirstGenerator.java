import java.awt.Point;
import java.util.Stack;

public class DepthFirstGenerator {

	public static Maze generate(int rows, int columns, float cellSize) {
		Maze maze = new Maze(columns, rows, cellSize);
		maze.clear();

		Point start = new Point(0, 0);
		Point end = new Point(rows - 1, columns - 1);

		boolean[][] visited = new boolean[maze.getColumns()][maze.getRows()];

		if (start(start, end, maze))
			return maze;
		else
			return null;
	}

	private static boolean start(Point start, Point end, Maze maze){
		//check state (start & end are on grid) and maze is not null
		if(maze != null
				||start.getX() >= maze.getColumns() || start.getX() < 0
				|| start.getY() >= maze.getRows() || start.getY() < 0
				|| end.getX() >= maze.getColumns() || end.getX() < 0
				|| end.getY() >= maze.getRows() || end.getY() < 0) return false;
		int visitCount = 0;
		int visitMax = maze.getColumns() * maze.getRows();
		
		boolean[][] visited = new boolean[maze.getColumns()][maze.getRows()];
		Stack<Point> posStack = new Stack<>();
		
		while(visitCount < visitMax){
			//depth first algorithm
		}
		
		return true;
	}

}
