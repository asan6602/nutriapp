import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class RecipesDB {
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> recipesList;

    public RecipesDB()
    {
        fileName = "src/Data/recipeDB.csv";
        recipesList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public RecipesDB(String fileName, int type)
    {
        this.fileName = fileName;
        recipesList = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        recipesList = fileHandler.readFile(fileName);
        return recipesList;
    }

    public void writeFile(ArrayList<String[]> recipesList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, recipesList);
    }
}
