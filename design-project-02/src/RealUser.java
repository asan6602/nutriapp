import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 */
public class RealUser implements NotifiedUsers, User{
    private String username;
    private String password;
    private String name;
    private float height;
    private float weight;
    private float targetWeight;
    private int age;
    private Goals currentGoal;
    private float calories;
    private double targetCalories;
    private ArrayList<Meal> eatenFood = new ArrayList<Meal>();
    private boolean isAuthenticated = false;
    private boolean choice = false;
    private ArrayList<Notification> inbox = new ArrayList<Notification>();
    private Team team;

    private ArrayList<Ingredient> ShoppingList;
    private String Team;

    //Attributes below necessary for Undo Subsystem
    CommandCollection CommandPop;
    Actions CommandInstance;
    Goals previousGoal;
    /**
     *
     * @param name string name of the user
     * @param height float height of the user
     * @param weight
     * @param age
     * @param targetWeight
     */
    public RealUser(String username, String name, float height, float weight, int age, float targetWeight, boolean isAuthenticated) {
        this.username = username;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.targetWeight = targetWeight;
        this.isAuthenticated = isAuthenticated;
        determineGoal();
    }

    /**
     * Constructor calls this function once user object is created to automaticity set the goal of the user
     * by comparing target weight with weight.
     */
    public void determineGoal()
    {
        float tempWeight = targetWeight - weight;

        if(tempWeight >= 0 && tempWeight < 5)
            currentGoal = new MantainWeight();
        else if( tempWeight < 0)
            currentGoal = new LoseWeight();
        else
            currentGoal = new GainWeight();

        targetCalories = currentGoal.adjustGoal(currentGoal);
    }

