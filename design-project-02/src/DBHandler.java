import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Laxmi Poudel
 */
public interface DBHandler
{
    public ArrayList<String[]> readFile(String fileName) throws IOException, ParserConfigurationException, SAXException;

    public void writeFile(String fileName, ArrayList<String[]> data) throws IOException, ParserConfigurationException, TransformerException;

}
