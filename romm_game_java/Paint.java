import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * This class is part of the "Haunted House" application. 
 * "Haunted House" is a very simple, text based adventure game.  
 *  
 * Draw class is for creating the map and other graphical elements of the game
 * 1. "man"
 * 2. "energy bar"
 * 3. "color changing"
 * 4. "random result of fighting"
 * @author   Lingkai Huang (21318909)
 * @version  8/1/2014
 */
public class Paint
{
    private Canvas myCanvas;
    private Random randomGenerator;
    /**
     * initialise map
     */
    public Paint()
    {
        myCanvas = new Canvas("Map of the house", 350, 250);
        randomGenerator = new Random();
        
    }
    
    /**
     * Draw a man
     */
    private void drawMan()
    {
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.gray);
        Ellipse2D.Double circle1 = new Ellipse2D.Double(150, 80, 20, 20);
        myCanvas.fill(circle1);
        myCanvas.setForegroundColor(Color.black); 
        Rectangle rect2 = new Rectangle(146, 100, 30, 30);
        myCanvas.fill(rect2);
        myCanvas.drawLine(146, 110, 120, 130);
        myCanvas.drawLine(176, 110, 196, 130);
        myCanvas.drawLine(153, 130, 153, 155);
        myCanvas.drawLine(168, 130, 168, 155);
        // Draw a man
    }
    
    /**
     * @return a color randomly
     */
    private Color randomColor()
    {
        Random random = new Random();
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        return (new Color(red, green, blue));
    }
    
    /**
     * change the color of "man" 
     */
    public void changeManColor()
    {
         drawMan();
         myCanvas.setForegroundColor(randomColor()); 
         Rectangle rect2 = new Rectangle(146, 100, 30, 30);
         myCanvas.fill(rect2);
    }
    
    /**
     * draw an energy bar
     */
    public void drawEnergyBar()
    {
        myCanvas.setVisible(true);
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
        myCanvas.setForegroundColor(Color.blue); 
        myCanvas.drawString("Energey bar :", 100, 50);
        myCanvas.setForegroundColor(Color.black); 
        myCanvas.drawLine(199, 34, 300, 34);
        myCanvas.drawLine(199, 55, 300, 55);
        myCanvas.drawLine(199, 34, 199, 55);
        myCanvas.drawLine(300, 34, 300, 55);
        myCanvas.setForegroundColor(Color.red); 
        Rectangle rect1 = new Rectangle(200, 35, 100, 20);
        myCanvas.fill(rect1);
        // Draw energetic bar
    }
    
    /**
     * draw information of "win"
     */ 
    public void winInfo()
    {
        myCanvas.setVisible(true);
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 40));
        myCanvas.setForegroundColor(Color.red);
        myCanvas.drawString("YOU WIN!!", 80, 185);
    }
    
    /**
     * get a random result of fighting with the monster
     * @return true if the player loss
     */
    public int isAlive()
    {
        int index = randomGenerator.nextInt(3);
        if (index == 0)
        {
            
            myCanvas.setVisible(true);
            myCanvas.setForegroundColor(Color.white);
            Rectangle rect5 = new Rectangle(200, 35, 100, 20);
            myCanvas.fill(rect5);
            myCanvas.setFont(new Font("helvetica", Font.BOLD, 40));
            myCanvas.setForegroundColor(Color.black);
            myCanvas.drawString("YOU LOSE!!", 80, 230);
            
        }
        else if (index == 1)
        {
             myCanvas.setVisible(true);
             drawEnergyBar();
             myCanvas.setForegroundColor(Color.white);
             Rectangle rect5 = new Rectangle(250, 35, 50, 20);
             myCanvas.fill(rect5);
             
        }
        else
        {
             printHero();
        }
        return index;
    }
    
    /**
     * print the word of 'HERO'.
     */
    public void printHero()
    {
        myCanvas.setVisible(true);
        myCanvas.setFont(new Font("helvetica", Font.PLAIN, 35));
        myCanvas.setForegroundColor(Color.red);
        myCanvas.drawString("HERO!!!", 200, 100);
    }
    
    /**
     * @param a
     * @param b
     * @param color
     * set a specific location with white color
     */
    public void manLocationWhite(int a, int b)
    {
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.white);
        Ellipse2D.Double circle1 = new Ellipse2D.Double(a, b, 10, 10);
        myCanvas.fill(circle1);
    }
    
    /**
     * show guide of location
     */
    public void locationInfo()
    {
        myCanvas.setVisible(true);
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 12));
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawString("Red dot is where you are.Find a map to see more clearly!!", 20, 20);
    }
    
    /**
     * @param a
     * @param b
     * set a specific location with red color
     */
    public void manLocationRed(int a, int b)
    {
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.red);
        Ellipse2D.Double circle1 = new Ellipse2D.Double(a, b, 10, 10);
        myCanvas.fill(circle1);
    }
    
    /**
     * After calling the method "manLocationRed",
     * we need to clean all the red dot,
     * then set an other red dot in somewhere else,
     * so that we can change location by drawing different dot in
     * different places
     */
    public void setLocationInvisible()
    {
        manLocationWhite(276, 190);
        manLocationWhite(276, 220);
        manLocationWhite(351, 190);
        manLocationWhite(201, 190);
        manLocationWhite(201, 140);
        manLocationWhite(276, 140);
        manLocationWhite(351, 390);
        manLocationWhite(201, 390);
        manLocationWhite(276, 390);
        manLocationWhite(276, 440);
    }
    
    
}
