
public class Maze {
	private static float defaultCellSize = 40;
	private Cell[][] grid;
	private float cellSize;
	
	public Maze(int columns, int rows, float cellSize){
		this.grid = new Cell[columns][rows];
		this.cellSize = cellSize;
		
		for(int col = 0; col < columns; col++){
			for(int row = 0; row < rows; row++){
				grid[col][row] = new Cell(col, row);
			}
		}
		
		clear();
	}
	
	public Maze(int columns, int rows){
		this(columns,rows, defaultCellSize);
	}

	public int getRows(){
		return this.grid[0].length;
	}
	
	public int getColumns(){
		return this.grid.length;
	}
	
	public float getCellSize(){
		return cellSize;
	}
	
	public boolean getRight(int x, int y) {
		return grid[x][y].getRight();
	}

	public boolean getLeft(int x, int y) {
		return grid[x][y].getLeft();
	}

	public boolean getTop(int x, int y) {
		return grid[x][y].getTop();
	}

	public boolean getBottom(int x, int y) {
		return grid[x][y].getBottom();
	}
	
	public boolean setRight(int x, int y, boolean state) {
		return grid[x][y].setRight(state);
	}

	public boolean setLeft(int x, int y, boolean state) {
		return grid[x][y].setLeft(state);
	}

	public boolean setTop(int x, int y, boolean state) {
		return grid[x][y].setTop(state);
	}

	public boolean setBottom(int x, int y, boolean state) {
		return grid[x][y].setBottom(state);
	}
	
	/**
	 * setState(boolean state)
	 * 
	 * @param state - The state in which to set every cell wall
	 * 
	 * Sets all cell walls to (state)
	 */
	public void setState(boolean state){
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y  < grid[x].length; y++){
				grid[x][y].setRight(state);
				grid[x][y].setLeft(state);
				grid[x][y].setTop(state);
				grid[x][y].setBottom(state);
			}
		}
	}

	/**
	 * clear()
	 * 
	 * set all the walls to false
	 */
	public void clear(){
		setState(false);
	}
	
	/**
	 * fill()
	 * 
	 * set all the walls to true
	 */
	public void fill(){
		setState(true);
	}
	
	public boolean checkState(){
		
		return true;
	}
}