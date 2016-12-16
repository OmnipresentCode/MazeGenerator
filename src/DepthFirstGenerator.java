import java.util.Random;

public class DepthFirstGenerator {

	private static int right = 0, left = 1, top = 2, bottom = 3;
	
	public static Maze generate(int rows, int columns, float cellSize) {
		Maze maze = new Maze(columns, rows, cellSize); // default maze
		setupGeneration(maze);
		return maze;
	}

	public static void generate(Maze maze) {
		if (maze == null || !maze.checkState())
			return;
		setupGeneration(maze);
	}

	public static void generateMemSafe(Maze maze) {
		Tracker tracker = stepGeneration(maze);
		
		while(tracker.next != Tracker.END){
			visualStep(maze, tracker);
		}
	}

	private static void setupGeneration(Maze maze) {
		// check state (start & end are on grid) and maze is not null
		if (maze == null || !maze.checkState())
			return;

		// must start with a filled maze, we will remove walls as we go
		maze.fill();

		boolean[][] visitedCell = new boolean[maze.getColumns()][maze.getRows()];

		// start at the end (there will only be one path through the maze and
		// all cells will be accesible)
		visit(maze, visitedCell, maze.getEnd());

	}

	private static void visit(Maze maze, boolean[][] visitedCell, Point curr) {

		visitedCell[curr.x][curr.y] = true;

		checkSides(maze, visitedCell, curr, 4);

		checkSides(maze, visitedCell, curr, 3);

		checkSides(maze, visitedCell, curr, 2);

		checkSides(maze, visitedCell, curr, 1);
	}

	private static void checkSides(Maze maze, boolean[][] visitedCell, Point curr, int possibleSides) {

		int[] notVisited = new int[possibleSides];

		boolean[] visitedSide = new boolean[4];

		// Walls are edges are considered visited (outside of array bounds)
		visitedSide[right] = curr.x + 1 == maze.getColumns() || visitedCell[curr.x + 1][curr.y];
		visitedSide[left] = curr.x == 0 || visitedCell[curr.x - 1][curr.y];
		visitedSide[top] = curr.y == 0 || visitedCell[curr.x][curr.y - 1];
		visitedSide[bottom] = curr.y + 1 == maze.getRows() || visitedCell[curr.x][curr.y + 1];

		for (int i = 0; i < notVisited.length; i++) {
			notVisited[i] = -1;
		}

		int i = 0;
		if (!visitedSide[right]) {
			notVisited[i] = right;
			i++;
		}
		if (!visitedSide[left]) {
			notVisited[i] = left;
			i++;
		}
		if (!visitedSide[top]) {
			notVisited[i] = top;
			i++;
		}
		if (!visitedSide[bottom]) {
			notVisited[i] = bottom;
			i++;
		}

		if (notVisited[notVisited.length - 1] != -1) {
			int nextVisit = new Random().nextInt(notVisited.length);
			if (notVisited[nextVisit] == right) {
				maze.setRight(curr.x, curr.y, false);
				maze.setLeft(curr.x + 1, curr.y, false);
				visit(maze, visitedCell, new Point(curr.x + 1, curr.y));
			} else if (notVisited[nextVisit] == left) {
				maze.setLeft(curr.x, curr.y, false);
				maze.setRight(curr.x - 1, curr.y, false);
				visit(maze, visitedCell, new Point(curr.x - 1, curr.y));
			} else if (notVisited[nextVisit] == top) {
				maze.setTop(curr.x, curr.y, false);
				maze.setBottom(curr.x, curr.y - 1, false);
				visit(maze, visitedCell, new Point(curr.x, curr.y - 1));
			} else if (notVisited[nextVisit] == bottom) {
				maze.setBottom(curr.x, curr.y, false);
				maze.setTop(curr.x, curr.y + 1, false);
				visit(maze, visitedCell, new Point(curr.x, curr.y + 1));
			}
		}
	}

