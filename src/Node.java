package src;

import src.Enums.Direction;

public class Node implements Comparable<Node> {
	int row;
	int col;
	Character value;
	int absVert;
	int absHoriz;
	int Cost;
	int aStarCost;
	Node prevNode;
	Direction direction;

	public Node(int row, int col, Character value, Direction direction) {
		this.row = row;
		this.col = col;
		this.value = value;
		this.Cost = 0;
		this.aStarCost = 1;
		this.prevNode = null;
		this.direction = direction;
	}

	public int getAbsVert() {
		return absVert;
	}

	public void setAbsVert(int absVert) {
		this.absVert = absVert;
	}

	public int getAbsHoriz() {
		return absHoriz;
	}

	public void setAbsHoriz(int absHoriz) {
		this.absHoriz = absHoriz;
	}

	public int getRow() {return this.row;}
	public int getCol() {return this.col;}
	public Character getValue() {return this.value;}
	
	public int getNumericValue() {
		if(this.value == 'S' || this.value == 'G'){
			return 1;
		}
		else{
			return Character.getNumericValue(this.value);
		}
	}

	public int getCost() {return this.Cost;}
	public void setCost(int pathCost) {this.Cost = pathCost;}
	public void setaStarCost(int cost){
		this.aStarCost = cost;
	}
	public int getaStarCost(){
		return this.aStarCost;
	}

	public Node getPrevNode() {return this.prevNode;}
	public void setPrevNode(Node prevNode) {this.prevNode = prevNode;}

	public boolean isBashNode(){
		return this.direction.equals(Direction.BASHUP) || this.direction.equals(Direction.BASHRIGHT) ||
				this.direction.equals(Direction.BASHDOWN) || this.direction.equals(Direction.BASHLEFT);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node n = (Node) o;
			return row == n.row && col == n.col && value.equals(n.value) && direction == n.direction;
		}
		return false;
	}

	@Override
	public int hashCode() {return row ^ 2 + col ^ 2;}

	@Override
	public int compareTo(Node o) {
		if (this.getaStarCost() > o.getaStarCost()) {
			return 1;
		} else if (this.getaStarCost() == o.getaStarCost()) {
			return 0;
		} else {
			return -1;
		}
	}
}
