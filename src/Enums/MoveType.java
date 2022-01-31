package src.Enums;

public enum MoveType {
    FORWARD,BASH,LEFT,RIGHT;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}