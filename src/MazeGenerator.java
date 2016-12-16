import processing.core.PApplet;

public class MazeGenerator extends PApplet {
	Maze maze;
	Tracker tracker;
	boolean track = true;

	public static void main(String[] args) {
		PApplet.main("MazeGenerator");
	}

	/**
	 * settings()
	 * 
	 * used exclusively for setting window size
	 */
	public void settings() {
		size(700, 700);
	}

	/**
	 * setup()
	 * 
	 * Setup processing 3
	 */
	public void setup() {
		int rows, columns;
		float cellSize = 20;
		rows = (int)(height/cellSize);
		columns = (int)(width/cellSize);
		
		maze = new Maze(columns, rows, cellSize);
		if(track){
			tracker = DepthFirstGenerator.stepGeneration(maze);
		}
		else
			DepthFirstGenerator.generateMemSafe(maze);
	}

	/**
	 * draw()
	 * 
	 * processing 3 draw function
	 */
	public void draw() {
		if (tracker != null && tracker.next != Tracker.END) {
			this.clear();
			background(255);

			DepthFirstGenerator.visualStep(maze, tracker);
			drawMaze(maze);

			noStroke();
			fill(0, 0, 255);
			rect(tracker.next.x * maze.getCellSize(), tracker.next.y * maze.getCellSize(), maze.getCellSize(),
					maze.getCellSize());
			stroke(0);
		}
		
		if(tracker == null){
			drawMaze(maze);
			noLoop();
		}
	}

	public void drawMaze(Maze maze) {
		int columns = maze.getColumns();
		int rows = maze.getRows();
		float cellSize = maze.getCellSize();

		// Draw Start
		fill(0, 255, 0);
		noStroke();
		rect(maze.getStart().x * cellSize, maze.getStart().y * cellSize, cellSize, cellSize);
		// Draw End
		fill(255, 0, 0);
		rect(maze.getEnd().x * cellSize, maze.getEnd().y * cellSize, cellSize, cellSize);

		stroke(0);
		for (int col = 0; col < columns; col++) {
			for (int row = 0; row < rows; row++) {
				if (maze.getRight(col, row)) {
					line((col + 1) * cellSize, row * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
				}
				if (maze.getLeft(col, row)) {
					line((col * cellSize), (row * cellSize), (col * cellSize), (row + 1) * cellSize);
				}
				if (maze.getTop(col, row)) {
					line(col * cellSize, row * cellSize, (col + 1) * cellSize, row * cellSize);
				}
				if (maze.getBottom(col, row)) {
					line(col * cellSize, (row + 1) * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
				}
			}
		}
	}

}