    public void setCurrentGoal(Goals currentGoal) {
        this.currentGoal = currentGoal;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    /**
     *Helper Methods (mainly imported from R1)
     * */
    public static boolean findIngredientDB(String name) {
        boolean found = false;
        try {
            String[] nextEntry;
            FileReader fileReader = new FileReader("src/Data/ingredientDB.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            nextEntry = csvReader.readNext();
            while ((nextEntry = csvReader.readNext()) != null) {
                String checkedName = nextEntry[1];
                if (checkedName.toLowerCase().equals(name.toLowerCase())) {
                    found = true;
                    break;
                }
            }
            csvReader.close();
        }
        catch(Exception e) {
            return false;
        }
        return found;
    }

    public static boolean findIngredient(String name) {
        boolean found = false;
        try {
            String[] nextEntry;
            FileReader fileReader = new FileReader("src/Data/PersonalStock.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            nextEntry = csvReader.readNext();
            while ((nextEntry = csvReader.readNext()) != null) {
                String checkedName = nextEntry[1];
                if (checkedName.toLowerCase().equals(name.toLowerCase())) {
                    found = true;
                    break;
                }
            }
            csvReader.close();
        }
        catch(Exception e) {
            return false;
        }
        return found;
    }

    public static List<String[]> csvTOlist(String csvfile) throws IOException, CsvValidationException {
        List<String[]> returned_data = new ArrayList<>();
        FileReader filereader = new FileReader(csvfile); //see if ingredient exists in the database
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextEntry;
        while ((nextEntry = csvReader.readNext()) != null) {
            returned_data.add(nextEntry);
        }
        return returned_data;
    }

    public static Float findCalories(String name) {
        Float fcalories = 0.0f;
        try {
            String[] nextEntry;
            FileReader fileReader = new FileReader("src/Data/ingredientDB.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            nextEntry = csvReader.readNext();
            while ((nextEntry = csvReader.readNext()) != null) {
                String checkedName = nextEntry[1];
                if (checkedName.toLowerCase().equals(name.toLowerCase())) {
                    fcalories = Float.parseFloat(nextEntry[2]);
                    break;
                }
            }
        }
        catch(Exception e) {
            return 0.0f;
        }
        return fcalories;
    }

    public static int getOldStock(String name, List<String[]> allStock) {
        int oldStock = 0;

        for(String[] s: allStock) {
            if(s[1].equals(name)) {
                oldStock = Integer.parseInt(s[3]);
                return oldStock;
            }
        }
        return oldStock;
    }

    public static int getIndex(String name, List<String[]> allStock) {
        int index = 0;
        for(String[] s: allStock) {
            if(s[1].equals(name)) {
                return index;
            }
            index += 1;
        }
        return -1;
    }

    @Override
    public String addToDailyFood() {
        System.out.println("It works!");
        return null;
    }

    @Override
    public String addToIngredients() {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Ingredient to by added (one word): ");
            String name = scan.nextLine();
            System.out.println("Enter stock: ");
            int stock = Integer.parseInt(scan.nextLine());

            //read through ingredidentDB to see if ingredient is valid
            boolean valid = findIngredientDB(name);
            if(valid) { //if the ingredient is valid, check whether or not it is in personalstock
                boolean inPersonal = findIngredient(name);

                try {
                    List<String[]> allStock = csvTOlist("src/Data/PersonalStock.csv");
                    FileWriter outputfile = new FileWriter("src/Data/PersonalStock.csv");
                    CSVWriter writer = new CSVWriter(outputfile);

                    if(inPersonal) { //if in personal, edit the csv row by creating a new and deleting the old
                        Float calories = findCalories(name);
                        int oldStock = getOldStock(name, allStock);
                        int index = getIndex(name, allStock);
                        allStock.remove(index);
                        int newStock = oldStock + stock;
                        String[] insertedValue = new String[] {getName(), name, calories.toString(), Integer.toString(newStock)};
                        allStock.add(insertedValue);

                    }
                    else{ //if not personal create the new csv row
                        Float calories = findCalories(name);
                        String [] insertedValue = new String[] {getName(), name, calories.toString(), Integer.toString(stock)};
                        allStock.add(insertedValue);
                    }
                    writer.writeAll(allStock);
                    writer.close();
                }
                catch(Exception e) {
                }
            }
            else {
                System.out.println("Ingredient not found");
            }
        return null;
    }


    @Override
    public String CreateMeal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String CreateRecipe() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String SetGoal() {
        if (currentGoal != null){ //used for the undo functionality
            previousGoal = currentGoal;
        }
        Goals GainWeight = new GainWeight();
        Goals MaintainWeight = new MantainWeight();
        Goals LoseWeight = new LoseWeight();

        Scanner scan = new Scanner(System.in);
        System.out.println("Choose one of the following Goals: ");
        System.out.println( "1. Gain Weight\n" +
                            "2. Maintain Weight\n" +
                            "3. Lose Weight");
        String selectedGoal = scan.nextLine();
        switch (selectedGoal) {
            case "1" -> setCurrentGoal(GainWeight);
            case "2" -> setCurrentGoal(MaintainWeight);
            case "3" -> setCurrentGoal(LoseWeight);
        }
        System.out.println("Your Goal was set to " + currentGoal.toString());
        return null;
    }

    @Override
    public String ViewShoppingList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String RemoveShoppingList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String AddShoppingList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String AddWorkout() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String ViewHistory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String DailyWeight() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * prompts the user to enter a username and password
     * checks to see if the username is already taken
     * if it isn't create the account by adding it to the userinfo.csv
     * then prompts the user to enter personal data to add to history
     * @author aaron santos
     */
    @Override
    public String CreateUser() {
        try {
            //prompts the user to enter the info for the new account
            Scanner scan = new Scanner(System.in);
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();

            //converted password
            String converted = PWDconvertor(password);

            AuthenticatorDB db = new AuthenticatorDB();
            ArrayList<String[]> userInfo = db.readFile();

            //check if username is already in the db
            boolean found = false;
            for (String[] i: userInfo) {
                if(i[0].equals(username)) {
                    found = true;
                }
            }

            //if found do not create, else create a new user
            if(found) {
                System.out.println("Username is taken... User not created");
            }
            else{
                System.out.println("User is created!");
                String[] newEntry= new String[2];
                newEntry[0] = username;
                newEntry[1] = converted;
                userInfo.add(newEntry);
                db.writeFile(userInfo);
                System.out.println("You must now enter new user's personal information...");
                enterPersonalInfo(username);
            }
        }
        catch(Exception e) {

        }
        return null;
    }
 
    /**
     * prints the ingredients in the ingredientDB.csv for the user to view
     * @author aaron santos
     */
    @Override
    public String BrowseIngredient() {
        try {
            IngredientDB db = new IngredientDB();
            ArrayList<String[]> list = db.readFile();
            for(String[] i: list) {
                for(String x: i) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }
        catch(Exception e) {

        }
        return null;
    }

    /**
     * prints the recipes in the recipeDB.csv for the user to view
     * @author aaron santos
     */
    @Override
    public String BrowseRecipe() {
        try {
            RecipesDB db = new RecipesDB();
            ArrayList<String[]> list = db.readFile();
            for(String[] i: list) {
                for(String x: i) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }
        catch(Exception e) {

        }
        return null;
    }

    /**
    * prints the meals in the mealDB.csv for the user to view
    * @author aaron santos
    */ 
    @Override
    public String BrowseMeal() {
        try {
            MealDB db = new MealDB();
            ArrayList<String[]> list = db.readFile();
            for(String[] i: list) {
                for(String x: i) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }
        catch(Exception e) {

        }
        return null;
    }

    /**
     * changes the user's password
     * prompts the user to enter a new password
     * converts password to the encrypted version
     * overwrites the entry in userinfo.csv and saves it
     * @author aaron santos
     */
    @Override
    public String ChangePassword() {
        try {
            //prompts user to enter new password
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter new password: ");
            String newPWD = scan.nextLine();
    
            //converts new password to ascii
            String converted = PWDconvertor(newPWD);

            //overwrite entry in user.info
            AuthenticatorDB db = new AuthenticatorDB();
            ArrayList<String[]> userInfo = db.readFile();
            int index = 0;
            String[] newEntry= new String[2];
            for (String[] i: userInfo) {
                if(i[0].equals(this.username)) {
                    newEntry[0] = this.username;
                    newEntry[1] = converted;
                    break;
                }
                else {
                    index += 1;
                }
            }
            //rewrites userinfo.csv
            userInfo.set(index, newEntry);
            db.writeFile(userInfo);
            

        }
        catch(Exception e){

        }


        return null;
    }

    @Override
    public String CreateTeam() {
        Scanner sc = new Scanner(System.in);
        String teamName;

        System.out.println("Enter the name of the team you want to create: \n");
        teamName = sc.nextLine();

        // Checks if the user is not on a team
        if(this.getTeam() == null){
            Team newTeam = new Team(teamName);
            this.team = newTeam;
            newTeam.add(this);
            //Creating the notification that will be sent to every team member
            Notification joinedTeam = new Notification(newTeam, false, username);
            newTeam.notifyObj(joinedTeam);

        }else{
            System.out.println("You are already on a team. You cannot create another.");
        }
        return null;
    }

    @Override
    public String LeaveTeam() {
        // TODO Auto-generated method stub
        Team current = this.getTeam();
        current.remove(this);
        Notification leftTeam = new Notification(current, username);
        current.notifyObj(leftTeam);
        this.team = null;
        return null;
    }

    @Override
    public String SendInvite() {
        // TODO Auto-generated method stub
        AuthenticatorDB db = new AuthenticatorDB();

        try {
            ArrayList<String []> userInfo = db.readFile();
            userInfo.remove(0);
            Scanner sc = new Scanner(System.in);

            System.out.println("Here are the Users you can invite. \nEnter the number corresponding to the user to send an invite.");
            int i = 1;

            for(String[] user: userInfo){
                System.out.println( i +") " + user[0]);
                i++;
            }
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    /* 
                    String[] user = userInfo.get(0);
                    HistoryDB dbH = new HistoryDB();
                    ArrayList<String[]> userHistory= dbH.readFile();

                    String[] latestinfo = null;
                    for(String[] line: userHistory) {
                        if(line[0].equals(user[0])) {
                            latestinfo = line;
                            break;
                        }
                    }
                    String name = latestinfo[1];
                    float height = Float.parseFloat(latestinfo[3]);
                    float weight = Float.parseFloat(latestinfo[4]);
                    int age = Integer.parseInt(latestinfo[5]);
                    float targetWeight = Float.parseFloat(latestinfo[6]);
                    RealUser invitedUser = new RealUser(username, name, height, weight, age, targetWeight, true);
                    //Creates the invite notification and sends it to the user
                    */
                    String[] invitedUser = userInfo.get(0);
                    Notification invited = new Notification(invitedUser[0], this.getTeam());
                    this.update(invited);
                    break;
                case 2:
                    /* 
                    String[] user2 = userInfo.get(1);
                    HistoryDB dbH2 = new HistoryDB();
                    ArrayList<String[]> userHistory2 = dbH2.readFile();

                    String[] latestinfo2  = null;
                    for(String[] line: userHistory2) {
                        if(line[0].equals(user2[0])) {
                            latestinfo = line;
                            break;
                        }
                    }
                    String name2 = latestinfo2[1];
                    float height2 = Float.parseFloat(latestinfo2[3]);
                    float weight2 = Float.parseFloat(latestinfo2[4]);
                    int age2 = Integer.parseInt(latestinfo2[5]);
                    float targetWeight2 = Float.parseFloat(latestinfo2[6]);
                    RealUser invitedUser2 = new RealUser(username, name2, height2, weight2, age2, targetWeight2, true);
                    //Creates the invite notification and sends it to the user
                    */
                    String[] invitedUser2 = userInfo.get(1);
                    Notification invited2 = new Notification(invitedUser2[0], this.getTeam());
                    this.update(invited2);
                    break;
                case 3:
                    /* 
                    String[] user3 = userInfo.get(1);
                    HistoryDB dbH3 = new HistoryDB();
                    ArrayList<String[]> userHistory3 = dbH3.readFile();

                    String[] latestinfo3  = null;
                    for(String[] line: userHistory3) {
                        if(line[0].equals(user3[0])) {
                            latestinfo = line;
                            break;
                        }
                    }
                    String name3 = latestinfo3[1];
                    float height3 = Float.parseFloat(latestinfo3[3]);
                    float weight3 = Float.parseFloat(latestinfo3[4]);
                    int age3 = Integer.parseInt(latestinfo3[5]);
                    float targetWeight3 = Float.parseFloat(latestinfo3[6]);
                    RealUser invitedUser3 = new RealUser(username, name3, height3, weight3, age3, targetWeight3, true);
                    //Creates the invite notification and sends it to the user
                    */
                    String[] invitedUser3 = userInfo.get(0);
                    Notification invited3 = new Notification(invitedUser3[0], this.getTeam());
                    this.update(invited3);
                break;
            }



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public String ViewInbox() {
        // TODO Auto-generated method stub
        System.out.println("Here are all of your notifications");
        for( Notification n : inbox){
            System.out.println(n.getMessage());
        }

        return null;
    }

    @Override
    public String Undo() {
        if (!CommandCollection.CommandLog.isEmpty()) {
            CommandInstance = CommandCollection.CommandLog.pop();
            CommandInstance.undoAction();
        }else {
            System.out.println("No Commands left to Undo");
        }
        return null;
    }

    @Override
    public String ExportData() throws IOException {
        ImportExportDB exportDB = new ImportExportDB();
        try {
            exportDB.export();
            System.out.println("Exported Successfully!!");
        } catch (ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String ImportData() throws IOException {
        ImportExportDB importData = new ImportExportDB();
        try {
            importData.importData();
            System.out.println("Imported Successfully!!");
        } catch (ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String EnterDailyWeight() {
        Scanner scan = new Scanner(System.in);
        System.out.print("It's a new day! Time to enter your daily weight for today:");
        String inputWeight = scan.nextLine();

        setCalories(0);
        setWeight(Float.parseFloat(inputWeight));

        return null;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * prompts the user to enter personal data
     * saves this data as an entry in history
     * 
     * @author aaron santos
     */
    private void enterPersonalInfo(String username) {
        Scanner scan = new Scanner(System.in);
        String[] newEntry = new String[9];
        newEntry[0] = username;
        System.out.print("What is your name?: ");
        newEntry[1] = scan.nextLine();
        String date = java.time.LocalDate.now().toString();
        newEntry[2] = date;
        System.out.print("What is your height(cm)?: ");
        newEntry[3] = scan.nextLine();
        System.out.print("What is your weight(kg)?: ");
        newEntry[4] = scan.nextLine();
        System.out.print("What is your age?: ");
        newEntry[5] = scan.nextLine();
        System.out.print("What is your targe weight(kg)?: ");
        newEntry[6] = scan.nextLine();
        newEntry[7] = "[]";
        newEntry[8] = "[]";

        //save to history.csv
        try {
            HistoryDB dbH = new HistoryDB();
            ArrayList<String[]> userHistory= dbH.readFile();
            userHistory.add(newEntry);
            dbH.writeFile(userHistory);
            System.out.println("Personal info saved!");
        }
        catch(Exception e) {

        }

    }

    /*
    * UNDO VERSION OF COMMANDS CAN BE FOUND UNDER HERE
    * */
    @Override
    public String undoGoal(){
        setCurrentGoal(previousGoal);
        System.out.println("Your current Goal was reverted back to " + currentGoal.toString());
        return null;
    }

    @Override
    public String UndoDailyWeight(){
        System.out.println("Feel free to re-enter your current weight!");
        EnterDailyWeight();
        return null;
    }

    public String undoIngredients() throws CsvValidationException, IOException {
        List<String[]> edited_PersonalStock = csvTOlist("src/Data/PersonalStock.csv");
        String[] removedInstance = edited_PersonalStock.remove(edited_PersonalStock.size() - 1);

        FileWriter outputfile = new FileWriter("src/Data/PersonalStock.csv");
        CSVWriter writer = new CSVWriter(outputfile);
        writer.writeAll(edited_PersonalStock);
        writer.close();

        System.out.println("Your most recent addition to your Ingredients was removed! The Ingredient was: " + removedInstance[1]);
        return null;
    }

    @Override
    public String undoInvite() {
        // TODO Auto-generated method stub
        int index = inbox.size() - 1;
        this.inbox.remove(index);
        System.out.println("Removed recent invite.");
        return null;
    }

    /**
     * converts a password to an ecrypted version of it.
     * 
     * @author aaron santos
     * @param password (user entered password)
     * @return password converted to the encrypted 
     */
    private String PWDconvertor(String password) {
        String converted = "";
        for(char c : password.toCharArray()) {
            int ascii = (int)c;
            converted += ascii;
        }
        return converted;
    }


    public String getInfo() {
        return name + " " + height + " " + weight + " " + age + " " + targetWeight;
    }

    @Override
    // Updates the RealUsers inbox with notifications as they are being recieved
    public void update(Notification notify) {
        // TODO Auto-generated method stub
        inbox.add(notify);
    }
    public Team getTeam(){
        return this.team;
    }
    public void setTeam(Team newTeam){
        this.team = newTeam;
    }
    public String getName(){
        return this.name;
    }
    public String getUserName(){
        return this.username;
    }
    public void setChoice(boolean _choice){
        this.choice = _choice;
    }
    public boolean getChoice(){
        return this.choice;
    }

    

}