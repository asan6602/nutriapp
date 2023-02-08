import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticatorDB
{
    private String fileName;
    private DBHandler fileHandler;
    private ArrayList<String[]> userInfo;

    public AuthenticatorDB()
    {
        fileName = "src/Data/userInfo.csv";
        userInfo = new ArrayList<>();
        fileHandler = new CSVAdapter();
    }

    public AuthenticatorDB(String fileName, int type)
    {
        this.fileName = fileName;
        userInfo = new ArrayList<>();

        if(type == 1)
            fileHandler = new CSVAdapter();
        else if(type == 2)
            fileHandler = new JSONAdapter();
        else if(type == 3)
            fileHandler = new XMLAdapter();
    }

    public ArrayList<String[]> readFile() throws IOException, ParserConfigurationException, SAXException {
        userInfo = fileHandler.readFile(fileName);
        return userInfo;
    }

    public void writeFile(ArrayList<String[]> userInfo) throws IOException, ParserConfigurationException, TransformerException {
        fileHandler.writeFile(fileName, userInfo);
    }
}
