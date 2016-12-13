
public class Cell {
	final static int RIGHT = 0, LEFT = 1, TOP = 2, BOTTOM = 3; 
	
	private float x;
	private float y;
	private boolean[] side = new boolean[4];
	
	public Cell(float x, float y){
		this.x = x;
		this.y = y;
		
		for(int i = 0; i < side.length; i++){
			side[i] = true;
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean getRight() {
		return side[RIGHT];
	}

	public boolean getLeft() {
		return side[LEFT];
	}

	public boolean getTop() {
		return side[TOP];
	}

	public boolean getBottom() {
		return side[BOTTOM];
	}
	
	public boolean setRight(boolean state) {
		return side[RIGHT] = state;
	}

	public boolean setLeft(boolean state) {
		return side[LEFT] = state;
	}

	public boolean setTop(boolean state) {
		return side[TOP] = state;
	}

	public boolean setBottom(boolean state) {
		return side[BOTTOM] = state;
	}
	
	
}
