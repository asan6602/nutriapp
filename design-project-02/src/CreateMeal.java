import java.util.ArrayList;
import java.util.Scanner;

public class CreateMeal implements Actions{
    User user;

    public CreateMeal(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
       user.CreateMeal();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
