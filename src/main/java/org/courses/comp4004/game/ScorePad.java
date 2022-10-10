package org.courses.comp4004.game;

public class ScorePad {

    private int totalScore = 0;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }
}
