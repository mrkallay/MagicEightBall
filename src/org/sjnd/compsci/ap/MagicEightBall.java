package org.sjnd.compsci.ap;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.Random;

/**
 * This class represents a magic eight ball that, when shaken, will display
 * the name of a randomly selected student.  When the eight ball is shaken and
 * the lastShake flag is set to true the logic guarantees that the same student
 * will not be called on consecutively. The purpose of the lastShake flag is to
 * support displaying a random sampling of student names before making the final
 * selection.
 *
 * @author briankallay
 * @see Student
 */
public class MagicEightBall {

    //array of Student objects
    Student[] students = new Student[10];

    //used to track the index of the currently called on student,
    //so we don't call on them consecutively
    int selectedIndex;

    //used to track the index of the currently scrolling student,
    //this is used strictly for displaying the scrolling names before
    //a student is selected...in other words it adds suspense :)
    int scrollingIndex;

    //random number generator
    Random randomSelector;

    public static BufferedImage image;
    private static Image img;

    /**
     * Creates a new MagicEightBall.
     */
    public MagicEightBall()
    {
        //create the student objects
        students[0] = new Student("Emily", 215);
        students[1] = new Student("Anthony", 209);
        students[2] = new Student("Gabe", 217);
        students[3] = new Student("Josh", 217);
        students[4] = new Student("Claude", 212);
        students[5] = new Student("Nestor", 212);
        students[6] = new Student("JackA", 212);
        students[7] = new Student("Chris", 216);
        students[8] = new Student("Quinn", 215);
        students[9] = new Student("JackM", 212);

        selectedIndex = -1;

        //this is the base eight ball image. After each shake it needs to be
        //redrawn to the buffered image, otherwise there would be overlapping names
        java.net.URL imgURL = getClass().getResource("images" +File.separator+ "eightball.png");
        img = null;
        try {
            img = ImageIO.read(imgURL).getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        loadImage();

        randomSelector = new Random();
    }

    /**
     * Causes the name on the image to get updated with a randomly chosen
     * student excluding the student previously chosen. Setting lastShake to
     * false will update the eightball buffered image with the newly selected
     * name but it will not update the selected index. This allows us to cycle
     * the names on the eight ball before picking the actual student to be
     * called on. Suspense is a beautiful thing.
     *
     * @param lastShake boolean that controls whether this shake will
     *                  update the selected index.
     */
    public void shakeItUp(boolean lastShake)
    {
        //pick a random student
        int index = randomSelector.nextInt(10);

        //
        while(index == selectedIndex) {
            index = randomSelector.nextInt(10);
        }

        //update the scrolling index with the newly called upon student
        scrollingIndex = index;

        //if lastShake is true, make this real
        if (lastShake) {
            selectedIndex = index;

            //update the student to reflect that they were called on
            students[selectedIndex].addTimesSelected();

            //print a report of how many times each student has been called on
            showStats();
        }

        //update the buffered image with the new name
        updateEightBallName();
    }

    /**
     * Displays the stats for each student which at this point is just
     * the number of times they have been called on.
     */
    private void showStats()
    {
        for(Student s : students)
        {
            System.out.println(s.getName() + ": " + s.getTimesSelected());
        }
    }

    /**
     * Draws the currently selected student's name on the eight ball using the
     * x coordinate defined on the student object.
     */
    private void updateEightBallName()
    {
        //reload the image, otherwise we will have overlapping names
        loadImage();

        // Draw the image on to the buffered image
        Graphics graphics = image.getGraphics();
        Color c=new Color(0f,0f,0f,0f );
        graphics.setColor(c);
        graphics.fillRect(0, 0, 200, 50);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
        graphics.drawString(students[scrollingIndex].getName(), students[scrollingIndex].getXLocation(), 240);
        graphics.dispose();
    }

    /**
     * Draws the eight ball image to the buffered image.  This needs to be done each time
     * the eight ball is shaken because the name is drawn on to the buffered image, so this
     * gives us a fresh eight ball to draw on.
     */
    private void loadImage()
    {
        // Create a buffered image with transparency
        image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics graphics = image.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();
    }
}
