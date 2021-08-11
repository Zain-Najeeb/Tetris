public class line extends blocks{

    public line(int[][]cords) {
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
        num = new int[]{0,255,255};

        return  num;
    }

}
