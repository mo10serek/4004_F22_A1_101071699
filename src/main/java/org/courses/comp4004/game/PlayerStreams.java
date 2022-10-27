package org.courses.comp4004.game;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PlayerStreams {

    PrintWriter out = null;
    BufferedReader in = null;
    //pirate-local.player.InteractionModes currentPlayerMode;

    public PlayerStreams(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}
