package org.courses.comp4004.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

public class LineParser { private String line;

    private String cmd = "";
    private String parmsLine = "";
    private List<String> parmsList= null;

    public LineParser() {
    }

    public void init(String line){
        this.line = line;
        cmd = "";
        parmsLine = "";
        String parmsLineParsed = "";
        parmsList = new ArrayList<String>();

        if(!isNull(this.line)){
            String[] splitCmd = this.line.trim().split("\\s+",2);
            if(!isNull(splitCmd[0])) {
                cmd = splitCmd[0];
            }
            if((splitCmd.length == 2) && (!isNull(splitCmd[1]))) {
                parmsLine = splitCmd[1];
            }

            if((splitCmd.length > 1) && (!isNull(splitCmd[1]))) {
                for(int i=1; i< splitCmd.length; i++) {
                    parmsLineParsed = parmsLineParsed + splitCmd[i];
                }
                if(parmsLineParsed.lastIndexOf(',') == parmsLineParsed.length()-1){
                    parmsLineParsed = parmsLineParsed.substring(0,parmsLineParsed.length()-1);
                }
                String[] splitParmsArray = parmsLineParsed.trim().split("\\s*,+\\s*");
                Collections.addAll(parmsList, splitParmsArray);
            }
        }
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
