import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface User {
    public String addToDailyFood();

    public String addToIngredients();

    public String CreateMeal();

    public String CreateRecipe();

    public String SetGoal();

    public String ViewShoppingList();

    public String AddShoppingList();

    public String RemoveShoppingList();

    public String AddWorkout();

    public String ViewHistory();

    public String DailyWeight();

    public String CreateUser();

    public String BrowseIngredient();

    public String BrowseRecipe();

    public String BrowseMeal();

    public String ChangePassword();

    public String CreateTeam();

    public String LeaveTeam();

    public String SendInvite();

    public String ViewInbox();

    public String Undo();

    public String ExportData() throws IOException;

    public String ImportData() throws IOException;

    public String EnterDailyWeight();

    public String undoGoal();

    public String UndoDailyWeight();

    public String undoIngredients() throws CsvValidationException, IOException;

    public String undoInvite();

}

