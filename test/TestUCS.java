package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import src.MapInitializer;
import src.Node;
import src.Search;

class TestUCS {

	Character[][] testBoard = {{'4', 'G', '4', '6'},
			{'2', '9', '9', '6'},
			{'1', '4', 'S', '3'}};
	Search searchHeuristic1 = new Search(1);
	Search searchHeuristic2 = new Search(2);
	Search searchHeuristic3 = new Search(3);
	Search searchHeuristic4 = new Search(4);
	Search searchHeuristic5 = new Search(5);
	Search searchHeuristic6 = new Search(6);

	MapInitializer map1 = new MapInitializer("Maps/Map1.txt");
	MapInitializer map2 = new MapInitializer("Maps/Map2.txt");
	MapInitializer map3 = new MapInitializer("Maps/Map3.txt");
	MapInitializer map4 = new MapInitializer("Maps/Map4.txt");
	MapInitializer map5 = new MapInitializer("Maps/Map5.txt");
	MapInitializer map6 = new MapInitializer("Maps/Map6.txt");
	MapInitializer map7 = new MapInitializer("Maps/Map7.txt");
	MapInitializer map8 = new MapInitializer("Maps/Map8.txt");
	MapInitializer map9 = new MapInitializer("Maps/Map9.txt");
	MapInitializer map10 = new MapInitializer("Maps/Map10.txt");


//	@Test
//	void findStartTest() {
//		Node start = search.findStart(testBoard);
//		assertEquals(2, start.getRow());
//		assertEquals(3, start.getCol());
//	}
//
//	@Test
//	void FindNeighborsTest() {
//		Node n = new Node(1, 2, testBoard[1][2], Direction.UP);
//		ArrayList<Node> nbrs = search.findNeighbors(testBoard,);
//		assertFalse(nbrs.isEmpty());
//		System.out.println("");
//	}
//
//	@Test
//	void OutOfBoundsNeighborTest() {
//		Node top = new Node(0, 2, testBoard[0][2], Direction.UP);
//		ArrayList<Node> nbrs1 = search.findNeighbors(testBoard, top);
//		assertFalse(nbrs1.isEmpty());
//		System.out.println("");
//
//		Node bottom = new Node(2, 1, testBoard[2][1], Direction.UP);
//		ArrayList<Node> nbrs2 = search.findNeighbors(testBoard, bottom);
//		assertFalse(nbrs2.isEmpty());
//		System.out.println("");
//
//		Node left = new Node(1, 0, testBoard[1][0], Direction.UP);
//		ArrayList<Node> nbrs3 = search.findNeighbors(testBoard, left);
//		assertFalse(nbrs3.isEmpty());
//		System.out.println("");
//
//		Node right = new Node(1, 3, testBoard[1][3], Direction.UP);
//		ArrayList<Node> nbrs4 = search.findNeighbors(testBoard, right);
//		assertFalse(nbrs4.isEmpty());
//		System.out.println("");
//
//		Node corner = new Node(0, 0, testBoard[0][0], Direction.UP);
//		ArrayList<Node> nbrs5 = search.findNeighbors(testBoard, corner);
//		assertFalse(nbrs5.isEmpty());
//	}
//
//	@Test
//	void NewContainsTest() {
//		PriorityQueue<Node> pq = new PriorityQueue<Node>();
//		//ArrayList<Node> al = new ArrayList<Node>();
//		Node a = new Node(1, 2, testBoard[1][2], Direction.UP);
//		a.setCost(10);
//		pq.add(a);
//		//al.add(a);
//
//		Node b = new Node(1, 2, testBoard[1][2], Direction.UP);
//		b.setCost(5);
//		if(pq.contains(b) && b.getCost() < a.getCost()) {
//			pq.remove(a);
//			pq.add(b);
//		}
//		//al.add(b);
//		assertTrue(pq.contains(a));
//		pq.remove();
//		assertFalse(pq.contains(a));
//		System.out.println("");
//	}
//
//	@Test
//	void MinPQTest() {
//		PriorityQueue<Node> pq = new PriorityQueue<Node>();
//		Node a = new Node(1, 2, testBoard[1][2], Direction.UP);
//		a.setCost(10);
//		pq.add(a);
//
//		Node b = new Node(0, 0, testBoard[0][0], Direction.UP);
//		b.setCost(5);
//		pq.add(b);
//
//		assertTrue(pq.peek().getCost() == 5);
//		pq.remove();
//		assertTrue(pq.peek().getCost() == 10);
//		System.out.println("");
//	}

	@Test
	void UCSTest() {
		LinkedList<Node> a = searchHeuristic5.AStar(map1.getMap());
		a.stream().forEach(x -> System.out.print(x.getValue() + " -> "));
		assertFalse(a.isEmpty());
	}

}
