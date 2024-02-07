package src.TetrisPiece;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;
import src.utility.Tetris;
import src.utility.TetroBlock;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

public abstract class Piece extends Actor{
    /*
     * Bring original code from I, J etc. into this super class.
     */

    public static class PieceType{
        public static final int I = 0;
        public static final int J = 1;
        public static final int L = 2;
        public static final int O = 3;
        public static final int S = 4;
        public static final int T = 5;
        public static final int Z = 6;
        public static final int P = 7;
        public static final int Q = 8;
        public static final int Plus = 9;
    }

    protected Location[][] r;

    protected final int blockId;
    protected final String blockName;

    protected Piece(Tetris tetris, int blockId, String blockName)
    {
        super();
        this.tetris = tetris;
        this.blockId = blockId;
        this.blockName = blockName;
    }

    protected Tetris tetris;
    private boolean isStarting = true;
    protected int rotId = 0;
    private int nb;
    protected ArrayList<TetroBlock> blocks = new ArrayList<TetroBlock>();
    private Actor nextTetrisBlock = null;
    private String autoBlockMove = "";
    private int autoBlockIndex = 0;
    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    // The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
    public void act()
    {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc =
                        new Location(getX() + a.getRelLoc(0).x, getY() + a.getRelLoc(0).y);
                gameGrid.addActor(a, loc);
            }
            isStarting = false;
            nb = 0;
        } else
        {
            if (nb >= blocks.size() && canAutoPlay())
                autoMove();

            setDirection(90);
            if (nb == 1)
                nextTetrisBlock = tetris.createRandomTetrisPiece();
            if (!advance())
            {
                if (nb == 0)  // Game is over when tetrisBlock cannot fall down
                    tetris.gameOver();
                else
                {
                    setActEnabled(false);
                    gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
                    tetris.setCurrentTetrisBlock(nextTetrisBlock);
                }
            }
            nb++;
        }
    }

    // Based on the input in the properties file, the block can move automatically
    private void autoMove() {
        String moveString = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);

        int keyEvent;

        switch (moveString) {
            case "L":
                keyEvent = KeyEvent.VK_LEFT;
                this.tetris.getGameController().moveBlock(this, keyEvent);
                break;
            case "R":
                keyEvent = KeyEvent.VK_RIGHT;
                this.tetris.getGameController().moveBlock(this, keyEvent);
                break;
            case "T":
                keyEvent = KeyEvent.VK_UP;
                this.tetris.getGameController().moveBlock(this, keyEvent);
                break;
            case "D":
                keyEvent = KeyEvent.VK_DOWN;
                this.tetris.getGameController().moveBlock(this, keyEvent);
                break;
        }

        autoBlockIndex++;
    }

    // Check if the block can be played automatically based on the properties file
    private boolean canAutoPlay() {
        if (autoBlockMove != null && !autoBlockMove.equals("")) {
            if (autoBlockMove.length() > autoBlockIndex) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void display(GameGrid gg, Location location)
    {
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(location.x + a.getRelLoc(0).x, location.y + a.getRelLoc(0).y);
            gg.addActor(a, loc);
        }
    }

    // Actual actions on the block: move the block left, right, drop and rotate the block
    public void left()
    {
        if (isStarting)
            return;
        setDirection(180);
        advance();
    }

    public void right()
    {
        if (isStarting)
            return;
        setDirection(0);
        advance();
    }

    public void rotate()
    {
        if (isStarting)
            return;

        int oldRotId = rotId; // Save it
        rotId++;
        if (rotId == 4)
            rotId = 0;

        if (canRotate(rotId))
        {
            for (TetroBlock a : blocks)
            {
                Location loc = new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
                a.setLocation(loc);
            }
        }
        else
            rotId = oldRotId;  // Restore

    }

    private boolean canRotate(int rotId)
    {
        // Check for every rotated tetroBlock within the tetrisBlock
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
            if (!gameGrid.isInGrid(loc))  // outside grid->not permitted
                return false;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(loc, TetroBlock.class));
            if (blocks.contains(block))  // in same tetrisBlock->skip
                break;
            if (block != null)  // Another tetroBlock->not permitted
                return false;
        }
        return true;
    }

    public void drop()
    {
        if (isStarting)
            return;
        setSlowDown(0);
    }

    // Logic to check if the block has been removed (as winning a line) or drop to the bottom
    private boolean advance()
    {
        boolean canMove = false;
        for (TetroBlock a: blocks) {
            if (!a.isRemoved()) {
                canMove = true;
            }
        }
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            if (!gameGrid.isInGrid(a.getNextMoveLocation()))
            {
                canMove = false;
                break;
            }
        }

        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(a.getNextMoveLocation(),
                            TetroBlock.class));
            if (block != null && !blocks.contains(block))
            {
                canMove = false;
                break;
            }
        }

        if (canMove)
        {
            move();
            return true;
        }
        return false;
    }

    // Override Actor.setDirection()
    public void setDirection(double dir)
    {
        super.setDirection(dir);
        for (TetroBlock a : blocks)
            a.setDirection(dir);
    }

    // Override Actor.move()
    public void move()
    {
        if (isRemoved())
            return;
        super.move();
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                break;
            a.move();
        }
    }

    // Override Actor.removeSelf()
    public void removeSelf()
    {
        super.removeSelf();
        for (TetroBlock a : blocks)
            a.removeSelf();
    }

    public String toString() {
        return "For testing, do not change: Block: " + blockName + ". Location: " + blocks + ". Rotation: " + rotId;
    }

    public int getBlockId(){return blockId;}
    public String getBlockName() {return blockName;}
}
