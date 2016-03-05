package view;


import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;
/**
 * This class is responsible for view of our program.
 */
public class View extends JFrame
{   
    /**
     * Object of our class will interact with Controller and Field objects.
     */
    private Controller controller;
    private Field field;

    public View(Controller controller)
    {
        this.controller = controller;
    }
    /**
     * Initializes view.
     */
    public void init()
    {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }
    
    public void setEventListener(EventListener eventListener)
    {
        field.setEventListener(eventListener);
    }
    /**
     * Updates view repainting the game field.
     */
    public void update()
    {
        field.repaint();
    }
    
    public GameObjects getGameObjects()
    {
        return controller.getGameObjects();
    }
    /**
     * Informs user that current level is completed, starts new level.
     */
    public void completed(int level)
    {
        update();
        JOptionPane.showMessageDialog(this, "Поздравляем! Вы прошли "+ level +" уровень!");
        controller.startNextLevel();
    }
}
