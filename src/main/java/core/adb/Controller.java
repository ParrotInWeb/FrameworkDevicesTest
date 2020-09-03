package core.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Controller {

    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setCommand(String command, String deviceNumber) {
        this.command = String.format(command, deviceNumber);
    }

    public String executeCommandAndGetResult() {
        try {
            Process commandResult = executeCommand();
            BufferedReader bufferedReader = getResult(commandResult);
            return joinLinesOfResult(bufferedReader);
        } catch (IOException ex) {
            return "";
        }
    }

    public Process executeCommand() {
        try {
            return Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            return null;
        }
    }

    private BufferedReader getResult(Process commandResult) {
        if (commandResult != null) {
            return new BufferedReader(new InputStreamReader(commandResult.getInputStream()));
        } else {
            return null;
        }
    }

    private String joinLinesOfResult(BufferedReader bufferedReader) throws IOException {
        String s;
        LinkedList<String> sl = new LinkedList<>();
        while ((s = bufferedReader.readLine()) != null) {
            sl.add(s);
        }
        return String.join(" ", sl);
    }
}
