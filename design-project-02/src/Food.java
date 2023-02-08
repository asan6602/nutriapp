
import java.util.ArrayList;

/**
 *
 */
public class Food implements FoodElement, ActOnTime {
    private String name;
    private float calories;
    private ArrayList<FoodElement> foods = new ArrayList<FoodElement>();


    public Food(String _name, ArrayList<FoodElement> foods){
        this.name = _name;
        this.calories = getCalories();
        this.foods = foods;

    }

    /**
     *
     * @param e
     */
    public void add(FoodElement e){
        foods.add(e);
    }

    /**
     *
     * @param e
     */
    public void remove(FoodElement e){
        foods.remove(e);
    }

    /**
     * Iterates through the food list
     * adds the calories of all the food in the food list
     * returns the total calories
     */

    public float getCalories(){
        float totalCalories = 0;
        for (FoodElement food: foods) {
            totalCalories += food.getCalories();
        }
        this.calories = totalCalories;
        return this.calories;
    }

    public ArrayList<FoodElement> getFoods() {
        return foods;
    }

    @Override
    public void dailyUpdate() {
        foods = new ArrayList<FoodElement>();
    }

    /* This might not be used
     * public void dailyUpdate(event; KeyEvent)
     */
}
