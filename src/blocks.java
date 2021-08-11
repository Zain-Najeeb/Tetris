import java.util.ArrayList;

public abstract class blocks {
    private int[][] cords;

    public blocks(int[][]cords) {
        this.cords = cords;
    }
    public int[][] getCords() {
        return cords;
    }
    public abstract int[] getColour();
    public abstract boolean rotate();
    public abstract boolean decreseYvalue(int counter);

}
