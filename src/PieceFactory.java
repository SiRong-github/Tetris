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
            case Piece.PieceType.I -> new I(tetris);
            case Piece.PieceType.J -> new J(tetris);
            case Piece.PieceType.L -> new L(tetris);
            case Piece.PieceType.O -> new O(tetris);
            case Piece.PieceType.S -> new S(tetris);
            case Piece.PieceType.T -> new T(tetris);
            case Piece.PieceType.Z -> new Z(tetris);
            case Piece.PieceType.P -> new P(tetris);
            case Piece.PieceType.Q -> new Q(tetris);
            case Piece.PieceType.Plus -> new Plus(tetris);
            default -> null;
        };
        return t;
    }
}
