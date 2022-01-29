package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapInitializer {
    private Character[][] map;

    public MapInitializer(String filename){
        this.map = readMap(filename);
    }

    public Character[][] getMap() {
        return map;
    }

    Character[][] readMap(String fileName){

        List<String> lines = new ArrayList<>();
        int rows = 0;
        int columns = 0;

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                rows++;
            }
            columns = (int) Math.ceil((double) lines.get(0).length()/2);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return createMap(lines,rows,columns);
    }

    Character[][] createMap(List<String> lines, int rows, int columns){
        Character[][] map = new Character[rows][columns];

        int currentRow = 0;
        int currentColumn = 0;
        int indexOfChar = 0;

        for (String line : lines){
            //System.out.println(line);
            while (currentColumn < columns){
                map[currentRow][currentColumn] = line.charAt(indexOfChar);
                currentColumn++;
                indexOfChar = indexOfChar + 2;
            }

            currentColumn = 0;
            indexOfChar = 0;
            currentRow++;
        }
        return map;
    }
}
