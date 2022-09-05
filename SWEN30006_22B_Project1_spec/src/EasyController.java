package src;

import java.awt.event.KeyEvent;
import java.util.Random;

public class EasyController extends GameController{
    private final static int NUM_PIECES = 7;

    public EasyController(Tetris tetris, Random random){
        super(tetris, random);
    }

    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    @Override
    public Piece createRandomTetrisBlock() {
        int piece = random.nextInt(NUM_PIECES);
        switch (piece) {
            case 0:
                return new I(tetris);
            case 1:
                return new J(tetris);
            case 2:
                return new L(tetris);
            case 3:
                return new O(tetris);
            case 4:
                return new S(tetris);
            case 5:
                return new T(tetris);
            default:
                return new Z(tetris);
        }
    }

    // Move piece according to game difficulty and keyEvent, see moveBlock inside Tetris class
    @Override
    public void moveBlock(Piece currentPiece, int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.VK_UP:
                currentPiece.rotate();
                break;
            case KeyEvent.VK_LEFT:
                currentPiece.left();
                break;
            case KeyEvent.VK_RIGHT:
                currentPiece.right();
                break;
            case KeyEvent.VK_DOWN:
                currentPiece.drop();
                break;
            default:
                return;
        }

    }

    // Get SlowDown speed according to current score and game difficulty
    @Override
    public int getCurrentSlowDown(int currentScore) {
        int slowDown = 5;
        
        if (currentScore > 50) slowDown = 0;
        else if (currentScore > 40) slowDown = 1;
        else if (currentScore > 30) slowDown = 2;
        else if (currentScore > 20) slowDown = 3;
        else if (currentScore > 10) slowDown = 4;

        return slowDown;
    }
}
