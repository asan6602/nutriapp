

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONAdapter implements DBHandler
{

    @Override
    public ArrayList<String[]> readFile(String fileName) throws IOException
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<String[]> strData = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject objData = (JSONObject) obj;

            for (int i = 0; i < objData.size(); i++) {
                String[] strList;
                if(objData.get(Integer.toString(i)) != null) {
                    String str =  objData.get(Integer.toString(i)).toString();
                    str = str.replaceAll("[^a-zA-Z0-9, ]", "");
//                    str = str.replace("[", "");
//                    str = str.replace("]", "");

                    strList = str.split(",");
                    strData.add(strList);
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return strData;
    }

    @Override
    public void writeFile(String fileName, ArrayList<String[]> data) throws IOException
    {
        JSONArray dataList;
        JSONObject dataObj = new JSONObject();

        for (int i = 0; i < data.size(); i++) {

            dataList = new JSONArray();

            for (int j = 0; j < data.get(0).length; j++) {

                dataList.add(data.get(i)[j]);
            }
            dataObj.put(i, dataList);
        }

        try(FileWriter file = new FileWriter(fileName))
        {
            file.write(dataObj.toJSONString());
            file.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
