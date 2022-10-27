package org.courses.comp4004.game;

import java.util.List;

public class LineParser { private String line;

    private String cmd = "";
    private String parmsLine = "";
    private List<String> parmsList= null;

    public LineParser() {
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getParmsLine() {
        return parmsLine;
    }

    public void setParmsLine(String parmsLine) {
        this.parmsLine = parmsLine;
    }

    public List<String> getParmsList() {
        return parmsList;
    }

    public void setParmsList(List<String> parmsList) {
        this.parmsList = parmsList;
    }

    @Override
    public String toString() {
        return "LineParser{" +
                "line='" + line + '\'' +
                ", cmd='" + cmd + '\'' +
                ", parmsLine='" + parmsLine + '\'' +
                ", parmsList=" + parmsList +
                '}';
    }
}
