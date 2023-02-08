import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;


public class IngredientDB
{
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> ingList;

    public IngredientDB()
    {
        fileName = "src/Data/ingredientDB.csv";
        ingList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public IngredientDB(String fileName, int type)
    {
        this.fileName = fileName;
        ingList = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        ingList = fileHandler.readFile(fileName);
        return ingList;
    }

    public void writeFile(ArrayList<String[]> ingList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, ingList);
    }
}
