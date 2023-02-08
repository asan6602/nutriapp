import java.util.Stack;

public class CommandCollection {
    protected static Stack<Actions> CommandLog = new Stack<>();

    public void addToLog(Actions recentEntry){
        CommandLog.add(recentEntry);
    }
}
