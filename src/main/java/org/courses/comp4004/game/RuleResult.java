package org.courses.comp4004.game;

public class RuleResult {
    private boolean pass;
    private int score;

    public RuleResult(boolean pass, int score) {
        this.pass = pass;
        this.score = score;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "" + pass + "  " + score;
    }
}
