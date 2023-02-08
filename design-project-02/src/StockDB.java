import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class StockDB
{
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> ingredientsList;

    public StockDB()
    {
        fileName = "src/Data/PersonalStock.csv";
        ingredientsList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public StockDB(String fileName, int type)
    {
        this.fileName = fileName;
        ingredientsList = new ArrayList<>();

        if(type == 1)
             fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        ingredientsList = fileHandler.readFile(fileName);
        return ingredientsList;
    }

    public void writeFile(ArrayList<String[]> ingredientsList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, ingredientsList);
    }

}
