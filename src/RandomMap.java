package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RandomMap {
	int numRows;
	int numCols;
	String[][] map;
	File file;
	
	public RandomMap(int r, int c, String name) {
		this.numRows = r;
		this.numCols = c;
		this.map = new String[r][c];
		//change for each person
		this.file = new File("/Users/rdwheeler/Downloads/RandomMaps/" + name + ".txt");
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numCols; j++) {
					double min = 1;
					double max = 9;
					int num = (int) Math.floor(Math.random()*(max-min+1)+min);
					this.map[i][j] = String.valueOf(num);
					System.out.print(num + "\t");
//					if(j == numCols - 1) sb.append(map[i][j]+'\t');	//shouldn't tab last column?
//					else sb.append(map[i][j]);
					sb.append(map[i][j]).append("\t");
				}
				sb.append('\n');
				System.out.print('\n');
			}
			bw.write(sb.toString());//save the string representation of the board
			bw.close();
		} catch (IOException e) {
			
		}
	}
}
