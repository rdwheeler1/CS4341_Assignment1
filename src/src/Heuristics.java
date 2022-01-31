package src;

public class Heuristics {

    public Heuristics(){}

    public int heuristic1(int absVert, int absHoriz) {
        return 0;
    }

    public int heuristic2(int absVert, int absHoriz) {
        if(absVert > absHoriz) {
            return absHoriz;
        } else {
            return absVert;
        }
    }

    public int heuristic3(int absVert, int absHoriz) {
        if(absVert > absHoriz) {
            return absVert;
        } else {
            return absHoriz;
        }
    }

    public int heuristic4(int absVert, int absHoriz) {
        int totalDistance = absHoriz + absVert;
        return totalDistance;
    }

    public int heuristic5(int absVert, int absHoriz) {
        int multiplyDistance = absHoriz * absVert;
        return multiplyDistance;
    }

    public int heuristic6(int absVert, int absHoriz) {
        return heuristic5(absVert, absHoriz) * 3;
    }
}
