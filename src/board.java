    import processing.core.PApplet;
import java.util.ArrayList;
public class board extends PApplet  {
    public static board instance;
    public static ArrayList<blocks> pieces = new ArrayList<>();
    public static boolean hasPiecebeenchosen = false;
    public static int currentPiece;
    public static int holdNum;
    public static ArrayList<blocks> holdPiece = new ArrayList<>();
    int timer = 0;
    public static ArrayList<Integer> blocks = new ArrayList<>();

    public void settings() {
        for (int i = 0; i< 5; i++) {
            blocks.add((int) Math.floor(Math.random()*(7-1+1)+1));
        }
        size(1000, 1000);
        if (instance == null) {
            instance = this;
        }

    }
    public void draw() {
        background(220,220,220);
        drawBoard();
        drawPieces(pieces);
        showNextPieces();
      if (!hasPiecebeenchosen) {
          GameManager.ClearLine();
          GameManager.endGame();
          timer = 0;
          int[][] cords = detrminePiece(blocks.get(0));
          pieces.add(getObj(blocks.get(0), cords));
          currentPiece = blocks.get(0);
          blocks.remove(0);
          blocks.add((int) Math.floor(Math.random()*(7-1+1)+1));

          hasPiecebeenchosen = true;
      }
      else {
          timer +=1;


          GameManager.outline();

            if ((pieces.get(pieces.size()-1).decreseYvalue(timer))) {
              for (int i = 0; i < 4; i++) {
                  board.pieces.get(board.pieces.size() - 1).getCords()[1][i] += 50 ;
              }

          }


      }
    }
    public void showNextPieces() {
        ArrayList<blocks> nextPieces = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int[][] cords = instance.detrminePieceNext(blocks.get(i));
            nextPieces.add(instance.getObj(blocks.get(i), cords));
            for (int ii = 0; ii < 4; ii++) {
                nextPieces.get(i).getCords()[0][ii] += 375;
                nextPieces.get(i).getCords()[1][ii] += 50 +150*i;
            }
        }
        if (holdPiece.size() != 0) {
            int[][] cords = instance.detrminePieceNext(holdNum);
            blocks hold = instance.getObj(holdNum, cords);
            int[] num = hold.getColour();
            for (int i = 0; i < 4; i++) {
                hold.getCords()[0][i] -= 275;
                hold.getCords()[1][i] += 50;
                                    instance.fill(num[0], num[1], num[2]);
                                    instance.rect(hold.getCords()[0][i] , hold.getCords()[1][i] , 25, 25);
            }
        }
        for (int i =0; i< 4; i++) {
            int[] num;
            for (blocks piece : nextPieces) {
                num  = piece.getColour();
                for (int ii = 0; ii < 4; ii++) {

                    instance.fill(num[0], num[1], num[2]);
                    instance.rect(piece.getCords()[0][ii] , piece.getCords()[1][ii] , 25, 25);
                }
            }
        }


    }
    public int[][] detrminePieceNext(int num) {
        int[][] cords = { {375, 400, 425, 450}, {50,50,50,50} };
        switch (num) {
            case 2 -> cords = new int[][]{{400, 425, 400, 425}, {50, 50, 75, 75}}; // square
            case 5 -> cords = new int[][]{{375, 400,400, 425}, {75, 75, 50, 75}}; //T piece
            case 6 -> cords = new int[][]{{375, 400,400, 425}, {75, 75, 50, 50}}; // z Right
            case 7 -> cords = new int[][]{{375, 400,400, 425}, {50, 50, 75, 75}}; //z Left
            case 4 -> cords = new int[][]{{375, 400,425, 425}, {75, 75, 75, 50}}; //J right
            case 3 -> cords = new int[][]{{375, 375,400, 425}, {50, 75, 75, 75}};
        }
        return cords;
    }

    public int[][] detrminePiece(int num) {
        int[][] cords = { {375, 425, 475, 525}, {50,50,50,50} };
        switch (num) {
            case 2 -> cords = new int[][]{{425, 475, 425, 475}, {50, 50, 100, 100}}; // square
            case 5 -> cords = new int[][]{{375, 425,425, 475}, {100, 100, 50, 100}}; //T piece
            case 6 -> cords = new int[][]{{375, 425,425, 475}, {100, 100, 50, 50}}; // z Right
            case 7 -> cords = new int[][]{{375, 425,425, 475}, {50, 50, 100, 100}}; //z Left
            case 4 -> cords = new int[][]{{375, 425,475, 475}, {100, 100, 100, 50}}; //J right
            case 3 -> cords = new int[][]{{375, 375,425, 475}, {50, 100, 100, 100}};
        }
        return cords;
    }
    public blocks getObj(int num, int[][] cords) {
        blocks b = new line(cords);


        switch (num) {
            case 2 -> b = new square(cords);
            case 5 -> b = new Tpiece(cords);
            case 6 -> b = new Zright(cords);
            case 7 -> b = new Zleft(cords);
            case 4 -> b = new Jright(cords);
            case 3 -> b = new Jleft(cords);
        }


        return b;
    }
    public  void drawBoard() {
        for (int ii = 0; ii < 18; ii++) {

            for (int i = 0; i < 10; i++) {
                instance.fill(255,255,255);
                instance.rect(225 + i * 50, 50+ii*50, 50, 50);
            }

        }
        instance.rect(75, 50 , 150, 150);
        for (int i = 0; i < 5; i++) {
            instance.rect(225 + 10 *50, 50 + i*150, 150, 150);

        }
    }

    public void drawPieces(ArrayList<blocks> pieces) {
        board.instance.drawBoard();
        int[] num;
        for (blocks piece : pieces) {
            num  = piece.getColour();
            for (int ii = 0; ii < 4; ii++) {
                fill(num[0], num[1], num[2]);
                rect(piece.getCords()[0][ii], piece.getCords()[1][ii], 50, 50);
            }
        }
    }
    public void keyPressed() {
        int[][] newList = new int[2][4];
        for (int i = 0; i < 4; i++) {
            newList[0][i] = board.pieces.get(board.pieces.size() - 1).getCords()[0][i];
            newList[1][i] = board.pieces.get(board.pieces.size() - 1).getCords()[1][i];
        }
        if (keyCode == LEFT) {
            if (!GameManager.decrease(newList, -50)) {
                for (int i = 0; i < 4; i++) {
                    pieces.get(pieces.size() - 1).getCords()[0][i] -= 50;
                }
            }
        }
        if (keyCode == RIGHT) {
            if (!GameManager.decrease(newList, 50)) {
                for (int i = 0; i < 4; i++) {
                    pieces.get(pieces.size() - 1).getCords()[0][i] += 50;
                }
            }
        }
        if (keyCode == UP) {
            if (pieces.get(pieces.size()-1).rotate()) {
                GameManager.ActualRotate();
            }
        }

        if (key == ' ') {
            GameManager.hardDrop();
        }
        if (keyCode == SHIFT) {
            if (holdPiece.size() == 0) {
                holdPiece.add(pieces.get(pieces.size()-1));
                pieces.remove(pieces.size()-1);
                GameManager.resetCords();
                holdNum = currentPiece;
                hasPiecebeenchosen = false;
            } else{
                holdPiece.add(pieces.get(pieces.size()-1));
                GameManager.resetCords();
                pieces.set(pieces.size()-1, holdPiece.get(0));
                holdPiece.remove(0);
            }

        }
        if (keyCode == DOWN) {
            for (int i =0; i<4; i++) {
                newList[1][i] += 50;
            }
            if (!GameManager.decrease(newList, 0)) {
                for (int i =0; i<4; i++) {
                    pieces.get(pieces.size()-1).getCords()[1][i] += 50;
                }
            }
        }
    }
}
