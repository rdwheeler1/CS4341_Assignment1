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
				goalFound = true;
				currNode = n;
			}

			System.out.println("exploring " + n.getValue());
			explored.put(n, n.getCost());

			ArrayList<Node> neighbors = findNeighbors(map, n);
			for(Node child : neighbors) {
				if(!frontier.contains(child) && !explored.containsKey(child)) {
					child.setPrevNode(n);
					child.setCost(child.getPrevNode().getCost() + calcCost(child));

					System.out.println("from " + child.getPrevNode().getValue() + " to "+
							child.getValue() + " costs: " +  child.getCost());

					frontier.add(child);
				} else if(frontier.contains(child)) {	// && child.cost < pre-existing.cost
					child.setPrevNode(n);
					child.setCost(child.getPrevNode().getCost() + calcCost(child));
					for(Node f : frontier) {	//****
						if(f.equals(child) && child.getCost() < f.getCost()) {
							frontier.remove(f);
							child.setPrevNode(n);
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

	int calcCost(Node n) {
		if(n.getValue().equals('S') || n.getValue().equals('G')) {
			return 1;
		} else {
			return n.getNumericValue();
		}
	}

//	public PriorityQueue<Node> UCS(Character[][] map) {
//
//		int heuristic = 0;
//
//		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
//		boolean[][] closedCells = new boolean[map.length][map[0].length];
//		initializeClosedMap(closedCells);
//		HashMap<Node, Node> explored = new HashMap<Node, Node>();
//		PriorityQueue<Node> path = new PriorityQueue<Node>();
//		boolean goalFound = false;
//		Node currNode = null;
//
//		Node start = findStart(map);
//		frontier.add(start);
//
//		//search through unexplored nodes until goal found
//		while (!frontier.isEmpty() && !goalFound) {
//			Node currentNode = null;
//			int shortestValue = -1;
//			for (Node n : frontier) {
//				if (n.getCost() + heuristic > shortestValue) {
//					currentNode = n;
//				}
//			}
//			if (currentNode.getValue().equals('G')) {
//				goalFound = true;
//				currNode = currentNode;
//				break;
//			}
//
//			closedCells[currentNode.getRow()][currentNode.getCol()] = true;
//			explored.put(currentNode, currentNode);
//			Node finalCurrentNode = currentNode;
//			frontier.remove(currentNode);
//			//frontier.removeIf(node -> node.getRow() == finalCurrentNode.getRow() && node.getCol() == finalCurrentNode.getRow());
//
//			ArrayList<Node> neighbors = findNeighbors(map, currentNode);
//			for (Node child : neighbors) {
//				child.setCost(child.prevNode.getCost() + child.getSingleNodeCost());
//				if (currentNode.getCost() > child.getCost() && closedCells[child.getRow()][child.getCol()]) {
//					explored.replace(child, child);
//				} else if (currentNode.getCost() < child.getCost() && frontier.contains(child)) {
//					explored.replace(child, child);
//				} else if (!closedCells[child.getRow()][child.getCol()] && !frontier.contains(child)) {
//					frontier.add(child);
//				}
//			}
//		}
//		//build path
//		path.add(currNode);
//
//		do {
//			currNode = currNode.getPrevNode();
//			path.add(currNode);
//		} while (!currNode.getValue().equals('S'));
//
//		return path;
//
//	}
	
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
					case UP -> onlyBash = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()], getNonBashDirection(n.direction));
					case RIGHT -> onlyBash = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], getNonBashDirection(n.direction));
					case DOWN -> onlyBash = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], getNonBashDirection(n.direction));
					case LEFT -> onlyBash = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], getNonBashDirection(n.direction));
				}
				onlyBash.setPrevNode(n);
				onlyBash.setSingleNodeCost(onlyBash.getNumericValue());
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
				bash.setSingleNodeCost(3);
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
				forward.setSingleNodeCost(forward.getNumericValue());
				neighbors.add(forward);
			} catch (Exception e){
				System.out.println("Forward neighbor is out of bounds");
			}
			right = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getRightDirection(n.direction));
			left = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getLeftDirection(n.direction));
			right.setPrevNode(n);
			left.setPrevNode(n);
			right.setSingleNodeCost((int) Math.ceil(((double) right.getNumericValue() / 2)));
			left.setSingleNodeCost((int) Math.ceil(((double) left.getNumericValue() / 2)));
			neighbors.add(right);
			neighbors.add(left);

			return neighbors;
		}
	}
	
}
