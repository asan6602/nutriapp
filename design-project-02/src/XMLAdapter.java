import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLAdapter implements DBHandler
{
    @Override
    public ArrayList<String[]> readFile(String fileName) throws IOException, ParserConfigurationException, SAXException {
        ArrayList<String[]> data = new ArrayList<>();
        String[] dataList;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder loader = factory.newDocumentBuilder();
            Document document = loader.parse(fileName);

            DocumentTraversal trav = (DocumentTraversal) document;

            NodeIterator it = trav.createNodeIterator(document.getDocumentElement(),
                    NodeFilter.SHOW_ELEMENT, null, true);


            Node node = it.nextNode();

            int num = 0;
            boolean done = false;
            ArrayList<String> strTemp = new ArrayList<>();
            ArrayList<String> strList = new ArrayList<>();

            while (node != null)
            {
                String str  = node.getNodeName();
                if(num >= 2 && !str.equals("dataType"))
                {
                    String str2 = node.getTextContent().trim();
                    if(!done)
                        strTemp.add(str);
                    strList.add(str2);
                }
                else if( num > 2)
                {

                    if(!done)
                    {
                        dataList = strTemp.toArray(new String[0]);
                        data.add(dataList);
                    }

                    dataList = strList.toArray(new String[0]);
                    data.add(dataList);
                    done = true;
                    strList = new ArrayList<>();

                }
                node = it.nextNode();
                num++;
            }
            if(strList.size() > 0)
            {
                dataList = strList.toArray(new String[0]);
                data.add(dataList);
            }


        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {
            System.out.println(e);
        }


        return data;
    }

    @Override
    public void writeFile(String fileName, ArrayList<String[]> data) throws IOException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("data");
        doc.appendChild(rootElement);

        String name;

        for (int i = 1; i < data.size(); i++) {
            Element dataNum = doc.createElement("dataType");
            rootElement.appendChild(dataNum);
            for (int j = 0; j < data.get(i).length; j++) {
                name = data.get(0)[j];
                name = name.replaceAll(" ", "");
                Element temp = doc.createElement(name);
                temp.setTextContent(data.get(i)[j]);
                dataNum.appendChild(temp);
            }
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        FileWriter write = new FileWriter(fileName);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(write);
        transformer.transform(source, result);

    }
}