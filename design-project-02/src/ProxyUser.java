import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class ProxyUser implements User{
    RealUser user;

    public ProxyUser(RealUser user) {
        this.user = user;
    }

    @Override
    public String addToDailyFood() {
        if(user.isAuthenticated() == true) {
            return user.addToDailyFood();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String addToIngredients() {
        if(user.isAuthenticated() == true) {
            return user.addToIngredients();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String CreateMeal() {
        if(user.isAuthenticated() == true) {
            return user.CreateMeal();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String CreateRecipe() {
        if(user.isAuthenticated() == true) {
            return user.CreateRecipe();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String SetGoal() {
        if(user.isAuthenticated() == true) {
            return user.SetGoal();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String ViewShoppingList() {
        if(user.isAuthenticated() == true) {
            return user.ViewShoppingList();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String RemoveShoppingList() {
        if(user.isAuthenticated() == true) {
            return user.RemoveShoppingList();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String AddShoppingList() {
        if(user.isAuthenticated() == true) {
            return user.AddShoppingList();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String AddWorkout() {
        if(user.isAuthenticated() == true) {
            return user.AddWorkout();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String ViewHistory() {
        if(user.isAuthenticated() == true) {
            return user.ViewHistory();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String DailyWeight() {
        if(user.isAuthenticated() == true) {
            return user.DailyWeight();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String CreateUser() {
        return user.CreateUser();
    }

    @Override
    public String BrowseIngredient() {
        return user.BrowseIngredient();
    }

    @Override
    public String BrowseRecipe() {
        return user.BrowseRecipe();
    }

    @Override
    public String BrowseMeal() {
        return user.BrowseMeal();
    }

    @Override
    public String ChangePassword() {
        if(user.isAuthenticated() == true) {
            return user.ChangePassword();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String CreateTeam() {
        if(user.isAuthenticated() == true) {
            return user.CreateTeam();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String LeaveTeam() {
        if(user.isAuthenticated() == true) {
            return user.LeaveTeam();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String SendInvite() {
        if(user.isAuthenticated() == true) {
            return user.SendInvite();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String ViewInbox() {
        if(user.isAuthenticated() == true) {
            return user.ViewInbox();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String Undo() {
        if(user.isAuthenticated() == true) {
            return user.Undo();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String ExportData() throws IOException {
        if(user.isAuthenticated() == true) {
            return user.ExportData();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String ImportData() throws IOException {
        if(user.isAuthenticated() == true) {
            return user.ImportData();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String EnterDailyWeight() {
        if(user.isAuthenticated() == true) {
            return user.EnterDailyWeight();
        }
        else {
            System.out.println("Not allowed for non-authenticated users");
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String UndoDailyWeight() {
        if(user.isAuthenticated() == true) {
            return user.UndoDailyWeight();
        }
        else {
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String undoIngredients() throws CsvValidationException, IOException {
        if(user.isAuthenticated() == true) {
            return user.undoIngredients();
        }
        else {
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String undoGoal() {
        if(user.isAuthenticated() == true) {
            return user.undoGoal();
        }
        else {
            return "Not allowed for non-authenticated users";
        }
    }

    @Override
    public String undoInvite() {
        // TODO Auto-generated method stub
        if(user.isAuthenticated() == true) {
            return user.undoInvite();
        }
        else {
            return "Not allowed for non-authenticated users";
        }
    }
}
