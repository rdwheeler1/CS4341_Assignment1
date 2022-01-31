package src;

public class Main {

    public static void main(String [] args) {
        MapInitializer mapInitializer = new MapInitializer(args[0]);
        Search search = new Search();
        search.AStar(mapInitializer.getMap());
    }

}
