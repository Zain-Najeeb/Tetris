public class Jright extends blocks{
    public Jright(int[][]cords) {
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
        num = new int[]{255,165,0};

        return  num;
    }
}

