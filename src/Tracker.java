import java.util.Stack;

public class Tracker {
	public final static Point END = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
	public boolean visitedCell[][];
	public Stack<Point> posStack;

	public Point next;

	public Tracker(Maze maze) {
		// check state (start & end are on grid) and maze is not null
		if (maze == null || !maze.checkState())
			return;

		this.visitedCell = new boolean[maze.getColumns()][maze.getRows()];
		this.posStack = new Stack<>();
		
		next = maze.getEnd();
		posStack.push(END);
	}

}
