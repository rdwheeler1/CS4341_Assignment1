

package src;

public class Heuristics {
    int heuristicNumber;

    public Heuristics(int heuristicNumber){
        this.heuristicNumber = heuristicNumber;
    }

    public int heuristic(int absVert, int absHoriz){
        switch (this.heuristicNumber){
            case 1:
                return 0;
            case 2:
                return Math.min(absVert, absHoriz);
            case 3:
                return Math.max(absVert, absHoriz);
            case 4:
                return absHoriz + absVert;
            case 5:
                return (int)Math.sqrt((absVert*absVert)+(absHoriz*absHoriz)) + absHoriz + absVert;
            case 6:
                int multiplyDistance2 = absHoriz * absVert;
                return multiplyDistance2 * 3;
        }
        return 0;
    }
}
