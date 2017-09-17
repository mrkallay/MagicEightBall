package org.sjnd.compsci.ap;

/**
 * @author Brian Kallay
 */
public class Student {

    private String name;

    //this lets us tweak exactly where the name should appear depending on length
    private int xLocation;

    private int timesSelected;

    //TODO: maybe we start tracking correct answers?
    private int timesCorrect;

    /**
     * Class constructor that initializes the student name and
     * x coordinate.
     * @param name  the student's name
     * @param xLocation the x coordinate to use when placing the name on the eight ball
     */
    public Student(String name, int xLocation)
    {
        this.name = name;
        this.xLocation = xLocation;
    }

    /**
     * Returns the name of the student.
     *
     * @return      the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x coordinate of where the name should be placed.  Allows
     * each name to be centered correctly.
     *
     * @return      the x coordinate
     */
    public int getXLocation() {
        return xLocation;
    }

    /**
     * Retrives the number of times this student has correctly answered a question.
     * @return the number of times this student has been correct.
     */
    public int getTimesCorrect() {
        return timesCorrect;
    }

    /**
     * Retrives the number of times this student has been called on by the eight ball.
     * @return the number of times this student has been called on.
     */
    public int getTimesSelected() {
        return timesSelected;
    }

    /**
     *Adds one to the timesSelected variable. Keeps track of
     * how many times the student has been called on.
     */
    public void addTimesSelected() {
        this.timesSelected++;
    }

    /**
     * Not currently wired up to anyting but will be an enhancement
     * made in class.
     *
     */
    public void addTimesCorrect() {
        this.timesCorrect++;
    }
}
