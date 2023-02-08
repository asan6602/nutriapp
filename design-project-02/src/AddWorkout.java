import java.util.Scanner;

import static java.lang.Math.floor;

public class AddWorkout implements Actions{
    User user;

    public AddWorkout(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
       user.AddWorkout();
       CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
