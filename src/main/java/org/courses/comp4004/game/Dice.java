package org.courses.comp4004.game;

public class Dice {
    private String figure;
    private String[] figures = {"skull", "parrot", "sword", "monkey", "coin", "diamond"};
    private boolean canRoll = true;

    private boolean canHold = false;

    public Dice() {
        roll();

    }

    public Dice(String figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return figure;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public boolean getCanRoll() {
        return canRoll;
    }

    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
    }

    public boolean getCanHold() { return canHold; }

    public void setCanHold(boolean canHold) { this.canHold = canHold; }

    /**
     * Random dice roll
     */
    public void roll() {
        int a = (int) (Math.random() * 6);
        figure = figures[(a == 6 ? 5 : a)];

    }

    public void disableRollIfFigure(String figure){
        if(this.figure.compareTo(figure) == 0){
            canRoll = false;
        }
    }
    public void enableRollIfFigure(String figure){
        if(this.figure.compareTo(figure) == 0){
            canRoll = true;
        }
    }

    public void holddice(){
        canHold = true;
    }

    /**
     * Roll randomly dice until a figure is any other than that specified in parameter
     *
     * @param face
     */
    public void rollNotFigure(String face) {
        while (figure.equals(face)) {
            roll();
        }
    }
}
