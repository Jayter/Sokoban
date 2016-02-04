package model;

import java.awt.*;

/**
 * Created by Nicki on 27.01.2016.
 */
 /**
  * This is a base class for all the objects of the game.
  */
public abstract class GameObject
{
    /**
     * Each object has it`s coordinates(x,y), width and height for correct display.
     * All variables are encapsulated.
     */
    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
        width = Model.FIELD_CELL_SIZE;
        height = Model.FIELD_CELL_SIZE;
    }
    
    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
    /**
     * This method is used to display object on the graphic component by the graphic context.
     * As a parameter it gets graphic context object, implemented by jawa.awt.graphics subclasses.
     * Each base class has to implement this method.
     */
    public abstract void draw(Graphics graphics);
}
