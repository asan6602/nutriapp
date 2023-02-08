import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class TeamDB {
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> teamList;

    public TeamDB()
    {
        fileName = "src/Data/team.csv";
        teamList = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public TeamDB(String fileName, int type)
    {
        this.fileName = fileName;
        teamList = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();

    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        teamList = fileHandler.readFile(fileName);
        return teamList;
    }

    public void writeFile(ArrayList<String[]> teamList) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, teamList);
    }
}
