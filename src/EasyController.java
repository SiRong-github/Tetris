// I removed the `createRandomTetrisBlock` method for EasyController.
// I removed the `moveBlock` method for EasyController.
// I removed the `getCurrentSlowDown` method for EasyController.

// I don't think there is a need for EasyController. It has the `base methods` that which all other
// controllers extend. Maybe EasyController should be the default GameController.

package src;

import java.awt.event.KeyEvent;
import java.util.Random;

public class EasyController extends GameController{
    public EasyController(Tetris tetris, Random random){
        super(tetris, random);
    }
}
