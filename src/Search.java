package src;
import src.Enums.Direction;

import java.lang.reflect.Array;
import java.util.*;

import static src.Enums.Direction.*;

public class Search {

	public Search() {
	}

	public LinkedList<Node> AStar(Character[][] map) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		HashMap<Node, Integer> explored = new HashMap<Node, Integer>();
		LinkedList<Node> path = new LinkedList<>();
		Node currNode = null;
		Node goal = findGoal(map);
		Heuristics heuristic = new Heuristics(4);

		Node start = findStart(map);
		start.setaStarCost(start.getCost() + heuristic.heuristic(start.getAbsVert(), start.getAbsHoriz()));
		frontier.add(start);

		//search through unexplored nodes until goal found
		while(!frontier.isEmpty()) {

			Node n = frontier.remove();
			if(n.getValue().equals('G')) {
				System.out.println(n.getCost());
				currNode = n;
				break;
			}

			System.out.println("exploring " + n.getValue());
			explored.put(n, n.getCost());

			ArrayList<Node> neighbors = findNeighbors(map, n, goal);
			for(Node child : neighbors) {
				if(!frontier.contains(child) && !explored.containsKey(child)) {

					System.out.println("from " + child.getPrevNode().getValue() + " to "+
							child.getValue() + " costs: " +
							(child.getCost() + heuristic.heuristic(child.getAbsVert(), child.getAbsHoriz())));
					child.setaStarCost(child.getCost() + heuristic.heuristic(child.getAbsVert(),child.getAbsHoriz()));
					frontier.add(child);
				} else if(frontier.contains(child)) {	// && child.cost < pre-existing.cost
					for(Node f : frontier) {	//****
						child.setaStarCost(child.getCost() + heuristic.heuristic(child.getAbsVert(),child.getAbsHoriz()));
						f.setaStarCost(f.getCost() + heuristic.heuristic(f.getAbsVert(), f.getAbsHoriz()));
						if(f.equals(child) && child.getaStarCost()
								< f.getaStarCost()) {
							frontier.remove(f);
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
			path.addFirst(currNode);
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

	public Node findGoal(Character[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				Character val = map[i][j];
				if(val.equals('G')) {
					return new Node(i,j,'G', UP);
				}
			}
		}
		return null;
	}

	public int getVert(Node start,Node goal) {
		int startHeight = start.getRow();
		int goalHeight = goal.getRow();
		int difference = startHeight - goalHeight;
		return Math.abs(difference);
	}

	public int getHoriz(Node start,Node goal) {
		int startHeight = start.getCol();
		int goalHeight = goal.getCol();
		int difference = startHeight - goalHeight;
		return Math.abs(difference);
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
	public ArrayList<Node> findNeighbors(Character[][] map, Node n, Node goal) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		Node bash = null;
		Node forward = null;
		Node left;
		Node right;
		Node onlyBash = null;

		if(n.isBashNode()){
			try{
				switch (n.getDirection()){
					case BASHUP -> onlyBash = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()], getNonBashDirection(n.getDirection()));
					case BASHRIGHT -> onlyBash = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], getNonBashDirection(n.getDirection()));
					case BASHDOWN -> onlyBash = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], getNonBashDirection(n.getDirection()));
					case BASHLEFT -> onlyBash = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], getNonBashDirection(n.getDirection()));
				}
				onlyBash.setPrevNode(n);
				onlyBash.setCost(onlyBash.getNumericValue() + onlyBash.getPrevNode().getCost());
				onlyBash.setAbsHoriz(getHoriz(onlyBash, goal));
				onlyBash.setAbsVert(getVert(onlyBash, goal));
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
				switch (n.getDirection()){
					case UP -> bash = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()], getBashDirection(n.getDirection()));
					case RIGHT -> bash = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], getBashDirection(n.getDirection()));
					case DOWN -> bash = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], getBashDirection(n.getDirection()));
					case LEFT -> bash = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], getBashDirection(n.getDirection()));
				}
				bash.setPrevNode(n);
				bash.setCost(3 + bash.getPrevNode().getCost());
				bash.setAbsHoriz(getHoriz(bash, goal));
				bash.setAbsVert(getVert(bash, goal));
				neighbors.add(bash);
			} catch (Exception e){
				System.out.println("Bash Neighbor is out of bounds");
			}
			try{
				switch (n.getDirection()){
					case UP -> forward = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()] , n.getDirection());
					case RIGHT -> forward = new Node(n.getRow(), n.getCol() + 1, map[n.getRow()][n.getCol()+1], n.getDirection());
					case DOWN -> forward = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()], n.getDirection());
					case LEFT -> forward = new Node(n.getRow(), n.getCol() - 1, map[n.getRow()][n.getCol()-1], n.getDirection());
				}
				forward.setPrevNode(n);
				forward.setCost(forward.getNumericValue() + forward.getPrevNode().getCost());
				forward.setAbsHoriz(getHoriz(forward, goal));
				forward.setAbsVert(getVert(forward, goal));
				neighbors.add(forward);
			} catch (Exception e){
				System.out.println("Forward neighbor is out of bounds");
			}
			right = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getRightDirection(n.getDirection()));
			left = new Node(n.getRow(), n.getCol(), map[n.getRow()][n.getCol()], getLeftDirection(n.getDirection()));
			right.setPrevNode(n);
			left.setPrevNode(n);
			right.setCost((int) Math.ceil(((double) right.getNumericValue() / 2)) + right.getPrevNode().getCost());
			left.setCost((int) Math.ceil(((double) left.getNumericValue() / 2)) + left.getPrevNode().getCost());
			right.setAbsHoriz(getHoriz(right, goal));
			right.setAbsVert(getVert(right, goal));
			left.setAbsHoriz(getHoriz(left, goal));
			left.setAbsVert(getVert(left, goal));
			neighbors.add(right);
			neighbors.add(left);

			return neighbors;
		}
	}
	
}