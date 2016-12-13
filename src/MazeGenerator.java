import processing.core.PApplet;

public class MazeGenerator extends PApplet{
	Maze maze;
	
	public static void main(String[] args) {
		

	}
	
	/**
	 * settings()
	 * 
	 * used exclusively for setting window size
	 */
	public void settings(){
		size(400,400);
	}
	
	/**
	 * setup()
	 * 
	 * Setup processing 3 
	 */
	public void setup(){
		int rows = 10, columns = 10;
		float cellSize = 40;
		maze = DepthFirstGenerator.generate(rows, columns, cellSize);
	}
	
	/**
	 * draw()
	 * 
	 * processing 3 draw function
	 */
	public void draw(){
		//TODO sort out drawing maze
	}

}
