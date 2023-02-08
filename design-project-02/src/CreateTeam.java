import java.util.Scanner;
public class CreateTeam implements Actions{
    User user;

    public CreateTeam(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.CreateTeam();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
