import processing.core.PApplet;

public class Main {
    public static void main(String[] args) {
        String[] processingArgs = {"Tetris"};
        board Sketch = new board();
        PApplet.runSketch(processingArgs, Sketch);

    }

}
