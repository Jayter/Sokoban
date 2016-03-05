package view;


import controller.EventListener;
import model.Direction;
import model.GameObject;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * Created by Nicki on 27.01.2016.
 */
/**
 * This class is used for all game objects display.
 * It represents game field for all events.
 */
public class Field extends JPanel
{
    private View view;
    public Field(View view)
    {
        this.view = view;
        KeyHandler handler = new KeyHandler();
        view.addKeyListener(handler);
        view.setFocusable(true);
    }

    private EventListener eventListener;
    /**
     * Draws all objects.
     */
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> set = gameObjects.getAll();
        for(GameObject gameObject: set)
        {
            gameObject.draw(g);
        }
    }
    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }
    /**
     * This inner class is responsible for keystrokes handling.
     */
    public class KeyHandler extends KeyAdapter
    {
        /**
         * Handles keystrokes, calling corresponding methods of the eventListener.
         */
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
            }
        }
    }
}
