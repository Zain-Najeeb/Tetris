public class GameManager {
    static boolean hardDrop = false;
    public static boolean decrease(int[][] newList, int numx) {

        int firstIteration = -1;
        if (board.pieces.size() -1 == 0) {
            firstIteration = 0;
        }

        for (int i = 0; i < board.pieces.size() + firstIteration; i++) {
            for (int j = 0; j < 4; j++) {
                for (int ii = 0; ii < 4; ii++) {
                    if ( ( board.pieces.size()- 1 !=0 &&(newList[1][j]  == board.pieces.get(i).getCords()[1][ii] && newList[0][j] + numx == board.pieces.get(i).getCords()[0][ii]) ) || newList[1][j] >= 950 ||  newList[0][j] + numx <= 175 ||newList[0][j] + numx >=725 || newList[1][j] < 50 || ( hardDrop &&board.pieces.size()- 1 !=0 &&( (newList[1][j]  >= board.pieces.get(i).getCords()[1][ii] && board.pieces.get(board.pieces.size()-1).getCords()[1][j] <= board.pieces.get(i).getCords()[1][ii])&& newList[0][j] + numx == board.pieces.get(i).getCords()[0][ii]))  ) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isRotatable() {
        int pivotx = board.pieces.get(board.pieces.size() - 1).getCords()[0][2];
        int pivoty = board.pieces.get(board.pieces.size() - 1).getCords()[1][2];
        int[][] original = new int[2][4];
        int[][] newList = new int[2][4];
        for (int i = 0; i < 4; i++) {
            original[0][i] = board.pieces.get(board.pieces.size() - 1).getCords()[0][i];
            original[1][i] = board.pieces.get(board.pieces.size() - 1).getCords()[1][i];
        }
        for (int i = 0; i < 4; i++) {
            newList[0][i] = (original[1][i] + pivotx - pivoty);
            newList[1][i] = (pivotx + pivoty - original[0][i]  );
        }
        return !decrease(newList,0);
    }
    public static void ActualRotate() {
        int pivotx = board.pieces.get(board.pieces.size() - 1).getCords()[0][2];
        int pivoty = board.pieces.get(board.pieces.size() - 1).getCords()[1][2];
        int[][] original = new int[2][4];

        for (int i = 0; i < 4; i++) {
            original[0][i] = board.pieces.get(board.pieces.size() - 1).getCords()[0][i];
            original[1][i] = board.pieces.get(board.pieces.size() - 1).getCords()[1][i];
        }
        for (int i = 0; i < 4; i++) {
            board.pieces.get(board.pieces.size()-1).getCords()[0][i] =(original[1][i] + pivotx - pivoty);
            board.pieces.get(board.pieces.size()-1).getCords()[1][i] = (pivotx + pivoty - original[0][i]  );
        }
    }

    public static void ClearLine() {
        int counter;
        for (int i = 0; i < 18; i ++) {

            counter = 0;
            for (int j = 0; j < 10; j++) {

                for (int ii = 0; ii < board.pieces.size(); ii++) {
                    for (int jj = 0; jj < 4; jj ++) {
                        if (board.pieces.get(ii).getCords()[0][jj] ==  225 + j * 50 && board.pieces.get(ii).getCords()[1][jj] == 50+i*50) {

                            counter++;
                        }
                    }
                }
                if (counter == 10) {
                    UpdatePosition(50+i*50);
                }
            }
        }
    }
    public static void UpdatePosition(int above) {
        for (int i = 0; i < board.pieces.size(); i ++) {
            for (int ii =0; ii <4; ii++) {
                if (board.pieces.get(i).getCords()[1][ii] == above) {
                    board.pieces.get(i).getCords()[1][ii] = 9999;
                }
                if (board.pieces.get(i).getCords()[1][ii] < above) {
                    board.pieces.get(i).getCords()[1][ii] += 50;
                }
            }
        }
        int counter;
        for (int i = 0; i < board.pieces.size(); i++) {
            counter = 0;
            for (int ii =0; ii <4; ii++) {
                if (board.pieces.get(i).getCords()[1][ii] == 9999) {
                    counter ++;
                }
                if (counter == 4) {
                    board.pieces.remove(i);
                }
            }

        }
    }
    public static boolean decrease() {

        int[][] newList = new int[2][4];
        for (int i = 0; i < 4; i++) {
            newList[0][i] = board.pieces.get(board.pieces.size() - 1).getCords()[0][i];
            newList[1][i] = board.pieces.get(board.pieces.size() - 1).getCords()[1][i] + 50 ;
        }

        if (GameManager.decrease(newList, 0))  {
            board.hasPiecebeenchosen = false; return false;
        }
        if (board.pieces.size()-1 == 0) {
            for (int i = 0; i < 4; i++) {
                if ((board.pieces.get(board.pieces.size()-1).getCords()[1][i] + 50 == 950)) {
                    board.hasPiecebeenchosen = false;
                    return false;
                }
            }
        }
        return true;
    }
    public static void hardDrop() {
        int [][] newList = new int[2][4];
        hardDrop = true;
     for (int i = 18; i > 0; i--) {

         for (int j = 0; j < 4; j++) {
             newList[0][j] = board.pieces.get(board.pieces.size()-1).getCords()[0][j];
             newList[1][j] = board.pieces.get(board.pieces.size()-1).getCords()[1][j] +50+i*50;
         }

         if (!decrease(newList, 0)) {
             for (int ii = 0; ii < 4; ii++) {

                 board.pieces.get(board.pieces.size()-1).getCords()[1][ii] += 50+i*50;
             }
             hardDrop = false;
             board.hasPiecebeenchosen = false;
             break;
         }
     }
     hardDrop = false;
    }
    public static void outline() {
        board b = board.instance;
        int[][] newList = new int[2][4];
        int[] num;
        hardDrop = true;
        num  = board.pieces.get(board.pieces.size()-1).getColour();
        for (int i = 18; i > 0; i--) {

            for (int j = 0; j < 4; j++) {
                newList[0][j] = board.pieces.get(board.pieces.size() - 1).getCords()[0][j];
                newList[1][j] = board.pieces.get(board.pieces.size() - 1).getCords()[1][j] + 50 + i * 50;
            }


            if (!decrease(newList, 0)) {
                for (int ii = 0; ii < 4; ii ++) {
                    b.noFill();
                    b.stroke(num[0], num[1], num[2]);
                    b.strokeWeight(3);
                    b.rect(newList[0][ii],newList[1][ii], 50, 50);
                }
                b.strokeWeight(1);

                b.stroke(0);
                break;

            }
        }
    hardDrop = false;

    }
    public static void resetCords() {

        if (board.holdPiece.size() > 1) {

            int[][] cords2 = board.instance.detrminePiece(board.holdNum);
            board.holdPiece.set(0, board.instance.getObj(board.holdNum, cords2));
            int num = board.holdNum;
            board.holdNum = board.currentPiece;
            board.currentPiece = num;
        } else {
            int[][] cords = board.instance.detrminePiece(board.currentPiece);
            board.holdPiece.set(0, board.instance.getObj(board.currentPiece, cords));
        }

    }
    public static void endGame() {
        int[][] cords = board.instance.detrminePiece(2);
        for (int i =0; i < board.pieces.size(); i++) {
            for (int j =0; j <4; j++) {
                if (board.pieces.get(i).getCords()[0][j] == cords[0][j] && board.pieces.get(i).getCords()[1][j] == cords[1][j]) {
                    System.exit(0);
                }
            }
        }
    }
}
