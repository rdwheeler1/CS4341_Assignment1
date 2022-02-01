package src;

public class Main {

    public static void main(String [] args) {
        MapInitializer mapInitializer = new MapInitializer(args[0]);
        int h = Integer.parseInt(args[1]);
        Search search = new Search(h);
        search.AStar(mapInitializer.getMap());
    }

}
