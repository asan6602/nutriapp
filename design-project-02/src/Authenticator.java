import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Authenticator {

    public Authenticator() {

    }

    /**
     * Provides functionality for user logging in
     * accesses userinfo.csv and history.csv
     * creates an authenticated user if found
     * creates a non-authenticated user if not found
     * @author aaron santos
     * @return created user
     */
    public RealUser Login() throws IOException, ParserConfigurationException, SAXException {
        Scanner scan = new Scanner(System.in);

        //prompts the user to enter
        System.out.print("Enter Username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();


        //username and password are used to search in authenticatorDB to see if the entry is in the csv
        boolean found = false;
        AuthenticatorDB db = new AuthenticatorDB();
        ArrayList<String[]> userInfo = db.readFile();
        for (String[] i: userInfo) {
            if(i[0].equals(username)) {
                if(hashing(password, i[1])) {
                    found = true;
                }
            }
        }
        //if they are found, search historyDB to get the found user's latest information and create the authenticated user
        if(found){
            HistoryDB dbH = new HistoryDB();
            ArrayList<String[]> userHistory= dbH.readFile();
            String[] latestinfo = null;
            
            for(String[] i: userHistory) {
                if(i[0].equals(username)) {
                    latestinfo = i;
                    break;
                }
            }
            System.out.println("Login succesful!");

            if(latestinfo == null) {
                RealUser authenticatedUser = new RealUser("","", 0, 0, 0, 0, true);
                return authenticatedUser;
            }
            else{
                String name = latestinfo[1];
                float height = Float.parseFloat(latestinfo[3]);
                float weight = Float.parseFloat(latestinfo[4]);
                int age = Integer.parseInt(latestinfo[5]);
                float targetWeight = Float.parseFloat(latestinfo[6]);
                RealUser authenticatedUser = new RealUser(username, name, height, weight, age, targetWeight, true);
                System.out.println(authenticatedUser.getInfo());
                return authenticatedUser;
            }
        }

        //if they are not found create a guest user
        else {
            System.out.println("Login unsuccesful... Logged-in as a Guest User");
            RealUser guestUser = new RealUser("Guest User","Guest User", 0, 0, 0, 0,false);
            return guestUser;
        }

    }

    /**
     * converts user entered password into encrypted format stored in the userInfo.csv.
     * encrypted format is ascii.
     * @author aaron santos
     * @params entered password, password from the db
     * @return if entered password matches the password from the db
     */
    private boolean hashing(String password, String DBpassword) {
        String converted = "";
        for(char c : password.toCharArray()) {
            int ascii = (int)c;
            converted += ascii;
        }
        if(converted.equals(DBpassword)) {
            return true;
        }
        else {
            return false;
        }
    }
}

