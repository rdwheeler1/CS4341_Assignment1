package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import src.Node;
import src.Search;

class TestUCS {

	String[][] testBoard = {{"4", "G", "4", "6"},
			{"2", "9", "9", "6"},
			{"1", "4", "3", "S"}};
	Search search = new Search();

	@Test
	void findStartTest() {
		Node start = search.findStart(testBoard);
		assertEquals(2, start.getRow());
		assertEquals(3, start.getCol());
	}

	@Test
	void FindNeighborsTest() {
		Node n = new Node(1, 2, testBoard[1][2]);
		ArrayList<Node> nbrs = search.findNeighbors(testBoard, n);
		assertFalse(nbrs.isEmpty());
		System.out.println("");
	}

	@Test
	void OutOfBoundsNeighborTest() {
		Node top = new Node(0, 2, testBoard[0][2]);
		ArrayList<Node> nbrs1 = search.findNeighbors(testBoard, top);
		assertFalse(nbrs1.isEmpty());
		System.out.println("");

		Node bottom = new Node(2, 1, testBoard[2][1]);
		ArrayList<Node> nbrs2 = search.findNeighbors(testBoard, bottom);
		assertFalse(nbrs2.isEmpty());
		System.out.println("");

		Node left = new Node(1, 0, testBoard[1][0]);
		ArrayList<Node> nbrs3 = search.findNeighbors(testBoard, left);
		assertFalse(nbrs3.isEmpty());
		System.out.println("");

		Node right = new Node(1, 3, testBoard[1][3]);
		ArrayList<Node> nbrs4 = search.findNeighbors(testBoard, right);
		assertFalse(nbrs4.isEmpty());
		System.out.println("");

		Node corner = new Node(0, 0, testBoard[0][0]);
		ArrayList<Node> nbrs5 = search.findNeighbors(testBoard, corner);
		assertFalse(nbrs5.isEmpty());
	}

	@Test
	void NewContainsTest() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		//ArrayList<Node> al = new ArrayList<Node>();
		Node a = new Node(1, 2, testBoard[1][2]);
		a.setCost(10);
		pq.add(a);
		//al.add(a);

		Node b = new Node(1, 2, testBoard[1][2]);
		b.setCost(5);
		if(pq.contains(b) && b.getCost() < a.getCost()) {
			pq.remove(a);
			pq.add(b);
		}
		//al.add(b);
		assertTrue(pq.contains(a));
		pq.remove();
		assertFalse(pq.contains(a));
		System.out.println("");
	}

	@Test
	void MinPQTest() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		Node a = new Node(1, 2, testBoard[1][2]);
		a.setCost(10);
		pq.add(a);

		Node b = new Node(0, 0, testBoard[0][0]);
		b.setCost(5);
		pq.add(b);

		assertTrue(pq.peek().getCost() == 5);
		pq.remove();
		assertTrue(pq.peek().getCost() == 10);
		System.out.println("");
	}

	@Test
	void UCSTest() {
		PriorityQueue<Node> a = search.UCS(testBoard);
		while(!a.isEmpty()) {
			System.out.print(a.remove().getValue() + " -> ");
		}
		assertFalse(!a.isEmpty());
	}

}
