import java.util.ArrayList;

public class Recipe implements FoodElement{
    private String name;
    private float calories;
    private ArrayList<Ingredient> ingredientsList;
    private String instructions;
    

    public Recipe(String _name, ArrayList<Ingredient> list, String instructions){
        this.name = _name;
        this.ingredientsList = list;
        this.instructions = instructions;

        //iterates through the given list to get the calories
        for (Ingredient ingredient: list) {
            this.calories += (ingredient.getCalories() *ingredient.getStock());
        }
    }

    /*
     * returns the calories of the recipe
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
     * getter for ingredients list 
     */
    public ArrayList<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    /*
     * getter for instructions
     */
    public String getInstructions() {
        return instructions;
    }

    public void printIngredients() {
        for(Ingredient i: ingredientsList) {
            System.out.println(i.getName());
        }
    }

}