	public static Tracker stepGeneration(Maze maze) {
		if (maze == null || !maze.checkState())
			return null;
		maze.fill();
		return new Tracker(maze);
	}

	public static void visualStep(Maze maze, Tracker tracker) {
		
		tracker.posStack.push(tracker.next);
		
		tracker.visitedCell[tracker.posStack.peek().x][tracker.posStack.peek().y] = true;
		
		visualCheckSides(maze, tracker, 4);
		
		if(tracker.next == tracker.posStack.peek())
			visualCheckSides(maze, tracker, 3);

		if(tracker.next == tracker.posStack.peek())
			visualCheckSides(maze, tracker, 2);

		if(tracker.next == tracker.posStack.peek())
			visualCheckSides(maze, tracker, 1);
		

		if(tracker.next == tracker.posStack.peek()) {
			//pop current
			tracker.posStack.pop();
			//next is set to previous
			tracker.next = tracker.posStack.pop();
		}
	}

	private static void visualCheckSides(Maze maze, Tracker tracker, int possibleSides) {

		int[] notVisited = new int[possibleSides];

		boolean[] visitedSide = new boolean[4];

		// Walls are edges are considered visited (outside of array bounds)
		visitedSide[right] = tracker.posStack.peek().x + 1 == maze.getColumns() || tracker.visitedCell[tracker.posStack.peek().x + 1][tracker.posStack.peek().y];
		visitedSide[left] = tracker.posStack.peek().x == 0 || tracker.visitedCell[tracker.posStack.peek().x - 1][tracker.posStack.peek().y];
		visitedSide[top] = tracker.posStack.peek().y == 0 || tracker.visitedCell[tracker.posStack.peek().x][tracker.posStack.peek().y - 1];
		visitedSide[bottom] = tracker.posStack.peek().y + 1 == maze.getRows() || tracker.visitedCell[tracker.posStack.peek().x][tracker.posStack.peek().y + 1];

		for (int i = 0; i < notVisited.length; i++) {
			notVisited[i] = -1;
		}

		int i = 0;
		if (!visitedSide[right]) {
			notVisited[i] = right;
			i++;
		}
		if (!visitedSide[left]) {
			notVisited[i] = left;
			i++;
		}
		if (!visitedSide[top]) {
			notVisited[i] = top;
			i++;
		}
		if (!visitedSide[bottom]) {
			notVisited[i] = bottom;
			i++;
		}

		if (notVisited[notVisited.length - 1] != -1) {
			int nextVisit = new Random().nextInt(notVisited.length);
			if (notVisited[nextVisit] == right) {
				maze.setRight(tracker.posStack.peek().x, tracker.posStack.peek().y, false);
				maze.setLeft(tracker.posStack.peek().x + 1, tracker.posStack.peek().y, false);
				tracker.next = new Point(tracker.posStack.peek().x + 1, tracker.posStack.peek().y);
			} else if (notVisited[nextVisit] == left) {
				maze.setLeft(tracker.posStack.peek().x, tracker.posStack.peek().y, false);
				maze.setRight(tracker.posStack.peek().x - 1, tracker.posStack.peek().y, false);
				tracker.next = new Point(tracker.posStack.peek().x - 1, tracker.posStack.peek().y);
			} else if (notVisited[nextVisit] == top) {
				maze.setTop(tracker.posStack.peek().x, tracker.posStack.peek().y, false);
				maze.setBottom(tracker.posStack.peek().x, tracker.posStack.peek().y - 1, false);
				tracker.next = new Point(tracker.posStack.peek().x, tracker.posStack.peek().y - 1);
			} else if (notVisited[nextVisit] == bottom) {
				maze.setBottom(tracker.posStack.peek().x, tracker.posStack.peek().y, false);
				maze.setTop(tracker.posStack.peek().x, tracker.posStack.peek().y + 1, false);
				tracker.next = new Point(tracker.posStack.peek().x, tracker.posStack.peek().y + 1);
			}
		}
	}

}
