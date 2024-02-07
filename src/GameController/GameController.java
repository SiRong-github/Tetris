package src.GameController;

import src.TetrisPiece.Piece;
import src.TetrisPiece.PieceFactory;
import src.utility.Tetris;

import java.awt.event.KeyEvent;
import java.util.Random;

public class GameController implements IGameController {
    protected final static int NUM_PIECES_EASY = 7;
    protected final static int ADDITIONAL_MEDIUM_PIECES = 3;

    protected Tetris tetris;
    protected Random random;
    protected PieceFactory pieceFactory;

    public GameController(Tetris tetris, PieceFactory pieceFactory, Random random){
        this.tetris = tetris;
        this.pieceFactory = pieceFactory;
        this.random = random;
    }

    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    public Piece createRandomTetrisPiece() {
        int piece = random.nextInt(NUM_PIECES_EASY);
        return pieceFactory.generatePiece(piece);
    }

    // delegate generate piece to pieceFactory
    public Piece generatePiece(int blockId){
        return pieceFactory.generatePiece(blockId);
    }

    // Move piece according to game difficulty and keyEvent, see moveBlock inside Tetris class
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
        }
    }

    // Get SlowDown speed according to current score and game difficulty
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
