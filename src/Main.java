package src;


public class Main {

    public static void main(String [] args) {
    	try {
	        MapInitializer mapInitializer = new MapInitializer(args[0]);
	        int h = Integer.parseInt(args[1]);
	        if(h > 6 || h < 1) throw new Exception("Heuristic out of bounds");
	        Search search = new Search(h);
	        //final long startTime = System.nanoTime();
	
	        search.AStar(mapInitializer.getMap());
	
	        //final long duration = System.nanoTime() - startTime;
	        //System.out.println(duration);
	        //System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

}
