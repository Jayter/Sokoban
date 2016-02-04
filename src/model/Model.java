package model;


import controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Nicki on 27.01.2016.
 */
public class Model
{   
    /**
     * This variable represents standard  field cell size. 
     * All game objects will occupy one cell of the playing field.
     * This variable is used for calculation of movements and collisions.
     * It`s used only inside model package, so it has default access modifier.
     */
    static final int FIELD_CELL_SIZE = 20;
    /**
     * GameObjects variable stores all game objects.
     */
    private GameObjects gameObjects;
    /**
     * This variable is responsible for currentLevel.
     */
    private int currentLevel = 1;
    /**
     * Files "levels.txt" is situated in the "res" package.
     */
    private LevelLoader levelLoader = new LevelLoader(Paths.get("src\\res\\levels.txt"));

    private EventListener eventListener;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }
    
    public GameObjects getGameObjects()
    {
        return gameObjects;
    }
    /**
     * This method receives new objects of the specified level and sets them into gameObjects variable.
     */
    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }
    /**
     * This method restarts current level.
     */
    public void restart()
    {
        restartLevel(currentLevel);
    }
    /**
     * This method starts next level.
     */
    public void startNextLevel()
    {
        restartLevel(++currentLevel);
    }
    /**
     * This method is responsible fore Player`s movements in "direction" direction.
     */
    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        
        if(checkWallCollision(player, direction))
            return;
        if(checkBoxCollision(direction))
            return;

        switch (direction)
        {
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
        }
        checkCompletion();
    }
    /**
     * This method checks the collision with the wall.
     * It returns true if the collision happens during
     * movement gameObject in direction and false otherwise.
     */
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for(Wall wall: gameObjects.getWalls())
        {
            if(gameObject.isCollision(wall,direction))
                return true;
        }
        return false;
    }
    /**
     * This method checks collision with the boxes.
     * It returns true if the player can`t be moved toward direction
     * (there is: or a box, followed by a wall, or box followed by another box).
     * 
     * Returns false, if the player can be moved toward direction (there is: 
     * either a free cell, or a house, or box, for which free cell or home).
     * 
     * In this way if there is a box that can be moved, then it moves the box to the new location.
     */
    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        for(Box box: gameObjects.getBoxes())
        {
            if (player.isCollision(box, direction))
            {
                if (checkWallCollision(box, direction))
                    return true;
                for(Box box1: gameObjects.getBoxes())
                {
                    if(box.isCollision(box1,direction))
                        return true;
                }
                switch (direction)
                {
                    case RIGHT:
                        box.move(FIELD_CELL_SIZE, 0);
                        break;
                    case LEFT:
                        box.move(-FIELD_CELL_SIZE, 0);
                        break;
                    case DOWN:
                        box.move(0, FIELD_CELL_SIZE);
                        break;
                    case UP:
                        box.move(0, -FIELD_CELL_SIZE);
                }
            }
        }
        return false;
    }
    /**
     * This method checks if current level is passed
     * (coordinates of all boxes correspond to the coordinates houses.)
     */
    public void checkCompletion()
    {
        int count = 0;
        for(Box box: gameObjects.getBoxes())
        {
            for(Home home: gameObjects.getHomes())
            {
                if(home.getX() == box.getX() && home.getY() == box.getY())
                    count++;
            }
        }
        
        if(count == gameObjects.getBoxes().size())
            eventListener.levelCompleted(currentLevel);
    }
}
