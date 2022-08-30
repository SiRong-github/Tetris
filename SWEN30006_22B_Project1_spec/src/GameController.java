package src;

import ch.aplu.jgamegrid.*;

import java.util.Random;

public abstract class GameController {

    protected Tetris tetris;
    protected Random random = new Random(0);
    public GameController(Tetris tetris, Random random){
        this.tetris = tetris;
        this.random = random;
    }

    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    public abstract Piece createRandomTetrisBlock();

    // Move piece according to game difficulty and keyEvent, see moveBlock inside Tetris class
    public abstract void moveBlock(Piece currentPiece, int keyEvent);
    // Get SlowDown speed according to current score and game difficulty
    public abstract int getCurrentSlowDown(int currentScore);

}
