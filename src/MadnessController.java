package src;

import java.awt.event.KeyEvent;
import java.util.Random;

public class MadnessController extends GameController{
    public MadnessController(Tetris tetris, PieceFactory pieceFactory, Random random){
        super(tetris, pieceFactory, random);
    }


    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    @Override
    public Piece createRandomTetrisPiece(){
        boolean isPiecePQPlus = (random.nextInt(10) >= NUM_PIECES_EASY) ? true : false;
        Piece piece;

        if (!isPiecePQPlus) {
            piece = super.createRandomTetrisPiece();
        }

        else {
            int rand = random.nextInt(ADDITIONAL_MEDIUM_PIECES);
            piece = pieceFactory.generatePiece(NUM_PIECES_EASY + rand);
        }

        return piece;
    }

    // Move piece according to game difficulty and keyEvent, see moveBlock inside Tetris class
    @Override
    public void moveBlock(Piece currentPiece, int keyEvent){
        switch (keyEvent) {
            case KeyEvent.VK_LEFT:
                currentPiece.left();
                break;
            case KeyEvent.VK_RIGHT:
                currentPiece.right();
                break;
            case KeyEvent.VK_DOWN:
                currentPiece.drop();
                break;
        }
    }

    // Get SlowDown speed according to current score and game difficulty
    @Override
    public int getCurrentSlowDown(int currentScore){
        int slowDownSimple = this.getCurrentSlowDown(currentScore);
        int slowDownMadness = random.nextInt(slowDownSimple, 2 * slowDownSimple + 1);

        return slowDownMadness;
    }
}
