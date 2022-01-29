package src;


public class Robot {
	
	String direction;
	Node node;
	int moves;
	
	public Robot(Node n) {
		direction = "north";
		node = n;
		moves = 0;
	}
	
	public String getDirection() {return this.direction;}
	public void setDirection(String dir) {direction = dir;}
	
	public void forward() {
		moves++;
//		if(direction.equals("north")) {
//			node.updateNode(node.getRow()-1, node.getCol(), moves);
//		} else if(direction.equals("south")) {
//			
//		} else if(direction.equals("east")) {
//			
//		} else if(direction.equals("west")) {
//			
//		}
	}
	
	public void turn() {
		moves++;
	}
	
	public void bash() {
		moves++;
		int bashTime = 3;	//bash takes set amount of time
		node.setCost(node.getCost()+bashTime);
		forward();
	}
}
