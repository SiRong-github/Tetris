package src;

public class PieceFactory {
    /*
    Author: Xiaojiang Zheng
    Create Piece according to piece ID for current Tetris game.
    Used by game controller.
     */

    private final Tetris tetris;
    public PieceFactory(Tetris tetris){
        this.tetris = tetris;
    }

    public Piece generatePiece(int blockId){
        Piece t = switch (blockId) {
            case 0 -> new I(tetris);
            case 1 -> new J(tetris);
            case 2 -> new L(tetris);
            case 3 -> new O(tetris);
            case 4 -> new S(tetris);
            case 5 -> new T(tetris);
            case 6 -> new Z(tetris);
            case 7 -> new P(tetris);
            case 8 -> new Q(tetris);
            case 9 -> new Plus(tetris);
            default -> null;
        };
        return t;
    }
}
