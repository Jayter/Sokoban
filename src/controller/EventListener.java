package controller;

import model.Direction;

/**
 * Created by Nicki on 29.01.2016.
 */
 /**
  * This interface must be implemented by the class that will handle events.
  * A class that will generate events will call methods of this interface.
  */
public interface EventListener
{ 
    /**
     * Moves object (Player) in "direction" direction.
     */
    void move(Direction direction);
    /**
     * Restarts current level.
     */
    void restart();
    /**
     * Starts next level.
     */
    void startNextLevel();
    /**
     * Informs view that current level is completed.
     */
    void levelCompleted(int level);
}
