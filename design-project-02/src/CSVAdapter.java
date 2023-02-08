import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Laxmi Poudel
 */
public class CSVAdapter implements DBHandler
{
    @Override
    public ArrayList<String[]> readFile(String fileName) throws IOException
    {
        ArrayList<String[]> data = new ArrayList<>();
        String nextLine[];
        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader reader = new CSVReader(fileReader);

            while ((nextLine = reader.readNext()) != null)
            {
                data.add(nextLine);
            }

        }catch (CsvValidationException e) {
            e.printStackTrace();
        }


        return data;
    }

    @Override
    public void writeFile(String fileName, ArrayList<String[]> data) throws IOException {


        try {
            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(fileWriter);


            for (String[] datum : data) {
                writer.writeNext(datum);
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
