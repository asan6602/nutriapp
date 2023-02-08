import java.time.LocalDate;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class PTUI {
    public static Actions action;
    
    public static void setCommand(Actions setAction) {
        action = setAction;
    }
    
    public static void performCommand() throws IOException, CsvValidationException{
        action.executeAction();
    }

    public static void help() {
        System.out.println("1: Create / Add Ingredients");
        System.out.println("2: Eat / Add Food");
        System.out.println("3: Create Recipe");
        System.out.println("4: Create Meal");
        System.out.println("5: Add Workout");
        System.out.println("6: Set Goal");
        System.out.println("7: View History");
        System.out.println("8: Add to Shopping List");
        System.out.println("9: Remove from Shopping List");
        System.out.println("10: View Shopping List");
        System.out.println("11: Create User");
        System.out.println("12: Browse Ingredients");
        System.out.println("13: Browse Recipes");
        System.out.println("14: Browse Meals");
        System.out.println("15: Change Password");
        System.out.println("16: Create Team");
        System.out.println("17: Send Invite");
        System.out.println("18: Leave Team");
        System.out.println("19: View Inbox");
        System.out.println("20: Export Data");
        System.out.println("21: Import Data");
        //System.out.println("22: Enter Daily Weight");
        System.out.println("undo: Undo");
    }

    public static void main(String[] args) throws IOException, CsvValidationException, ParserConfigurationException, SAXException {
        Scanner scan = new Scanner(System.in);
        
        Authenticator authenticator = new Authenticator();

        RealUser RealUser = authenticator.Login();
        User ProxyUser = new ProxyUser(RealUser);
        System.out.println("command 'help' for help");
        System.out.println("command 'exit' for exit");

        if(RealUser.isAuthenticated()){
            Actions enterDailyWeight = new EnterDailyWeight(ProxyUser);
            setCommand(enterDailyWeight);
            performCommand();
        }

        boolean looper = true;
        while(looper) {
            System.out.print("Enter Command: ");
            String textCommand = scan.nextLine().toLowerCase();
            switch(textCommand) {
                case "help":
                    help();
                    break;
                case "1":
                    Actions actionAddIngredient = new AddIngredients(ProxyUser);
                    setCommand(actionAddIngredient);
                    performCommand();
                    break;
                case "2":
                    Actions actionEat = new AddDailyFood(ProxyUser);
                    setCommand(actionEat);
                    performCommand();
                    break;
                case "3":
                    Actions actionCreateRecipe = new CreateRecipe(ProxyUser);
                    setCommand(actionCreateRecipe);
                    performCommand();
                    break;
                case "4":
                    Actions actionCreateMeal = new CreateMeal(ProxyUser);
                    setCommand(actionCreateMeal);
                    performCommand();
                    break;
                case "5":
                    Actions actionWorkout = new AddWorkout(ProxyUser);
                    setCommand(actionWorkout);
                    performCommand();
                    break;
                case "6":
                    Actions setGoal = new SetGoal(ProxyUser);
                    setCommand(setGoal);
                    performCommand();
                    break;
                case "7":
                    Actions viewHistory = new ViewHistory(ProxyUser);
                    setCommand(viewHistory);
                    performCommand();
                    break;
                case "8":
                    Actions actionAddShopping = new AddShoppingItem(ProxyUser);
                    setCommand(actionAddShopping);
                    performCommand();
                    break;
                case "9":
                    Actions actionRemoveShopping = new RemoveShoppingItem(ProxyUser);
                    setCommand(actionRemoveShopping);
                    performCommand();
                    break; 
                case "10":
                    Actions actionViewShopping = new ViewShoppingItem(ProxyUser);
                    setCommand(actionViewShopping);
                    performCommand();
                    break;
                case "11":
                    Actions createUser = new CreateUser(ProxyUser);
                    setCommand(createUser);
                    performCommand();
                    break;
                case "12":
                    Actions browseIngredient = new BrowseIngredient(ProxyUser);
                    setCommand(browseIngredient);
                    performCommand();
                    break;
                case "13":
                    Actions browseRecipe = new BrowseRecipe(ProxyUser);
                    setCommand(browseRecipe);
                    performCommand();
                    break;
                case "14":
                    Actions browseMeal = new BrowseMeal(ProxyUser);
                    setCommand(browseMeal);
                    performCommand();
                    break;
                case "15":
                    Actions changePassword = new ChangePassword(ProxyUser);
                    setCommand(changePassword);
                    performCommand();
                    break;
                case "16":
                    Actions createTeam = new CreateTeam(ProxyUser);
                    setCommand(createTeam);
                    performCommand();
                    break;
                case "17":
                    Actions sendInvite = new SendInvite(ProxyUser);
                    setCommand(sendInvite);
                    performCommand();
                    break;
                case "18":
                    Actions leaveTeam = new LeaveTeam(ProxyUser);
                    setCommand(leaveTeam);
                    performCommand();
                    break;
                case "19":
                    Actions viewInbox = new ViewInbox(ProxyUser);
                    setCommand(viewInbox);
                    performCommand();
                    break;
                case "20":
                    Actions exportData = new ExportData(ProxyUser);
                    setCommand(exportData);
                    performCommand();
                    break;
                case "21":
                    Actions importData = new ImportData(ProxyUser);
                    setCommand(importData);
                    performCommand();
                    break;
                case "22":
                    Actions enterDailyWeight = new EnterDailyWeight(ProxyUser);
                    setCommand(enterDailyWeight);
                    performCommand();
                    break;
                case "undo":
                    Actions undo = new Undo(ProxyUser);
                    setCommand(undo);
                    performCommand();
                    break;
                case "exit":
                    looper= false;
                    break;
                case "":
                    break;
            }


        }
        scan.close();
        
    }
}