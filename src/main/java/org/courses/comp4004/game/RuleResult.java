package org.courses.comp4004.game;

public class RuleResult {
    private boolean pass;
    private int score;
    private String message = "";

    public RuleResult(boolean pass, int score) {
        this.pass = pass;
        this.score = score;
    }

    public RuleResult(boolean pass, int score, String message) {
        this.pass = pass;
        this.score = score;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "" + pass + ", =" + score + ", " + message;
    }
}
