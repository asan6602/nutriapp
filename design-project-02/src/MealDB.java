import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class MealDB 
{
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> mealList;

    public MealDB()
    {
        fileName = "src/Data/mealDB.csv";
        mealList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public MealDB(String fileName, int type)
    {
        this.fileName = fileName;
        mealList = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        mealList = fileHandler.readFile(fileName);
        return mealList;
    }

    public void writeFile(ArrayList<String[]> ingredientsList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, ingredientsList);
    }
}
