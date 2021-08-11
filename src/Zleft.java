public class Zleft extends blocks {
    public Zleft(int[][]cords) {
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
        num = new int[]{200,0,0};

        return  num;
    }
}
