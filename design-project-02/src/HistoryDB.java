import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryDB
{
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> historyList;

    public HistoryDB()
    {
        fileName = "src/Data/history.csv";
        historyList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public HistoryDB(String fileName, int type)
    {
        this.fileName = fileName;
        historyList = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        historyList = fileHandler.readFile(fileName);
        return historyList;
    }

    public void writeFile(ArrayList<String[]> historyList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, historyList);
    }
}
