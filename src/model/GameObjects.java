package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicki on 29.01.2016.
 */
/**
 * This class is used to store all objects of the game.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }
    /**
     * This method returns all game objects that current storage contains.
     */
    public Set<GameObject> getAll()
    {
        Set<GameObject> result = new HashSet<>();
        result.addAll(walls);
        result.addAll(boxes);
        result.addAll(homes);
        result.add(player);
        return result;
    }
}
