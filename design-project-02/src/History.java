import java.util.ArrayList;


import java.io.IOException;  
import java.util.GregorianCalendar;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

public  class History implements CycleRegister{
    private static GregorianCalendar gcal = new GregorianCalendar();
    
    private static ArrayList<ActOnTime> notify = new ArrayList<ActOnTime>();

    public History(){
        notify = new ArrayList<ActOnTime>();
    }
    
    /*
     * Adds peoples that need to be notified
     */
    public static void addTimeNeeds(ActOnTime alert) {
        notify.add(alert);
    }
    
    @Override
    /*
     * Removes people that do not need to be notified
     */
    public void removeTimeNeeds(ActOnTime alert) {
        notify.remove(alert);
    }
    
    
    /*
     * Send all the notifications when the current day changes
     */
    
    public static  void notifyObj() {
        GregorianCalendar currentDay = new GregorianCalendar();
        if(gcal.getTime() != currentDay.getTime()){
            for(ActOnTime obj: notify){
                try {
                    obj.dailyUpdate();
                } catch (CsvValidationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            gcal = currentDay;
        }
    }
    
    
    /*@Author: Abiel Yemane
     * Reads through every line of history until it reaches the last line
     * Returns the last line because it is the most recent entry
     */
}
