package src;

public class Node implements Comparable<Node> {
	int row;
	int col;
	String value;
	int cost;
	Node prevNode;
	Robot robot;

	public Node(int row, int col, String value) {
		this.row = row;
		this.col = col;
		this.value = value;
		this.cost = 0;
		this.prevNode = null;
		this.robot = new Robot();
	}

	public int getRow() {return this.row;}
	public int getCol() {return this.col;}
	public String getValue() {return this.value;}
	
	public int getCost() {return this.cost;}
	public void setCost(int cost) {this.cost = cost;}

	public Node getPrevNode() {return this.prevNode;}
	public void setPrevNode(Node prevNode) {this.prevNode = prevNode;}

	public Robot getRobot() {return this.robot;}
	public void setRobot(Robot robot) {this.robot = robot;}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node n = (Node) o;
			return row == n.row && col == n.col && value.equals(n.value);
		}
		return false;
	}

	@Override
	public int hashCode() {return row ^ 2 + col ^ 2;}

	@Override
	public int compareTo(Node o) {
		if (this.getCost() > o.getCost()) {
			return 1;
		} else if (this.getCost() == o.getCost()) {
			return 0;
		} else {
			return -1;
		}
	}
}
