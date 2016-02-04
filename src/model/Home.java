package model;

import java.awt.*;

/**
 * Created by Nicki on 29.01.2016.
 */
/**
 * This class is responsible for places on the field
 * in which you need to move all the boxes.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLUE);
        int leftUpperCornerX = getX() - getWidth()/2;
        int leftUpperCornerY = getY() - getHeight()/2;

        graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}
