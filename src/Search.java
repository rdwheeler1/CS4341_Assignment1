package src;
import src.Enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import static src.Enums.Direction.*;

public class Search {

	public Search() {}

	public PriorityQueue<Node> UCS(Character[][] map) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		HashMap<Node, Integer> explored = new HashMap<Node, Integer>();
		PriorityQueue<Node> path = new PriorityQueue<Node>();
		boolean goalFound = false;
		Node currNode = null;

		Node start = findStart(map);
		frontier.add(start);

		//search through unexplored nodes until goal found
		while(!frontier.isEmpty() && goalFound == false) {
			if(frontier.isEmpty()) {return null;}

			Node n = frontier.remove();
			if(n.getValue().equals('G')) {
				System.out.println(n.getCost());
				goalFound = true;
				currNode = n;
				break;
			}

			System.out.println("exploring " + n.getValue());
			explored.put(n, n.getCost());

			ArrayList<Node> neighbors = findNeighbors(map, n);
			for(Node child : neighbors) {
				if(!frontier.contains(child) && !explored.containsKey(child)) {

					System.out.println("from " + child.getPrevNode().getValue() + " to "+
							child.getValue() + " costs: " +  child.getCost());

					frontier.add(child);
				} else if(frontier.contains(child)) {	// && child.cost < pre-existing.cost
					for(Node f : frontier) {	//****
						if(f.equals(child) && child.getCost() < f.getCost()) {
							frontier.remove(f);
							//child.setPrevNode(n);
							frontier.add(child);
							break;
						}
					}
				}
			}
		}

		//build path
		path.add(currNode);

		do {
			currNode = currNode.getPrevNode();
			path.add(currNode);
		} while(!currNode.getValue().equals('S'));

		return path;

	}
	
	public Node findStart(Character[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				Character val = map[i][j];
				if(val.equals('S')) {
					return new Node(i,j,'S',UP);
				}
			}
		}
		return null;
	}

	public void initializeClosedMap(boolean[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = false;
			}
		}
	}

	public Direction getBashDirection(Direction direction){
		Direction returnDirection = direction;
		switch (direction){
			case UP -> returnDirection = BASHUP;
			case RIGHT -> returnDirection = BASHRIGHT;
			case DOWN -> returnDirection = BASHDOWN;
			case LEFT -> returnDirection = BASHLEFT;
		}
		return returnDirection;
	}

	public Direction getNonBashDirection(Direction direction){
		Direction returnDirection = direction;
		switch (direction){
			case BASHUP -> returnDirection = UP;
			case BASHRIGHT -> returnDirection = RIGHT;
			case BASHDOWN -> returnDirection = DOWN;
			case BASHLEFT -> returnDirection = LEFT;
		}
		return returnDirection;
	}

	public Direction getRightDirection(Direction direction){
		Direction returnDirection = direction;
		switch (direction){
			case UP -> returnDirection = RIGHT;
			case RIGHT -> returnDirection = DOWN;
			case DOWN -> returnDirection = LEFT;
			case LEFT -> returnDirection = UP;
		}
		return returnDirection;
	}

	public Direction getLeftDirection(Direction direction){
		Direction returnDirection = direction;
		switch (direction){
			case UP -> returnDirection = LEFT;
			case RIGHT -> returnDirection = UP;
			case DOWN -> returnDirection = RIGHT;
			case LEFT -> returnDirection = DOWN;
		}
		return returnDirection;
	}
	
	// gets surrounding nodes of a current node
	public ArrayList<Node> findNeighbors(Character[][] map, Node n) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		Node bash = null;
		Node forward = null;
		Node left;
		Node right;
		Node onlyBash = null;

		if(n.isBashNode()){
			try{
				switch (n.direction){
					case BASHUP -> onlyBash = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()], getNonBashDirection(n.direction));
					case BASHRIGHT -> onlyBash = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], getNonBashDirection(n.direction));
					case BASHDOWN -> onlyBash = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], getNonBashDirection(n.direction));
					case BASHLEFT -> onlyBash = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], getNonBashDirection(n.direction));
				}
				onlyBash.setPrevNode(n);
				onlyBash.setCost(onlyBash.getNumericValue() + onlyBash.prevNode.getCost());
				neighbors.add(onlyBash);
			}
			catch (Exception e){
				System.out.println("Neighbor is out of bounds");
				return neighbors;
			}
			return neighbors;
		}

		else{
			try{
				switch (n.direction){
					case UP -> bash = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()], getBashDirection(n.direction));
					case RIGHT -> bash = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], getBashDirection(n.direction));
					case DOWN -> bash = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], getBashDirection(n.direction));
					case LEFT -> bash = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], getBashDirection(n.direction));
				}
				bash.setPrevNode(n);
				bash.setCost(3 + bash.prevNode.getCost());
				neighbors.add(bash);
			} catch (Exception e){
				System.out.println("Bash Neighbor is out of bounds");
			}
			try{
				switch (n.direction){
					case UP -> forward = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()] , n.direction);
					case RIGHT -> forward = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], n.direction);
					case DOWN -> forward = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], n.direction);
					case LEFT -> forward = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], n.direction);
				}
				forward.setPrevNode(n);
				forward.setCost(forward.getNumericValue() + forward.prevNode.getCost());
				neighbors.add(forward);
			} catch (Exception e){
				System.out.println("Forward neighbor is out of bounds");
			}
			right = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getRightDirection(n.direction));
			left = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getLeftDirection(n.direction));
			right.setPrevNode(n);
			left.setPrevNode(n);
			right.setCost((int) Math.ceil(((double) right.getNumericValue() / 2)) + right.prevNode.getCost());
			left.setCost((int) Math.ceil(((double) left.getNumericValue() / 2)) + left.prevNode.getCost());
			neighbors.add(right);
			neighbors.add(left);

			return neighbors;
		}
	}
	
}
