package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RandomMap {
	int numRows;
	int numCols;
	Character[][] map;
	File file;
	
	public RandomMap(int r, int c, String name) {
		this.numRows = r;
		this.numCols = c;
		this.map = new Character[r][c];
		//change for each person
		this.file = new File("C:/Users/abmoo/Documents/CS4341/" + name + ".txt");
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numCols; j++) {
					double min = 1;
					double max = 9;
					int num = (int) Math.floor(Math.random()*(max-min+1)+min);
					this.map[i][j] = (char) num;
					System.out.print(num + '\t');
//					if(j == numCols - 1) sb.append(map[i][j]+'\t');	//shouldn't tab last column?
//					else sb.append(map[i][j]);
					sb.append(map[i][j]+'\t');
				}
				sb.append('\n');
				System.out.print('\n');
			}
			bw.write(sb.toString());//save the string representation of the board
			bw.close();
		} catch (IOException e) {
			
		}
	}
	
//	public void makeFile(String name) {
//		try {
//
//		    File file = new File(name + ".txt");
//
//		    if (!file.exists()) {
//		        file.createNewFile();
//		    }
//
//		    FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		    try (BufferedWriter bw = new BufferedWriter(fw)) {
//		        bw.write(map, 0, board.length());
//		    }
//
//		    System.out.println("Map saved");
//
//		} catch (IOException e) {
//
//		}
//	}
}
