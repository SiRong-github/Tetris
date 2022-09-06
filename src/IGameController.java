package src;

public interface IGameController {
    public Piece createRandomTetrisBlock();
    public void moveBlock(Piece currentPiece, int keyEvent);
    public int getCurrentSlowDown(int currentScore);
}
