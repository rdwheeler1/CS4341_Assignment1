package src.Enums;

//4 move types the robot could make
public enum MoveType {
    FORWARD,BASH,LEFT,RIGHT;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}