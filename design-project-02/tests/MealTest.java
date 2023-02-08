import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class MealTest {
    @Test
    public void testConstructor() {
        Ingredient testIngredient1 = new Ingredient("potato", 100.00f);
        Ingredient testIngredient2 = new Ingredient("carrot", 80.00f);
        Ingredient testIngredient3 = new Ingredient("greenbeans", 120.00f);
        ArrayList<Ingredient> testlist = new ArrayList<Ingredient>();
        testlist.add(testIngredient1);
        testlist.add(testIngredient2);
        testlist.add(testIngredient3);
        Recipe testRecipe = new Recipe("veggies", testlist, "Throw in pot. Cook. Serve.");

        ArrayList<Ingredient> testlist2 = new ArrayList<Ingredient>();
        Ingredient testIngredient4 = new Ingredient("meat", 300.00f);
        Ingredient testIngredient5 = new Ingredient("sauce", 50.00f);
        testlist2.add(testIngredient4);
        testlist2.add(testIngredient5);
        Recipe testRecipe2 = new Recipe("sauce and meat", testlist2, "Throw in pot. Cook. Serve.");

        ArrayList<Recipe> testlist3 = new ArrayList<Recipe>();
        testlist3.add(testRecipe);
        testlist3.add(testRecipe2);
        Meal testMeal = new Meal("meals", testlist3);

        assertEquals(testMeal.getCalories(), 650.00f);
        
    }
}
