package src;

import java.util.Random;

public class EasyController extends GameController{
    public EasyController(Tetris tetris, Random random){
        super(tetris, random);
    }


    // create block according to game difficulty by calling Tetris.generateBlock(blockId)
    @Override
    public Piece createRandomTetrisBlock(){

        return new I(tetris);
    }

    // Move piece according to game difficulty and keyEvent, see moveBlock inside Tetris class
    @Override
    public void moveBlock(Piece currentPiece, int keyEvent){

    }
    // Get SlowDown speed according to current score and game difficulty
    @Override
    public int getCurrentSlowDown(int currentScore){

        return 5;
    }
}
