package org.courses.comp4004.game;

public class PostStatus {
    public String outMsg;
    public boolean success;
    public int score;

    public PostStatus(String outMsg, boolean success) {
        this.outMsg = outMsg;
        this.success = success;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
