package Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Data
{
    /**
     * Reads the file as String using Scanner object (scan) and stores the data in 2d ArrayList.
     * @return fileData a ArrayList of String.
     */
    public ArrayList<ArrayList<String>> readData();

    /**
     * This function takes a string newData to update the array list then once the new data is in the list it rewrites
     * the csv file to add the new data to the file.
     * @param newData data to be written in the file
     * @throws IOException throws IOException if the file cannot be open.
     */
    public void write(String newData) throws IOException;


    /**
     * This function takes name as string and search through the name to find the data to be deleted. Once the function
     * finds the data it uses that index to remove the data. Then it calls the write function to rewrite the updated
     * data to csv file.
     * @param name string name of the data to be deleted.
     */
    public void delete(String name);

    /**
     * Updates the csv file data by using name and data type. It searches through the file data to find the
     * matching name and data type, once it finds the data it updates it in the 7itj first then calls the write function to rewrite
     * the updated data in the file.
     * @param name the name of the data to be updated
     * @param dataType the data type to be updated.
     * @param newData data to be updated
     * @throws IOException throws IOException if the file cannot be open.
     */
    public void update(String name, String dataType, String newData);
}

