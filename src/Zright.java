public class Zright extends blocks {
    public Zright(int[][]cords) {
        super(cords);

    }
    public  boolean rotate() {
        return GameManager.isRotatable();
    }
    public boolean decreseYvalue(int counter ) {

        if (counter%30 == 0) {
            return GameManager.decrease();
        }

        return  false;
    }

    public int[] getColour() {
        int[] num;
        num = new int[]{2,137,16};

        return  num;
    }
}
