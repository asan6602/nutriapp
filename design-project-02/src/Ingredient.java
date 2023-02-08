import java.util.Objects;

public class Ingredient implements FoodElement{
    private String name;
    private float calories;
    private int stock;



    public Ingredient(String _name, float _calories){
        this.name = _name;
        this.calories = _calories;
        this.stock = 0;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /*
     * returns the calories of the ingredient
     * used by Food.java to return total
     */
    public float getCalories(){
        return this.calories;
    }

    /*
     * getter for name
     */
    public String getName() {
        return this.name;
    }

    /*
     * getter for stock
     */
    public int getStock() {
        return this.stock;
    }

    /*
     * methods for adding stock
     * if no number parameter is used, adds 1
     */
    public void addStock() {
        this.stock += 1;
    }

    public void addStock(int num) {
        this.stock += num;
    }

    /*
     * methods for removing stock
     * if no number parameter is used, subtracts 1
     * if stock goes belows zero, it is set to zero
     */
    public void removeStock() {
        this.stock -= 1;
        if (this.stock < 0) {
            this.stock = 0;
        }
    }

    public void removeStock(int num) {
        this.stock -= num;
        if (this.stock < 0) {
            this.stock = 0;
        }
    }

    public boolean sameIngredient(Ingredient comparedIngredient){
        return (Objects.equals(this.getName(), comparedIngredient.getName())) && (this.getCalories() == comparedIngredient.getCalories());
    }

    public static void main(String[] args) {
        Ingredient apple = new Ingredient("Apple", 100);
        Ingredient orange = new Ingredient("Apple", 100);

        System.out.println(apple.sameIngredient(orange));
    }
}
