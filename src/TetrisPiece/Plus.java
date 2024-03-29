package src.TetrisPiece;

import ch.aplu.jgamegrid.*;
import src.utility.Tetris;
import src.utility.TetroBlock;

public class Plus extends Piece {
    /*
     * Initializing blocks for + piece.
     */
    Plus(Tetris tetris)
    {
        super(tetris, PieceType.Plus, "+");
        this.r = new Location[5][4];
        // rotId 0
        r[0][0] = new Location(new Location(0, 0));
        r[1][0] = new Location(new Location(-1, 1));
        r[2][0] = new Location(new Location(0, 1));
        r[3][0] = new Location(new Location(1, 1));
        r[4][0] = new Location(new Location(0,2));
        // rotId 1
        r[0][1] = new Location(new Location(0, 0));
        r[1][1] = new Location(new Location(-1, 1));
        r[2][1] = new Location(new Location(0, 1));
        r[3][1] = new Location(new Location(1, 1));
        r[4][1] = new Location(new Location(0,2));
        // rotId 2
        r[0][2] = new Location(new Location(0, 0));
        r[1][2] = new Location(new Location(-1, 1));
        r[2][2] = new Location(new Location(0, 1));
        r[3][2] = new Location(new Location(1, 1));
        r[4][2] = new Location(new Location(0,2));
        // rotId 3
        r[0][3] = new Location(new Location(0, 0));
        r[1][3] = new Location(new Location(-1, 1));
        r[2][3] = new Location(new Location(0, 1));
        r[3][3] = new Location(new Location(1, 1));
        r[4][3] = new Location(new Location(0,2));

        for (int i = 0; i < r.length; i++)
            blocks.add(new TetroBlock(blockId, r[i]));
    }
}
