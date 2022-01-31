

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
                return absHoriz * absVert;
            case 6:
                int multiplyDistance2 = absHoriz * absVert;
                return multiplyDistance2 * 3;
        }
        return 0;
    }

//    public int heuristic1(int absVert, int absHoriz) {
//        return 0;
//    }
//
//    public int heuristic2(int absVert, int absHoriz) {
//        if(absVert > absHoriz) {
//            return absHoriz;
//        } else {
//            return absVert;
//        }
//    }
//
//    public int heuristic3(int absVert, int absHoriz) {
//        if(absVert > absHoriz) {
//            return absVert;
//        } else {
//            return absHoriz;
//        }
//    }
//
//    public int heuristic4(int absVert, int absHoriz) {
//        int totalDistance = absHoriz + absVert;
//        return totalDistance;
//    }
//
//    public int heuristic5(int absVert, int absHoriz) {
//        int multiplyDistance = absHoriz * absVert;
//        return multiplyDistance;
//    }
//
//    public int heuristic6(int absVert, int absHoriz) {
//        return heuristic5(absVert, absHoriz) * 3;
//    }
}