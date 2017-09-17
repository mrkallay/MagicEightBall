package org.sjnd.compsci.ap;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.*;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This driver class is responsible for displaying the UI which is just a JFrame
 * with a JLabel containg the MagicEightBall image.  The start() method loops
 * when the enter key is pressed in the console and the program terminates when
 * Q is entered.
 *
 * @author Brian Kallay
 */
public class MagicDriver {

    private JFrame myFrame;
    private ImageIcon icon;
    private JLabel label;
    private MagicEightBall eightBall;
    private int numberOfShakes;

    public MagicDriver()
    {
        eightBall = new MagicEightBall();

        //the JFrame is the window we show the UI in
        myFrame = new JFrame();
        myFrame.setResizable(false);
        myFrame.setTitle("Magic 8 Ball");

        //the icon and label combined make up the eight ball displayed
        icon = new ImageIcon(MagicEightBall.image);
        label = new JLabel(icon);

        myFrame.add(label);
        myFrame.pack();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        MagicDriver driver = new MagicDriver();
        driver.numberOfShakes = 150;
        driver.start();
    }

    /**
     * Starts the loop that drives the eight ball.  Anytime enter is pressed
     * in the console the eight ball will be updated with a new name, unless
     * Q is typed which will end the loop and dispose of the JFrame.
     */
    private void start()
    {
        //provide user with instructions
        System.out.println("Press Enter to shake the eight ball (Q to quit):");

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        //keep our loop going as long as the user doesn't enter Q (or q)
        while (!command.toUpperCase().equals("Q"))
        {
            updateEightBall();
            command = input.nextLine();
        }

        //clean up the screen on exit...remove our JFrame
        myFrame.dispose();
    }

    /**
     * Responsible for shaking the eight ball and updating the screen.
     * This will shake the eight ball as many times as numberOfShakes is
     * set to, updating the screen each time and then do a final shake that
     * determines who is called on.
     */
    private void updateEightBall()
    {
        //this is the suspense loop, updating the
        //name each shake, but not actually calling
        //on anyone yet
        for (int i=0;i<numberOfShakes;i++) {
            eightBall.shakeItUp(false);
            System.out.println(i);
            updateScreen();
        }

        //final shake...this one is for real
        eightBall.shakeItUp(true);

        updateScreen();
    }

    /**
     * Updates the UI by updating the image in the JLabel
     */
    private void updateScreen()
    {
        myFrame.remove(label);
        icon = new ImageIcon(MagicEightBall.image);
        label = new JLabel(icon);

        myFrame.add(label);
        myFrame.pack();
    }

}


