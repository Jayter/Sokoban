package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nicki on 29.01.2016.
 */
 /**
  * This class is responsible for loading levels from a text file.
  */
public class LevelLoader
{   
    /**
     * This variable is responsible for path to file that contains levels description.
     */
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }
    /**
     * This method returns all game objects from the source file.
     */
    public GameObjects getLevel(int level)
    { 
        /**
         * The source file contains only sixty levels. 
         * If the parameter is greater, we should decrease it.
         */
        level = level % 60;
        level = (level == 0) ? 60 : level;
        
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        
        try
        {   
            FileReader fileReader = new FileReader(levels.toString());
            BufferedReader reader = new BufferedReader(fileReader);
            /**
             * Searching for the right level.
             */
            while (true)
            {
                String input = reader.readLine();
                if (("Level: " + level).equals(input))
                    break;
            }
            /**
             * Width and height variables are used in the loops.
             * They`re used for accurate calculating of object`s coordinates.
             */
            int width = Integer.parseInt(reader.readLine().split(" ")[2]);
            int height = Integer.parseInt(reader.readLine().split(" ")[2]);
            /**
             * Using loops to be sure that all objects have correct coordinates.
             */
            for(int y = 0; y < height; y++)
            {   
                int yCoord = y * Model.FIELD_CELL_SIZE + Model.FIELD_CELL_SIZE / 2;
                
                String in = reader.readLine();
                char[] array = in.toCharArray();
                
                for (int x = 0; x < width; x++)
                {   
                    int xCoord = x * Model.FIELD_CELL_SIZE + Model.FIELD_CELL_SIZE / 2;
                    
                    char character = array[x];
                    /**
                     * Source file contains next characters: '@' - player, '*' - box,
                     * '.' - home, 'X' - wall, '&' - box inside the home.
                     */
                    switch (character)
                    {
                        case '&':
                            boxes.add(new Box(xCoord, yCoord));
                            homes.add(new Home(xCoord, yCoord));
                            break;
                        case '@':
                            player = new Player(xCoord, yCoord);
                            break;
                        case 'X':
                            walls.add(new Wall(xCoord, yCoord));
                            break;
                        case '.':
                            homes.add(new Home(xCoord, yCoord));
                            break;
                        case '*':
                            boxes.add(new Box(xCoord, yCoord));
                    }
                }
            }
            reader.close();
            fileReader.close();
        }
        catch (IOException ignored){}
        return new GameObjects(walls, boxes, homes, player);
    }
}
