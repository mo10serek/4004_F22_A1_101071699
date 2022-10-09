package org.courses.comp4004.game;

public class FCard {

    private String figure;
    private int value;


    public FCard(String figure, int value) {
        this.figure = figure;
        this.value = value;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return figure + ", " + value ;
    }
}
