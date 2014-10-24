import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.util.ArrayList;
import java.io.File;
import javax.swing.border.*;

/**
 * This class is the GUI of the "Haunted House" application. 
 * This class implements a graphical user interface with a text 
 * entry area, text output areas, buttons, images and some basic
 * functions
 * 
 * @author   Lingkai Huang (21318909)
 * @version  8/1/2014
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log,itemLog,bagLog,textLog;
    private JLabel mainImage,mapImage;
    
    ImageIcon[] images = { new ImageIcon("images/balcony.jpg"), new ImageIcon("images/bedroom.jpg"),
             new ImageIcon("images/studio.jpg"), new ImageIcon("images/office.jpg"),new ImageIcon("images/bathroom.jpg"),
             new ImageIcon("images/study.jpg"),new ImageIcon("images/hall.jpg"),new ImageIcon("images/livingroom.jpg"),
             new ImageIcon("images/toilet.jpg"),new ImageIcon("images/kitchen.jpg"),};
             
    ImageIcon[] mapImages = { new ImageIcon("images/map/0balcony.jpg"), new ImageIcon("images/map/1bedroom.jpg"),
             new ImageIcon("images/map/2studio.jpg"), new ImageIcon("images/map/3office.jpg"),new ImageIcon("images/map/4bathroom.jpg"),
             new ImageIcon("images/map/5study.jpg"),new ImageIcon("images/map/6hall.jpg"),new ImageIcon("images/map/7livingroom.jpg"),
             new ImageIcon("images/map/8toilet.jpg"),new ImageIcon("images/map/9kitchen.jpg"),};
        
    private static final String VERSION = "Version 08/01/2014";  
    private int index;
    private Room room;
    private JButton upButton,northButton,downButton,southButton,eastButton,westButton,
                    pickButton,dropButton,useButton,lookButton,endButton,helpButton;
    private JFrame myTextFrame;
 
    private int selectVersion; 
    /**
     * Construct a UserInterface. 
     * @param gameEngine  The GameEngine object implementing the game logic.
     * @param index The index for selecting game with two different version
     */
    public UserInterface(GameEngine gameEngine, int index)
    {
        setSelectVersion(index);
        engine = gameEngine;
        if(selectVersion == 1)
        {createGUI();}
        else
        {createTextGUI();}
    }
    
    /**
     * @param index The index called by constructor
     */
    public void setSelectVersion(int index)
    {
        selectVersion = index;
    }
    
    /**
     * Print out some text into the main text area.
     */
    public void print(String text)
    {
        if(selectVersion==1)
        {
            log.append(text);
            log.setCaretPosition(log.getDocument().getLength());
        }
        else
        {
            textLog.append(text);
            textLog.setCaretPosition(textLog.getDocument().getLength());
        }
    }
    
    /**
     * Print out some text into the "item" text area.
     */
    public void printItem(String text)
    {
        if(selectVersion==1)
        {
            itemLog.setText(text);
        }
    }
    
    /**
     * Print out some text into the "bag" text area.
     */
    public void printBag(String text)
    {
        if(selectVersion==1)
        {
            bagLog.setText(text);
        }
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        
        if(selectVersion==1)
        {
            log.append(text + "\n");
            log.setCaretPosition(log.getDocument().getLength());
        }
        else
        {
            textLog.append(text + "\n");
            textLog.setCaretPosition(textLog.getDocument().getLength());
            
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }
    
    /**
     * Set up graphical user interface of only text
     */
    private void createTextGUI()
    {
        myTextFrame = new JFrame("Game");
        entryField = new JTextField(34);
        myTextFrame.setLocation(350,200);
        myTextFrame.setPreferredSize(new Dimension(400, 500));
        textLog = new JTextArea();
        textLog.setEditable(false);
        JScrollPane listScroller = new JScrollPane(textLog);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        myTextFrame.getContentPane().add(panel, BorderLayout.CENTER);
        // add some event listeners to some components
        myTextFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);

        myTextFrame.pack();
        myTextFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Game");
        entryField = new JTextField(34);
        myFrame.setPreferredSize(new Dimension(1100,900));
        myFrame.setLocation(350,100);
        makeMenuBar(myFrame);
        
        JPanel contentPanel = (JPanel)myFrame.getContentPane();
        contentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        contentPanel.setLayout(new BorderLayout());
        
        Font y = new Font("Serif",0,18);
        log = new JTextArea();
        log.setFont(y);
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(500, 400));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BorderLayout());
        midPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        midPanel.add(listScroller, BorderLayout.CENTER);
        
        mainImage = new JLabel(images[index]);
        mainImage.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        Font x = new Font("Serif",0,20);
        itemLog = new JTextArea();
        itemLog.setFont(x);
        itemLog.setEditable(false);
        JScrollPane itemScroller = new JScrollPane(itemLog);
        itemScroller.setPreferredSize(new Dimension(250, 200));
        itemScroller.setMinimumSize(new Dimension(100,100));
        
        JPanel itemRoomPanel = new JPanel(new BorderLayout());
        JLabel itemImg=new JLabel(new ImageIcon("images/otherIMG/itemsInRoom.jpg"));
        itemRoomPanel.add(itemImg, BorderLayout.CENTER);
        itemRoomPanel.add(itemScroller, BorderLayout.SOUTH);
        
        bagLog = new JTextArea();
        bagLog.setFont(x);
        bagLog.setEditable(false);
        JScrollPane bagScroller = new JScrollPane(bagLog);
        bagScroller.setPreferredSize(new Dimension(250, 200));
        bagScroller.setMinimumSize(new Dimension(100,100));
        
        JPanel itemBagPanel = new JPanel(new BorderLayout());
        JLabel bagImg=new JLabel(new ImageIcon("images/otherIMG/itemsInBag.jpg"));
        itemBagPanel.add(bagImg, BorderLayout.CENTER);
        itemBagPanel.add(bagScroller, BorderLayout.SOUTH);
        
        JPanel upPanel = new JPanel(new FlowLayout());
        upPanel.add(itemRoomPanel);
        upPanel.add(mainImage);
        upPanel.add(itemBagPanel);
        
        contentPanel.add(upPanel, BorderLayout.NORTH);
        contentPanel.add(midPanel, BorderLayout.CENTER);
        
        JPanel dirButton = new JPanel();
        dirButton.setLayout(new BorderLayout());
        
        JPanel northMid = new JPanel(new GridLayout());
        upButton = new JButton("Up");
        upButton.setMnemonic(KeyEvent.VK_Q );
        upButton.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go up");
                                   
                                   if(index == 7){
                                       index = 2;
                                    }
                                    mainImage.setIcon(images[index]);
                                    mapImage.setIcon(mapImages[index]);
                                }
                           });
        
        northMid.add(upButton);
        northButton = new JButton("North");
        northButton.setMnemonic(KeyEvent.VK_W );
        northButton.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go north");
                                   
                                   if(index == 0 || index == 1 || index== 3 || index== 6){
                                       index ++;
                                    }
                                    mainImage.setIcon(images[index]);
                                    mapImage.setIcon(mapImages[index]);
                                }
                           });
        northMid.add(northButton);
        downButton = new JButton("Down");
        downButton.setMnemonic(KeyEvent.VK_E );
        downButton.addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go down");
                                   
                                   if(index == 2){
                                       index = 7;
                                    }
                                    mainImage.setIcon(images[index]);
                                    mapImage.setIcon(mapImages[index]);
                                }
                           });
        northMid.add(downButton);
        dirButton.add(northMid, BorderLayout.NORTH);
        
        JPanel southMid = new JPanel(new GridLayout());
        JLabel label3 = new JLabel(" ");
        southMid.add(label3);
        southButton = new JButton("South");
        southButton.setMnemonic(KeyEvent.VK_S );
        southButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go south");
                                   
                                   if(index== 4||index ==2||index ==1||index ==7){
                                       index--;
                                    }
                                   mainImage.setIcon(images[index]);
                                   mapImage.setIcon(mapImages[index]);
                                }
                           });
        southMid.add(southButton);
        JLabel label4 = new JLabel(" ");
        southMid.add(label4);
        dirButton.add(southMid, BorderLayout.SOUTH);
        
        eastButton = new JButton("East");
        eastButton.setMnemonic(KeyEvent.VK_D );
        eastButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go east");
                                   
                                   if(index== 4 || index ==3){
                                       index = index -2;
                                    }
                                    else if(index== 1){
                                       index = 5;
                                    }
                                    else if(index== 8){
                                       index --;
                                    }
                                    else if(index== 7){
                                       index = 9;
                                    }
                                   mainImage.setIcon(images[index]);
                                   mapImage.setIcon(mapImages[index]);
                                }
                           });
        dirButton.add(eastButton, BorderLayout.EAST);
        
        westButton = new JButton("West");
        westButton.setMnemonic(KeyEvent.VK_A );
        westButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("go west");
                                   
                                   if(index== 1 || index ==2){
                                       index = index +2;
                                    }
                                   else if(index== 5){
                                       index = 1;
                                   }
                                   else if(index== 9){
                                       index = 7;
                                   }
                                   else if(index== 7){
                                       index ++;
                                   }
                                   mainImage.setIcon(images[index]);
                                   mapImage.setIcon(mapImages[index]);
                                }
                           });
        dirButton.add(westButton, BorderLayout.WEST);
        
        pickButton = new JButton("Pick");
        pickButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showPickOption(); }
                           }); 
        dropButton = new JButton("Drop");
        dropButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showDropOption(); }
                           });   
        useButton = new JButton("Use");
        useButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showUseOption(); }
                           });  
        lookButton = new JButton("Look around");
        lookButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { engine.interpretCommand("look"); }
                           }); 
        endButton = new JButton("End Game");
        endButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   engine.interpretCommand("quit");
                                   ableButton(false); }
                           });     
        helpButton = new JButton("help");
        helpButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { engine.interpretCommand("help"); }
                           }); 
        // Add dirButton into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout());
        flow.add(pickButton);
        flow.add(dropButton);
        flow.add(useButton);
        flow.add(dirButton);
        flow.add(lookButton);
        flow.add(helpButton);
        flow.add(endButton);
        
        contentPanel.add(flow, BorderLayout.SOUTH); 
        JPanel rightBorder = new JPanel();
        rightBorder.setBorder(new EmptyBorder(5, 5, 5, 5));
        rightBorder.setLayout(new GridLayout(0, 1));
        rightBorder.setPreferredSize(new Dimension(500, 400));
         
        mapImage = new JLabel(mapImages[index]);
        mapImage.setBorder(new EmptyBorder(5, 5, 5, 5));
       
        rightBorder.add(mapImage); 
        contentPanel.add(rightBorder, BorderLayout.EAST); 
        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this); 
        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }
    
    /**
     * enable or disable buttons
     */
    public void ableButton(boolean a)
    {
        if(selectVersion==1)
        {
            upButton.setEnabled(a);
            northButton.setEnabled(a);
            downButton.setEnabled(a);
            southButton.setEnabled(a);
            eastButton .setEnabled(a);
            westButton.setEnabled(a);
            pickButton.setEnabled(a);
            dropButton.setEnabled(a);
            useButton.setEnabled(a);
            lookButton.setEnabled(a);
            endButton.setEnabled(a);
            helpButton.setEnabled(a);
        }
    }
    
    /**
     * button of "use"
     */
    private void showUseOption()
    {
        String input = JOptionPane.showInputDialog(null, "Enter a item to use:", "Using item",
        JOptionPane.WARNING_MESSAGE);
        engine.interpretCommand("use " + input);
    }
    
    /**
     * button of "drop"
     */
    private void showDropOption()
    {
        String input = JOptionPane.showInputDialog(null, "Enter a item to drop:", "Droping item",
        JOptionPane.WARNING_MESSAGE);
        engine.interpretCommand("drop " + input);
    }
    
    /**
     * button of "pick"
     */
    private void showPickOption()
    {
        String input = JOptionPane.showInputDialog(null, "Enter a item to pick:", "Picking item",
        JOptionPane.WARNING_MESSAGE);
        engine.interpretCommand("pick " + input);
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        processCommand();
    }

    /**
     * A command has been entered. 
     * Read the command and do 
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");
        engine.interpretCommand(input);
    }
    
    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("Menu");
        menubar.add(menu);
        
        item = new JMenuItem("PLAY");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {ableButton(true);  }
                           });
        menu.add(item);
        
        menu.addSeparator();

        item = new JMenuItem("QUIT");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);
        

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About this game...");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showAbout(); }
                           });
        menu.add(item);
        
        menu.addSeparator();
        
        item = new JMenuItem("Game Instruction");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {printInstruction();}
                           });
        menu.add(item);

    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    /**
     * 'Lighter' function: make the picture lighter
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(myFrame, 
                    "Developer: Lingkai Huang\n" + VERSION,
                    "About this game", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * print instruction
     */
    private void printInstruction()
    {
        println("");
        println("*Instruction:");
        println("-use direction button to move!");
        println("-Shortcuts: ALT+ W(north),S(south),A(west),D(east),Q(up),E(down)!");
        println("-Type the name of item when using 'pick'; 'drop'; 'use'!");
        println("-Use 'weapon' to fight with monster; but you may die randomly!");
        println("-Use 'hat' to change color");
        println("-You need to find a 'key' to open the door in hall to escape!!!!!");
        println("");
    }
}
