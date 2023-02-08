import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Scanner;

public class AddShoppingItem implements Actions{
    User user;

    public AddShoppingItem(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.AddShoppingList();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }

}
