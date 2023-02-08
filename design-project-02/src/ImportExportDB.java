import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ImportExportDB
{
    Scanner scan = new Scanner(System.in);


    public ArrayList<String[]> importData() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        int fileType, dataType;
        String fileName;
        ArrayList<String[]> data = new ArrayList<>();

        System.out.println("1. CSV\n2. JSON\n3. XML");
        System.out.println("What type of file do you want to import (Enter a number to select):");
        fileType = scan.nextInt();

        if(fileType > 0 && fileType < 4)
        {
            System.out.println(" 1. Current ingredient stock \n2. All the ingredient data \n3. Recipe file \n4. Meal file \n5. User Info \n6. History \n7. Team data");
            System.out.println("What kind of data are you importing (Enter a number to select): ");
            dataType = scan.nextInt();

            System.out.println("Enter the file name: ");
            fileName = scan.next();

            switch (dataType)
            {
                case 1:
                    StockDB stock = new StockDB(fileName, fileType);
                    data = stock.readFile();
                    stock = new StockDB();
                    stock.writeFile(data);
                    break;
                case 2:
                    IngredientDB ing = new IngredientDB(fileName, fileType);
                    data = ing.readFile();
                    ing = new IngredientDB();
                    ing.writeFile(data);
                    break;
                case 3:
                    RecipesDB recipe = new RecipesDB(fileName, fileType);
                    data = recipe.readFile();
                    recipe = new RecipesDB();
                    recipe.writeFile(data);
                    break;
                case 4:
                    MealDB meal = new MealDB(fileName, fileType);
                    data = meal.readFile();
                    meal = new MealDB();
                    meal.writeFile(data);
                    break;
                case 5:
                    AuthenticatorDB auth = new AuthenticatorDB(fileName, fileType);
                    data = auth.readFile();
                    auth = new AuthenticatorDB();
                    auth.writeFile(data);
                    break;
                case 6:
                    HistoryDB his = new HistoryDB(fileName, fileType);
                    data = his.readFile();
                    his = new HistoryDB();
                    his.writeFile(data);
                    break;
                case 7:
                    TeamDB team = new TeamDB(fileName, fileType);
                    data = team.readFile();
                    team = new TeamDB();
                    team.writeFile(data);
                    break;

            }

        }
        else
            System.out.println("Invalid input!!");

        return data;
    }

    public void export() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        int fileType, dataType;
        String fileName;
        ArrayList<String[]> data;

        System.out.println("1. CSV\n2. JSON\n3. XML");
        System.out.println("What type of file do you want to export (Enter a number to select):");
        fileType = scan.nextInt();

        if(fileType > 0 && fileType < 4) {
            System.out.println(" 1. Current ingredient stock \n2. All the ingredient data \n3. Recipe file \n4. Meal file \n5. User Info \n6. History \n7. Team data");
            System.out.println("What kind of data are you exporting (Enter a number to select): ");
            dataType = scan.nextInt();

            System.out.println("Enter the file name: ");
            fileName = scan.next();

            switch (dataType)
            {
                case 1:
                    StockDB stock = new StockDB();
                    data = stock.readFile();
                    stock = new StockDB(fileName, fileType);
                    stock.writeFile(data);
                    break;
                case 2:
                    System.out.println("Here in the right place");
                    IngredientDB ing = new IngredientDB();
                    data = ing.readFile();
                    System.out.println("current list size " + data.size());
                    IngredientDB ing2 = new IngredientDB(fileName, fileType);
                    ing2.writeFile(data);
                    break;
                case 3:
                    System.out.println("Wrong place");
                    RecipesDB recipe = new RecipesDB();
                    data = recipe.readFile();
                    recipe = new RecipesDB(fileName, fileType);
                    recipe.writeFile(data);
                    break;
                case 4:
                    MealDB meal = new MealDB();
                    data = meal.readFile();
                    meal = new MealDB(fileName, fileType);
                    meal.writeFile(data);
                    break;
                case 5:
                    AuthenticatorDB auth = new AuthenticatorDB();
                    data = auth.readFile();
                    auth = new AuthenticatorDB(fileName, fileType);
                    auth.writeFile(data);
                    break;
                case 6:
                    HistoryDB his = new HistoryDB();
                    data = his.readFile();
                    his = new HistoryDB(fileName, fileType);
                    his.writeFile(data);
                    break;
                case 7:
                    TeamDB team = new TeamDB();
                    data = team.readFile();
                    team = new TeamDB(fileName, fileType);
                    team.writeFile(data);
                    break;


            }

        }
        else
                System.out.println("Invalid input!!");



    }
}
