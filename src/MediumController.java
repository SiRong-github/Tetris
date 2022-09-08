// I removed the `moveBlock` method for MediumController.

package src;

import java.util.Random;

public class MediumController extends GameController{
    private final static double FASTER_FACTOR = 0.2;

    public MediumController(Tetris tetris, PieceFactory pieceFactory, Random random){
        super(tetris, pieceFactory, random);
    }

    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    @Override
    public Piece createRandomTetrisPiece() {
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

    // Get SlowDown speed according to current score and game difficulty
    @Override
    public int getCurrentSlowDown(int currentScore){

        double slowDown = 5 - (5 * FASTER_FACTOR);

        if (currentScore > 50) slowDown = 0;
        else if (currentScore > 40) slowDown = 1 - (1 * FASTER_FACTOR);
        else if (currentScore > 30) slowDown = 2 - (2 * FASTER_FACTOR);
        else if (currentScore > 20) slowDown = 3 - (3 * FASTER_FACTOR);
        else if (currentScore > 10) slowDown = 4 - (4 * FASTER_FACTOR);

        return (int) slowDown;
    }
}
