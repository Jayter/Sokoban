package model;

/**
 * Created by Nicki on 28.01.2016.
 */
/**
 * This is a class for all objects that support collision logic.
 * That is player or boxes can`t move along them - they collide.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }
    /**
     * This method returns true if during moving current object in the "direction" direction
     * on FIELD_CELL_SIZE it will collide with "gameObject". Otherwise - returns false.
     * Collision is coincidence x and y coordinates.
     */
    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        boolean result = false;
        switch (direction)
        {
            case RIGHT:
                if(getX() + Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY())
                    result = true;
                break;
            case LEFT:
                if(getX() - Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY())
                    result = true;
                break;
            case UP:
                if(getX() == gameObject.getX() && getY() - Model.FIELD_CELL_SIZE == gameObject.getY())
                    result = true;
                break;
            case DOWN:
                if(getX() == gameObject.getX() && getY() + Model.FIELD_CELL_SIZE == gameObject.getY())
                    result = true;
        }
        return result;
    }
}
