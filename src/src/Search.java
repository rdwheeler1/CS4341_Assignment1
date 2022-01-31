package src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Search {

//	Robot robot = new Robot();
	
	public Search() {}
	
	public PriorityQueue<Node> UCS(String[][] map) {
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
			if(n.getValue().equals("G")) {
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
		} while(!currNode.getValue().equals("S"));

		return path;
		
	}
	
	//NEEDS TO BE CHANGED TO REFLECT ASSIGNMENT
	int calcCost(Node n) {
		if(n.getValue().equals("S") || n.getValue().equals("G")) {
			return 1;
		} else {
			return Integer.parseInt(n.getValue());
		}
	}
	
//	public ArrayList<Node> updateCostOfNeighbors(Node currNode, ArrayList<Node> neighbors) {
//		for(Node n : neighbors) {
//			if(currNode.getRobot().getDirection().equals("north")) {
//				
//			} else if(currNode.getRobot().getDirection().equals("south")) {
//				
//			} else if(currNode.getRobot().getDirection().equals("east")) {
//				
//			} else if(currNode.getRobot().getDirection().equals("west")) {
//				
//			}
//		}
//	}
	
	public Node findStart(String[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				String val = map[i][j];
				if(val.equals("S")) {
					return new Node(i,j,"S");
				}
			}
		}
		return null;
	}

	public Node findGoal(String[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				String val = map[i][j];
				if(val.equals("G")) {
					return new Node(i,j,"G");
				}
			}
		}
		return null;
	}
	
	// gets surrounding nodes of a current node
	public ArrayList<Node> findNeighbors(String[][] map, Node n) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
		Node left = null;
		Node right = null;
		Node up = null;
		Node down = null;
		if(n.getRow() != 0) {	// not top edge of map
			up = new Node(n.getRow()-1, n.getCol(), map[n.getRow()-1][n.getCol()]);
//			System.out.println("up: "+up.getValue());
//			up.setCost(calcCost(up));
			neighbors.add(up);
		} if(n.getCol() != 0) { // not left edge of map
			left = new Node(n.getRow(), n.getCol()-1, map[n.getRow()][n.getCol()-1]);
//			System.out.println("left: "+left.getValue());
//			left.setCost(calcCost(left));
			neighbors.add(left);
		} if(n.getRow() != map.length-1) { // not bottom edge of map
			down = new Node(n.getRow()+1, n.getCol(), map[n.getRow()+1][n.getCol()]);
//			System.out.println("down: "+down.getValue());
//			down.setCost(calcCost(down));
			neighbors.add(down);
		} if(n.getCol() != map[0].length-1) { // not right edge of map
			right = new Node(n.getRow(), n.getCol()+1, map[n.getRow()][n.getCol()+1]);
//			System.out.println("right: "+right.getValue());
//			right.setCost(calcCost(right));
			neighbors.add(right);
		} 
		return neighbors;
	}

	public int getVert(Node start,Node goal) {
		int startHeight = start.getRow();
		int goalHeight = goal.getRow();
		int difference = startHeight - goalHeight;
		int vert = Math.abs(difference);
		return vert;
	}

	public int getHoriz(Node start,Node goal) {
		int startHeight = start.getCol();
		int goalHeight = goal.getCol();
		int difference = startHeight - goalHeight;
		int horiz = Math.abs(difference);
		return horiz;
	}

	public PriorityQueue<Node> aStar(String[][] map) {
		PriorityQueue<Node> frontier = new PriorityQueue<>();
		PriorityQueue<Node> explored = new PriorityQueue<>();
		PriorityQueue<Node> path = new PriorityQueue<>();
		Heuristics heuristic = new Heuristics();


		Node start = findStart(map);
		Node goal = findGoal(map);
		while(!frontier.isEmpty()) {
			Node exploring = frontier.peek();
			if(exploring == goal) {
				path.add(exploring);
				return path;
			}
			ArrayList<Node> neighbours = findNeighbors(map, exploring);
			for(Node child: neighbours) {
				int vertical = getVert(start,goal);
				int horizontal = getHoriz(start,goal);
				int totalValue = calcCost(child) + heuristic.heuristic1(vertical,horizontal);

				if(!frontier.contains(child) && !explored.contains(child)) {
					child.prevNode = exploring;
					child.cost = totalValue;
					frontier.add(child);
				} else {
					if(totalValue < child.cost) {
						child.prevNode = exploring;
						child.cost = totalValue;
						if (explored.contains(child)) {
							explored.remove(child);
							frontier.add(child);
						}
					}
				}
			}
			frontier.remove(exploring);
			explored.add(exploring);
		}
		return path;
	}
	
}
