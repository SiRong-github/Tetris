package src.GameController;

import src.TetrisPiece.Piece;

public interface IGameController {
    public Piece createRandomTetrisPiece();
    public Piece generatePiece(int blockId);
    public void moveBlock(Piece currentPiece, int keyEvent);
    public int getCurrentSlowDown(int currentScore);
}
