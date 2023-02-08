package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase implements Data
{

    private final String fileName;                  //Name of the file to be read and write on
    private ArrayList<ArrayList<String>> fileData;  //Data stored form the file
    private Scanner scan;                           //Used to read the file


    /**
     * Initializes the class variable
     * @param fileName fileName to be initialized
     */
    public DataBase(String fileName)
    {
        this.fileName = fileName;
        fileData = new ArrayList<>();
    }

    /**
     * A file object is created by given fileName and is used to init
     * Scanner is initializes to the file. When the Scanner object scan is used it gets input
     * from the provided file.
     */
    private void openFile()
    {
        try {
            scan = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {

            System.out.println("File didn't open");
            System.out.println(e);
        }
    }


    @Override
    public ArrayList<ArrayList<String>> readData()
    {
        openFile();
        this.fileData = new ArrayList<>();

        while (this.scan.hasNext())
        {
            String str = this.scan.nextLine();
            ArrayList<String> tempList = new ArrayList<>();
            for (String strTemp: str.split(","))
            {
                tempList.add(strTemp);
            }
            this.fileData.add(tempList);
        }
        scan.close();
        return this.fileData;
    }

    @Override
    public void write(String newData) throws IOException
    {
        try {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(newData);
            fileData = new ArrayList<>(readData());
            fileData.add(tempList);
            writeFile();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("An a error occurred!!");
            System.out.println(e);
        }

    }

    /**
     * Writes the file using BufferedWriter. It gets the data to be written from fileData. Once all the data has been
     * written it closes the file.
     * @throws IOException throws IOException if the file cannot be open.
     */
    private void writeFile() throws IOException
    {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(fileName));
            for (ArrayList<String> tempList: fileData) {
                for (String data : tempList) {
                    write.write(data);
                    write.write(",");
                }
                write.newLine();
            }

            write.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An a error occurred!!");
            System.out.println(e);
        }
    }


    @Override
    public void delete(String name)
    {

        try {
            fileData = new ArrayList<>(readData());

            for (int i = 0; i < fileData.size(); i++) {
                ArrayList<String> tempList = fileData.get(i);
                if(name.equals(tempList.get(1)))
                {
                    fileData.remove(i);
                    writeFile();
                    break;
                }
                else
                    System.out.println("The data doesn't exist!!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An a error occurred!!");
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(String name, String dataType, String newData)
    {
        try {
            fileData = new ArrayList<>(readData());
            int count = 0;
            for (ArrayList<String> tempList: fileData)
            {
                if (name.equals(tempList.get(1)))
                    for (int i = 0; i < tempList.size(); i++)
                        if (dataType.equals(tempList.get(i)))
                        {
                            tempList.set(i + 1, newData);
                            fileData.set(count, tempList);
                            writeFile();
                        }
                count++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An a error occurred!!");
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
