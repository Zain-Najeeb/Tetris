public class square extends blocks {
    public square(int[][]cords) {
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
        num = new int[]{251,255,12};

        return  num;
    }
}
