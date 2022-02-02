package src;

public class Main {

    public static void main(String [] args) {
        MapInitializer mapInitializer = new MapInitializer(args[0]);
        int h = Integer.parseInt(args[1]);
        Search search = new Search(h);
        //final long startTime = System.nanoTime();

        search.AStar(mapInitializer.getMap());

        //final long duration = System.nanoTime() - startTime;
        //System.out.println(duration);
        //System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
    }

}
