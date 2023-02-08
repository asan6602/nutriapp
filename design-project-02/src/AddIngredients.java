import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class AddIngredients implements Actions{
    User user;

    public AddIngredients(User user) {
        this.user = user;
    }

    @Override
    public void executeAction(){
        user.addToIngredients();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {
        try {
            user.undoIngredients();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.addToIngredients();
    }

}
