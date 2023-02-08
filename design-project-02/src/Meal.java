import java.util.ArrayList;

public class Meal implements FoodElement{
    private String name;
    private float calories;
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public Meal(String _name, ArrayList<Recipe> list){
        this.name = _name;
        this.recipes = list;

        //iterates through the given list to get the calories
        for (Recipe recipe: list) {
            this.calories += recipe.getCalories();
        }
    }
    /*
     * returns the calories of the meal
     * used by Food.java to return total
     */
    public float getCalories(){
        return this.calories;
    }

    /*
     * getter for name
     */
    public String getName() {
        return name;
    }

    /*
     * gett for recipe list
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void printRecipes() {
        for(Recipe r: recipes) {
            System.out.println(r.getName());
        }
    }

}
