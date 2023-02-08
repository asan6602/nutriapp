import java.util.Scanner;
public class SetGoal implements Actions{
    User user;

    public SetGoal(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.SetGoal();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {
        user.undoGoal();
    }
}