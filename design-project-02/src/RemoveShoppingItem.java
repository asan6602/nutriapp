import java.util.Scanner;

public class RemoveShoppingItem implements Actions{
    User user;

    public RemoveShoppingItem(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.RemoveShoppingList();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
